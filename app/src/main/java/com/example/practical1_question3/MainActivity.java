package com.example.practical1_question3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.hardware.Sensor.TYPE_LIGHT;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView temp_Output;
    TextView pressure_Output;
    SensorManager sensorManager;
    Sensor tempSensor;
    Sensor pressureSensor;
    Boolean isTempAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        temp_Output = findViewById(R.id.txtView_temp_sensor);
        pressure_Output = findViewById(R.id.txtView_pressure_Sensor);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTempAvailable = true;
        } else {
            temp_Output.setText("Temp is not available!!");
            isTempAvailable = false;
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null) {
            pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            isTempAvailable = true;
        } else {
            temp_Output.setText("Pressure is not available!!");
            isTempAvailable = false;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isTempAvailable){
            sensorManager.registerListener(this,tempSensor,SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this,pressureSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isTempAvailable){
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
       if (event.sensor.getType()==Sensor.TYPE_AMBIENT_TEMPERATURE){
           temp_Output.setText(event.values[0] + " ℃");
       }
       else if (event.sensor.getType()==Sensor.TYPE_PRESSURE) {
           pressure_Output.setText(event.values[0] + " hPa");
       }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}