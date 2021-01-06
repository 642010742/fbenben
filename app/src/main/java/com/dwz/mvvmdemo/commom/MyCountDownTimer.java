package com.dwz.mvvmdemo.commom;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * 用于登录页面倒计时
 */
public class MyCountDownTimer {
    private static MyCountDownTimer myCountDownTimer = null;
    private CountDownTimer timer;

    public static MyCountDownTimer getInstance() {
        if (myCountDownTimer == null) {
            myCountDownTimer = new MyCountDownTimer();
        }
        return myCountDownTimer;
    }

    public void startTimer(final TextView textView) {
        if (timer == null) {
            timer = new CountDownTimer(61000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    textView.setEnabled(false);
                    textView.setText(millisUntilFinished / 1000 + "s");
                }

                @Override
                public void onFinish() {
                    textView.setEnabled(true);
                    textView.setText("获取验证码");
                }
            };
        }
        timer.start();
    }

    public void cancleTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
