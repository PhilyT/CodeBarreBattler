package com.example.mbds.barcodebattler;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


/**
 * Created by Tom on 08/02/2018.
 */

public class ConnectedThread {
    private static final String TAG = "MY_APP_DEBUG_TAG";
    private OutputStream mmOutStream;

    private BluetoothSocket mmSocket;

    public ConnectedThread(BluetoothDevice device) {
        // Use a temporary object that is later assigned to mmSocket
        // because mmSocket is final.
        BluetoothSocket tmp = null;
        OutputStream tmpOut = null;

        try {
            // Get a BluetoothSocket to connect with the given BluetoothDevice.
            // MY_UUID is the app's UUID string, also used in the server code.
            tmp = device.createRfcommSocketToServiceRecord(UUID.fromString("2,5"));
            tmpOut = tmp.getOutputStream();
        } catch (IOException e) {
            Log.e(TAG, "Socket's create() method failed", e);
        }
        mmOutStream = tmpOut;
        mmSocket = tmp;
    }

    public void send(Intent data) {
        byte[] mmbyte = data.getByteArrayExtra("Creature");
        try {
            mmOutStream.write(mmbyte);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Closes the client socket and causes the thread to finish.
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the client socket", e);
        }
    }
}
