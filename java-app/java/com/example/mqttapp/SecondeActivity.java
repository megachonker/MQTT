package com.example.mqttapp;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class SecondeActivity extends AppCompatActivity {

    static String MQTTHOST = "tcp://SENSORED:SENSORED";
    static String USERNAME = "client";
    static String PASSWORD = "SENSORED";
    String topicStr = "SENSORED";

    MqttAndroidClient client2;

    TextView subText2;

    MqttConnectOptions options;

    Vibrator vibrator;


    Button on2, off2, lampe1;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seconde_activity);


        subText2 = (TextView) findViewById(R.id.subText2);

        on2 = (Button) findViewById(R.id.on2);
        off2 = (Button) findViewById(R.id.off2);
        lampe1 = (Button) findViewById(R.id.lampe1);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        String clientId2 = MqttClient.generateClientId();
        client2 = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId2);

        options = new MqttConnectOptions();
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());

        lampe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }) ;
        on2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String topic = topicStr;
                String message = "ON";
                try {
                    client2.publish(topic, message.getBytes(), 0, false);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                ;
                client2.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {

                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        subText2.setText(new String(message.getPayload()));

                        vibrator.vibrate(500);
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });
            }

        });

        off2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String topic = topicStr;
                String message = "OFF";
                try {
                    client2.publish(topic, message.getBytes(), 0, false);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                ;
                client2.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {

                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        subText2.setText(new String(message.getPayload()));

                        vibrator.vibrate(500);
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });
            }

        });

        try {
            IMqttToken token = client2.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(SecondeActivity.this, "connected!", Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(SecondeActivity.this, "connection failed!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }




        client2.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                subText2.setText(new String(message.getPayload()));

                vibrator.vibrate(500);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    private void setSubscription () {
        try {
            client2.subscribe(topicStr, 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void pub(View v){
        String topic = topicStr;
        String message = "GET";
        try {
            client2.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e){
            e.printStackTrace();
        }
    }

    public void conn2 (View v){
        try {
            IMqttToken token = client2.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(SecondeActivity.this, "connected!", Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(SecondeActivity.this, "connection failed!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void disconn2 (View v){
        try {
            IMqttToken token = client2.disconnect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(SecondeActivity.this, "disconnected!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(SecondeActivity.this, "could not disconnect", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}

