package com.example.connectivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int BLUETOOTH_PERMISSION_REQUEST_CODE = 1;
    private static final int BLUETOOTH_ENABLE_REQUEST_CODE = 2;

    Switch wifiiii, bluetooot;
    WifiManager wifiManager;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiiii = findViewById(R.id.switch1);
        bluetooot = findViewById(R.id.switch3);

        // Set initial state for Wi-Fi
        if (wifiManager != null) {
            wifiiii.setChecked(wifiManager.isWifiEnabled());
        }

        // Set initial state for Bluetooth
        if (bluetoothAdapter != null) {
            bluetooot.setChecked(bluetoothAdapter.isEnabled());
        }

        // --- Set OnCheckedChangeListener for WIFI ---
        wifiiii.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (wifiManager == null) {
                Toast.makeText(this, "Wi-Fi not supported on this device.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isChecked) {
                wifiManager.setWifiEnabled(true);
                Toast.makeText(this, "WIFI ON", Toast.LENGTH_SHORT).show();
            } else {
                wifiManager.setWifiEnabled(false);
                Toast.makeText(this, "WIFI Off", Toast.LENGTH_SHORT).show();
            }
        });

        // --- Set OnCheckedChangeListener for BLUETOOTH ---
        bluetooot.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (bluetoothAdapter == null) {
                Toast.makeText(getApplicationContext(), "Bluetooth not supported on this device.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isChecked) {
                // Check for BLUETOOTH_CONNECT permission on Android 12+
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                        ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                    // Request the permission from the user
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, BLUETOOTH_PERMISSION_REQUEST_CODE);

                } else {
                    // Permission is already granted or not needed (on older versions)
                    if (!bluetoothAdapter.isEnabled()) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, BLUETOOTH_ENABLE_REQUEST_CODE);
                    }
                }
            } else {
                // Disable Bluetooth
                bluetoothAdapter.disable();
                Toast.makeText(getApplicationContext(), "Bluetooth Turned Off", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // This method handles the result of the runtime permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == BLUETOOTH_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, now attempt to enable Bluetooth
                if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, BLUETOOTH_ENABLE_REQUEST_CODE);
                }
            } else {
                // Permission denied, inform the user
                Toast.makeText(this, "Bluetooth permission is required to enable Bluetooth.", Toast.LENGTH_SHORT).show();
                bluetooot.setChecked(false); // Reset the switch to reflect the denial
            }
        }
    }

    // This method handles the result of the Bluetooth enable intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BLUETOOTH_ENABLE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Bluetooth was successfully enabled
                Toast.makeText(this, "Bluetooth is ON", Toast.LENGTH_SHORT).show();
            } else {
                // User denied enabling Bluetooth
                Toast.makeText(this, "Failed to turn on Bluetooth.", Toast.LENGTH_SHORT).show();
                bluetooot.setChecked(false); // Reset switch state
            }
        }
    }
}