package com.example.lxh.firstapp.common;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by lxh on 2018/8/20.
 * 解除引用关系，防止内存泄漏
 */

public class CommonHandler extends Handler {

    public interface MessageHandler {
        void handleMessage(Message message);
    }

    private WeakReference<MessageHandler> mWeakReference;

    public CommonHandler(MessageHandler handler) {
        mWeakReference = new WeakReference<>(handler);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        MessageHandler handler = mWeakReference.get();
        if (handler != null) {
            handler.handleMessage(msg);
        }
    }
}
