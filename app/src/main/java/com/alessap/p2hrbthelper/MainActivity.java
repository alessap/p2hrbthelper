package com.alessap.p2hrbthelper;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothAdapter;

import java.lang.reflect.Method;
import java.util.Set;

class MyGetBTDevices implements View.OnClickListener {

    // TODO alessap: How to enable a background process?
    // TODO alessap: allow user to define which device to disconnect

    @Override
    public void onClick(View view) {
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

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Get BT devices", new MyGetBTDevices()).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
