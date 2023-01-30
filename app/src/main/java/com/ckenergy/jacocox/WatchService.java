package com.ckenergy.jacocox;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class WatchService extends Service {

    private static final String TAG = "_WatchService";

    private ScheduledExecutorService executorService;

    public static void open(Context context) {
        context.startService(new Intent(context, WatchService.class));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "WatchService onCreate ");
        if (executorService == null) {
            executorService = Executors.newSingleThreadScheduledExecutor();
        }
        executorService.scheduleAtFixedRate(runnable, 20, 20, TimeUnit.SECONDS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "WatchService onStartCommand ");
        return super.onStartCommand(intent, flags, startId);
    }

    private Runnable runnable = () -> {
        Log.d(TAG, "WatchService 定时任务在运行中 " );
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "WatchService onDestroy ");
        try {
            if (executorService != null) {
                Log.d(TAG, "定时任务关闭");
                executorService.shutdown();
                executorService.awaitTermination(0, TimeUnit.SECONDS);
                executorService.shutdownNow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void checkWatchSerExist(Context context) {
        try {
            if (!isServiceRunning(context, WatchService.class.getName())) {
                Log.d(TAG, "WatchService 不在运行中 准备重启");
                Intent intent = new Intent(context, WatchService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(intent);
                } else {
                    context.startService(intent);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static boolean isServiceRunning(Context context, String serviceName) {
        try {
            if (TextUtils.isEmpty(serviceName)) {
                return false;
            }
            ActivityManager myManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ArrayList<ActivityManager.RunningServiceInfo> runningService
                    = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(Integer.MAX_VALUE);
            if (runningService != null && !runningService.isEmpty()) {
                for (int i = 0; i < runningService.size(); i++) {
                    if (runningService.get(i).service.getClassName().equals(serviceName)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
