package com.example.myapplication4;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn1);
        TextView textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    try {
                        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=fb40812752b8680883bb8abd322914a5");
                        HttpURLConnection connection =
                                (HttpURLConnection) url.openConnection();
                        Scanner scanner = new Scanner(connection.getInputStream());
                        StringBuilder json = new StringBuilder();
                        while (scanner.hasNext()){
                            json.append(scanner.nextLine());
                            json.append("\n");
                        }
                        JSONObject t1 = new JSONObject(json.toString());
                        JSONObject t2 = t1.getJSONObject("main");
                        String temperature = t2.getString("temp");
                        int t3 =(int) (Double.parseDouble(temperature) - 273.15);
                        temperature = Integer.toString(t3);
                        textView.setText(temperature);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}