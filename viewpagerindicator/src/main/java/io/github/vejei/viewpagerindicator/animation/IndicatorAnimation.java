package io.github.vejei.viewpagerindicator.animation;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

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
