package com.example.mqttapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class deux_activity extends AppCompatActivity {
    static String MQTTHOST = "tcp://SENSORED:SENSORED";
    static String USERNAME = "client";
    static String PASSWORD = "SENSORED";
    String topicStr = "SENSORED";
    String topicStr2 = "SENSORED";

    MqttAndroidClient client;

    TextView subText, lmp1;


    MqttConnectOptions options;

    Vibrator vibrator;


    Button on, off, lampe2;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deux_activity);


        subText = (TextView) findViewById(R.id.subText3);

        on = (Button) findViewById(R.id.on);
        off = (Button) findViewById(R.id.off);
        lampe2 = (Button) findViewById(R.id.lampe2);

        lmp1 = (TextView) findViewById(R.id.lmp1);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);

        options = new MqttConnectOptions();
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());





        lampe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deux_activity.this, MainActivity.class);
                startActivity(intent);
            }
        }) ;

        on.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String topic = topicStr;
                String topic2 = topicStr2;
                String message = "ON";
                try {
                    client.publish(topic, message.getBytes(), 0, false);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                ;
                try {
                    client.publish(topic2, message.getBytes(), 0, false);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                client.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {

                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        subText.setText(new String(message.getPayload()));

                        vibrator.vibrate(500);
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });
                client.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {

                    }

                    @Override
                    public void messageArrived(String topic2, MqttMessage message) throws Exception {
                        subText.setText(new String(message.getPayload()));

                        vibrator.vibrate(500);
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });

            }
        });


        off.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String topic = topicStr;
                String topic2 = topicStr2;
                String message = "OFF";
                lmp1.setTextColor(Color.RED);
                try {
                    client.publish(topic, message.getBytes(), 0, false);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                ;
                try {
                    client.publish(topic2, message.getBytes(), 0, false);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                ;
                client.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {

                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        subText.setText(new String(message.getPayload()));

                        vibrator.vibrate(500);
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });

                client.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {

                    }

                    @Override
                    public void messageArrived(String topic2, MqttMessage message) throws Exception {
                        subText.setText(new String(message.getPayload()));

                        vibrator.vibrate(500);
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });
            }

        });

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(deux_activity.this, "connected!", Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(deux_activity.this, "connection failed!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }




        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                subText.setText(new String(message.getPayload()));

                vibrator.vibrate(500);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    private void setSubscription () {
        try {
            client.subscribe(String.valueOf(topicStr), 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void pub(View v) {
        String topic = topicStr;
        String topic2 = topicStr2;
        String message = "GET";
        try {
            client.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        try {
            client.publish(topic2, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }



    public void conn3 (View v){
        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(deux_activity.this, "connected!", Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(deux_activity.this, "connection failed!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void disconn3 (View v){
        try {
            IMqttToken token = client.disconnect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(deux_activity.this, "disconnected!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(deux_activity.this, "could not disconnect", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }


}



