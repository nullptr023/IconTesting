package com.example.nullptr023.icontesting;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telecom.Call;
import android.telecom.InCallService;
import android.util.Log;

/**
 * Created by nullptr023 on 2/19/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class CustomInCallService extends InCallService {

    private static final String TAG =CustomInCallService.class.getSimpleName();
    private static boolean isIncomingCall = false;

    private Call.Callback mCallback = new Call.Callback() {
        @Override
        public void onStateChanged(Call call, int state) {

            Log.e(TAG, "call-state: " + state);
            if (state == Call.STATE_ACTIVE) {
                Log.d(TAG, "call is active");
                if (!isIncomingCall) {
                    Log.d(TAG, "call active: outgoing call");
                    call.playDtmfTone('7');
                    call.stopDtmfTone();
                    call.playDtmfTone('1');
                    call.stopDtmfTone();
                    call.playDtmfTone('3');
                    call.stopDtmfTone();
                    call.playDtmfTone('4');
                    call.stopDtmfTone();
                    call.playDtmfTone('#');
                    call.stopDtmfTone();
                } else {
                    Log.d(TAG, "call active: incoming call");
                }
            } else if (state == Call.STATE_DIALING) {
                Log.d(TAG, "call is dialing");
                isIncomingCall = false;
            } else if (state == Call.STATE_CONNECTING) {
                Log.d(TAG, "call is connecting");
            } else if (state == Call.STATE_DISCONNECTED) {
                Log.d(TAG, "call disconnected");
                isIncomingCall = false;
            } else if (state == Call.STATE_DISCONNECTING) {
                Log.d(TAG, "call is disconnecting");
            } else if (state == Call.STATE_RINGING) {
                Log.d(TAG, "call is ringing");
                isIncomingCall = true;
            } else if (state == Call.STATE_NEW) {
                Log.d(TAG, "call is new");
            } else if (state == Call.STATE_HOLDING) {
                Log.d(TAG, "call is holding");
            } else if (state == Call.STATE_PULLING_CALL) {
                Log.d(TAG, "call is pulling call");
            }

        }
    };



    @Override
    public void onCallAdded(Call call) {
        Log.e(TAG, "onCallAdded");
        // get dialed number here and type it
        call.registerCallback(mCallback);
    }

    @Override
    public void onCallRemoved(Call call) {
        call.unregisterCallback(mCallback);
    }
}
