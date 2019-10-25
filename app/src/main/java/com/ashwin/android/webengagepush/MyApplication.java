package com.ashwin.android.webengagepush;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.webengage.sdk.android.WebEngage;
import com.webengage.sdk.android.WebEngageActivityLifeCycleCallbacks;
import com.webengage.sdk.android.WebEngageConfig;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initWebEngage();
    }

    private void initWebEngage() {
        WebEngageConfig config = new WebEngageConfig.Builder()
                .setWebEngageKey("YOUR-WEBENGAGE-LICENSE-CODE")
                .setDebugMode(true)
                .build();

        registerActivityLifecycleCallbacks(new WebEngageActivityLifeCycleCallbacks(this, config));

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();
                Log.d("debug-log", "FCM Reg ID: " + token);
                WebEngage.get().setRegistrationID(token);
            }
        });
    }
}
