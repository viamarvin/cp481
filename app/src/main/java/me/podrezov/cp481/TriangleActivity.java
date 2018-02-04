package me.podrezov.cp481;

import java.io.IOException;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.Toast;

public class TriangleActivity extends AppCompatActivity {
    View view;
    SoundPool soundPool;
    AssetManager assetManager;
    int soundOgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        assetManager = getAssets();
        soundOgg = loadSound("sound.ogg");
        super.onCreate(savedInstanceState);

        view = new TriangleView(this, soundOgg, soundPool);
        setContentView(view);
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor d = null;
        try {
            d = assetManager.openFd(fileName);
        } catch (IOException e) {
            Toast.makeText(this, R.string.sound_file_not_found,Toast.LENGTH_SHORT).show();
            return -1;
        }

        return soundPool.load(d,1);
    }
}
