package com.example.airhumidifier;

public class Response {

    private final float temp;
    private final int humi;
    private final String transmitionMsg;
    private final String measurmentMsg;

    Response(float temp, int humi, String transmitionMsg, String measurmentMsg) {
        this.temp = temp;
        this.humi = humi;
        this.transmitionMsg = transmitionMsg;
        this.measurmentMsg = measurmentMsg;
    }

    public float getTemp() {
        return temp;
    }

    public int getHumi() {
        return humi;
    }

    public String getMeasurmentMsg() {
        return measurmentMsg;
    }

    public String getTransmitionMsg() {
        return transmitionMsg;
    }

    boolean correctTransmitionMsg() {
        return transmitionMsg.contains("OK");
    }

    boolean correctMeasurmentMsg() {
        return measurmentMsg.contains("OK");
    }
}
