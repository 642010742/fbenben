package com.dwz.library.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dwz.library.R;
import com.dwz.library.listener.OnTabMenuClickListener;
import com.dwz.library.utils.PixelUtil;

import java.util.ArrayList;
import androidx.annotation.IdRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class FragmentTabMenuView extends RelativeLayout {

    @SuppressLint("ResourceType")
    @IdRes
    public static final int containerID = 0x0050;
    @SuppressLint("ResourceType")
    @IdRes
    public static final int textID = 0x0051;
    @SuppressLint("ResourceType")
    @IdRes
    public static final int imageID = 0x0052;
    @SuppressLint("ResourceType")
    @IdRes
    public static final int hintID = 0x0053;
    @SuppressLint("ResourceType")
    @IdRes
    public static final int menuContainerID = 0x0058;
    @SuppressLint("ResourceType")
    @IdRes
    public static final int fragmentContainerID = 0x0059;

    public static final int MENU_TYPE_DRAWABLE_CHANGE = 0x1000;
    public static final int MENU_TYPE_DRAWABLE_TINT = 0x1001;


    private int menuType = MENU_TYPE_DRAWABLE_CHANGE;
    private int normalTextColor = 0xff2b2b2b;
    private int checkedTextColor = 0xffff0000;
    private int normalDrawableTint = normalTextColor;
    private int checkedDrawableTint = checkedTextColor;
    private float topPadding = 0;
    private float bottomPadding = 0;
    private float menuSize = 14;

    private int bgColor;
    private int bgColorChecked;
    //private int bgContainer;
    private boolean closeClick = false;
    private int tabHeight = 60;
    private int tabWidth = -1;

    private ArrayList<MenuBean> menuBeans;
    private LinearLayout.LayoutParams layoutParams;
    private LayoutParams textLayoutParams;
    private LayoutParams imageLayoutParams;
    private LayoutParams imageNoTextLayoutParams;
    private LayoutParams centerLayoutParams;

    private int nowCheckedNum = -1;

    private OnTabMenuClickListener listener;
    protected LinearLayout menuContainer;
    protected RelativeLayout fragmentContainer;
    protected LayoutParams menuParams;
    protected LayoutParams fragmentParams;
    private FragmentManager fManager;
    private Context context;

    public FragmentTabMenuView(Context context) {
        this(context, null, 0);
        this.context = context;
    }

    public FragmentTabMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public FragmentTabMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs);
        final float scale = getContext().getResources().getDisplayMetrics().density;
        menuParams = new LayoutParams(
                tabWidth <= 0 ? tabWidth : (int) (tabWidth * scale + 0.5f),
                tabHeight);
        menuParams.addRule(ALIGN_PARENT_BOTTOM);

        fragmentParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        fragmentContainer = new RelativeLayout(context);
        fragmentContainer.setId(fragmentContainerID);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            fragmentContainer.setClipChildren(false);
            fragmentContainer.setClipToPadding(false);
        }

        fragmentParams.addRule(ABOVE, menuContainerID);
        fragmentParams.addRule(ALIGN_PARENT_TOP);
        this.addView(fragmentContainer, fragmentParams);

        menuContainer = new LinearLayout(context);
        menuContainer.setClipChildren(false);
        menuContainer.setClipToPadding(false);
        //if(bgContainer!=bgColor){
        // menuContainer.setBackgroundColor(bgContainer);
        menuContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_theme_ling));
