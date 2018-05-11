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

public class MainActivity extends AppCompatActivity {

    private String TAG = "hackAvgleHash";
    private String videoId = null;


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
                Snackbar.make(view, "Try Hack Avgle ...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                EditText editText = (EditText) findViewById((com.awesapp.isafe.R.id.videoId));
                videoId = editText.getText().toString();
                String result = "value null";
                if (videoId != null) {
                    result = hackAvgleHash(videoId);
                    final String finalResult = result;
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
                }
                Toast.makeText(MainActivity.this, "successfully! tap to copy", Toast.LENGTH_SHORT).show();

            }
        });
    }


    /**
     * invoke avgle so
     *
     * @param videoId
     * @return
     */
    public String hackAvgleHash(String videoId) {
        Log.d(TAG, getApplicationContext().getPackageName().toString());
        String time = String.valueOf((int) (System.currentTimeMillis() / 1000));
        String hash = new PSVS21().computeHash(getApplicationContext(), videoId, time);
        String url = "https://avgle.com/mp4.php?vid=" + videoId + "&ts=" + time + "&hash=" + hash;
        return url;
    }

}
