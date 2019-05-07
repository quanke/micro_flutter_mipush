package com.wjapi.micro.micro_flutter_mipush;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Process;

import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * MicroFlutterMipushPlugin
 */
public class MicroFlutterMipushPlugin implements MethodCallHandler, EventChannel.StreamHandler {

    private Activity activity;
    private static ReceiverPluginHandler sHandler = null;

    private static EventChannel.EventSink eventSink;

    public MicroFlutterMipushPlugin(Activity activity) {
        this.activity = activity;
    }

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "micro_flutter_mipush");
        channel.setMethodCallHandler(new MicroFlutterMipushPlugin(registrar.activity()));

        final EventChannel eventChannel = new EventChannel(registrar.messenger(), "micro_flutter_receiver_mipush");
        eventChannel.setStreamHandler(new MicroFlutterMipushPlugin(registrar.activity()));
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {


        if ("init".equals(call.method)) {
            init(call, result);
        } else {
            result.notImplemented();
        }
    }


    private void init(MethodCall call, Result result) {
        if (sHandler == null) {
            sHandler = new ReceiverPluginHandler(activity);
        }
        String appId = call.argument("appId");
        String appKey = call.argument("appKey");
// 注册push服务，注册成功后会向DemoMessageReceiver发送广播
        // 可以从DemoMessageReceiver的onCommandResult方法中MiPushCommandMessage对象参数中获取注册信息
        if (shouldInit()) {
            MiPushClient.registerPush(activity, appId, appKey);
            result.success("");
        }
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = activity.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onListen(Object o, EventChannel.EventSink eventSink) {
        this.eventSink = eventSink;

    }

    @Override
    public void onCancel(Object o) {

    }

    public static ReceiverPluginHandler getHandler() {
        return sHandler;
    }

    public static class ReceiverPluginHandler extends Handler {

        private Context context;

        public ReceiverPluginHandler(Context context) {
            this.context = context;
        }

        @Override
        public void handleMessage(Message msg) {
            String s = (String) msg.obj;

//            eventSink.success(s);
//            if (!TextUtils.isEmpty(s)) {
//                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
//            }


        }
    }
}
