package library.listener;

import android.content.Intent;

import library.commonModel.TitleOptions;

public interface ChangeTitleListener {
    void typeTitle(TitleOptions titleOptions, Intent intent);
    TitleOptions title();
    int  initStatusBarColor();
}
