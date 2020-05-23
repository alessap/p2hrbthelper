package com.alessap.p2hrbthelper;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Method;
import java.lang.String;
import java.util.Set;

class ForgetPebble implements View.OnClickListener {

        // TODO alessap: How to enable a background process?
        // TODO alessap: allow user to define which device to disconnect

        @Override
        public void onClick(View view) {
            Log.d("Forget Pebble","Forget Pebble activated.");
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            boolean is_connected = false;

            if (pairedDevices.size() > 0) {
                // There are paired devices. Get the name and address of each paired device.
                for (BluetoothDevice device : pairedDevices) {
                    String deviceName = device.getName();
                    String deviceHardwareAddress = device.getAddress(); // MAC address
                    try {
                        Method m = device.getClass().getMethod("isConnected", (Class[]) null);
                        is_connected = (boolean) m.invoke(device, (Object[]) null);
                    } catch (Exception e) {
                        is_connected = false;
                        Log.e("Getting connected state has failed.", e.getMessage());
                    }
                    if (deviceName.equals("Pebble 4D3F") && !is_connected){
                        try {
                            Method m = device.getClass()
                                    .getMethod("removeBond", (Class[]) null);
                            m.invoke(device, (Object[]) null);
                        } catch (Exception e) {
                            Log.e("Removing has failed.", e.getMessage());
                        }
                    }
                };
            };
        };
    };
