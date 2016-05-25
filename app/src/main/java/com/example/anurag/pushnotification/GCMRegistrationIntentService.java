package com.example.anurag.pushnotification;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
/**
 * Created by Anurag on 5/25/2016.
 */
public class GCMRegistrationIntentService extends IntentService {

    public static final String REGISTRATION_SUCCESS = "RegistrationSuccess";
    public static final String REGISTRATION_ERROR = "RegistrationError";


    public GCMRegistrationIntentService() {
        super("");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        registerGCM();
    }

    private void registerGCM() {

        Intent registrationComplete = null;


        String token = null;
        try {

            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());


            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            Log.w("GCMRegIntentService", "token:" + token);

           registrationComplete = new Intent(REGISTRATION_SUCCESS);

            registrationComplete.putExtra("token", token);
        } catch (Exception e) {

            Log.w("GCMRegIntentService", "Registration error");
            registrationComplete = new Intent(REGISTRATION_ERROR);
        }


        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}
