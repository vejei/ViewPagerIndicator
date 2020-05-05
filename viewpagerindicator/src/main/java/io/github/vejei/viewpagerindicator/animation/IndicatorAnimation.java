package io.github.vejei.viewpagerindicator.animation;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

/*abstract class IndicatorAnimation {
    static final int DURATION = 3000;
    ValueAnimator valueAnimator;

    ShapePainter.Shape shape;
    ShapePainter painter;
    Paint paint;
    int indicatorRadius;
    int indicatorColor;
    int indicatorSelectedColor;

    IndicatorAnimation(ShapePainter.Shape shape, Paint paint, int indicatorRadius,
                       int indicatorColor, int indicatorSelectedColor,
                       AnimationUpdateListener animationUpdateListener) {
        this.shape = shape;
        this.paint = paint;
        this.indicatorRadius = indicatorRadius;
        this.indicatorColor = indicatorColor;
        this.indicatorSelectedColor = indicatorSelectedColor;

        createPainter();
        setupAnimator();
    }

    private void createPainter() {
        switch (this.shape) {
            case CIRCLE:
                painter = new CirclePainter(paint);
                break;
            case RECT:
                painter = new RectPainter(paint);
                break;
        }
    }

    private void setupAnimator() {
        valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(DURATION);
        valueAnimator.setInterpolator(new LinearInterpolator());
    }

    abstract void drawItems(Canvas canvas, int coordinateX, int coordinateY);
    abstract void drawCurrentItem(Canvas canvas, int coordinateX, int coordinateY);
    abstract void drawNextItem(Canvas canvas, int coordinateX, int coordinateY);
    abstract void updateValue(float offset, int start, int end);

    interface AnimationUpdateListener {
        void onAnimationUpdate(AnimationValue animationValue);
    }
}*/
public abstract class IndicatorAnimation {
    public static final int DEFAULT_DURATION = 3000;
    protected long duration;
    ValueAnimator valueAnimator;

    public IndicatorAnimation(AnimationUpdateListener animationUpdateListener) {
        setupAnimator();
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    private void setupAnimator() {
        valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(DEFAULT_DURATION);
        valueAnimator.setInterpolator(new LinearInterpolator());
    }

    public void schedule(float offset) {
        valueAnimator.setCurrentPlayTime((long) (offset * DEFAULT_DURATION));
    }

    public void updateValue(int start, int end) {}

    public interface AnimationUpdateListener {
        void onAnimationUpdate(AnimationValue animationValue);
    }
}
