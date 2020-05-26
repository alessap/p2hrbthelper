package com.alessap.p2hrbthelper;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;

public class Listen2Pebble {
    public void forget_pebble_if_disconnected(Context context, Intent intent) {
        IntentFilter f1 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        IntentFilter f2 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);

        //context.registerReceiver((android.content.BroadcastReceiver) BroadcastReceiver, f1);
        // this.registerReceiver(bluetoothAdapter, f1);
        // this.registerReceiver(bluetoothAdapter, f2);
    }
}
