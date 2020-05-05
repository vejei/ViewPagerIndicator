package io.github.vejei.viewpagerindicator.painter;

import android.graphics.Canvas;
import android.graphics.Paint;

import io.github.vejei.viewpagerindicator.animation.AnimationValue;
import io.github.vejei.viewpagerindicator.indicator.RectIndicator;

public class RectPainter extends Painter {
    private RectIndicator view;
    int width;
    int height;
    float cornerRadius;

    public RectPainter(Paint paint, int color, int selectedColor, int width, int height, float cornerRadius, RectIndicator view) {
        super(paint, color, selectedColor);
        this.width = width;
        this.height = height;
        this.cornerRadius = cornerRadius;
        this.view = view;
    }

    public RectIndicator getView() {
        return view;
    }

    public void setView(RectIndicator view) {
        this.view = view;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override
    public void draw(Canvas canvas, int position) {
        int color = getColor();
        if (position == view.getCurrentPosition()) {
            color = selectedColor;
        }
        int coordinateX = view.getCoordinateX(position);
        int coordinateY = view.getCoordinateY();
        paint.setColor(color);
        canvas.drawRoundRect(coordinateX, coordinateY, coordinateX + width,
                coordinateY + height, cornerRadius, cornerRadius, paint
        );
    }

    public static class AnimationPainter extends RectPainter {
        AnimationValue animationValue;

        public AnimationPainter(Paint paint, int color, int selectedColor, int width, int height,
                                float cornerRadius, RectIndicator view) {
            super(paint, color, selectedColor, width, height, cornerRadius, view);
        }

        public void setAnimationValue(AnimationValue animationValue) {
            this.animationValue = animationValue;
        }
    }

    public static final class SlideAnimationPainter extends AnimationPainter {
        public SlideAnimationPainter(Paint paint, int color, int selectedColor, int width, int height,
                                     float cornerRadius, RectIndicator view) {
            super(paint, color, selectedColor, width, height, cornerRadius, view);
        }

        @Override
        public void draw(Canvas canvas, int position) {
            int coordinateX = getView().getCoordinateX(position);
            int coordinateY = getView().getCoordinateY();

            paint.setColor(color);
            canvas.drawRoundRect(coordinateX, coordinateY, coordinateX + width,
                    coordinateY + height, cornerRadius, cornerRadius, paint
            );

            AnimationValue.SlideAnimationValue value =
                    (AnimationValue.SlideAnimationValue) animationValue;
            paint.setColor(selectedColor);
            canvas.drawRoundRect(value.getCoordinateX(), coordinateY,
                    value.getCoordinateX() + width, coordinateY + height, cornerRadius,
                    cornerRadius, paint
            );
        }
    }

    public static final class ColorAnimationPainter extends AnimationPainter {
        public ColorAnimationPainter(Paint paint, int color, int selectedColor, int width, int height,
                                     float cornerRadius, RectIndicator view) {
            super(paint, color, selectedColor, width, height, cornerRadius, view);
        }

        @Override
        public void draw(Canvas canvas, int position) {
            int coordinateX = getView().getCoordinateX(position);
            int coordinateY = getView().getCoordinateY();

            paint.setColor(color);
            canvas.drawRoundRect(coordinateX, coordinateY, coordinateX + width,
                    coordinateY + height, cornerRadius, cornerRadius, paint
            );

            AnimationValue.ColorAnimationValue value = (AnimationValue.ColorAnimationValue) animationValue;
            int left = getView().getCoordinateX(getView().getCurrentPosition());
            paint.setColor(value.getColor());
            canvas.drawRoundRect(left, coordinateY, left + width,
                    coordinateY + height, cornerRadius, cornerRadius, paint
            );

            left = getView().getCoordinateX(getView().getNextPosition());
            paint.setColor(value.getColorReverse());
            canvas.drawRoundRect(left, coordinateY, left + width,
                    coordinateY + height, cornerRadius, cornerRadius, paint
            );
        }
    }
}
