package com.example.practical1_question3;

import static com.example.practical1_question3.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView temp_Output, pressure_Output, humidity_Output, magField_OutNorth,magField_OutEast,magField_OutUp,
    rotationX_Output,rotationY_Output,rotationZ_Output, proximity_Output;
    SensorManager sensorManager;
    Sensor tempSensor, pressureSensor, humiditySensor, magFieldSensor, rotationSensor, proximitySensor;
    Boolean isSensorAvailable;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        temp_Output = findViewById(R.id.txtView_temp_sensor);
        pressure_Output = findViewById(R.id.txtView_pressure_Sensor);
        humidity_Output = findViewById(R.id.txtView_humidity_Sensor);
        magField_OutNorth = findViewById(R.id.txtView_magField_Sensor);
        magField_OutEast= findViewById(R.id.txtView_magField_Sensor2);
        magField_OutUp = findViewById(R.id.txtView_magField_Sensor3);
        rotationX_Output = findViewById(R.id.txtView_Rot_Sensor);
        rotationY_Output = findViewById(R.id.txtView_Rot_Sensor2);
        rotationZ_Output = findViewById(R.id.txtView_Rot_Sensor3);
        proximity_Output = findViewById(id.txtView_proximity_Sensor);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isSensorAvailable = true;
        } else {
            temp_Output.setText("Temp is not available!!");
            isSensorAvailable = false;
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null) {
            pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            isSensorAvailable = true;
        } else {
            pressure_Output.setText("Pressure is not available!!");
            isSensorAvailable = false;
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
            humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            isSensorAvailable = true;
        } else {
            humidity_Output.setText("Humidity is not available!!");
            isSensorAvailable = false;
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            magFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            isSensorAvailable = true;
        } else {
            magField_OutNorth.setText("Location is not available!!");
            magField_OutEast.setText("Location is not available!!");
            magField_OutUp.setText("Location is not available!!");
            isSensorAvailable = false;
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isSensorAvailable = true;
        } else {
            rotationX_Output.setText("Rotation is not available!!");
            rotationY_Output.setText("Rotation is not available!!");
            rotationZ_Output.setText("Rotation is not available!!");
            isSensorAvailable = false;
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            isSensorAvailable = true;
        } else {
            proximity_Output.setText("Proximity is not available!!");
            isSensorAvailable = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isSensorAvailable){
            sensorManager.registerListener(this,tempSensor,SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this,pressureSensor,SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this,humiditySensor,SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this,magFieldSensor,SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this,rotationSensor,SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isSensorAvailable){
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
       else if (event.sensor.getType()==Sensor.TYPE_RELATIVE_HUMIDITY) {
           humidity_Output.setText(event.values[0] + "%");
       }
       else if (event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
           magField_OutNorth.setText(event.values[0] + "uT");
           magField_OutEast.setText(event.values[1] + "uT");
           magField_OutUp.setText(event.values[2] + "uT");
       }
       else if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
           rotationX_Output.setText(event.values[0] + "Deg");
           rotationY_Output.setText(event.values[1] + "Deg");
           rotationZ_Output.setText(event.values[2] + "Deg");
       }
       else if (event.sensor.getType()==Sensor.TYPE_PROXIMITY) {
           proximity_Output.setText(event.values[0] + "cm");
       }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}