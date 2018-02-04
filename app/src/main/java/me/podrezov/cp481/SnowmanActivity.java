package me.podrezov.cp481;

import java.util.Random;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SnowmanActivity extends Activity
{
    ImageView images[];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snowman);

        images = new ImageView[3];
        images[0] = (ImageView) findViewById(R.id.s1);
        images[1] = (ImageView) findViewById(R.id.s2);
        images[2] = (ImageView) findViewById(R.id.s3);

        int dW = getResources().getDisplayMetrics().widthPixels;
        int dH = getResources().getDisplayMetrics().heightPixels;
        int x = dW / 2;
        int y = dH / 2;

        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(x / 3,x / 3);
        lp1.leftMargin = x - x / 4;
        lp1.topMargin = y + y / 2 - y / 8;
        images[0].setLayoutParams(lp1);

        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(x / 3,x / 3);
        lp2.leftMargin = x - x / 4 - x / 3 - x / 10;
        lp2.topMargin = y + y / 2 - y / 8;
        images[1].setLayoutParams(lp2);

        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(x / 3,x / 3);
        lp3.leftMargin = x - x / 4 + x / 3 + x / 10;
        lp3.topMargin = y + y / 2 - y / 8;
        images[2].setLayoutParams(lp3);

        Random r = new Random();
        Schar t1 = new Schar(images[0],800);
        Thread ts1 = new Thread(t1);
        ts1.start();

        Schar t2 = new Schar(images[1],1500 + r.nextInt(1000));
        Thread ts2 = new Thread(t2);
        ts2.start();

        Schar t3 = new Schar(images[2], 3000 + r.nextInt(1000));
        Thread ts3 = new Thread(t3);
        ts3.start();
    }

    class Schar implements Runnable
    {
        ImageView iv;
        int sleep;
        Random r;

        public Schar(ImageView iv, int sleep)
        {
            this.iv = iv;
            this.sleep = sleep;
            r = new Random();

        }

        @Override
        public void run()
        {
            while (true) {
                SnowmanActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GradientDrawable oval = new GradientDrawable();
                        oval.setShape(GradientDrawable.OVAL);
                        oval.setColor(Color.rgb(r.nextInt(255), r.nextInt(255),
                                r.nextInt(255)));
                        iv.setImageDrawable(oval);
                    }
                });
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {

                }
            }
        }
    }
}
