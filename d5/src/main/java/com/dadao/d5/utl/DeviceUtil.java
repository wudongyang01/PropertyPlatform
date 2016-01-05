package com.dadao.d5.utl;

import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

public class DeviceUtil {

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion(Context c) {
        try {
            PackageManager manager = c.getPackageManager();
            PackageInfo info = manager.getPackageInfo(c.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    public static String getDeviceId(Activity c) {
        final TelephonyManager tm = (TelephonyManager) c.getBaseContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = ""
                + android.provider.Settings.Secure.getString(
                c.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(),
                ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString();
    }

    public static String getDeviceId2(Activity c) {

        final TelephonyManager tm = (TelephonyManager) c.getBaseContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }
}
