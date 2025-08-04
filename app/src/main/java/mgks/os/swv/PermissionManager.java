package mgks.os.swv;

/*
  Smart WebView v7
  https://github.com/mgks/Android-SmartWebView

  A modern, open-source WebView wrapper for building advanced hybrid Android apps.
  Native features, modular plugins, and full customisation—built for developers.

  - Documentation: https://docs.mgks.dev/smart-webview
  - Plugins: https://docs.mgks.dev/smart-webview/plugins
  - Discussions: https://github.com/mgks/Android-SmartWebView/discussions
  - Sponsor the Project: https://github.com/sponsors/mgks

  MIT License — https://opensource.org/licenses/MIT

  Mentioning Smart WebView in your project helps others find it and keeps the dev loop alive.
*/

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionManager {

    private static final String TAG = "PermissionManager";

    // --- Permission Request Codes ---
    public static final int INITIAL_REQUEST_CODE = 100;
    public static final int CAMERA_REQUEST_CODE = 101;
    public static final int STORAGE_REQUEST_CODE = 102;
    public static final int MICROPHONE_REQUEST_CODE = 103;

    private final Activity activity;

    public PermissionManager(Activity activity) {
        this.activity = activity;
    }

    /**
     * Checks all configured features in SmartWebView.java and requests the
     * required permissions in a single batch.
     * This should be called on app launch.
     */
    public void requestInitialPermissions() {
        List<String> permissionsToRequest = new ArrayList<>();

        // Iterate through the permission groups defined in SmartWebView config.
        for (String permissionGroup : SWVContext.ASWP_REQUIRED_PERMISSIONS) {
            switch (permissionGroup) {
                case "LOCATION":
                    if (!isLocationPermissionGranted()) {
                        permissionsToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION);
                    }
                    break;

                case "NOTIFICATIONS":
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !isNotificationPermissionGranted()) {
                        permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS);
                    }
                    break;

                case "STORAGE":
                    if (SWVContext.ASWP_FUPLOAD && !isStoragePermissionGranted()) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            permissionsToRequest.add(Manifest.permission.READ_MEDIA_IMAGES);
                        } else {
                            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                        }
                    }
                    break;

                case "MICROPHONE":
                    if (!isMicrophonePermissionGranted()) {
                        permissionsToRequest.add(Manifest.permission.RECORD_AUDIO);
                    }
                    break;
            }
        }

        // Request all needed permissions at once
        if (!permissionsToRequest.isEmpty()) {
            Log.d(TAG, "Requesting initial permissions: " + permissionsToRequest);
            ActivityCompat.requestPermissions(activity, permissionsToRequest.toArray(new String[0]), INITIAL_REQUEST_CODE);
        } else {
            Log.d(TAG, "All initial permissions are already granted.");
        }
    }

    /**
     * Request camera permissions when needed (includes storage if required).
     */
    public void requestCameraPermission() {
        if (!isCameraPermissionGranted()) {
            List<String> permissions = new ArrayList<>();
            permissions.add(Manifest.permission.CAMERA);
            if (!isStoragePermissionGranted()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    permissions.add(Manifest.permission.READ_MEDIA_IMAGES);
                } else {
                    permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                }
            }
            ActivityCompat.requestPermissions(activity, permissions.toArray(new String[0]), CAMERA_REQUEST_CODE);
        }
    }

    /**
     * Request microphone permission when needed.
     */
    public void requestMicrophonePermission() {
        if (!isMicrophonePermissionGranted()) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    MICROPHONE_REQUEST_CODE);
        }
    }

    // --- Helper methods to check permission status ---

    public boolean isLocationPermissionGranted() {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isNotificationPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
        }
        return true; // Not required before Android 13
    }

    public boolean isCameraPermissionGranted() {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED;
        }
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isMicrophonePermissionGranted() {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }
  }