//        menuContainer.setPadding(0, 1, 0, 0);
        //自己改的
        menuContainer.setPadding(0, 0, 0, 0);
        //}
        menuContainer.setId(menuContainerID);
        this.addView(menuContainer, menuParams);
    }

    private void init(Context context, AttributeSet attrs) {
        menuBeans = new ArrayList<>();
        layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabMenuView);
            menuType = typedArray.getInt(R.styleable.TabMenuView_menuType, MENU_TYPE_DRAWABLE_CHANGE);
            normalTextColor = typedArray.getColor(R.styleable.TabMenuView_normalTextColor, normalTextColor);
            checkedTextColor = typedArray.getColor(R.styleable.TabMenuView_checkedTextColor, checkedTextColor);
            normalDrawableTint = typedArray.getColor(R.styleable.TabMenuView_normalDrawableTint, normalTextColor);
            checkedDrawableTint = typedArray.getColor(R.styleable.TabMenuView_checkedDrawableTint, checkedDrawableTint);
            bgColor = typedArray.getColor(R.styleable.TabMenuView_bgColor, 0xFFFFFFFF);
            bgColorChecked = typedArray.getColor(R.styleable.TabMenuView_bgColorChecked, bgColor);
            //bgContainer=typedArray.getColor(R.styleable.TabMenuView_bgContainer,bgColor);
            topPadding = bottomPadding = typedArray.getDimension(R.styleable.TabMenuView_tbPadding, 0);
            topPadding = typedArray.getDimension(R.styleable.TabMenuView_topPadding, topPadding);
            bottomPadding = typedArray.getDimension(R.styleable.TabMenuView_bottomPadding, bottomPadding);
            menuSize = typedArray.getDimension(R.styleable.TabMenuView_menuSize, menuSize);
            closeClick = typedArray.getBoolean(R.styleable.TabMenuView_closeClick, false);
            tabHeight = typedArray.getDimensionPixelOffset(R.styleable.TabMenuView_tabMenuHeight, tabHeight);
            tabWidth = typedArray.getDimensionPixelOffset(R.styleable.TabMenuView_tabMenuWidth, tabWidth);
            typedArray.recycle();
        }
        setTextLayoutParams();
        setImageLayoutParams();
        //自己加的
        setNoTextImageLayoutParams();
    }

    public void setNormalTextColor(int normalTextColor) {
        this.normalTextColor = normalTextColor;
    }

    public void setCheckedTextColor(int checkedTextColor) {
        this.checkedTextColor = checkedTextColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public void setTextLayoutParams() {
        textLayoutParams = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        textLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
    }

    public void setImageLayoutParams() {
        imageLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        imageLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        imageLayoutParams.addRule(RelativeLayout.ABOVE, textID);
    }

    public void setNoTextImageLayoutParams() {
        imageNoTextLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        imageNoTextLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        imageNoTextLayoutParams.addRule(RelativeLayout.ABOVE, textID);
    }

    public LayoutParams getHintLayoutParams(float size) {
        LayoutParams hintLayoutParams = new LayoutParams(PixelUtil.dp2px(size), PixelUtil.dp2px(size));
        hintLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        hintLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        if (size < 8) {
            hintLayoutParams.setMargins(PixelUtil.dp2px(20), 0, 0, 0);
        } else {
            hintLayoutParams.setMargins(0, 0, PixelUtil.dp2px(14), 0);
        }
        return hintLayoutParams;
    }

    public void setCenterLayoutParams(int marginTop) {
        centerLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        centerLayoutParams.setMargins(0, -marginTop, 0, 0);
        centerLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        centerLayoutParams.addRule(RelativeLayout.ABOVE, textID);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void addMenu(String menu, int img, int imgChecked, Class<? extends Fragment> clazz) {
        MenuBean menuBean = new MenuBean();

        menuBean.normalSrc = img;
        menuBean.checkedSrc = imgChecked;

        menuBean.container = new RelativeLayout(getContext());
        menuBean.container.setId(containerID);
        menuBean.container.setLayoutParams(layoutParams);
        //menuBean.container.setBackgroundColor(bgColor);
        menuBean.container.setBackground(getResources().getDrawable(R.drawable.bg_theme_ling));

        menuBean.container.setGravity(RelativeLayout.CENTER_IN_PARENT);
        menuBean.container.setPadding(0, (int) topPadding, 0, (int) bottomPadding);
        menuBean.container.setOnClickListener(clickListener);
        menuBean.container.setTag(menuBeans.size());

        RelativeLayout mcon = new RelativeLayout(getContext());
        mcon.setClipChildren(false);
        mcon.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        menuBean.text = new TextView(getContext());
        menuBean.text.setLayoutParams(textLayoutParams);
        menuBean.text.setText(menu);
        menuBean.text.setId(textID);
        menuBean.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuSize);
        menuBean.text.setTextColor(normalTextColor);
        menuBean.text.setGravity(Gravity.CENTER);
        mcon.addView(menuBean.text);

        menuBean.iv = new ImageView(getContext());
        if (TextUtils.isEmpty(menu)) {
            menuBean.iv.setLayoutParams(imageNoTextLayoutParams);
        } else {
            menuBean.iv.setLayoutParams(imageLayoutParams);
        }

        menuBean.iv.setImageResource(img);
        menuBean.iv.setId(imageID);
        menuBean.iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        if (menuType == MENU_TYPE_DRAWABLE_TINT) {
            menuBean.iv.setColorFilter(normalDrawableTint);
        }
        mcon.addView(menuBean.iv);

        menuBean.hintTag = new TextView(getContext());
        menuBean.hintTag.setLayoutParams(getHintLayoutParams(5));
        menuBean.hintTag.setId(hintID);
        menuBean.hintTag.setTextColor(0xFFFFFFFF);
        menuBean.hintTag.setTextSize(8);
        menuBean.hintTag.setGravity(Gravity.CENTER);
        menuBean.hintTag.setVisibility(GONE);
        //menuBean.hintTag.setBackgroundResource(R.drawable.red_point);
        mcon.addView(menuBean.hintTag);

        menuBean.container.addView(mcon);
        menuBean.clazz = clazz;
        menuBeans.add(menuBean);
    }

    public void addCenterMenu(String menu, int img, int marginTop) {
        addCenterMenu(menu, img, img, marginTop, null);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void addCenterMenu(String menu, int img, int imgChecked, int marginTop, Class<? extends Fragment> clazz) {
        MenuBean menuBean = new MenuBean();

        menuBean.normalSrc = img;
        menuBean.checkedSrc = imgChecked;

        menuBean.container = new RelativeLayout(getContext());
        menuBean.container.setId(containerID);
        menuBean.container.setLayoutParams(layoutParams);
        //menuBean.container.setBackgroundColor(bgColor);
        //menuBean.container.setBackground(getResources().getDrawable(R.drawable.bg_theme_ling));

        menuBean.container.setGravity(RelativeLayout.CENTER_IN_PARENT);
        menuBean.container.setPadding(0, (int) topPadding, 0, (int) bottomPadding);
        menuBean.container.setOnClickListener(clickListener);
        menuBean.container.setTag(menuBeans.size());
        menuBean.container.setClipChildren(false);
        menuBean.container.setClipToPadding(false);
        this.setClipToPadding(false);
        this.setClipChildren(false);

        RelativeLayout mcon = new RelativeLayout(getContext());
        mcon.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        menuBean.text = new TextView(getContext());
        menuBean.text.setLayoutParams(textLayoutParams);
        menuBean.text.setText(menu);
        menuBean.text.setId(textID);
        menuBean.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuSize);
        menuBean.text.setTextColor(normalTextColor);
        menuBean.text.setGravity(Gravity.CENTER);
        menuBean.text.setVisibility(GONE);
        mcon.addView(menuBean.text);

        menuBean.iv = new ImageView(getContext());
        setCenterLayoutParams(marginTop);
        menuBean.iv.setLayoutParams(centerLayoutParams);
        menuBean.iv.setImageResource(img);
        menuBean.iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        menuBean.iv.setId(imageID);
        if (menuType == MENU_TYPE_DRAWABLE_TINT) {
            menuBean.iv.setColorFilter(normalDrawableTint);
        }
        mcon.addView(menuBean.iv);

        menuBean.container.addView(mcon);
        menuBean.clazz = clazz;
        menuBeans.add(menuBean);
    }

    public void setHintTag(int pos, String tag, float size) {
        if (checkMenuAt(pos)) {
            menuBeans.get(pos).hintTag.setLayoutParams(getHintLayoutParams(size));
            menuBeans.get(pos).hintTag.setText(tag);
            menuBeans.get(pos).hintTag.setVisibility(VISIBLE);
        }
    }

    public void setHintTag(int pos, boolean isShow) {
        if (checkMenuAt(pos)) {
            menuBeans.get(pos).hintTag.setText("");
            menuBeans.get(pos).hintTag.setVisibility(isShow ? VISIBLE : INVISIBLE);
        }
    }

    public void addMenu(String menu, int img, Class<Fragment> clazz) {
        this.addMenu(menu, img, img, clazz);
    }

    public void refreshMenuViewAt(FragmentManager fManager, int i) {
        if (this.fManager == null) {
            this.fManager = fManager;
        } else {
            fManager.getFragments().clear();
        }
        FragmentTransaction transaction = fManager.beginTransaction();
        menuContainer.removeAllViews();
        for (MenuBean menu : menuBeans) {
            menuContainer.addView(menu.container);
            if (menu.fragment != null) {
                transaction.hide(menu.fragment);
            }
        }
        transaction.commitAllowingStateLoss();
        checked(i);
    }

    public void refreshMenuView(FragmentManager fManager) {
        refreshMenuViewAt(fManager, -1);
    }

    public void setNowChecked(int nowChecked) {
        if (nowChecked != this.nowCheckedNum) {
            MenuBean menu = menuBeans.get(nowChecked);
            if (menu.clazz == null && menu.fragment == null) {

            } else {
                unChecked();
                checked(nowChecked);
            }
        }
    }

    public Fragment getFragment(int position) {
        return menuBeans.get(position).fragment;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void unChecked() {
        if (checkMenuAt(this.nowCheckedNum)) {
            MenuBean menu = menuBeans.get(this.nowCheckedNum);
            if (menuType == MENU_TYPE_DRAWABLE_TINT) {
                //menu.container.setBackgroundColor(bgColor);
                menu.container.setBackground(getResources().getDrawable(R.drawable.bg_theme_ling));

                menu.text.setTextColor(normalTextColor);
                menu.iv.setColorFilter(normalDrawableTint);
            } else {
                //menu.container.setBackgroundColor(bgColor);
                menu.container.setBackground(getResources().getDrawable(R.drawable.bg_theme_ling));

                menu.text.setTextColor(normalTextColor);
                menu.iv.setImageResource(menu.normalSrc);
            }
            if (menu.fragment != null) {
                fManager.beginTransaction().hide(menu.fragment).commitAllowingStateLoss();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void checked(int num) {
        this.nowCheckedNum = num;
        if (checkMenuAt(num)) {
            MenuBean menu = menuBeans.get(num);
            if (menuType == MENU_TYPE_DRAWABLE_TINT) {
                //menu.container.setBackgroundColor(bgColorChecked);
                menu.container.setBackground(getResources().getDrawable(R.drawable.bg_theme_ling));

                menu.text.setTextColor(checkedTextColor);
                menu.iv.setColorFilter(checkedDrawableTint);
            } else {
                //menu.container.setBackgroundColor(bgColorChecked);
                menu.container.setBackground(getResources().getDrawable(R.drawable.bg_theme_ling));

                menu.text.setTextColor(checkedTextColor);
                menu.iv.setImageResource(menu.checkedSrc);
            }
            if (menu.fragment != null) {
                fManager.beginTransaction().show(menu.fragment).commitAllowingStateLoss();
            } else {
                try {
                    menu.fragment = menu.clazz.newInstance();
                    fManager.beginTransaction().add(fragmentContainerID, menu.fragment).commitAllowingStateLoss();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private boolean checkMenuAt(int num) {
        return (num >= 0 && num < menuBeans.size()) ? true : false;
    }

    public class MenuBean {

        public int normalSrc;
        public int checkedSrc;

        public TextView text;
        public ImageView iv;
        public TextView hintTag;
        public RelativeLayout container;
        public Class<? extends Fragment> clazz;
        public Fragment fragment;
    }

    public void setOnTabMenuClickListener(OnTabMenuClickListener listener) {
        this.listener = listener;
    }

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int nowNum = v.getTag() == null ? 0 : (int) v.getTag();
            if (listener != null) {
                listener.onTabMenuClick(v, nowCheckedNum, nowNum);
            }
            if (!closeClick) {
                setNowChecked(nowNum);
            }
        }
    };

    public int getNowCheckedNum() {
        return nowCheckedNum;
    }
}
