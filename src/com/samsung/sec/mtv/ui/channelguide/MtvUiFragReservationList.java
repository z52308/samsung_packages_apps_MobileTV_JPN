// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.channelguide;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.provider.*;
import com.samsung.sec.mtv.ui.common.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.samsung.sec.mtv.ui.channelguide:
//            MtvUiChannelSchedule, MtvUiChannelGuide

public class MtvUiFragReservationList extends MtvUiFrag
    implements android.app.LoaderManager.LoaderCallbacks, android.widget.AdapterView.OnItemClickListener, android.widget.AdapterView.OnItemLongClickListener
{
    private class ReservationAdapter extends CursorAdapter
    {

        public void bindView(View view, Context context, Cursor cursor)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationAdapter;->bindView(Landroid/view/View;Landroid/content/Context;Landroid/database/Cursor;)V");
            MtvReservation mtvreservation = MtvReservationManager.builder(cursor);
            view.setTag(mtvreservation);
            String s;
            String s1;
            if(DateFormat.is24HourFormat(Log.d(MtvUiFragReservationList.this).getApplicationContext()))
                s = (new SimpleDateFormat("M/d (EEE) H:mm")).format(new Date(mtvreservation.mTimeStart));
            else
                s = (new SimpleDateFormat("M/d (EEE) h:mm a")).format(new Date(mtvreservation.mTimeStart));
            if(mtvreservation.mTimeEnd > 0L)
            {
                if(DateFormat.is24HourFormat(Log.d(MtvUiFragReservationList.this).getApplicationContext()))
                    s1 = (new StringBuilder()).append(s).append(" - ").append((new SimpleDateFormat("H:mm")).format(new Date(mtvreservation.mTimeEnd))).toString();
                else
                    s1 = (new StringBuilder()).append(s).append(" - ").append((new SimpleDateFormat("h:mm a")).format(new Date(mtvreservation.mTimeEnd))).toString();
            } else
            {
                s1 = (new StringBuilder()).append(s).append(" -            ").toString();
            }
            ((ImageView)view.findViewById(0x7f0a0053)).setImageDrawable(mIcon[mtvreservation.mPgmType][selectStatusIconIndex(mtvreservation)]);
            ((TextView)view.findViewById(0x7f0a0113)).setText((new StringBuilder()).append(s1).append(" ").append("Ch ").append(mtvreservation.mPch).append(" ").toString());
            ((TextView)view.findViewById(0x7f0a0112)).setText(mtvreservation.mPgmName);
            view.findViewById(0x7f0a0048).setVisibility(8);
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewgroup)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationAdapter;->newView(Landroid/content/Context;Landroid/database/Cursor;Landroid/view/ViewGroup;)Landroid/view/View;");
            return mInflater.inflate(0x7f03002d, viewgroup, false);
        }

        public int selectStatusIconIndex(MtvReservation mtvreservation)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationAdapter;->selectStatusIconIndex(Lcom/samsung/sec/mtv/provider/MtvReservation;)I");
            int i;
            if(mtvreservation.mPgmStatus == 0 && mtvreservation.mTimeStart < System.currentTimeMillis() - 5000L)
            {
                MtvUtilDebug.Mid(Log.d(), "selectStatusIconIndex() : ooops!!! hit an expired reservation,setting it failed");
                i = 1;
                MtvReservationManager.UpdateStatus(getActivity(), mtvreservation, 2);
            } else
            if(mtvreservation.mPgmStatus == 0 || mtvreservation.mPgmStatus == 6 || mtvreservation.mPgmStatus == 1)
                i = 0;
            else
                i = 1;
            return i;
        }

        private final Drawable mIcon[][];
        private final LayoutInflater mInflater;
        final MtvUiFragReservationList this$0;

        public ReservationAdapter(Context context, Cursor cursor)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationAdapter;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;Landroid/content/Context;Landroid/database/Cursor;)V");
            this$0 = MtvUiFragReservationList.this;
            super(context, cursor);
            int ai[] = new int[2];
            ai[0] = 2;
            ai[1] = 2;
            mIcon = (Drawable[][])Array.newInstance(android/graphics/drawable/Drawable, ai);
            mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
            mIcon[1][0] = context.getResources().getDrawable(0x7f0200c0);
            mIcon[0][0] = context.getResources().getDrawable(0x7f0200bd);
            mIcon[1][1] = context.getResources().getDrawable(0x7f0200c1);
            mIcon[0][1] = context.getResources().getDrawable(0x7f0200be);
        }
    }

    public static class ReservationDetail extends DialogFragment
    {

        private Dialog initDetails(final MtvReservation mtvreserve)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->initDetails(Lcom/samsung/sec/mtv/provider/MtvReservation;)Landroid/app/Dialog;");
            makeReservationInfo();
            Activity activity = getActivity();
            ArrayList arraylist = mInfoList;
            String as[] = new String[2];
            as[0] = "MENU_TITLE";
            as[1] = "MENU_VALUE";
            int ai[] = new int[2];
            ai[0] = 0x7f0a0110;
            ai[1] = 0x7f0a0111;
            SimpleAdapter simpleadapter = new SimpleAdapter(activity, arraylist, 0x7f03002c, as, ai);
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
            builder.setTitle(getString(0x7f0702bf));
            builder.setAdapter(simpleadapter, null);
            class _cls1
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail$1;->onClick(Landroid/content/DialogInterface;I)V");
                    if(Log.d(ReservationDetail.this) != null)
                        Log.d(ReservationDetail.this).dismiss();
                }

                final ReservationDetail this$0;

                _cls1()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail$1;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;)V");
                    this$0 = ReservationDetail.this;
                    super();
                }
            }

            builder.setPositiveButton(getString(0x7f070034), new _cls1());
            class _cls2
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail$2;->onClick(Landroid/content/DialogInterface;I)V");
                    MtvReservationManager.delete(getActivity(), ((MtvProgram) (mtvreserve)).mUriId);
                    if(Log.d(ReservationDetail.this) != null)
                        Log.d(ReservationDetail.this).dismiss();
                }

                final ReservationDetail this$0;
                final MtvReservation val$mtvreserve;

                _cls2()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail$2;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;Lcom/samsung/sec/mtv/provider/MtvReservation;)V");
                    this$0 = ReservationDetail.this;
                    mtvreserve = mtvreservation;
                    super();
                }
            }

            builder.setNegativeButton(getString(0x7f07028d), new _cls2());
            mDetailsDialog = builder.create();
            mDetailsDialog.setCanceledOnTouchOutside(false);
            mDetailsDialog.requestWindowFeature(1);
            mDetailsDialog.getWindow().setFlags(1024, 1024);
            mDetailsDialog.show();
            mDetailsDialog.setOnKeyListener(mKeyListener);
            mDialog = mDetailsDialog;
            return mDialog;
        }

        private void makeEndTime()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->makeEndTime()V");
            map = new HashMap();
            map.put("MENU_TITLE", getString(0x7f0702c6));
            String s = (new SimpleDateFormat("M/d (EEE) H:mm")).format(new Date(mtvreserve.mTimeEnd));
            map.put("MENU_VALUE", s);
            mInfoList.add(map);
        }

        private void makeReservationInfo()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->makeReservationInfo()V");
            makeresult();
            makereason();
            makeprogtype();
            makechannel();
            makeTVstation();
            makeStartTime();
            makeEndTime();
            makeprogname();
        }

        private void makeStartTime()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->makeStartTime()V");
            map = new HashMap();
            map.put("MENU_TITLE", getString(0x7f0702c5));
            String s = (new SimpleDateFormat("M/d (EEE) H:mm")).format(new Date(mtvreserve.mTimeStart));
            map.put("MENU_VALUE", s);
            mInfoList.add(map);
        }

        private void makeTVstation()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->makeTVstation()V");
            map = new HashMap();
            map.put("MENU_TITLE", getString(0x7f0702c4));
            MtvChannel mtvchannel = MtvChannelManager.findByPChannel(getActivity(), mtvreserve.mPch);
            String s;
            if(mtvchannel != null)
                s = mtvchannel.mChannelName;
            else
                s = "";
            map.put("MENU_VALUE", s);
            mInfoList.add(map);
        }

        private void makechannel()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->makechannel()V");
            String s = (new StringBuilder()).append(getString(0x7f0702ac)).append(mtvreserve.mPch).toString();
            map = new HashMap();
            map.put("MENU_TITLE", getString(0x7f0702a8));
            map.put("MENU_VALUE", s);
            mInfoList.add(map);
        }

        private void makeprogname()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->makeprogname()V");
            map = new HashMap();
            map.put("MENU_TITLE", getString(0x7f0702c2));
            map.put("MENU_VALUE", mtvreserve.mPgmName);
            mInfoList.add(map);
        }

        private void makeprogtype()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->makeprogtype()V");
            map = new HashMap();
            map.put("MENU_TITLE", getString(0x7f0702c3));
            if(mtvreserve.mPgmType == 0)
                map.put("MENU_VALUE", getString(0x7f07008a));
            else
                map.put("MENU_VALUE", getString(0x7f0702b3));
            mInfoList.add(map);
        }

        private void makereason()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->makereason()V");
            if(mtvreserve.mPgmStatus != 1) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if(mtvreserve.mPgmType == 1 && mtvreserve.mPgmStatus == 6)
            {
                MtvUtilDebug.Low("ReservationDetail", "user clicked reservation info in the middle of a watch Reservation !");
                continue; /* Loop/switch isn't completed */
            }
            map = new HashMap();
            map.put("MENU_TITLE", getString(0x7f0702c1));
            if(mtvreserve.mPgmStatus != 2)
                break; /* Loop/switch isn't completed */
            map.put("MENU_VALUE", getString(0x7f0700dc));
