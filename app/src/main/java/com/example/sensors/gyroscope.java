package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.renderscript.Sampler;
import android.widget.TextView;

public class gyroscope extends AppCompatActivity implements SensorEventListener {
    private TextView textView;
    private boolean gyroavbl;
    private Sensor gyro;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        getSupportActionBar().hide();

        textView=findViewById(R.id.gyro);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null)
        {
            gyro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            gyroavbl = true;
        }
        else{
            gyroavbl = false;
            textView.setText("Gyroscope Not Available");
        }
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(gyroavbl){
            sensorManager.registerListener(this,gyro, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textView.setText(event.values[0]+"=Xaxis\n"+event.values[1]+"=YAxis\n"+event.values[2]+"=Zaxis");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}