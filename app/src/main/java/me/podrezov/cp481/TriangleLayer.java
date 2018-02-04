package me.podrezov.cp481;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class TriangleLayer {
    int xl, xr, yt, yb;
    private Paint p;
    private Rect rect;

    public TriangleLayer(int color) {
        p = new Paint();
        p.setColor(color);
        rect = new Rect();
    }

    public void set(int x, int y, int width, int height) {
        xl = x;
        xr = x + width;
        yt = y;
        yb = y + height;
        rect.set(xl, yt, xr, yb);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, p);
    }
}
