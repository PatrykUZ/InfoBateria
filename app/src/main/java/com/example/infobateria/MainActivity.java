package com.example.infobateria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView poziomNaladowania;
    private TextView stan;
    private TextView zrodloZasilania;
    private TextView dostepna;
    private TextView poziom;
    private TextView stan2;
    private TextView technologia;
    private TextView temperatura;
    private TextView napiecie;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poziomNaladowania = (TextView) findViewById(R.id.poziomNaladowania);
        stan = (TextView) findViewById(R.id.stan);
        zrodloZasilania = (TextView) findViewById(R.id.zrodloZasilania);
        dostepna = (TextView) findViewById(R.id.dostepna);
        poziom = (TextView) findViewById(R.id.poziom);
        stan2 = (TextView) findViewById(R.id.stan2);
        technologia = (TextView) findViewById(R.id.technologia);
        temperatura = (TextView) findViewById(R.id.temperatura);
        napiecie = (TextView) findViewById(R.id.napiecie);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);

        BroadcastReceiver mBatInfoReveiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                progressBar.setProgress(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1));
                poziomNaladowania.setText("Level: " +intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1));
                stan.setText("Stan: " +intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1));
                zrodloZasilania.setText("Źródło zasilania: " +intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1));
                dostepna.setText("Dostępna?: " +intent.getIntExtra(BatteryManager.EXTRA_PRESENT, -1));
                poziom.setText("Poziom: " +intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1));
                stan2.setText("Stan: " +intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1));
                technologia.setText("Technologia: " +intent.getIntExtra(BatteryManager.EXTRA_TECHNOLOGY, -1));
                temperatura.setText("Temperatura: " +intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1));
                napiecie.setText("Napięcie: " +intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1));
            }
        };

        registerReceiver(mBatInfoReveiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

}
