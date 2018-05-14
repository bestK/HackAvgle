package com.awesapp.isafe;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.awesapp.isafe.misc.LuckyDrawFragment;
import com.awesapp.isafe.svs.parsers.PSVS0;
import com.awesapp.isafe.svs.parsers.PSVS21;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String TAG = "hackAvgleHash";
    private String videoId = null;
    private String hashUrl = null;
    private String downloadUrl = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ClipboardManager myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        setContentView(com.awesapp.isafe.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(com.awesapp.isafe.R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(com.awesapp.isafe.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById((com.awesapp.isafe.R.id.videoId));
                String result = "value null";
                if (editText.getText().length() > 0) {
                    Snackbar.make(view, "Try Hack Avgle ...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    videoId = editText.getText().toString();
                    result = hackAvgleHash(videoId);
//                    Thread getDownLoadUrl = new Thread(new getAvgleDownLoadUrlHandler());
//                    getDownLoadUrl.start();

                    final String finalResult = hashUrl;
                    TextView textView = (TextView) findViewById(R.id.avgleHash);
                    textView.setText(result);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ClipData myClip;
                            myClip = ClipData.newPlainText("text", finalResult);
                            myClipboard.setPrimaryClip(myClip);
                            Toast.makeText(MainActivity.this, "copied!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(MainActivity.this, "successfully! tap to copy", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    class getAvgleDownLoadUrlHandler implements Runnable {

        @Override
        public void run() {
            try {
                downloadUrl = getAvgleDownLoadUrl(hashUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * invoke avgle so
     *
     * @param videoId
     * @return
     */
    public String hackAvgleHash(String videoId) {
        Log.d(TAG, getApplicationContext().getPackageName().toString());
        String time = getTimeStr();
        String hash = new PSVS21().computeHash(getApplicationContext(), videoId, time);
        String url = "https://avgle.com/mp4.php?vid=" + videoId + "&ts=" + time + "&hash=" + hash;
        hashUrl = url;
        return url;
    }

    public String getTimeStr() {
        long timeStampSec = System.currentTimeMillis() / 1000;
        String timestamp = String.format("%010d", timeStampSec);
        return timestamp;
    }


    public String getAvgleDownLoadUrl(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
