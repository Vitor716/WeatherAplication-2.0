package com.example.weatheraplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class Temperature extends AppCompatActivity implements SensorEventListener {
    private TextView txtTemp;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private Boolean isTemperatureSensorAvailable;
    public TextView txtAva;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        txtTemp = findViewById(R.id.txtTemp);
        txtAva = findViewById(R.id.txtTemp);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }else{
            txtTemp.setText("O sensor de temperatura não esta funcionando");
            txtAva.setText("Tente Novamente");
            isTemperatureSensorAvailable = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        txtTemp.setText(sensorEvent.values[0]+" ºC");
        if (sensorEvent.values[0] >= 30){
            txtAva.setText("O ambiente está muito quente");
        } else if (sensorEvent.values[0] <= 29 && sensorEvent.values[0] >= 18){
            txtAva.setText("O ambiente está com um clima agradavel");
        } else if (sensorEvent.values[0] <= 17){
            txtAva.setText("O ambiente está muito frio");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume(){
        super.onResume();
        if(isTemperatureSensorAvailable){
            sensorManager.registerListener(this,tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(isTemperatureSensorAvailable){
            sensorManager.unregisterListener(this);
        }
    }
}