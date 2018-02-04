package me.podrezov.cp481;

import java.util.ArrayList;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.SoundPool;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;

public class TriangleView extends View
{
    private ArrayList<Triangle> triangleObjs;
    private TriangleLayer layer;
    int sound;
    SoundPool soundPool;

    int objBackground, objColor;
    int objSpeed = 20;

    public TriangleView(Context context, int sound, SoundPool soundPool)
    {
        super(context);

        int maxX = getResources().getDisplayMetrics().widthPixels;
        SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(context);

        objColor = setting.getInt("colorObject", 0);
        objBackground = setting.getInt("colorBackground", 0);
        objSpeed = setting.getInt("objectSpeed", 20);

        this.sound = sound;
        this.soundPool = soundPool;

        triangleObjs = new ArrayList<Triangle>();
        layer = new TriangleLayer(objBackground);

        int size = (int) (maxX / 7);

        triangleObjs.add(new Triangle(size, maxX / 2, size, objColor, 1, objSpeed));

        this.setFocusable(true);
        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(final Canvas canvas)
    {
        layer.draw(canvas);
        for (final Triangle t : triangleObjs) {
            t.draw(canvas);
            if (t.moveTriangle(layer)) {
                soundPool.play(sound, 1, 1, 1, 0, 1);
            }
        }

        invalidate();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH)
    {
        layer.set(0, 0, w, h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int maxX = getResources().getDisplayMetrics().widthPixels;
        int size = (int) (maxX / 7);
        float ttx = event.getX();
        float tty = event.getY();

        if (tty < size) {
            tty += size;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                triangleObjs.add(new Triangle(size, ttx, tty, objColor, 1, objSpeed));
                break;
        }

        return true;
    }
}
