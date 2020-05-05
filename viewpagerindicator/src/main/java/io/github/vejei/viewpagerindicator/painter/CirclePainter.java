package io.github.vejei.viewpagerindicator.painter;

import android.graphics.Canvas;
import android.graphics.Paint;

import io.github.vejei.viewpagerindicator.animation.AnimationValue;
import io.github.vejei.viewpagerindicator.indicator.CircleIndicator;

public class CirclePainter extends Painter {
    private CircleIndicator view;
    private int radius;

    public CirclePainter(Paint paint, int color, int selectedColor, int radius, CircleIndicator view) {
        super(paint, color, selectedColor);
        this.radius = radius;
        this.view = view;
    }

    public CircleIndicator getView() {
        return view;
    }

    public void setView(CircleIndicator view) {
        this.view = view;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Canvas canvas, int position) {
        int color = getColor();
        if (position == view.getCurrentPosition()) {
            color = selectedColor;
        }
        paint.setColor(color);
        canvas.drawCircle(view.getCoordinateX(position), view.getCoordinateY(), radius, paint);
    }

    public static class AnimationPainter extends CirclePainter {
        AnimationValue animationValue;

        public AnimationPainter(Paint paint, int color, int selectedColor, int radius,
                                CircleIndicator view) {
            super(paint, color, selectedColor, radius, view);
        }

        public void setAnimationValue(AnimationValue animationValue) {
            this.animationValue = animationValue;
        }
    }

    public static final class SlideAnimationPainter extends AnimationPainter {

        public SlideAnimationPainter(Paint paint, int color, int selectedColor, int radius,
                                     CircleIndicator view) {
            super(paint, color, selectedColor, radius, view);
        }

        @Override
        public void draw(Canvas canvas, int position) {
            paint.setColor(color);
            canvas.drawCircle(getView().getCoordinateX(position), getView().getCoordinateY(),
                    getRadius(), paint);

            AnimationValue.SlideAnimationValue value =
                    (AnimationValue.SlideAnimationValue) animationValue;
            paint.setColor(selectedColor);
            canvas.drawCircle(value.getCoordinateX(), getView().getCoordinateY(), getRadius(), paint);
        }
    }

    public static final class ScaleAnimationPainter extends AnimationPainter {

        public ScaleAnimationPainter(Paint paint, int color, int selectedColor, int radius,
                                     CircleIndicator view) {
            super(paint, color, selectedColor, radius, view);
        }

        @Override
        public void draw(Canvas canvas, int position) {
            // draw unselected items
            paint.setColor(color);
            canvas.drawCircle(getView().getCoordinateX(position), getView().getCoordinateY(),
                    getRadius() * 0.8f, paint);

            // draw current item
            AnimationValue.ScaleAnimationValue value =
                    (AnimationValue.ScaleAnimationValue) animationValue;
            paint.setColor(value.getColor());
            canvas.drawCircle(getView().getCoordinateX(getView().getCurrentPosition()),
                    getView().getCoordinateY(), value.getRadius(), paint);

            // draw next item
            paint.setColor(value.getColorReverse());
            canvas.drawCircle(getView().getCoordinateX(getView().getNextPosition()),
                    getView().getCoordinateY(), value.getRadiusReverse(), paint);
        }
    }

    public static final class ColorAnimationPainter extends AnimationPainter {

        public ColorAnimationPainter(Paint paint, int color, int selectedColor, int radius,
                                     CircleIndicator view) {
            super(paint, color, selectedColor, radius, view);
        }

        @Override
        public void draw(Canvas canvas, int position) {
            // draw unselected items
            paint.setColor(color);
            canvas.drawCircle(getView().getCoordinateX(position), getView().getCoordinateY(),
                    getRadius(), paint);

            // draw current item
            AnimationValue.ColorAnimationValue value =
                    (AnimationValue.ColorAnimationValue) animationValue;
            paint.setColor(value.getColor());
            canvas.drawCircle(getView().getCoordinateX(getView().getCurrentPosition()),
                    getView().getCoordinateY(), getRadius(), paint);

            // draw next item
            paint.setColor(value.getColorReverse());
            canvas.drawCircle(getView().getCoordinateX(getView().getNextPosition()),
                    getView().getCoordinateY(), getRadius(), paint);
        }
    }
}
