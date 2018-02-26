package me.podrezov.cp481;

import java.io.IOException;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.res.AssetFileDescriptor;
import android.view.View;
import android.widget.Toast;

public class TriangleActivity extends AppCompatActivity {
    View view;
    SoundPool soundPool;
    int soundOgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        soundOgg = soundPool.load(this, R.raw.sound,1);
        super.onCreate(savedInstanceState);

        view = new TriangleView(this, soundOgg, soundPool);
        setContentView(view);
    }
}
