package io.github.vejei.viewpagerindicator.animation;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;

public final class SlideAnimation extends IndicatorAnimation {
    private static final String PN_ANIMATE_COORDINATE_X = "animate_coordinate_x";
    private AnimationValue.SlideAnimationValue animationValue;
    private int animateCoordinateX;

    public SlideAnimation(final AnimationUpdateListener animationUpdateListener) {
        super(animationUpdateListener);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animateCoordinateX = (int) animation.getAnimatedValue(PN_ANIMATE_COORDINATE_X);
                animationValue = new AnimationValue.SlideAnimationValue();
                animationValue.setCoordinateX(animateCoordinateX);
                animationUpdateListener.onAnimationUpdate(animationValue);
            }
        });
    }

    @Override
    public void updateValue(int start, int end) {
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofInt(PN_ANIMATE_COORDINATE_X, start, end);
        valueAnimator.setValues(valuesHolder);
    }
}
