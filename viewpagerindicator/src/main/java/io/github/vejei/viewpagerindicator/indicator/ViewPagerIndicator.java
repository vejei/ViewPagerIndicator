package io.github.vejei.viewpagerindicator.indicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import io.github.vejei.viewpagerindicator.animation.IndicatorAnimation;
import io.github.vejei.viewpagerindicator.animation.SlideAnimation;

abstract class ViewPagerIndicator extends View {
    protected int indicatorColor;
    protected int indicatorSelectedColor;
    protected int indicatorGap;
    protected int indicatorItemCount;

    protected int currentPosition = 0;
    protected int nextPosition = 1;
    protected float scrollOffset;

    protected IndicatorAnimation indicatorAnimation;

    protected ViewPager2 viewPager2;
    protected ViewPager2.OnPageChangeCallback onPageChangeCallback;
    protected ViewPager viewPager;
    protected ViewPager.OnPageChangeListener onPageChangeListener;

    ViewPagerIndicator(Context context) {
        super(context);
    }

    ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidth;
        if ((getLayoutParams().width == ViewGroup.LayoutParams.MATCH_PARENT)
                || (getLayoutParams().width == ViewGroup.LayoutParams.FILL_PARENT)) {
            viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            viewWidth = measureWidth();
        } else {
            viewWidth = getLayoutParams().width;
        }

        int viewHeight;
        if ((getLayoutParams().height == ViewGroup.LayoutParams.MATCH_PARENT)
                || (getLayoutParams().height == ViewGroup.LayoutParams.FILL_PARENT)) {
            viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            viewHeight = measureHeight();
        } else {
            viewHeight = getLayoutParams().height;
        }

        int horizontalPadding = getPaddingLeft() + getPaddingRight();
        int verticalPadding = getPaddingTop() + getPaddingBottom();

        int width = viewWidth + horizontalPadding;
        int height = viewHeight + verticalPadding;

        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
    }

    @ColorInt
    public int getIndicatorColor() {
        return indicatorColor;
    }

    public void setIndicatorColor(@ColorRes int color) {
        indicatorColor = getResources().getColor(color);
        invalidate();
    }

    @ColorInt
    public int getIndicatorSelectedColor() {
        return indicatorSelectedColor;
    }

    public void setIndicatorSelectedColor(@ColorRes int color) {
        this.indicatorSelectedColor = getResources().getColor(color);
        invalidate();
    }

    public int getIndicatorGap() {
        return indicatorGap;
    }

    public void setIndicatorGap(int gap) {
        this.indicatorGap = gap;
        invalidate();
    }

    public int getItemCount() {
        return indicatorItemCount;
    }

    public void setItemCount(int itemCount) {
        this.indicatorItemCount = itemCount;
        invalidate();
        requestLayout();
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public int getNextPosition() {
        return nextPosition;
    }

    protected int getActualPosition(int position) {
        return position % getItemCount();
    }

    public void setWithViewPager2(ViewPager2 viewPager2) {
        setWithViewPager2(viewPager2, true);
    }

    public void setWithViewPager2(ViewPager2 viewPager2, boolean useItemCount) {
        this.viewPager2 = viewPager2;
        onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ViewPagerIndicator.this.onPageScrolled(getActualPosition(position), positionOffset);
            }
        };
        this.viewPager2.registerOnPageChangeCallback(onPageChangeCallback);

        if (useItemCount) {
            RecyclerView.Adapter adapter = this.viewPager2.getAdapter();
            if (adapter == null) {
                setItemCount(0);
            } else {
                setItemCount(adapter.getItemCount());
            }
        }
    }

    public void setWithViewPager(ViewPager viewPager) {
        setWithViewPager(viewPager, true);
    }

    public void setWithViewPager(ViewPager viewPager, boolean useItemCount) {
        this.viewPager = viewPager;
        this.onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ViewPagerIndicator.this.onPageScrolled(getActualPosition(position), positionOffset);
            }

            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrollStateChanged(int state) {}
        };
        this.viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    public void release() {
        if (this.viewPager2 != null && this.onPageChangeCallback != null) {
            this.viewPager2.unregisterOnPageChangeCallback(onPageChangeCallback);
            this.viewPager2 = null;
            this.onPageChangeCallback = null;
        }
        if (this.viewPager != null && this.onPageChangeListener != null) {
            this.viewPager.removeOnPageChangeListener(onPageChangeListener);
            this.viewPager = null;
            this.onPageChangeListener = null;
        }
    }

    protected void onPageScrolled(int position, float positionOffset) {
        boolean rightScrollEnded = position > currentPosition;
        boolean leftScrollEnded = position + 1 < currentPosition;

        if (rightScrollEnded || leftScrollEnded) {
            currentPosition = position;
        }

        boolean slideToRight = currentPosition == position && positionOffset != 0;

        int targetPosition;
        if (slideToRight) {
            scrollOffset = positionOffset;
            targetPosition = position + 1;
        } else {
            scrollOffset = 1 - positionOffset;
            targetPosition = position;
        }

        if (getItemCount() <= 0 && targetPosition < 0) {
            targetPosition = 0;
        } else {
            targetPosition = getActualPosition(targetPosition);
        }

        nextPosition = targetPosition;

        if (scrollOffset > 1) {
            scrollOffset = 1;
        } else if (scrollOffset < 0) {
            scrollOffset = 0;
        }

        if (scrollOffset == 1) {
            currentPosition = nextPosition;
        }

        int start = getCoordinateX(currentPosition);
        int end = getCoordinateX(nextPosition);

        if (indicatorAnimation == null) {
            invalidate();
        } else {
            if (indicatorAnimation instanceof SlideAnimation) {
                indicatorAnimation.updateValue(start, end);
            }
            indicatorAnimation.schedule(scrollOffset);
        }
    }

    public int getCoordinateX(int position) {
        return 0;
    }
    public int getCoordinateY() {
        return 0;
    }

    abstract protected int measureWidth();
    abstract protected int measureHeight();
}
