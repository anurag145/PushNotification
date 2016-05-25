package com.example.anurag.pushnotification;
import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;
/**
 * Created by Anurag on 5/25/2016.
 */
public class GCMTokenRefreshListenerService extends InstanceIDListenerService  {
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, GCMRegistrationIntentService.class);
        startService(intent);
    }
}
