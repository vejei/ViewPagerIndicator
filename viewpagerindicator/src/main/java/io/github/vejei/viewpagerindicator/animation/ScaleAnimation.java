package io.github.vejei.viewpagerindicator.animation;

import android.animation.ArgbEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;

public final class ScaleAnimation extends IndicatorAnimation {
    private static final String PN_ANIMATE_RADIUS = "animate_radius";
    private static final String PN_ANIMATE_RADIUS_REVERSE = "animate_radius_reverse";
    private static final String PN_ANIMATE_COLOR = "animate_color";
    private static final String PN_ANIMATE_COLOR_REVERSE = "animate_color_reverse";

    private AnimationValue.ScaleAnimationValue animationValue;
    private int animateRadius;
    private int animateRadiusReverse;
    private int animateColor;
    private int animateColorReverse;

    public ScaleAnimation(int startColor, int endColor, int startRadius, int endRadius,
                   final AnimationUpdateListener animationUpdateListener) {
        super(animationUpdateListener);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animateRadius = (int) animation.getAnimatedValue(PN_ANIMATE_RADIUS);
                animateRadiusReverse = (int) animation.getAnimatedValue(PN_ANIMATE_RADIUS_REVERSE);
                animateColor = (int) animation.getAnimatedValue(PN_ANIMATE_COLOR);
                animateColorReverse = (int) animation.getAnimatedValue(PN_ANIMATE_COLOR_REVERSE);

                animationValue = new AnimationValue.ScaleAnimationValue();
                animationValue.setColor(animateColor);
                animationValue.setColorReverse(animateColorReverse);
                animationValue.setRadius(animateRadius);
                animationValue.setRadiusReverse(animateRadiusReverse);
                animationUpdateListener.onAnimationUpdate(animationValue);
            }
        });
        PropertyValuesHolder colorValuesHolder = PropertyValuesHolder.ofInt(PN_ANIMATE_COLOR,
                startColor, endColor);
        colorValuesHolder.setEvaluator(new ArgbEvaluator());
        PropertyValuesHolder colorReverseValuesHolder = PropertyValuesHolder.ofInt(
                PN_ANIMATE_COLOR_REVERSE, endColor, startColor);
        colorReverseValuesHolder.setEvaluator(new ArgbEvaluator());

        PropertyValuesHolder radiusValuesHolder = PropertyValuesHolder.ofInt(PN_ANIMATE_RADIUS,
                startRadius, endRadius);
        PropertyValuesHolder radiusValuesReverseHolder = PropertyValuesHolder.ofInt(
                PN_ANIMATE_RADIUS_REVERSE, endRadius, startRadius);

        valueAnimator.setValues(colorValuesHolder, colorReverseValuesHolder, radiusValuesHolder,
                radiusValuesReverseHolder);
    }
}
