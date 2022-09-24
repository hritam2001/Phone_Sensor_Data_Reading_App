package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class magnetometer extends AppCompatActivity implements SensorEventListener {
    private TextView textView;
    private Sensor sensor;

    private static SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_magnetometer);
        textView=(TextView) findViewById(R.id.magnet);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor((Sensor.TYPE_MAGNETIC_FIELD));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensor!=null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
            else{
                textView.setText("Sensor Not Found");
            }
        }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float azimuth =Math.round(event.values[0]);
        float pitch = Math.round(event.values[1]);
        float roll = Math.round(event.values[2]);

        double tesla = Math.sqrt((azimuth*azimuth)+(pitch*pitch)+(roll*roll));
        String text = String.format("%.2f",tesla);
        textView.setText(text+"µF");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}