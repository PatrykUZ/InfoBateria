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

    private BroadcastReceiver mBatInfoReveiver;
    private IntentFilter filter;

    private boolean onPause = false;

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

        mBatInfoReveiver = mBatInfoReveiver();
        filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        registerReceiver(mBatInfoReveiver, filter);

        onPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onPause) {
            unregisterReceiver(mBatInfoReveiver);
            onPause = false;
        }
    }

    private BroadcastReceiver mBatInfoReveiver() {
        BroadcastReceiver mBatInfoReveiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                progressBar.setProgress(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0));
                poziomNaladowania.setText("Level: " +intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0));
                stan.setText("Stan: " +intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1));
                zrodloZasilania.setText("Źródło zasilania: " +intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0));
                dostepna.setText("Dostępna?: " +intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT));
                poziom.setText("Poziom: " +intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1));
                stan2.setText("Stan: " +intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1));
                technologia.setText("Technologia: " +intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));
                temperatura.setText("Temperatura: " +intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10);
                napiecie.setText("Napięcie: " +intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0) / 1000);
            }
        };
        return mBatInfoReveiver;
    }

}
