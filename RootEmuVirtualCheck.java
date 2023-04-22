import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class AppCheck {

    private static final String[] KNOWN_VIRTUAL_DEVICE_MANUFACTURERS = {"Genymotion", "VirtualBox", "VMware"};
    private static final String[] KNOWN_VIRTUAL_DEVICE_MODELS = {"Emulator", "Android SDK built for x86"};

    public static boolean isRunningOnEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    public static boolean isRunningOnVirtualSpace() {
        String[] paths = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su",
                "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su",
                "/data/local/su", "/su/bin/su"};
        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDeviceRooted() {
        String buildTags = Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) {
                return true;
            }
        } catch (Exception e) {
            // do nothing
        }
        return false;
    }

    public static boolean isRunningOnVirtualDevice() {
        for (String manufacturer : KNOWN_VIRTUAL_DEVICE_MANUFACTURERS) {
            if (Build.MANUFACTURER.contains(manufacturer)) {
                return true;
            }
        }
        for (String model : KNOWN_VIRTUAL_DEVICE_MODELS) {
            if (Build.MODEL.contains(model)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        if (isRunningOnEmulator() || isRunningOnVirtualSpace() || isDeviceRooted() || isRunningOnVirtualDevice()) {
            System.exit(0);
        }
        // continue with the app
    }
}
