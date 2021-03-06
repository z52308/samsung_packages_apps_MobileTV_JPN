// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.bml;

import android.broadcast.IMtvOneSegBmlViewControl;
import android.broadcast.IMtvOneSegBmlViewListener;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import com.access.bml.BMLNativeView;
import com.samsung.sec.mtv.app.context.MtvAppPlaybackContext;
import com.samsung.sec.mtv.app.context.MtvAppProgramComponents;
import com.samsung.sec.mtv.utility.MtvPreferences;
import com.samsung.sec.mtv.utility.MtvUtilMemoryStatus;
import java.io.*;
import java.util.GregorianCalendar;

// Referenced classes of package com.samsung.sec.mtv.app.bml:
//            IMtvAppBmlDialogListener, IMtvAppBmlSurfaceListener, IMtvAppBmlAnimationListener

public class MtvAppBml
    implements IMtvOneSegBmlViewListener
{

    private MtvAppBml(Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;-><init>(Landroid/content/Context;)V");
        super();
        mMtvAppPlaybackContext = null;
        mBmlViewControl = null;
        mPreferences = null;
        mBmlSurfaceListener = null;
        mBmlDialogListener = null;
        mBmlAnimationListener = null;
        mbmlCntrlType = MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_BASIC;
        isBMLDialogMsgSet = false;
        mCuurUIEvt = MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_NONE;
        mBmlUIMsgHandler = new Handler() {

            public void handleMessage(Message message)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml$1;->handleMessage(Landroid/os/Message;)V");
                MtvUtilDebug.Mid("MtvAppBml", (new StringBuilder()).append("handleMessage : ").append(message.what).toString());
                static class _cls2
                {

                    static final int $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[];
                    static final int $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlUIEvents[];

                    static 
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml$2;-><clinit>()V");
                        $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages = new int[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.values().length];
                        NoSuchFieldError nosuchfielderror75;
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_SETLOCATION_HTTP.ordinal()] = 1;
                        }
                        catch(NoSuchFieldError nosuchfielderror) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_SETLOCATION_SSL_BEGIN.ordinal()] = 2;
                        }
                        catch(NoSuchFieldError nosuchfielderror1) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_SETLOCATION_SSL_END.ordinal()] = 3;
                        }
                        catch(NoSuchFieldError nosuchfielderror2) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_SERVER_CERT_CONFIRM_DIALOG.ordinal()] = 4;
                        }
                        catch(NoSuchFieldError nosuchfielderror3) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_SERVER_CERT_CONFIRM_DIALOG_QEUSTION.ordinal()] = 5;
                        }
                        catch(NoSuchFieldError nosuchfielderror4) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_ERROR_NOMEMORY.ordinal()] = 6;
                        }
                        catch(NoSuchFieldError nosuchfielderror5) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_ERROR_MIXEDSECURETYPE.ordinal()] = 7;
                        }
                        catch(NoSuchFieldError nosuchfielderror6) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_ERROR_BADURL.ordinal()] = 8;
                        }
                        catch(NoSuchFieldError nosuchfielderror7) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_ERROR_BADMIMETYPE.ordinal()] = 9;
                        }
                        catch(NoSuchFieldError nosuchfielderror8) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_ERROR_TOTAL_SIZEOVER.ordinal()] = 10;
                        }
                        catch(NoSuchFieldError nosuchfielderror9) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_TRANSMITTEXTDATA.ordinal()] = 11;
                        }
                        catch(NoSuchFieldError nosuchfielderror10) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_transmitTextDataOverIP.ordinal()] = 12;
                        }
                        catch(NoSuchFieldError nosuchfielderror11) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_writePersistentArray.ordinal()] = 13;
                        }
                        catch(NoSuchFieldError nosuchfielderror12) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_saveImageToMemoryCard.ordinal()] = 14;
                        }
                        catch(NoSuchFieldError nosuchfielderror13) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_saveHttpServerImageToMemoryCard.ordinal()] = 15;
                        }
                        catch(NoSuchFieldError nosuchfielderror14) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_X_DPA_writeCproBM.ordinal()] = 16;
                        }
                        catch(NoSuchFieldError nosuchfielderror15) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_X_DPA_mailTo.ordinal()] = 17;
                        }
                        catch(NoSuchFieldError nosuchfielderror16) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_X_DPA_startResidentApp.ordinal()] = 18;
                        }
                        catch(NoSuchFieldError nosuchfielderror17) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_X_DPA_phoneTo.ordinal()] = 19;
                        }
                        catch(NoSuchFieldError nosuchfielderror18) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_X_DPA_writeSchInfo.ordinal()] = 20;
                        }
                        catch(NoSuchFieldError nosuchfielderror19) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_X_DPA_writeAddressBookInfo.ordinal()] = 21;
                        }
                        catch(NoSuchFieldError nosuchfielderror20) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_X_DPA_getCurPos.ordinal()] = 22;
                        }
                        catch(NoSuchFieldError nosuchfielderror21) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_X_DPA_getIRDID.ordinal()] = 23;
                        }
                        catch(NoSuchFieldError nosuchfielderror22) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_PERMITFUNCTION_X_DPA_getRcvCond.ordinal()] = 24;
                        }
                        catch(NoSuchFieldError nosuchfielderror23) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_WRITEBOOKMARK.ordinal()] = 25;
                        }
                        catch(NoSuchFieldError nosuchfielderror24) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_WRITEBOOKMARK_REPLACE_OLDEST.ordinal()] = 26;
                        }
                        catch(NoSuchFieldError nosuchfielderror25) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_WRITEPERSISTENTARRAY.ordinal()] = 27;
                        }
                        catch(NoSuchFieldError nosuchfielderror26) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_DTV_FAILUREACTION_OUTOFBASEURIDIRECTORY.ordinal()] = 28;
                        }
                        catch(NoSuchFieldError nosuchfielderror27) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_DTV_FAILUREACTION_ILLEGALFUNCTION.ordinal()] = 29;
                        }
                        catch(NoSuchFieldError nosuchfielderror28) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_DTV_FAILUREACTION_INVALIDSUFFIX.ordinal()] = 30;
                        }
                        catch(NoSuchFieldError nosuchfielderror29) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_DTV_DRAWERROR_TOOMANY_NESTS.ordinal()] = 31;
                        }
                        catch(NoSuchFieldError nosuchfielderror30) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_DTV_DRAWERROR_TIMEDOUT.ordinal()] = 32;
                        }
                        catch(NoSuchFieldError nosuchfielderror31) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_ARIBDC_OPEN.ordinal()] = 33;
                        }
                        catch(NoSuchFieldError nosuchfielderror32) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_ARIBDC_READ.ordinal()] = 34;
                        }
                        catch(NoSuchFieldError nosuchfielderror33) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_ARIBDC_TIMEDOUT.ordinal()] = 35;
                        }
                        catch(NoSuchFieldError nosuchfielderror34) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_AUTH_FORMAT.ordinal()] = 36;
                        }
                        catch(NoSuchFieldError nosuchfielderror35) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_AUTH_UNKNOWN.ordinal()] = 37;
                        }
                        catch(NoSuchFieldError nosuchfielderror36) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_BROADCAST_TRANSITON.ordinal()] = 38;
                        }
                        catch(NoSuchFieldError nosuchfielderror37) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_CACHE_EXPIRE.ordinal()] = 39;
                        }
                        catch(NoSuchFieldError nosuchfielderror38) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_CACHE_NONE.ordinal()] = 40;
                        }
                        catch(NoSuchFieldError nosuchfielderror39) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_CROSSMEDIA.ordinal()] = 41;
                        }
                        catch(NoSuchFieldError nosuchfielderror40) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_DNS_INVAL.ordinal()] = 42;
                        }
                        catch(NoSuchFieldError nosuchfielderror41) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_DNS_INVAL_TVCALL.ordinal()] = 43;
                        }
                        catch(NoSuchFieldError nosuchfielderror42) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_DNS_NOSERVER.ordinal()] = 44;
                        }
                        catch(NoSuchFieldError nosuchfielderror43) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_DNS_NOTFOUND.ordinal()] = 45;
                        }
                        catch(NoSuchFieldError nosuchfielderror44) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_DNS_TIMEOUT.ordinal()] = 46;
                        }
                        catch(NoSuchFieldError nosuchfielderror45) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_DOMAIN.ordinal()] = 47;
                        }
                        catch(NoSuchFieldError nosuchfielderror46) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_FILE_OPEN.ordinal()] = 48;
                        }
                        catch(NoSuchFieldError nosuchfielderror47) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_FILE_READ.ordinal()] = 49;
                        }
                        catch(NoSuchFieldError nosuchfielderror48) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_GENERIC.ordinal()] = 50;
                        }
                        catch(NoSuchFieldError nosuchfielderror49) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_NO_CONTENT.ordinal()] = 51;
                        }
                        catch(NoSuchFieldError nosuchfielderror50) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_REDIRECT_CANCELED.ordinal()] = 52;
                        }
                        catch(NoSuchFieldError nosuchfielderror51) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_REDIRECT_FORMAT.ordinal()] = 53;
                        }
                        catch(NoSuchFieldError nosuchfielderror52) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_REQ_BODY_SIZEOVER.ordinal()] = 54;
                        }
                        catch(NoSuchFieldError nosuchfielderror53) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_REQ_HEADER_SIZEOVER.ordinal()] = 55;
                        }
                        catch(NoSuchFieldError nosuchfielderror54) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_REQTIMEOUT.ordinal()] = 56;
                        }
                        catch(NoSuchFieldError nosuchfielderror55) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_RESPTIMEOUT.ordinal()] = 57;
                        }
                        catch(NoSuchFieldError nosuchfielderror56) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_SSL.ordinal()] = 58;
                        }
                        catch(NoSuchFieldError nosuchfielderror57) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_TCP.ordinal()] = 59;
                        }
                        catch(NoSuchFieldError nosuchfielderror58) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_TOO_MANY_REDIRECT.ordinal()] = 60;
                        }
                        catch(NoSuchFieldError nosuchfielderror59) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_TOOMANYAUTH.ordinal()] = 61;
                        }
                        catch(NoSuchFieldError nosuchfielderror60) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_HTTP_E_AUTH_NOHEADER.ordinal()] = 62;
                        }
                        catch(NoSuchFieldError nosuchfielderror61) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_HTTP_E_REQ_TOOMANYCONTINUE.ordinal()] = 63;
                        }
                        catch(NoSuchFieldError nosuchfielderror62) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_NOTIFY_CONTENT_ERROR_UNKNOWN.ordinal()] = 64;
                        }
                        catch(NoSuchFieldError nosuchfielderror63) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_SYSTEMCONTINUE.ordinal()] = 65;
                        }
                        catch(NoSuchFieldError nosuchfielderror64) { }
                        try
                        {
                            $SwitchMap$android$broadcast$oneseg$MtvOneSegBmlParams$DialogMessages[android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages.BML_QUERY_DTV_WRITEBOOKMARK_REPLACE_EXPIRED.ordinal()] = 66;
                        }
                        catch(NoSuchFieldError nosuchfielderror65) { }
                        $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlUIEvents = new int[MtvAppBmlConstants.AppBmlUIEvents.values().length];
                        try
                        {
                            $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlUIEvents[MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_SET_ANIMATION_TEXT.ordinal()] = 1;
                        }
                        catch(NoSuchFieldError nosuchfielderror66) { }
                        try
                        {
                            $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlUIEvents[MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_START_ANIMATION.ordinal()] = 2;
                        }
                        catch(NoSuchFieldError nosuchfielderror67) { }
                        try
                        {
                            $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlUIEvents[MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_STOP_ANIMATION.ordinal()] = 3;
                        }
                        catch(NoSuchFieldError nosuchfielderror68) { }
                        try
                        {
                            $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlUIEvents[MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_SET_DIALOG_BTN_NUM.ordinal()] = 4;
                        }
                        catch(NoSuchFieldError nosuchfielderror69) { }
                        try
                        {
                            $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlUIEvents[MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_DESTROY_DIALOGUE.ordinal()] = 5;
                        }
                        catch(NoSuchFieldError nosuchfielderror70) { }
                        try
                        {
                            $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlUIEvents[MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_SHOW_DIALOGUE.ordinal()] = 6;
                        }
                        catch(NoSuchFieldError nosuchfielderror71) { }
                        try
                        {
                            $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlUIEvents[MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_SET_DIALOGUE_MSG.ordinal()] = 7;
                        }
                        catch(NoSuchFieldError nosuchfielderror72) { }
                        try
                        {
                            $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlUIEvents[MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_START_IME_INPUT_METHOD.ordinal()] = 8;
                        }
                        catch(NoSuchFieldError nosuchfielderror73) { }
                        try
                        {
                            $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlUIEvents[MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_START_TVLINK_TAB.ordinal()] = 9;
                        }
                        catch(NoSuchFieldError nosuchfielderror74) { }
                        $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlUIEvents[MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_KEYPAD_CHANGED.ordinal()] = 10;