_L4:
            mInfoList.add(map);
            if(true) goto _L1; else goto _L3
_L3:
            if(mtvreserve.mPgmStatus == 7)
                map.put("MENU_VALUE", getString(0x7f0700dc));
            else
            if(mtvreserve.mPgmStatus == 3)
                map.put("MENU_VALUE", getString(0x7f0702cb));
            else
            if(mtvreserve.mPgmStatus == 4 || mtvreserve.mPgmStatus == 8)
                map.put("MENU_VALUE", getString(0x7f0702cc));
            else
            if(mtvreserve.mPgmStatus == 5 || mtvreserve.mPgmStatus == 9)
                map.put("MENU_VALUE", getString(0x7f0702cd));
              goto _L4
            if(true) goto _L1; else goto _L5
_L5:
        }

        private void makeresult()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->makeresult()V");
            map = new HashMap();
            map.put("MENU_TITLE", getString(0x7f0702c0));
            if(mtvreserve.mPgmStatus == 1)
            {
                if(mtvreserve.mPgmType == 0)
                    map.put("MENU_VALUE", getString(0x7f0702c9));
                else
                    map.put("MENU_VALUE", getString(0x7f0702ca));
            } else
            if(mtvreserve.mPgmType == 1 && mtvreserve.mPgmStatus == 6)
                map.put("MENU_VALUE", getString(0x7f0702ca));
            else
            if(mtvreserve.mPgmType == 0 && (mtvreserve.mPgmStatus == 8 || mtvreserve.mPgmStatus == 9))
                map.put("MENU_VALUE", getString(0x7f0702d0));
            else
            if(mtvreserve.mPgmType == 0)
                map.put("MENU_VALUE", getString(0x7f0702c7));
            else
                map.put("MENU_VALUE", getString(0x7f0702c8));
            mInfoList.add(map);
        }

        public Dialog onCreateDialog(Bundle bundle)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;");
            if(bundle != null)
                mtvreserve = (MtvReservation)bundle.getSerializable("MtvReserve");
            MtvUtilDebug.Low("ReservationDetail", (new StringBuilder()).append("onCreateDialog  mTimeStart:\n").append(mTimeStart).append(" mContext ").append(mContext).toString());
            return initDetails(mtvreserve);
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->onSaveInstanceState(Landroid/os/Bundle;)V");
            bundle.putSerializable("MtvReserve", mtvreserve);
        }

        public Context mContext;
        AlertDialog mDetailsDialog;
        private AlertDialog mDialog;
        private ArrayList mInfoList;
        private android.content.DialogInterface.OnKeyListener mKeyListener;
        private long mTimeStart;
        private HashMap map;
        private MtvReservation mtvreserve;


