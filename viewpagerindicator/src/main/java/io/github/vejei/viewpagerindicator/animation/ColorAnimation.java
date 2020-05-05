package io.github.vejei.viewpagerindicator.animation;

import android.animation.ArgbEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;

public class ColorAnimation extends IndicatorAnimation {
    private static final String PN_ANIMATE_COLOR = "animate_color";
    private static final String PN_ANIMATE_COLOR_REVERSE = "animate_color_reverse";

    private int animateColor;
    private int animateColorReverse;

    public ColorAnimation(final int startColor, final int endColor,
                          final AnimationUpdateListener animationUpdateListener) {
        super(animationUpdateListener);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animateColor = (int) animation.getAnimatedValue(PN_ANIMATE_COLOR);
                animateColorReverse = (int) animation.getAnimatedValue(PN_ANIMATE_COLOR_REVERSE);

                AnimationValue.ColorAnimationValue animationValue = new AnimationValue.ColorAnimationValue();
                animationValue.setColor(animateColor);
                animationValue.setColorReverse(animateColorReverse);
                animationUpdateListener.onAnimationUpdate(animationValue);
            }
        });
        PropertyValuesHolder colorValuesHolder = PropertyValuesHolder.ofInt(PN_ANIMATE_COLOR,
                startColor, endColor);
        colorValuesHolder.setEvaluator(new ArgbEvaluator());
        PropertyValuesHolder colorReverseValuesHolder = PropertyValuesHolder.ofInt(
                PN_ANIMATE_COLOR_REVERSE, endColor, startColor);
        colorReverseValuesHolder.setEvaluator(new ArgbEvaluator());

        valueAnimator.setValues(colorValuesHolder, colorReverseValuesHolder);
    }
}