_L2:
                        return;
                        nosuchfielderror75;
                        if(true) goto _L2; else goto _L1
_L1:
                    }
                }

                _cls2..SwitchMap.com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlUIEvents[uiEvents[message.what].ordinal()];
                JVM INSTR tableswitch 1 10: default 104
            //                           1 105
            //                           2 148
            //                           3 184
            //                           4 220
            //                           5 260
            //                           6 296
            //                           7 337
            //                           8 390
            //                           9 460
            //                           10 496;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L1:
                return;
_L2:
                if(Log.d(MtvAppBml.this) != null)
                {
                    Log.d(MtvAppBml.this, MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_SET_ANIMATION_TEXT);
                    Log.d(MtvAppBml.this).setBmlAnimationText((MtvAppBmlConstants.BmlAppAnimMessages)message.obj);
                }
                continue; /* Loop/switch isn't completed */
_L3:
                if(Log.d(MtvAppBml.this) != null)
                {
                    Log.d(MtvAppBml.this, MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_START_ANIMATION);
                    Log.d(MtvAppBml.this).startBmlAnimation();
                }
                continue; /* Loop/switch isn't completed */
_L4:
                if(Log.d(MtvAppBml.this) != null)
                {
                    Log.d(MtvAppBml.this, MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_STOP_ANIMATION);
                    Log.d(MtvAppBml.this).stopBmlAnimation();
                }
                continue; /* Loop/switch isn't completed */