/*
        static AlertDialog access$200(ReservationDetail reservationdetail)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;->access$200(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;)Landroid/app/AlertDialog;");
            return reservationdetail.mDialog;
        }

*/

        public ReservationDetail()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;-><init>()V");
            super();
            mInfoList = new ArrayList();
            map = null;
            mDialog = null;
            mDetailsDialog = null;
            class _cls3
                implements android.content.DialogInterface.OnKeyListener
            {

                public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
                {
                    boolean flag;
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail$3;->onKey(Landroid/content/DialogInterface;ILandroid/view/KeyEvent;)Z");
                    flag = true;
                    i;
                    JVM INSTR tableswitch 4 4: default 32
                //                               4 38;
                       goto _L1 _L2
_L1:
                    flag = false;
_L4:
                    return flag;
_L2:
                    if(keyevent.getAction() == flag && Log.d(ReservationDetail.this) != null)
                        Log.d(ReservationDetail.this).dismiss();
                    if(true) goto _L4; else goto _L3
_L3:
                }

                final ReservationDetail this$0;

                _cls3()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail$3;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;)V");
                    this$0 = ReservationDetail.this;
                    super();
                }
            }

            mKeyListener = new _cls3();
        }

        public ReservationDetail(MtvReservation mtvreservation)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList$ReservationDetail;-><init>(Lcom/samsung/sec/mtv/provider/MtvReservation;)V");
            super();
            mInfoList = new ArrayList();
            map = null;
            mDialog = null;
            mDetailsDialog = null;
            mKeyListener = new _cls3();
            mtvreserve = mtvreservation;
        }
    }


    public MtvUiFragReservationList()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;-><init>()V");
        super();
    }

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onCreate(Landroid/os/Bundle;)V");
        MtvUtilDebug.Low(TAG, "onCreate called...");
        mActivity = getActivity();
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public Loader onCreateLoader(int i, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onCreateLoader(ILandroid/os/Bundle;)Landroid/content/Loader;");
        return new CursorLoader(getActivity(), MtvReservationManager.CONTENT_URI, null, null, null, null);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onCreateOptionsMenu(Landroid/view/Menu;Landroid/view/MenuInflater;)V");
        MtvUtilDebug.Low(TAG, "onCreateOptionsMenu called...");
        menu.clear();
        menu.add(0, 1, 0, 0x7f070289).setIcon(0x7f0200ff);
        menu.add(0, 2, 0, 0x7f07028a).setIcon(0x7f020104);
        menu.add(0, 3, 0, 0x7f07028b).setIcon(0x7f020101);
        menu.add(0, 4, 0, 0x7f07028d).setIcon(0x7f020100);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
        MtvUtilDebug.Low(TAG, "onCreateView called :");
        mLayoutView = layoutinflater.inflate(0x7f03001d, viewgroup, false);
        mListView = (ListView)mLayoutView.findViewById(0x7f0a0098);
        getLoaderManager().initLoader(2, null, this);
        return mLayoutView;
    }

    public void onDestroyView()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onDestroyView()V");
        MtvUtilDebug.Low(TAG, "onDestroyView called :");
        super.onDestroyView();
        getLoaderManager().destroyLoader(2);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V");
        Object obj = view.getTag();
        if(!(obj instanceof MtvReservation) || MtvChannelManager.getCount(mActivity) < 1)
        {
            MtvUtilDebug.Low(TAG, "onItemClick invalid tag");
        } else
        {
            MtvReservation mtvreservation = (MtvReservation)obj;
            if(mtvreservation.mTimeStart > System.currentTimeMillis() && mtvreservation.mPgmStatus == 0)
            {
                Intent intent = new Intent(mActivity, com/samsung/sec/mtv/ui/common/MtvUiManualReservation);
                intent.putExtra("MtvReservation", mtvreservation.mTimeStart);
                startActivity(intent);
            } else
            {
                (new ReservationDetail(mtvreservation)).show(getFragmentManager(), "ReservationDetail");
            }
        }
    }

    public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onItemLongClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)Z");
        Object obj = view.getTag();
        if(!(obj instanceof MtvReservation))
        {
            MtvUtilDebug.Low(TAG, "onItemLongClick invalid tag");
        } else
        {
            MtvReservation mtvreservation = (MtvReservation)obj;
            String s1;
            String s2;
            Bundle bundle;
            if(DateFormat.is24HourFormat(mActivity.getApplicationContext()))
            {
                String s3 = (new SimpleDateFormat("M/d H:mm")).format(new Date(mtvreservation.mTimeStart));
                s1 = (new StringBuilder()).append(s3).append(" - ").append((new SimpleDateFormat("H:mm")).format(new Date(mtvreservation.mTimeEnd))).toString();
            } else
            {
                String s = (new SimpleDateFormat("M/d h:mm a")).format(new Date(mtvreservation.mTimeStart));
                s1 = (new StringBuilder()).append(s).append(" - ").append((new SimpleDateFormat("h:mm a")).format(new Date(mtvreservation.mTimeEnd))).toString();
            }
            s2 = (new StringBuilder()).append(getString(0x7f0702ac)).append(mtvreservation.mPch).append(" ").append(s1).toString();
            bundle = new Bundle();
            MtvUtilDebug.Low(TAG, "onItemLongClick");
            bundle.putInt("dialogType", 5);
            bundle.putString("title", s2);
            bundle.putInt("db_id", mtvreservation.mUriId);
            MtvUiDialogsFrag.newInstance(bundle).show(getActivity().getFragmentManager(), "dialog");
        }
        return false;
    }

    public void onLoadFinished(Loader loader, Cursor cursor)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onLoadFinished(Landroid/content/Loader;Landroid/database/Cursor;)V");
        mListAdapter = new ReservationAdapter(mActivity, cursor);
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
        ((ImageView)mLayoutView.findViewById(0x7f0a009a)).setAlpha(90);
        mListView.setEmptyView(mLayoutView.findViewById(0x7f0a0099));
    }

    public volatile void onLoadFinished(Loader loader, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onLoadFinished(Landroid/content/Loader;Ljava/lang/Object;)V");
        onLoadFinished(loader, (Cursor)obj);
    }

    public void onLoaderReset(Loader loader)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onLoaderReset(Landroid/content/Loader;)V");
        if(mListView != null)
            mListView.setAdapter(null);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onOptionsItemSelected(Landroid/view/MenuItem;)Z");
        menuitem.getItemId();
        JVM INSTR tableswitch 1 4: default 44
    //                   1 46
    //                   2 67
    //                   3 87
    //                   4 100;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return true;
