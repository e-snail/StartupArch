package roff.startuparch.util;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by wuyongbo on 16-6-8.
 */
public final class DeviceInfo {

    public static DeviceInfo instance = new DeviceInfo();

    public static DeviceInfo getInstance() {
        return instance;
    }

    private Application applicationContext;
    private TelephonyManager mTelephonyManager;

    public void initialization(Application applicationContext) {
        this.applicationContext = applicationContext;

        mTelephonyManager = (TelephonyManager) applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public String getIMEI() {
        String myIMEI = "00000000";
        try {
            myIMEI = mTelephonyManager.getDeviceId();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return myIMEI;
    }
}
