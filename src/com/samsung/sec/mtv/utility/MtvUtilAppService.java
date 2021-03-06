// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.app.ActivityManager;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.PowerManager;
import android.util.Log;
import android.view.*;
import java.util.List;

// Referenced classes of package com.samsung.sec.mtv.utility:
//            MtvUtilTvOut

public class MtvUtilAppService
{

    private MtvUtilAppService()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;-><init>()V");
        super();
    }

    public static boolean forceresetMtvVisibiltySettings()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->forceresetMtvVisibiltySettings()Z");
        MtvUtilTvOut.resumeTvOut();
        return true;
    }

    public static int getCurrentOrientation(Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->getCurrentOrientation(Landroid/content/Context;)I");
        int i = ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getRotation();
        int j;
        if(i == 1 || i == 3)
        {
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvUtilAppService", "Lanscape...");
            j = 1;
        } else
        {
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvUtilAppService", "Portrait...");
            j = 0;
        }
        return j;
    }

    public static int getRotation(Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->getRotation(Landroid/content/Context;)I");
        return ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    public static boolean isAbnormalExit()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->isAbnormalExit()Z");
        return isAbnormalExit;
    }

    public static boolean isAppExiting()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->isAppExiting()Z");
        return isAppExiting;
    }

    public static boolean isIntentAvailable(Context context, Intent intent)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->isIntentAvailable(Landroid/content/Context;Landroid/content/Intent;)Z");
        boolean flag;
        if(context.getPackageManager().queryIntentActivities(intent, 0x10000).size() > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isScreenOn(Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->isScreenOn(Landroid/content/Context;)Z");
        return ((PowerManager)context.getSystemService("power")).isScreenOn();
    }

    public static void releaseCPUPartialWakeLock()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->releaseCPUPartialWakeLock()V");
        if(mCpuWakeLock != null && mCpuWakeLock.isHeld())
            mCpuWakeLock.release();
    }

    public static boolean resetMtvVisibiltySettings(Context context)
    {
        String s;
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->resetMtvVisibiltySettings(Landroid/content/Context;)Z");
        s = null;
        ActivityManager activitymanager = (ActivityManager)context.getSystemService("activity");
        if(activitymanager != null)
            s = ((android.app.ActivityManager.RunningTaskInfo)activitymanager.getRunningTasks(1).get(0)).topActivity.getClassName();
        if(s == null) goto _L2; else goto _L1
_L1:
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUtilAppService", (new StringBuilder()).append("resetMtvVisibiltySettings() :topActivityName = ").append(s).toString());
        if(s.contains("com.samsung.sec.mtv")) goto _L2; else goto _L3
_L3:
        boolean flag;
        MtvUtilDebug.Low("MtvUtilAppService", "resetMtvVisibiltySettings: Not Top Activity.. resetting ");
        MtvUtilTvOut.resumeTvOut();
        flag = true;
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static void setAbnormalExit(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->setAbnormalExit(Z)V");
        isAbnormalExit = flag;
    }

    public static void setAppExiting(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->setAppExiting(Z)V");
        isAppExiting = flag;
    }

    public static boolean setMtvVisibiltySettings(Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->setMtvVisibiltySettings(Landroid/content/Context;)Z");
        MtvUtilDebug.Low("MtvUtilAppService", "setMtvVisibiltySettings()");
        MtvUtilTvOut.suspendTvOut();
        return true;
    }

    public static void unbindDrawables(View view)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;->unbindDrawables(Landroid/view/View;)V");
        if(view != null && view.getBackground() != null)
            view.getBackground().setCallback(null);
        if(view instanceof ViewGroup)
        {
            for(int i = 0; i < ((ViewGroup)view).getChildCount(); i++)
                unbindDrawables(((ViewGroup)view).getChildAt(i));

            ((ViewGroup)view).removeAllViews();
        }
    }

    private static boolean isAbnormalExit = false;
    private static boolean isAppExiting = false;
    private static android.os.PowerManager.WakeLock mCpuWakeLock;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilAppService;-><clinit>()V");
    }
}
