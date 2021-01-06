package com.dwz.mvvmdemo.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.dwz.mvvmdemo.R;
import com.dwz.mvvmdemo.commom.MyCountDownTimer;
import com.dwz.mvvmdemo.vm.LoginVModel;

import library.baseView.BaseActivity;
import library.utils.CheckUtils;
import library.utils.StatusBarUtil;
import library.utils.ToastUtil;

public class LoginActivity extends BaseActivity<LoginVModel> implements View.OnClickListener {

    @Override
    public Class<LoginVModel> getVMClass() {
        return LoginVModel.class;
    }

    @Override
    public int LayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews() {
        vm.bind.welcome.setText("欢迎来到" + getResources().getString(R.string.app_name) + "~~");
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusBarUtil.setLightMode(this);
    }

    @Override
    public void initListener() {
        vm.bind.back.setOnClickListener(this);
        vm.bind.clear.setOnClickListener(this);
        vm.bind.loginAway.setOnClickListener(this);
        vm.bind.getCode.setOnClickListener(this);
        vm.bind.login.setOnClickListener(this);
        vm.bind.register.setOnClickListener(this);

        vm.bind.phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pwd = vm.bind.pwd.getText().toString().trim();
                if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(s.toString().trim())) {
                    vm.bind.login.setBackgroundResource(R.color.c3caaff);
                } else {
                    vm.bind.login.setBackgroundResource(R.color.c0f6eff);
                }
            }
        });

        vm.bind.pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String phone = vm.bind.phone.getText().toString().trim();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(s.toString().trim())) {
                    vm.bind.login.setBackgroundResource(R.color.c3caaff);
                } else {
                    vm.bind.login.setBackgroundResource(R.color.c0f6eff);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                pCloseActivity();
                break;
            case R.id.clear:
                vm.bind.phone.setText("");
                break;
            case R.id.loginAway:
                vm.pwdLogin = !vm.pwdLogin;
                vm.bind.loginAway.setText(vm.pwdLogin ? "验证码登录" : "账号密码登录");
                vm.bind.pwd.setHint(vm.pwdLogin ? "请输入密码" : "请输入验证码");
                vm.bind.getCode.setVisibility(vm.pwdLogin ? View.GONE : View.VISIBLE);
                break;
            case R.id.getCode:
                if (CheckUtils.isPhone(vm.bind.phone.getText().toString())) {
                    MyCountDownTimer.getInstance().startTimer(vm.bind.getCode);
                } else {
                    ToastUtil.showShort("请输入正确的手机号");
                }
                break;
            case R.id.login:
                if (!CheckUtils.isPhone(vm.bind.phone.getText().toString())) {
                    ToastUtil.showShort("请输入正确的手机号");
                    return;
                }
                if (vm.pwdLogin) {
                    if (!CheckUtils.isPwd(vm.bind.pwd.getText().toString())) {
                        ToastUtil.showShort("请输入正确的密码");
                        return;
                    }
                } else {
                    if (!CheckUtils.isCode(vm.bind.pwd.getText().toString())) {
                        ToastUtil.showShort("请输入正确的验证码");
                        return;
                    }
                }
                Intent intentLogin = new Intent(LoginActivity.this,MainActivity.class);
                pStartActivity(intentLogin,true);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyCountDownTimer.getInstance().cancleTimer();
    }
}