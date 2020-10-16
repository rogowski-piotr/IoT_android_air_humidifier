package com.example.airhumidifier;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KlientESP8266 extends AsyncTask<Void, Void, Void> {

    private String IP;
    private int PORT;
    private String message;
    private String exceptionMessage;
    private String responseServer;
    private Activity activity;

    KlientESP8266(Activity activity, String ip, int port, String message) {
        this.activity = activity;
        this.IP = ip;
        this.PORT = port;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(activity, "Setting value...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity a = (MainActivity) activity;
        Gson gson = new Gson();

        if (exceptionMessage != null) {
            a.showErrors(exceptionMessage);
            return;
        }

        Response response = gson.fromJson(responseServer, Response.class);

        if (response.correctMeasurmentMsg() && response.correctTransmitionMsg()) {
            a.showResult(String.valueOf(response.getTemp()), String.valueOf(response.getHumi()));
            a.showErrors(null);
        } else {
            a.showResult(String.valueOf(response.getTemp()), String.valueOf(response.getHumi()));
            a.showErrors(response.getTransmitionMsg() + "\n" + "Measurment: " + response.getMeasurmentMsg());
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Socket socket = new Socket(IP, PORT);

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(message);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            responseServer = reader.readLine();

            writer.close();
            reader.close();
            socket.close();
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
            responseServer = null;
        }
        return null;
    }
}