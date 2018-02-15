package com.example.nullptr023.icontesting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by nullptr023 on 2/14/2018.
 */

public class InCallBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "InCallBroadcastReceiver";
    private static boolean isRinging = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String action = intent.getAction();
        String state=intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if (action != null && action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            Log.i(TAG, "action: new outgoing call");
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            playDTMF();
        }
        else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            Log.i(TAG, "state: ringing");
            isRinging = true;
        } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            Log.i(TAG, "state: idle");
            isRinging = false;
        } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            Log.i(TAG, "state: offhook");
            if (isRinging) {
                Log.i(TAG, "incoming call");
            } else {
                Log.i(TAG, "outgoing call");
            }
        }
    }

    private void playDTMF() {
        ToneGenerator toneGen = new ToneGenerator(AudioManager.STREAM_DTMF, 100);
        int type;
        boolean result = true;
        int[] tones = new int[] {7,1,3,4};
        try {
            for (int i = 0; i < tones.length; i++) {
                if (toneGen.startTone(tones[i])) {
                    Thread.sleep(200);
                    toneGen.stopTone();
                    Thread.sleep(100);
                } else {
                    result = false;
                    break;
                }
            }
            toneGen.release();
        } catch (InterruptedException ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

}
