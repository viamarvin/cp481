package me.podrezov.cp481;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Triangle {
    int size;
    float curX, curY;
    int sk1, sk2;
    float maxY;
    Path path;
    Paint pBackCol, pGray;

    public Triangle(int size, float curX, float curY, int color, int sk1, int sk2) {
        this.size = size;
        this.curX = curX;
        this.curY = curY;
        maxY = curY - size;
        this.sk1 = sk1;
        this.sk2 = sk2;

        path = new Path();
        this.pBackCol = new Paint();
        this.pBackCol.setColor(color);

        pGray = new Paint();
        pGray.setColor(Color.DKGRAY);
        pGray.setStrokeWidth(2);
    }

    public Boolean moveTriangle(TriangleLayer layer) {
        if (maxY < 0) {
            maxY = layer.yt + 1;
        }

        Boolean playSound = false;
        curX += sk1;
        curY += sk2;

        if (layer.yb - maxY <= 2 * size) {
            sk2 = 0;
        } else {
            if (curX + size > layer.xr) {
                sk1 = -sk1;
                curX = layer.xr - size;
            } else if (curX - size < layer.xl) {
                sk1 = -sk1;
                curX = layer.xl + size;
            }

            if (curY + size > layer.yb) {
                sk2 = -sk2;
                sk2 = sk2 + 2;
                curY = layer.yb - size;
                maxY = maxY + 2 * size;
                playSound = true;
            } else if (curY - size < maxY) {
                sk2 = -sk2;
                sk2 = sk2 + 5;
            }
        }

        return playSound;
    }

    public void draw(Canvas canvas) {
        if (sk2 != 0) {
            path = new Path();
            path.moveTo(curX, curY - size);
            path.lineTo(curX + size, curY + size);
            path.lineTo(curX - size, curY + size);
            path.close();

            canvas.drawPath(path, pBackCol);
            canvas.drawLine(curX, curY - size, curX + size, curY + size, pGray);
            canvas.drawLine(curX + size, curY + size, curX - size, curY + size, pGray);
            canvas.drawLine(curX - size, curY + size, curX, curY - size , pGray);
        }
    }
}
