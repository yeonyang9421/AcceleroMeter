package com.example.edu.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener, SeekBar.OnSeekBarChangeListener {
    SeekBar seekBar;
    int threshole = 3;
    float previousX, previousY, previousZ, curerrentY, stepts = 0;
    float acceleration = 0.0f;
    float x, y, z;
    TextView textViewSteps, textViewGx, textViewGy, textViewGz, textviewseeknum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekbar);
        textViewSteps = findViewById(R.id.textViewSteps);
        textviewseeknum = findViewById(R.id.textviewseeknum);
        textViewGx = findViewById(R.id.textViewGx);
        textViewGy = findViewById(R.id.textViewGy);
        textViewGz = findViewById(R.id.textViewGz);

        seekBar.setProgress(threshole);
        seekBar.setOnSeekBarChangeListener(this);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        curerrentY = y;
        if (Math.abs(curerrentY - previousY) > threshole) {
            stepts++;
            textViewSteps.setText(String.valueOf(stepts));
        }
        textViewGx.setText(String.valueOf(x));
        previousX = x;
        textViewGy.setText(String.valueOf(y));
        previousY = y;
        textViewGz.setText(String.valueOf(z));
        previousZ = z;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        threshole = progress;
//        textviewseeknum.setText(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
