package io.github.vejei.viewpagerindicator.painter;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Painter {
    protected Paint paint;
    protected int color;
    protected int selectedColor;

    public Painter(Paint paint, int color, int selectedColor) {
        this.paint = paint;
        this.color = color;
        this.selectedColor = selectedColor;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public abstract void draw(Canvas canvas, int position);
}
