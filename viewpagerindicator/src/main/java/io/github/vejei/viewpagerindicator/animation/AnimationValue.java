package io.github.vejei.viewpagerindicator.animation;

public class AnimationValue {

    public static final class SlideAnimationValue extends AnimationValue {
        private int coordinateX;

        public int getCoordinateX() {
            return coordinateX;
        }

        public void setCoordinateX(int coordinateX) {
            this.coordinateX = coordinateX;
        }
    }

    public static final class ScaleAnimationValue extends AnimationValue {
        private int color;
        private int colorReverse;
        private int radius;
        private int radiusReverse;

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getColorReverse() {
            return colorReverse;
        }

        public void setColorReverse(int colorReverse) {
            this.colorReverse = colorReverse;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public int getRadiusReverse() {
            return radiusReverse;
        }

        public void setRadiusReverse(int radiusReverse) {
            this.radiusReverse = radiusReverse;
        }
    }

    public static final class ColorAnimationValue extends AnimationValue {
        private int color;
        private int colorReverse;

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getColorReverse() {
            return colorReverse;
        }

        public void setColorReverse(int colorReverse) {
            this.colorReverse = colorReverse;
        }
    }
}
