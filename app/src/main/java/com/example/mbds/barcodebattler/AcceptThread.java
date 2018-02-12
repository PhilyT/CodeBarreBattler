package com.example.mbds.barcodebattler;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by Tom on 08/02/2018.
 */

public abstract class AcceptThread extends Thread {
    private static final String TAG = "MY_APP_DEBUG_TAG";
    private BluetoothServerSocket mmServerSocket;

    public AcceptThread(BluetoothAdapter mBluetoothAdapter) {
        // Use a temporary object that is later assigned to mmServerSocket
        // because mmServerSocket is final.
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code.
            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("MY_APP", UUID.fromString("2,5"));

        } catch (IOException e) {
            Log.e(TAG, "Socket's listen() method failed", e);
        }
        mmServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned.

        InputStream tmpIn = null;
        while (true) {
            try {
                socket = mmServerSocket.accept();
                tmpIn = socket.getInputStream();

                if (socket != null) {
                    byte[] mmBuffer = new byte[1024];
                    int numBytes; // bytes returned from read()
                    numBytes = tmpIn.read(mmBuffer);
                }
            } catch (IOException e) {
                Log.e(TAG, "Socket's accept() method failed", e);
                break;
            }
        }
    }


    public abstract void onReception(Intent data);

    // Closes the connect socket and causes the thread to finish.
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the connect socket", e);
        }
    }
}