_L5:
                if(Log.d(MtvAppBml.this) != null)
                {
                    Log.d(MtvAppBml.this, MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_SET_DIALOG_BTN_NUM);
                    Log.d(MtvAppBml.this).setDialogBtnNum(message.arg1);
                }
                continue; /* Loop/switch isn't completed */
_L6:
                if(Log.d(MtvAppBml.this) != null)
                {
                    Log.d(MtvAppBml.this, MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_DESTROY_DIALOGUE);
                    Log.d(MtvAppBml.this).destroyBMLDialog();
                }
                continue; /* Loop/switch isn't completed */
_L7:
                if(Log.d(MtvAppBml.this) != null)
                {
                    Log.d(MtvAppBml.this, MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_SHOW_DIALOGUE);
                    Log.d(MtvAppBml.this).showBMLDialog(message.arg1);
                }
                continue; /* Loop/switch isn't completed */
_L8:
                if(Log.d(MtvAppBml.this) != null && message.obj != MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_SRTING_NONE)
                {
                    Log.d(MtvAppBml.this, MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_SET_DIALOGUE_MSG);
                    Log.d(MtvAppBml.this).setDialogMessege((MtvAppBmlConstants.AppBmlDialogMessages)message.obj);
                }
                continue; /* Loop/switch isn't completed */
_L9:
                MtvAppBmlConstants.AppBmlIMEInputParams appbmlimeinputparams = (MtvAppBmlConstants.AppBmlIMEInputParams)message.obj;
                if(Log.d(MtvAppBml.this) != null)
                {
                    Log.d(MtvAppBml.this, MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_START_IME_INPUT_METHOD);
                    Log.d(MtvAppBml.this).IMEStartPeer(appbmlimeinputparams.text, appbmlimeinputparams.isPassword, appbmlimeinputparams.isMultiLine, appbmlimeinputparams.mode, appbmlimeinputparams.maxlength);
                }
                continue; /* Loop/switch isn't completed */
_L10:
                if(Log.d(MtvAppBml.this) != null)
                {
                    Log.d(MtvAppBml.this, MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_START_TVLINK_TAB);
                    Log.d(MtvAppBml.this).startTvLinkTab();
                }
                continue; /* Loop/switch isn't completed */
_L11:
                if(Log.d(MtvAppBml.this) != null)
                {
                    Log.d(MtvAppBml.this, MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_KEYPAD_CHANGED);
                    Log.d(MtvAppBml.this).bmlControlTypeChanged();
                }
                if(true) goto _L1; else goto _L12
