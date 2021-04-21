package com.dwz.library.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.dwz.library.R;


/**
 * 验证码定时
 */
public class TimeCount extends CountDownTimer {

	private TextView tvCode;

	public TimeCount(long millisInFuture, long countDownInterval, TextView tvCode) {
		super(millisInFuture, countDownInterval);
		this.tvCode = tvCode;
	}

	@Override
	public void onFinish() {
		tvCode.setText("重新获取");
		tvCode.setClickable(true);
		tvCode.setTextColor(tvCode.getResources().getColor(R.color.white));
	}

	@Override
	public void onTick(long millisUntilFinished) {
		tvCode.setClickable(false);
		tvCode.setText(millisUntilFinished / 1000 + "秒后重试");
		tvCode.setTextColor(tvCode.getResources().getColor(R.color.white));
	}

}
