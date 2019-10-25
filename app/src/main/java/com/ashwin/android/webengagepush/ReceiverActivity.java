package com.ashwin.android.webengagepush;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiverActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            String str = getBundleString(bundle);
            ((TextView) findViewById(R.id.intent_textview)).setText(str);
        }
    }

    private static String getBundleString(Bundle bundle) {
        if (bundle == null) {
            return "null";
        }
        String data = "";
        for (String key : bundle.keySet()) {
            if (!data.isEmpty()) {
                data = data + ", ";
            }
            data = data + key + ": " + bundle.get(key);
        }
        return data;
    }
}
