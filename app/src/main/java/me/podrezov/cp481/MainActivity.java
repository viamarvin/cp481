package me.podrezov.cp481;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showCalc(View v) {
        Intent intObj = new Intent(this, CalcActivity.class);
        startActivity(intObj);
    }

    public void showSettingPage(View v)
    {
        Intent intObj = new Intent(this, MyPreferenceActivity.class);
        startActivity(intObj);
    }
}
