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
import android.os.Vibrator;
import android.widget.TextView;
public class proximity extends AppCompatActivity implements SensorEventListener {
    private TextView textView;
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private boolean proxavbl;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        getSupportActionBar().hide();

        textView=findViewById(R.id.proximityy);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        vibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)!=null) {
            proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            proxavbl = true;
        }
        else {
            proxavbl=false;
            textView.setText("Proximity Sensor Not Available");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textView.setText(event.values[0]+"cm");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
        }else{
            vibrator.vibrate(500);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(proxavbl){
            sensorManager.registerListener(this,proximitySensor,sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(proxavbl){
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}