_L12:
            }

            final MtvAppBml this$0;
            MtvAppBmlConstants.AppBmlUIEvents uiEvents[];

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml$1;-><init>(Lcom/samsung/sec/mtv/app/bml/MtvAppBml;)V");
                this$0 = MtvAppBml.this;
                super();
                uiEvents = MtvAppBmlConstants.AppBmlUIEvents.values();
            }
        };
        mContext = context;
    }

    public static String byteArrayToString(byte abyte0[])
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->byteArrayToString([B)Ljava/lang/String;");
        String s;
        try
        {
            s = new String(abyte0, "UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            MtvUtilDebug.Low("MtvAppBml", "UnsupportedEncodingException");
            s = "";
        }
        return s;
    }

    public static MtvAppBml getInstance(Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->getInstance(Landroid/content/Context;)Lcom/samsung/sec/mtv/app/bml/MtvAppBml;");
        if(mMtvAppBml == null)
            mMtvAppBml = new MtvAppBml(context);
        mContext = context;
        return mMtvAppBml;
    }

    public void appExIMEEndPeer(boolean flag, byte abyte0[])
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->appExIMEEndPeer(Z[B)V");
        if(mBmlViewControl != null)
            mBmlViewControl.appExIMEEndPeer(flag, abyte0);
    }

    public void cb_appIMEStartPeer(byte abyte0[], boolean flag, boolean flag1, int i, int j)
    {
        int k;
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_appIMEStartPeer([BZZII)V");
        k = 0;
        i;
        JVM INSTR tableswitch 0 12: default 80
    //                   0 156
    //                   1 80
    //                   2 80
    //                   3 80
    //                   4 80
    //                   5 162
    //                   6 168
    //                   7 175
    //                   8 182
    //                   9 189
    //                   10 196
    //                   11 203
    //                   12 210;
           goto _L1 _L2 _L1 _L1 _L1 _L1 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        MtvAppBmlConstants.AppBmlIMEInputParams appbmlimeinputparams = new MtvAppBmlConstants.AppBmlIMEInputParams();
        appbmlimeinputparams.isMultiLine = flag1;
        appbmlimeinputparams.isPassword = flag;
        appbmlimeinputparams.maxlength = j;
        appbmlimeinputparams.mode = k;
        appbmlimeinputparams.text = abyte0;
        MtvAppBmlConstants.AppBmlUIEvents appbmluievents = MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_START_IME_INPUT_METHOD;
        if(mBmlUIMsgHandler != null)
            mBmlUIMsgHandler.sendMessage(mBmlUIMsgHandler.obtainMessage(appbmluievents.ordinal(), appbmlimeinputparams));
        return;
_L2:
        k = 0;
        continue; /* Loop/switch isn't completed */
_L3:
        k = 5;
        continue; /* Loop/switch isn't completed */
_L4:
        k = 6;
        continue; /* Loop/switch isn't completed */
_L5:
        k = 7;
        continue; /* Loop/switch isn't completed */
_L6:
        k = 8;
        continue; /* Loop/switch isn't completed */
_L7:
        k = 9;
        continue; /* Loop/switch isn't completed */
_L8:
        k = 10;
        continue; /* Loop/switch isn't completed */
_L9:
        k = 11;
        continue; /* Loop/switch isn't completed */
_L10:
        k = 12;
        if(true) goto _L1; else goto _L11
_L11:
    }

    public void cb_bmlControlTypeChanged()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_bmlControlTypeChanged()V");
        MtvAppBmlConstants.AppBmlUIEvents appbmluievents = MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_KEYPAD_CHANGED;
        if(mBmlUIMsgHandler != null)
            mBmlUIMsgHandler.sendMessage(mBmlUIMsgHandler.obtainMessage(appbmluievents.ordinal()));
    }

    public void cb_bmlHaltExecuted()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_bmlHaltExecuted()V");
        if(mBmlDialogListener != null)
            mBmlDialogListener.halt();
    }

    public void cb_destroyBMLDialog()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_destroyBMLDialog()V");
        MtvAppBmlConstants.AppBmlUIEvents appbmluievents = MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_DESTROY_DIALOGUE;
        if(mBmlUIMsgHandler != null)
            mBmlUIMsgHandler.sendMessage(mBmlUIMsgHandler.obtainMessage(appbmluievents.ordinal()));
    }

    public void cb_mailToPeer(byte abyte0[], byte abyte1[], byte abyte2[])
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_mailToPeer([B[B[B)V");
        String s = byteArrayToString(abyte0);
        String s1 = byteArrayToString(abyte1);
        String s2 = byteArrayToString(abyte2);
        String as[] = new String[1];
        as[0] = s2;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.EMAIL", as);
        intent.putExtra("android.intent.extra.SUBJECT", s);
        intent.putExtra("android.intent.extra.TEXT", s1);
        mContext.startActivity(Intent.createChooser(intent, "Send mail..."));
    }

    public void cb_phoneToPeer(String s)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_phoneToPeer(Ljava/lang/String;)V");
        MtvUtilDebug.Mid("MtvAppBml", "BML_CB_XDPA_PhoneToPeer:");
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse((new StringBuilder()).append("tel:").append(s).toString()));
        mContext.startActivity(intent);
    }

    public boolean cb_processCommand(int i, int j, String s)
    {
        boolean flag;
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_processCommand(IILjava/lang/String;)Z");
        flag = false;
        if(mBmlViewControl != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        i;
        JVM INSTR lookupswitch 2: default 48
    //                   0: 54
    //                   152: 85;
           goto _L3 _L4 _L5
_L3:
        flag = true;
          goto _L6
_L4:
        if(mPreferences.getBroadcastDataNotifyMode() == 1)
        {
            mBmlViewControl.setDailogNeed(false);
            mBmlViewControl.setReplyToEngine(true);
        } else
        if(mPreferences.getBroadcastDataNotifyMode() == 0 && mBmlViewControl.allowConnection(i))
        {
            mBmlViewControl.setDailogNeed(false);
            mBmlViewControl.setReplyToEngine(true);
        }
_L5:
        if(s.compareTo("X_DPA_getCurPos") == 0)
        {
            if(mPreferences.getBroadcastDataLocationMode() == 0)
            {
                if(mBmlViewControl.allowLocation(i))
                {
                    mBmlViewControl.setDailogNeed(false);
                    mBmlViewControl.setReplyToEngine(true);
                }
            } else
            if(mPreferences.getBroadcastDataLocationMode() == 1)
            {
                mBmlViewControl.setDailogNeed(false);
                mBmlViewControl.setReplyToEngine(true);
            }
        } else
        if(s.compareTo("X_DPA_getIRDID") == 0)
            if(mPreferences.getBroadcastDataManufactureMode() == 1)
            {
                mBmlViewControl.setDailogNeed(false);
                mBmlViewControl.setReplyToEngine(false);
            } else
            {
                mBmlViewControl.setDailogNeed(false);
                mBmlViewControl.setReplyToEngine(true);
            }
        if(true) goto _L3; else goto _L6
_L6:
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void cb_setBMLFullView()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_setBMLFullView()V");
        if(mBmlSurfaceListener != null)
            mBmlSurfaceListener.setBMLFullView(true);
    }

    public void cb_setBmlAnimationText(android.broadcast.oneseg.MtvOneSegBmlParams.AnimMessages animmessages)
    {
        MtvAppBmlConstants.BmlAppAnimMessages bmlappanimmessages;
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_setBmlAnimationText(Landroid/broadcast/oneseg/MtvOneSegBmlParams$AnimMessages;)V");
        bmlappanimmessages = MtvAppBmlConstants.BmlAppAnimMessages.MTV_APP_BML_ANIM_MSG_NONE;
        if(animmessages != android.broadcast.oneseg.MtvOneSegBmlParams.AnimMessages.BML_RECEIVING) goto _L2; else goto _L1
_L1:
        bmlappanimmessages = MtvAppBmlConstants.BmlAppAnimMessages.MTV_APP_BML_RECEIVING;
_L4:
        MtvAppBmlConstants.AppBmlUIEvents appbmluievents = MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_SET_ANIMATION_TEXT;
        if(mBmlUIMsgHandler != null)
            mBmlUIMsgHandler.sendMessage(mBmlUIMsgHandler.obtainMessage(appbmluievents.ordinal(), bmlappanimmessages));
        return;
_L2:
        if(animmessages == android.broadcast.oneseg.MtvOneSegBmlParams.AnimMessages.BML_RETREIVING)
            bmlappanimmessages = MtvAppBmlConstants.BmlAppAnimMessages.MTV_APP_BML_RETREIVING;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void cb_setDialogBtnNum(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_setDialogBtnNum(I)V");
        MtvAppBmlConstants.AppBmlUIEvents appbmluievents = MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_SET_DIALOG_BTN_NUM;
        if(mBmlUIMsgHandler != null)
            mBmlUIMsgHandler.sendMessage(mBmlUIMsgHandler.obtainMessage(appbmluievents.ordinal(), i, 0));
    }

    public void cb_setDialogMessege(android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages dialogmessages)
    {
        MtvAppBmlConstants.AppBmlDialogMessages appbmldialogmessages;
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_setDialogMessege(Landroid/broadcast/oneseg/MtvOneSegBmlParams$DialogMessages;)V");
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_SRTING_NONE;
        _cls2..SwitchMap.android.broadcast.oneseg.MtvOneSegBmlParams.DialogMessages[dialogmessages.ordinal()];
        JVM INSTR tableswitch 1 66: default 300
    //                   1 345
    //                   2 352
    //                   3 359
    //                   4 366
    //                   5 373
    //                   6 380
    //                   7 387
    //                   8 394
    //                   9 401
    //                   10 408
    //                   11 415
    //                   12 422
    //                   13 429
    //                   14 436
    //                   15 443
    //                   16 450
    //                   17 457
    //                   18 464
    //                   19 471
    //                   20 478
    //                   21 485
    //                   22 492
    //                   23 499
    //                   24 506
    //                   25 513
    //                   26 520
    //                   27 527
    //                   28 534
    //                   29 541
    //                   30 548
    //                   31 555
    //                   32 562
    //                   33 569
    //                   34 576
    //                   35 583
    //                   36 590
    //                   37 597
    //                   38 604
    //                   39 611
    //                   40 618
    //                   41 625
    //                   42 632
    //                   43 639
    //                   44 646
    //                   45 653
    //                   46 660
    //                   47 667
    //                   48 674
    //                   49 681
    //                   50 688
    //                   51 695
    //                   52 702
    //                   53 709
    //                   54 716
    //                   55 723
    //                   56 730
    //                   57 737
    //                   58 744
    //                   59 751
    //                   60 758
    //                   61 765
    //                   62 772
    //                   63 779
    //                   64 786
    //                   65 793
    //                   66 800;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67
_L1:
        break; /* Loop/switch isn't completed */
_L67:
        break MISSING_BLOCK_LABEL_800;
_L68:
        MtvAppBmlConstants.AppBmlUIEvents appbmluievents;
        if(mContext != null)
            isBMLDialogMsgSet = true;
        else
            isBMLDialogMsgSet = false;
        appbmluievents = MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_SET_DIALOGUE_MSG;
        if(mBmlUIMsgHandler != null)
            mBmlUIMsgHandler.sendMessage(mBmlUIMsgHandler.obtainMessage(appbmluievents.ordinal(), appbmldialogmessages));
        return;
_L2:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_SETLOCATION_HTTP;
          goto _L68
_L3:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_SETLOCATION_SSL_BEGIN;
          goto _L68
_L4:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_SETLOCATION_SSL_END;
          goto _L68
_L5:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_SERVER_CERT_CONFIRM_DIALOG;
          goto _L68
_L6:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_SERVER_CERT_CONFIRM_DIALOG_QEUSTION;
          goto _L68
_L7:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_ERROR_NOMEMORY;
          goto _L68
_L8:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_ERROR_MIXEDSECURETYPE;
          goto _L68
_L9:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_ERROR_BADURL;
          goto _L68
_L10:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_ERROR_BADMIMETYPE;
          goto _L68
_L11:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_ERROR_TOTAL_SIZEOVER;
          goto _L68
_L12:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_TRANSMITTEXTDATA;
          goto _L68
_L13:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_transmitTextDataOverIP;
          goto _L68
_L14:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_writePersistentArray;
          goto _L68
_L15:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_saveImageToMemoryCard;
          goto _L68
_L16:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_saveHttpServerImageToMemoryCard;
          goto _L68
_L17:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_writeCproBM;
          goto _L68
_L18:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_mailTo;
          goto _L68
_L19:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_startResidentApp;
          goto _L68
_L20:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_phoneTo;
          goto _L68
_L21:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_writeSchInfo;
          goto _L68
_L22:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_writeAddressBookInfo;
          goto _L68
_L23:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_getCurPos;
          goto _L68
_L24:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_getIRDID;
          goto _L68
_L25:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_getRcvCond;
          goto _L68
_L26:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_WRITEBOOKMARK;
          goto _L68
_L27:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_WRITEBOOKMARK_REPLACE_OLDEST;
          goto _L68
_L28:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_WRITEPERSISTENTARRAY;
          goto _L68
_L29:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_DTV_FAILUREACTION_OUTOFBASEURIDIRECTORY;
          goto _L68
_L30:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_DTV_FAILUREACTION_ILLEGALFUNCTION;
          goto _L68
_L31:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_DTV_FAILUREACTION_INVALIDSUFFIX;
          goto _L68
_L32:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_DTV_DRAWERROR_TOOMANY_NESTS;
          goto _L68
_L33:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_DTV_DRAWERROR_TIMEDOUT;
          goto _L68
_L34:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_ARIBDC_OPEN;
          goto _L68
_L35:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_ARIBDC_READ;
          goto _L68
_L36:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_ARIBDC_TIMEDOUT;
          goto _L68
_L37:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_AUTH_FORMAT;
          goto _L68
_L38:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_AUTH_UNKNOWN;
          goto _L68
_L39:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_BROADCAST_TRANSITON;
          goto _L68
_L40:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_CACHE_EXPIRE;
          goto _L68
_L41:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_CACHE_NONE;
          goto _L68
_L42:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_CROSSMEDIA;
          goto _L68
_L43:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_DNS_INVAL;
          goto _L68
_L44:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_DNS_INVAL_TVCALL;
          goto _L68
_L45:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_DNS_NOSERVER;
          goto _L68
_L46:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_DNS_NOTFOUND;
          goto _L68
_L47:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_DNS_TIMEOUT;
          goto _L68
_L48:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_DOMAIN;
          goto _L68
_L49:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_FILE_OPEN;
          goto _L68
_L50:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_FILE_READ;
          goto _L68
_L51:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_GENERIC;
          goto _L68
_L52:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_NO_CONTENT;
          goto _L68
_L53:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_REDIRECT_CANCELED;
          goto _L68
_L54:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_REDIRECT_FORMAT;
          goto _L68
_L55:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_REQ_BODY_SIZEOVER;
          goto _L68
_L56:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_REQ_HEADER_SIZEOVER;
          goto _L68
_L57:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_REQTIMEOUT;
          goto _L68
_L58:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_RESPTIMEOUT;
          goto _L68
_L59:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_SSL;
          goto _L68
_L60:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_TCP;
          goto _L68
_L61:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_TOO_MANY_REDIRECT;
          goto _L68
_L62:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_TOOMANYAUTH;
          goto _L68
_L63:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_HTTP_E_AUTH_NOHEADER;
          goto _L68
_L64:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_HTTP_E_REQ_TOOMANYCONTINUE;
          goto _L68
_L65:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_UNKNOWN;
          goto _L68
_L66:
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_SYSTEMCONTINUE;
          goto _L68
        appbmldialogmessages = MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_WRITEBOOKMARK_REPLACE_EXPIRED;
          goto _L68
    }

    public boolean cb_showBMLDialog(int i)
    {
        boolean flag;
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_showBMLDialog(I)Z");
        flag = false;
        if(mBmlViewControl.isDailogNeed() && mContext != null && mContext.getResources().getConfiguration().orientation != 2 && isBMLDialogMsgSet) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        MtvAppBmlConstants.AppBmlUIEvents appbmluievents = MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_SHOW_DIALOGUE;
        if(mBmlUIMsgHandler != null)
        {
            mBmlUIMsgHandler.sendMessage(mBmlUIMsgHandler.obtainMessage(appbmluievents.ordinal(), i, 0));
            isBMLDialogMsgSet = false;
            flag = true;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void cb_startBmlAnimation()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_startBmlAnimation()V");
        MtvAppBmlConstants.AppBmlUIEvents appbmluievents = MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_START_ANIMATION;
        if(mBmlUIMsgHandler != null)
            mBmlUIMsgHandler.sendMessage(mBmlUIMsgHandler.obtainMessage(appbmluievents.ordinal()));
    }

    public int cb_startResidentAppPeer(byte abyte0[], int i, byte abyte1[], String as[])
    {
        char c;
        String s;
        int k;
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_startResidentAppPeer([BI[B[Ljava/lang/String;)I");
        c = '\uFC16';
        s = byteArrayToString(abyte0);
        int j = as.length;
        if(!s.equals("ComBrowser"))
            break MISSING_BLOCK_LABEL_184;
        if(j < 3)
        {
            MtvUtilDebug.Mid("MtvAppBml", "XDPA>StartResidentAppPeer: Few ex_info !:");
            c = '\uFC15';
        } else
        {
label0:
            {
                k = Integer.parseInt(as[1]);
                if(k != android.broadcast.oneseg.MtvOneSegBmlParams.DTVBMLXDPABrowserType.BML_START_RESIDENT_APP_C_PROFILE_BROWSER.ordinal())
                    break label0;
                MtvUtilDebug.Mid("MtvAppBml", "BML_START_RESIDENT_APP_C_PROFILE_BROWSER is not supported !:");
            }
        }
_L1:
        return c;
        if(k != android.broadcast.oneseg.MtvOneSegBmlParams.DTVBMLXDPABrowserType.BML_START_RESIDENT_APP_FULL_BROWSER.ordinal())
            continue; /* Loop/switch isn't completed */
        MtvUtilDebug.Mid("MtvAppBml", "BML_START_RESIDENT_APP_FULL_BROWSER !:");
_L3:
        if(as != null)
            try
            {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(as[0]));
                mContext.startActivity(intent);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
_L4:
        c = '\0';
          goto _L1
        if(k != android.broadcast.oneseg.MtvOneSegBmlParams.DTVBMLXDPABrowserType.BML_START_RESIDENT_APP_LIVE_BROWSER.ordinal()) goto _L1; else goto _L2
_L2:
        MtvUtilDebug.Mid("MtvAppBml", "BML_START_RESIDENT_APP_LIVE_BROWSER !:");
          goto _L3
        if(s.equals("BookmarkList"))
        {
            MtvAppBmlConstants.AppBmlUIEvents appbmluievents = MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_START_TVLINK_TAB;
            if(mBmlUIMsgHandler != null)
                mBmlUIMsgHandler.sendMessage(mBmlUIMsgHandler.obtainMessage(appbmluievents.ordinal()));
        } else
        {
            MtvUtilDebug.Mid("MtvAppBml", "BML_CB_XDPA_StartResidentAppPeer: Unknown application name");
        }
          goto _L4
    }

    public void cb_stopBmlAnimation()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_stopBmlAnimation()V");
        MtvAppBmlConstants.AppBmlUIEvents appbmluievents = MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_STOP_ANIMATION;
        if(mBmlUIMsgHandler != null)
            mBmlUIMsgHandler.sendMessage(mBmlUIMsgHandler.obtainMessage(appbmluievents.ordinal()));
    }

    public int cb_storeImage(byte abyte0[], boolean flag, byte abyte1[])
    {
        Bitmap bitmap;
        String s;
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_storeImage([BZ[B)I");
        MtvUtilDebug.Mid("MtvAppBml", "BML_CB_Control_SaveImageToMemoryCardPeer:");
        bitmap = BitmapFactory.decodeByteArray(abyte1, 0, abyte1.length);
        if(bitmap == null)
            MtvUtilDebug.Mid("MtvAppBml", "BML_CB_Control_SaveImageToMemoryCardPeer: in_data is null");
        s = new String(abyte0);
        if(mPreferences.getBroadcastImageLocationStorage() != 0 || !MtvUtilMemoryStatus.isExternalMemoryAvailable()) goto _L2; else goto _L1
_L1:
        String s1;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Mid("MtvAppBml", (new StringBuilder()).append("SaveImageTo Memorycard  ").append(abyte0).toString());
        s1 = "/mnt/extSdCard/";
_L8:
        File file = new File(new File(s1), s);
        if(!file.exists()) goto _L4; else goto _L3
_L3:
        if(!flag) goto _L6; else goto _L5
_L5:
        MtvUtilDebug.Mid("MtvAppBml", "Overrite the image ! ");
        file.delete();
_L4:
        file.createNewFile();
_L9:
        FileOutputStream fileoutputstream = null;
        FileOutputStream fileoutputstream1 = new FileOutputStream(file);
        if(bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileoutputstream1))
            MtvUtilDebug.Error("MtvAppBml", (new StringBuilder()).append("ImageFile is saved :/sdcard/image/").append(s).toString());
        IOException ioexception;
        int i;
        try
        {
            fileoutputstream1.close();
        }
        catch(IOException ioexception3)
        {
            MtvUtilDebug.Error("MtvAppBml", (new StringBuilder()).append("saveFile FileOutputStream.close() IOException [").append(ioexception3).append("]").toString());
            ioexception3.printStackTrace();
        }
_L10:
        i = 0;
_L7:
        return i;
_L2:
label0:
        {
            if(mPreferences.getBroadcastImageLocationStorage() != 0 || MtvUtilMemoryStatus.isExternalMemoryAvailable())
                break label0;
            i = -1050;
        }
          goto _L7
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Mid("MtvAppBml", (new StringBuilder()).append("SaveImageTo Phone  ").append(abyte0).toString());
        s1 = "/sdcard/";
          goto _L8
_L6:
        i = -1053;
          goto _L7
        ioexception;
        MtvUtilDebug.Error("MtvAppBml", (new StringBuilder()).append("saveFile File creation fail [").append(ioexception).append("]").toString());
        ioexception.printStackTrace();
          goto _L9
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
_L13:
        MtvUtilDebug.Error("MtvAppBml", (new StringBuilder()).append("saveFile FileNotFoundException [").append(filenotfoundexception).append("]").toString());
        filenotfoundexception.printStackTrace();
        try
        {
            fileoutputstream.close();
        }
        catch(IOException ioexception2)
        {
            MtvUtilDebug.Error("MtvAppBml", (new StringBuilder()).append("saveFile FileOutputStream.close() IOException [").append(ioexception2).append("]").toString());
            ioexception2.printStackTrace();
        }
          goto _L10
        Exception exception;
        exception;
_L12:
        try
        {
            fileoutputstream.close();
        }
        catch(IOException ioexception1)
        {
            MtvUtilDebug.Error("MtvAppBml", (new StringBuilder()).append("saveFile FileOutputStream.close() IOException [").append(ioexception1).append("]").toString());
            ioexception1.printStackTrace();
        }
        throw exception;
        exception;
        fileoutputstream = fileoutputstream1;
        if(true) goto _L12; else goto _L11
_L11:
        filenotfoundexception;
        fileoutputstream = fileoutputstream1;
          goto _L13
    }

    public void cb_updateBMLSurfaceView()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_updateBMLSurfaceView()V");
        if(mBmlSurfaceListener != null)
            mBmlSurfaceListener.updateBMLSurfaceView();
    }

    public int cb_writeAddressBookInfoPeer(byte abyte0[], byte abyte1[], String s, String s1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_writeAddressBookInfoPeer([B[BLjava/lang/String;Ljava/lang/String;)I");
        String s2 = byteArrayToString(abyte0);
        byteArrayToString(abyte1);
        Intent intent = new Intent("android.intent.action.INSERT", android.provider.ContactsContract.Contacts.CONTENT_URI);
        intent.putExtra("name", s2);
        intent.putExtra("phone", s);
        intent.putExtra("email", s1);
        mContext.startActivity(intent);
        return 0;
    }

    public int cb_writeSchInfoPeer(short word0, int i, int j, int k, int l, int i1, int j1, 
            short word1, byte abyte0[], byte abyte1[], boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->cb_writeSchInfoPeer(SIIIIIIS[B[BZ)I");
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Mid("MtvAppBml", (new StringBuilder()).append("writeSchInfoPeer: Y").append(word0).append("M").append(i).append("D").append(j).append("W").append(k).append("H").append(l).append("Mn").append(i1).append("Sc").append(j1).append("Msc").append(word1).toString());
        long l1 = (new GregorianCalendar(word0, i, j, l, i1, j1)).getTimeInMillis();
        long l2 = 0x36ee80L + l1;
        String s = byteArrayToString(abyte0);
        String s1 = byteArrayToString(abyte1);
        Intent intent = new Intent("android.intent.action.EDIT");
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", l1);
        intent.putExtra("endTime", l2);
        intent.putExtra("title", s);
        intent.putExtra("description", s1);
        if(flag)
            intent.putExtra("alarm", 0);
        mContext.startActivity(intent);
        return 0;
    }

    public void deInit()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->deInit()V");
        if(mBmlViewControl != null)
        {
            mBmlViewControl.dettachViewListener(this);
            mBmlViewControl = null;
        }
        mContext = null;
        mPreferences = null;
        mBmlSurfaceListener = null;
        mBmlDialogListener = null;
        mBmlAnimationListener = null;
        mMtvAppPlaybackContext = null;
        mMtvAppBml = null;
    }

    public MtvAppBmlConstants.BmlControlType getBmlControlType()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->getBmlControlType()Lcom/samsung/sec/mtv/app/bml/MtvAppBmlConstants$BmlControlType;");
        if(mBmlViewControl != null) goto _L2; else goto _L1
_L1:
        MtvAppBmlConstants.BmlControlType bmlcontroltype = MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_BASIC;
_L4:
        return bmlcontroltype;
_L2:
        int i;
        i = mBmlViewControl.getUserKeyPadType();
        if((i & 0x20) != 32 || (i & 1) != 1 || (i & 4) != 4 || (i & 0x10) == 16)
            break; /* Loop/switch isn't completed */
        MtvUtilDebug.Low("MtvAppBml", "getBmlControlType: 1st case");
        mbmlCntrlType = MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_KEYPAD;
_L5:
        bmlcontroltype = mbmlCntrlType;
        if(true) goto _L4; else goto _L3
_L3:
        if((i & 0x20) == 32 && (i & 1) == 1 && (i & 4) == 4)
        {
            MtvUtilDebug.Low("MtvAppBml", "getBmlControlType: 2nd case");
            mbmlCntrlType = MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_KEYPAD;
        } else
        if((i & 0x10) == 16 && (i & 1) == 1 && (i & 4) == 4)
        {
            MtvUtilDebug.Low("MtvAppBml", "getBmlControlType: 3rd case");
            mbmlCntrlType = MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_KEYPAD;
        } else
        if((i & 4) == 4 && (i & 1) == 1)
        {
            MtvUtilDebug.Low("MtvAppBml", "getBmlControlType: 4th case");
            mbmlCntrlType = MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_KEYPAD;
        } else
        if((i & 1) == 1)
        {
            MtvUtilDebug.Low("MtvAppBml", "getBmlControlType: 5th case");
            mbmlCntrlType = MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_BASIC;
        } else
        if((i & 4) == 4)
        {
            MtvUtilDebug.Low("MtvAppBml", "getBmlControlType: 6th case");
            mbmlCntrlType = MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_KEYPAD;
        } else
        if((i & 0x10) == 16)
        {
            MtvUtilDebug.Low("MtvAppBml", "getBmlControlType: 7th case");
            mbmlCntrlType = MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_KEYPAD;
        } else
        {
            MtvUtilDebug.Low("MtvAppBml", "getBmlControlType: 8th case");
            mbmlCntrlType = MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_BASIC;
        }
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public MtvAppBmlConstants.AppBmlUIEvents getCurrentUIEvt()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->getCurrentUIEvt()Lcom/samsung/sec/mtv/app/bml/MtvAppBmlConstants$AppBmlUIEvents;");
        return mCuurUIEvt;
    }

    public byte[] getPrevUserName()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->getPrevUserName()[B");
        return mBmlViewControl.getfAuth_UserName();
    }

    public String getPrevUserPassWd()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->getPrevUserPassWd()Ljava/lang/String;");
        return mBmlViewControl.getfAuth_Password();
    }

    public void init(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->init(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)V");
        mMtvAppPlaybackContext = mtvappplaybackcontext;
        if(mPreferences == null)
            mPreferences = new MtvPreferences(mContext);
        if(mMtvAppPlaybackContext != null)
            mBmlViewControl = mMtvAppPlaybackContext.getComponents().getBml().getControlInterface();
    }

    public boolean isPrevUserNameNedded()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->isPrevUserNameNedded()Z");
        boolean flag;
        if(mBmlViewControl != null)
            flag = mBmlViewControl.getfAuth_KeepUserName();
        else
            flag = false;
        return flag;
    }

    public boolean isPrevUserPasswdNedded()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->isPrevUserPasswdNedded()Z");
        boolean flag;
        if(mBmlViewControl != null)
            flag = mBmlViewControl.getfAuth_KeepPassword();
        else
            flag = false;
        return flag;
    }

    public void onKeyEvent(KeyEvent keyevent)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->onKeyEvent(Landroid/view/KeyEvent;)V");
        if(keyevent != null && mBmlViewControl != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Error("MtvAppBml", "onKeyEvent: keyEvent NULL ");
_L4:
        return;
_L2:
        if(!mBmlViewControl.onKeyEvent(keyevent))
            MtvUtilDebug.Low("MtvAppBml", "onKeyEvent:BML is Halted. Skip Key event");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onResume(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->onResume(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)V");
        mMtvAppPlaybackContext = mtvappplaybackcontext;
        MtvUtilDebug.Low("MtvAppBml", "onResume: entered ");
        if(mPreferences == null)
            mPreferences = new MtvPreferences(mContext);
        if(mMtvAppPlaybackContext != null)
            mBmlViewControl = mMtvAppPlaybackContext.getComponents().getBml().getControlInterface();
        if(mBmlViewControl != null)
            mBmlViewControl.attachViewListener(this);
    }

    public void openBMLHomePage()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->openBMLHomePage()V");
        if(mBmlViewControl != null)
        {
            MtvUtilDebug.Low("MtvAppBml", "setBMLHomePage: Setting BML home page");
            mBmlViewControl.openBMLHomePage();
        }
    }

    public void processMouseEvent(int i, int j, int k)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->processMouseEvent(III)V");
        android.broadcast.oneseg.MtvOneSegBmlParams.action.ACTION_DOWN;
        k;
        JVM INSTR tableswitch 0 2: default 40
    //                   0 66
    //                   1 74
    //                   2 82;
           goto _L1 _L2 _L3 _L4
_L1:
        android.broadcast.oneseg.MtvOneSegBmlParams.action action = android.broadcast.oneseg.MtvOneSegBmlParams.action.ACTION_DOWN;
_L6:
        if(mBmlViewControl != null)
            mBmlViewControl.processMouseEvent(i, j, action);
        return;
_L2:
        action = android.broadcast.oneseg.MtvOneSegBmlParams.action.ACTION_DOWN;
        continue; /* Loop/switch isn't completed */
_L3:
        action = android.broadcast.oneseg.MtvOneSegBmlParams.action.ACTION_MOVE;
        continue; /* Loop/switch isn't completed */
_L4:
        action = android.broadcast.oneseg.MtvOneSegBmlParams.action.ACTION_UP;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void refreshBMLScreen()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->refreshBMLScreen()V");
        MtvUtilDebug.Low("MtvAppBml", "refreshBMLScreen:  refersh BML surface ");
        setBMLViewSize(-1, -1, 0, 0);
    }

    public void registerBmlAnimationListener(IMtvAppBmlAnimationListener imtvappbmlanimationlistener)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->registerBmlAnimationListener(Lcom/samsung/sec/mtv/app/bml/IMtvAppBmlAnimationListener;)V");
        mBmlAnimationListener = imtvappbmlanimationlistener;
    }

    public void registerBmlDialogListener(IMtvAppBmlDialogListener imtvappbmldialoglistener)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->registerBmlDialogListener(Lcom/samsung/sec/mtv/app/bml/IMtvAppBmlDialogListener;)V");
        mBmlDialogListener = imtvappbmldialoglistener;
    }

    public void registerBmlSurface(Context context, BMLNativeView bmlnativeview)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->registerBmlSurface(Landroid/content/Context;Lcom/access/bml/BMLNativeView;)V");
        if(mBmlViewControl != null)
            mBmlViewControl.registerSurface(context, bmlnativeview);
    }

    public void registerBmlSurfaceListener(IMtvAppBmlSurfaceListener imtvappbmlsurfacelistener)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->registerBmlSurfaceListener(Lcom/samsung/sec/mtv/app/bml/IMtvAppBmlSurfaceListener;)V");
        mBmlSurfaceListener = imtvappbmlsurfacelistener;
    }

    public void sendDialogReply(MtvAppBmlConstants.BmlDialogReply bmldialogreply, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->sendDialogReply(Lcom/samsung/sec/mtv/app/bml/MtvAppBmlConstants$BmlDialogReply;I)V");
        if(bmldialogreply != MtvAppBmlConstants.BmlDialogReply.MTV_APP_BML_DIALOG_REPLAY_YES) goto _L2; else goto _L1
_L1:
        mBmlViewControl.replyToCommand(android.broadcast.oneseg.MtvOneSegBmlParams.onesegBmlDialogReply.MTV_ONESEG_BML_DIALOG_REPLAY_YES, i);
_L4:
        return;
_L2:
        if(bmldialogreply == MtvAppBmlConstants.BmlDialogReply.MTV_APP_BML_DIALOG_REPLAY_NO)
            mBmlViewControl.replyToCommand(android.broadcast.oneseg.MtvOneSegBmlParams.onesegBmlDialogReply.MTV_ONESEG_BML_DIALOG_REPLAY_NO, i);
        else
        if(bmldialogreply == MtvAppBmlConstants.BmlDialogReply.MTV_APP_BML_DIALOG_REPLAY_CANCEL)
            mBmlViewControl.replyToCommand(android.broadcast.oneseg.MtvOneSegBmlParams.onesegBmlDialogReply.MTV_ONESEG_BML_DIALOG_REPLAY_CANCEL, i);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean setBMLViewSize(int i, int j, int k, int l)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->setBMLViewSize(IIII)Z");
        boolean flag;
        if(mBmlViewControl != null && !mBmlViewControl.setDisplaySize(i, j, k, l))
        {
            MtvUtilDebug.Low("MtvAppBml", "setBMLViewSize:BML is Halted. skip Browser_SetRect ");
            flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public void setPrevUserPassWd(String s)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->setPrevUserPassWd(Ljava/lang/String;)V");
        mBmlViewControl.setfPassword_Reply(s);
    }

    public void setUserName(byte abyte0[])
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->setUserName([B)V");
        mBmlViewControl.setfUserName_Reply(abyte0);
    }

    public void storeUserName(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->storeUserName(Z)V");
        mBmlViewControl.setfKeepUserName_Reply(flag);
    }

    public void storeUserPasswd(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->storeUserPasswd(Z)V");
        mBmlViewControl.setfKeepPassword_Reply(flag);
    }

    private static Context mContext = null;
    private static MtvAppBml mMtvAppBml = null;
    private boolean isBMLDialogMsgSet;
    private IMtvAppBmlAnimationListener mBmlAnimationListener;
    private IMtvAppBmlDialogListener mBmlDialogListener;
    private IMtvAppBmlSurfaceListener mBmlSurfaceListener;
    private Handler mBmlUIMsgHandler;
    private IMtvOneSegBmlViewControl mBmlViewControl;
    private MtvAppBmlConstants.AppBmlUIEvents mCuurUIEvt;
    private MtvAppPlaybackContext mMtvAppPlaybackContext;
    private MtvPreferences mPreferences;
    private MtvAppBmlConstants.BmlControlType mbmlCntrlType;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;-><clinit>()V");
    }


