package me.podrezov.cp481;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

public class Snowman extends View
{
    GradientDrawable[] snow;
    Path carrot, bucket;
    int x,y,dW,dH;

    public Snowman(Context v, AttributeSet as)
    {
        super(v, as);
        snow = new GradientDrawable[3];

        dW = getResources().getDisplayMetrics().widthPixels;
        dH = getResources().getDisplayMetrics().heightPixels;
        x = 7*dW/18;
        y = 5*dH/8;

        for (int i=0;i<3;i++){
            snow[i]=new GradientDrawable();
            snow[i].setShape(GradientDrawable.OVAL);
            snow[i].setColor(Color.parseColor("#ffffff"));
            snow[i].setStroke(2, Color.parseColor("#bae1ff"));
        }

        snow[0].setBounds(41*x/60, 3*y/20+y/10,x*33/20, y/2+y/10);
        snow[1].setBounds(x*13/24-x/10, y/2, 43*x/24+x/10, y);
        snow[2].setBounds(x/5, 4*y/5, 2*x+x/5, 3*y/2);
    }

    protected void onDraw(Canvas canvas)
    {
        Paint pp1 = new Paint();
        Paint pp2 = new Paint();

        pp1.setColor(Color.parseColor("#4a3b34"));
        pp2.setColor(Color.RED);
        pp1.setStrokeWidth(8);
        pp2.setStrokeWidth(5);

        pp1.setColor(Color.BLACK);
        pp1.setColor(Color.parseColor("#66391c"));
        pp1.setStrokeWidth(15);
        pp1.setStrokeWidth(10);
        pp1.setColor(Color.BLACK);

        // Тело
        snow[2].draw(canvas);
        snow[1].draw(canvas);
        snow[0].draw(canvas);

        // Нос
        carrot = new Path();
        carrot.moveTo(x + x / 16 + 30, y / 2 + y / 18);
        carrot.lineTo(x + x / 16,  y / 3 + y / 16);
        carrot.lineTo(x + x / 4,  y / 3 + y / 16);

        carrot.close();
        canvas.drawPath(carrot, pp2);

        // Глаза
        canvas.drawCircle(x, y / 3, x / 13, pp1);
        canvas.drawCircle(x + x / 3, y / 3, x / 13, pp1);

        // Ведро
        pp1.setColor(Color.WHITE);
        bucket = new Path();
        bucket.moveTo(x + x / 7, y / 3 + y / 18);
        bucket.lineTo(x + x / 16,  y / 2 + y / 16);
        bucket.lineTo(x + x / 4, y / 3 + y / 18);
        bucket.lineTo(x + x / 16,  y / 2 + y / 16);
        bucket.close();
        canvas.drawPath(bucket, pp1);

        invalidate();
    }
}