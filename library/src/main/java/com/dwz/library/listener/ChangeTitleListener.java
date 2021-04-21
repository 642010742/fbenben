package com.dwz.library.listener;

import android.content.Intent;

import com.dwz.library.commonModel.TitleOptions;


public interface ChangeTitleListener {
    void typeTitle(TitleOptions titleOptions, Intent intent);
    TitleOptions title();
}
