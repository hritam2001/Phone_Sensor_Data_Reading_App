package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class temperature extends AppCompatActivity implements SensorEventListener {
    private TextView textview;
    private SensorManager sensorManager;;
    private Sensor tempsensor;
    private boolean istempavailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        getSupportActionBar().hide();

        textview= findViewById(R.id.temp);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)!=null){
            istempavailable=true;
            tempsensor=sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }else{
            istempavailable=false;
            textview.setText("Temperature Sensor not Available");
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(istempavailable){
            sensorManager.registerListener(this,tempsensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onResume() {
        if(istempavailable){
            sensorManager.unregisterListener(this);
        }
        super.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorevent) {
        textview.setText(sensorevent.values[0]+"ºC");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}