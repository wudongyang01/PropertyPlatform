package net.meluo.propertyplatform.app;

import android.app.Application;
import android.graphics.Typeface;
import android.os.Environment;
import android.view.View;

import com.koushikdutta.ion.Ion;
import com.way.util.CrashHandler;
import com.way.util.L;
import com.way.util.PreferenceUtils;

import net.meluo.propertyplatform.bean.User;
import net.meluo.propertyplatform.core.PreferenceConstants;

import java.io.File;

public class DDApp extends Application {

    private static DDApp mApplication;

    private Typeface typeFace;
    private View targetView;

    public User user = null;

    public synchronized static DDApp getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        L.isDebug = PreferenceUtils.getPrefBoolean(this,
                PreferenceConstants.ISNEEDLOG, true);
        if (PreferenceUtils.getPrefBoolean(this,
                PreferenceConstants.REPORT_CRASH, true))
            CrashHandler.getInstance().init(this);

        Ion.getDefault(this).proxy("192.168.1.160", 51003);

        initData();

    }

    private void initData() {
        File dir = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator + "_pp");
        if (!dir.exists())
            dir.mkdir();
        File voiceDir = new File(dir, "voice");
        if (!voiceDir.exists())
            voiceDir.mkdir();
        File crashDir = new File(dir, "crash");
        if (!crashDir.exists())
            crashDir.mkdir();

        // 初始化数据库
        // if (User.isFirst(this)) {
        // ExcelUtl.E2D(this);
        // User.setFirst(this, false);
        // }

    }

    public View getTargetView() {
        return targetView;
    }

    public void setTargetView(View targetView) {
        this.targetView = targetView;
    }

    public Typeface getTypeFace() {
        return typeFace;
    }

}