/*
    static IMtvAppBmlAnimationListener access$000(MtvAppBml mtvappbml)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->access$000(Lcom/samsung/sec/mtv/app/bml/MtvAppBml;)Lcom/samsung/sec/mtv/app/bml/IMtvAppBmlAnimationListener;");
        return mtvappbml.mBmlAnimationListener;
    }

*/


/*
    static MtvAppBmlConstants.AppBmlUIEvents access$102(MtvAppBml mtvappbml, MtvAppBmlConstants.AppBmlUIEvents appbmluievents)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->access$102(Lcom/samsung/sec/mtv/app/bml/MtvAppBml;Lcom/samsung/sec/mtv/app/bml/MtvAppBmlConstants$AppBmlUIEvents;)Lcom/samsung/sec/mtv/app/bml/MtvAppBmlConstants$AppBmlUIEvents;");
        mtvappbml.mCuurUIEvt = appbmluievents;
        return appbmluievents;
    }

*/


/*
    static IMtvAppBmlDialogListener access$200(MtvAppBml mtvappbml)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->access$200(Lcom/samsung/sec/mtv/app/bml/MtvAppBml;)Lcom/samsung/sec/mtv/app/bml/IMtvAppBmlDialogListener;");
        return mtvappbml.mBmlDialogListener;
    }

*/


/*
    static IMtvAppBmlSurfaceListener access$300(MtvAppBml mtvappbml)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/bml/MtvAppBml;->access$300(Lcom/samsung/sec/mtv/app/bml/MtvAppBml;)Lcom/samsung/sec/mtv/app/bml/IMtvAppBmlSurfaceListener;");
        return mtvappbml.mBmlSurfaceListener;
    }

*/
}
