# Android Air Humidifier Client
Android aplication as a client connect with the server in [air humidifier](https://github.com/rogowski-piotr/IoT_air_humidifier) and send the setted humidity value. Response measured data from controller module  in [air humidifier](https://github.com/rogowski-piotr/IoT_air_humidifier) via server in Json fromat. Client converting data and displaying it or if any problems occurs eg. error in communication between modules, will also list them to the screen.

## Hardware Requirements
- Android device with SDK 26 or above
- The server module - [ESP8266](https://www.espressif.com/en/products/socs/esp8266)
- The controller module - [Arduino UNO](https://store.arduino.cc/arduino-uno-rev3)

## Software Requirements
- Android Studio to edti the code and bulid APK file
- Android SDK 26 or above

## Setup
Open project in Andoid Studio and build APK file. Then install aplication on your device. "SETUP" button allow to change default address. If you are sure of the address entered then set the humidity and  "SET VALUE" button allow to connect with air humidifier witch will response data with the current air condition.

![view](https://github.com/rogowski-piotr/IoT_android_air_humidifier/blob/master/view.png)