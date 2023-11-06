package com.example.practical1_question3;

import static android.hardware.Sensor.TYPE_LIGHT;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Luminosity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor lightSensor;
    SensorEventListener lightEventListener;
    View root;
    float maxValue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(androidx.appcompat.R.style.Theme_AppCompat);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luminosity);

        root = findViewById(R.id.root);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);


        if (lightSensor ==null){
            Toast.makeText(this,"No Light sensor", Toast.LENGTH_SHORT).show();
            finish();
        }

        maxValue = lightSensor.getMaximumRange();

        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float value = sensorEvent.values[0];

                // Show Action bar with title and Values
                getSupportActionBar().setTitle("Luminosity: " + value + " lx");
                //Create a colour variable to use in the ActionBar
                ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F9D58"));
                //Change the colour of the actionBar
                getSupportActionBar().setBackgroundDrawable(colorDrawable);

                int newValue = (int)(255f * value / maxValue);
                root.setBackgroundColor(Color.rgb(newValue,newValue,newValue));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(lightEventListener,lightSensor,sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightEventListener);
    }
}