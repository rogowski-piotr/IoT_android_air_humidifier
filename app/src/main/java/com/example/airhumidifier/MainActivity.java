package com.example.airhumidifier;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewValue;
    private TextView textViewInfo;
    private TextView textViewTemp;
    private TextView textViewHumi;
    private TextView textViewFirstInfo;
    private SeekBar seekBar;
    private Button buttonSetVal;
    private Button buttonSetup;

    private static String IP = "192.168.0.19";
    private static int PORT = 50007;
    private static int setValue = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewValue = (TextView) findViewById(R.id.textViewSetVal);
        textViewInfo = (TextView) findViewById(R.id.textViewInfo);
        textViewTemp = (TextView) findViewById(R.id.textViewTemp);
        textViewHumi = (TextView) findViewById(R.id.textViewHumi);
        textViewFirstInfo = (TextView) findViewById(R.id.textViewFirstInfo);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        buttonSetVal = (Button) findViewById(R.id.buttonSetVal);
        buttonSetup = (Button) findViewById(R.id.buttonSetup);

        buttonSetVal.setOnClickListener(this);
        buttonSetup.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setValue = 25 + progress * 5;
                textViewValue.setText(setValue + "%");
            }
            public void onStartTrackingTouch(SeekBar seekBar) { }
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSetVal:
                textViewFirstInfo.setVisibility(View.INVISIBLE);
                String setValueStr = String.valueOf(setValue);
                new KlientESP8266(this, IP, PORT, setValueStr).execute();
                break;

            case R.id.buttonSetup:
                break;
        }
    }

    public void showErrors(String errorMsg) {
        errorMsg = errorMsg == null ? "" : errorMsg;
        textViewInfo.setText(errorMsg);
    }

    public void showResult(String temp, String humi) {
        temp = temp == null ? "" : "Actual temperature: " + temp + " °C";
        humi = humi == null ? "" : "Actual humidity: " + humi + " %";
        textViewTemp.setText(temp);
        textViewHumi.setText(humi);
    }
}
