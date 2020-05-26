package com.alessap.p2hrbthelper;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BluetoothReceiver extends BroadcastReceiver {
    public BluetoothReceiver(){
        //No initialisation code needed
    }

    @Override
    public void onReceive(Context context, Intent intent){
        if(BluetoothDevice.ACTION_ACL_CONNECTED.equals(action.getAction()){
            //Do something with bluetooth device connection
        } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action.getAction()){
            //Do something with bluetooth device disconnection
        }
    }
}