_L2:
        startActivity(new Intent(getActivity(), com/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule));
        continue; /* Loop/switch isn't completed */
_L3:
        startActivity(new Intent(getActivity(), com/samsung/sec/mtv/ui/common/MtvUiManualReservation));
        continue; /* Loop/switch isn't completed */
_L4:
        ((MtvUiChannelGuide)getActivity()).addMemInfoFrag();
        continue; /* Loop/switch isn't completed */
_L5:
        Intent intent = new Intent(mActivity, com/samsung/sec/mtv/ui/common/MtvUiRemoveList);
        intent.putExtra("Remove_List_Type", 102);
        startActivityForResult(intent, 0);
        if(true) goto _L1; else goto _L6
_L6:
    }

    public void onPrepareOptionsMenu(Menu menu)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onPrepareOptionsMenu(Landroid/view/Menu;)V");
        MtvUtilDebug.Low(TAG, "onPrepareOptionsMenu");
        menu.clear();
        boolean flag = false;
        boolean flag1 = true;
        menu.clear();
        MtvAppPlaybackContext mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mtvappplaybackcontext != null && mtvappplaybackcontext.getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE)
            flag = true;
        if(mListAdapter != null && mListAdapter.getCount() == 0)
            flag1 = false;
        menu.add(0, 1, 0, 0x7f070289).setIcon(0x7f0200ff).setEnabled(flag);
        menu.add(0, 2, 0, 0x7f07028a).setIcon(0x7f020104);
        menu.add(0, 3, 0, 0x7f07028b).setIcon(0x7f020101);
        if(flag1)
            menu.add(0, 4, 0, 0x7f07028d).setIcon(0x7f020100).setEnabled(flag1);
    }

    public void onUpdate(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->onUpdate(ILjava/lang/Object;)V");
        i;
        JVM INSTR tableswitch 1 2: default 32
    //                   1 39
    //                   2 106;
           goto _L1 _L2 _L3
_L1:
        super.onUpdate(i, obj);
        return;
_L2:
        Bundle bundle = new Bundle();
        int j = -1;
        if(obj != null)
            j = ((Integer)obj).intValue();
        bundle.putInt("dialogType", 6);
        bundle.putInt("db_id", j);
        MtvUiDialogsFrag.newInstance(bundle).show(getActivity().getFragmentManager(), "dialog");
        continue; /* Loop/switch isn't completed */
_L3:
        reserve = MtvReservationManager.find(mActivity, reserve.mTimeStart, new boolean[0]);
        (new ReservationDetail(reserve)).show(getFragmentManager(), "ReservationDetail");
        if(true) goto _L1; else goto _L4
_L4:
    }

    private static String TAG = "MtvUiFragReservationList";
    private static MtvReservation reserve = null;
    private Activity mActivity;
    private View mLayoutView;
    private ListAdapter mListAdapter;
    private ListView mListView;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;-><clinit>()V");
    }


/*
    static Activity access$000(MtvUiFragReservationList mtvuifragreservationlist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->access$000(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;)Landroid/app/Activity;");
        return mtvuifragreservationlist.mActivity;
    }

*/


/*
    static String access$100()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList;->access$100()Ljava/lang/String;");
        return TAG;
    }

*/
}
