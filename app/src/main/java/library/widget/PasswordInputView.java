package library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dwz.mvvmdemo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * TODO: document your custom view class.
 */
public class PasswordInputView extends LinearLayout {
    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;
    private boolean isHide = false;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;
    private Context context;
    private List<EditText> passwordViewList = new ArrayList<>();

    public PasswordInputView(Context context) {
        super(context);
        init(null, 0,context);
    }

    public PasswordInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0,context);
    }

    public PasswordInputView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle,context);
    }

    private void init(AttributeSet attrs, int defStyle, Context context) {
        this.context = context;
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.PasswordInputView, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.PasswordInputView_passwordCustomString_);
        mExampleColor = a.getColor(
                R.styleable.PasswordInputView_passwordCustomColor_,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.PasswordInputView_passwordCustomDimension_,
                mExampleDimension);
        a.getBoolean(R.styleable.PasswordInputView_passwordInputHide,isHide);

        if (a.hasValue(R.styleable.PasswordInputView_passwordCustomDrawable_)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.PasswordInputView_passwordCustomDrawable_);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
//        mTextPaint = new TextPaint();
//        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
//        mTextPaint.setTextAlign(Paint.Align.LEFT);
//
//        // Update TextPaint and text measurements from attributes
//        invalidateTextPaintAndMeasurements();
        removeAllViews();
        passwordViewList.clear();
        addView(LayoutInflater.from(context).inflate(R.layout.layout_pwd_input,null));

        ViewGroup viewGroup = (ViewGroup)getChildAt(0);
        for(int i=0; i<viewGroup.getChildCount(); i++){
            EditText editText = (EditText) viewGroup.getChildAt(i);
            if(isHide){
                // 密文
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }else{
                // 明文
                editText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            passwordViewList.add(editText);
            editText.addTextChangedListener(new EditChangedListener(i));
            if(i != 0){
                editText.setOnKeyListener(new KeyListener(i));
            }
            editText.setTag(i);
            editText.setCursorVisible(false);
//            editText.setBackground(mExampleDrawable);
//            editText.setBackground(getResources().getDrawable(R.drawable.selector_pwd));
//            if(!editText.getText().toString().isEmpty()){
//                editText.setBackground(getResources().getDrawable(R.drawable.pwd_select));
//            }
            editText.setBackground(getResources().getDrawable(R.mipmap.pwd_unselect));
        }
    }

    public String getPasswordStr(){
        if(!judgePassWord()){
            return "";
        }

        String password = "";
        for(int i = 0; i < passwordViewList.size(); i++){
            password = password +  passwordViewList.get(i).getText().toString();
        }
        return password;

    }

    public boolean judgePassWord() {
        for (int i = 0; i < passwordViewList.size(); i++) {
            if (TextUtils.isEmpty(passwordViewList.get(i).getText().toString())) {
                return false;// 有一个密码不符合，就立即跳出
            }
        }
        return true;
    }

    public boolean re() {
        for (int i = 0; i < passwordViewList.size(); i++) {
            if (TextUtils.isEmpty(passwordViewList.get(i).getText().toString())) {
                return false;// 有一个密码不符合，就立即跳出
            }
        }
        return true;
    }

    class KeyListener implements OnKeyListener {
        private int position;
        public KeyListener(int positions){
            this.position = positions;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {


            if ((((EditText) v).getText().toString() == null || ((EditText) v)
                    .getText().toString().isEmpty())
                    && keyCode == KeyEvent.KEYCODE_DEL
                    && event.getAction() == KeyEvent.ACTION_DOWN) {
                // 该EditText的 内容已为空，并且 del 键按下
                v.clearFocus();// 清除该控件焦点
                // 将焦点给到前面一个EditText
                EditText editText = passwordViewList.get(position-1);
                editText.setText("");
                 editText.requestFocus(); //也可以
//                editText.requestFocusFromTouch();
//                editText.setBackground(getResources().getDrawable(R.drawable.pwd_select));
            }

            return false;
        }
    };

    class EditChangedListener implements TextWatcher {
        private CharSequence temp; // 监听前的文本
        private int editStart; // 光标开始位置
        private int editEnd; // 光标结束位置
        private int position;

        public EditChangedListener(int positions){
            this.position = positions;
        }
        // 输入文本之前的状态
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        // 输入文字中的状态，count是一次性输入字符数
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        // 输入文字后的状态
        @Override
        public void afterTextChanged(Editable s) {
            if (s != null && s.length() == 1) {
                if (position < passwordViewList.size() - 1) {// 焦点后移
                    passwordViewList.get(position).clearFocus();
                    passwordViewList.get(position+1).requestFocus();
                }
                setFlag(false, position);// 对应标志位 置 1
//            passwordViewList.get(position)
//                Toast.makeText(context,position+"    "+s.toString(),Toast.LENGTH_SHORT).show();
              if(passwordViewList.size()==6 && judgePassWord()){//判断输入是否完整
                  StringBuffer inputText = new StringBuffer("");
                  for (int i = 0; i < 6; i++) {
                      inputText.append(passwordViewList.get(i).getText());
                  }
                  fListener.inputFinished(inputText.toString());
              }
            }
            else{
                setFlag(true, position);
            }

        }
    };
    private OnPswInputFinishedListener fListener=null;
    public interface OnPswInputFinishedListener{
        void inputFinished(String inputText);
    }
    public void setOnPswInputFinishedListener(OnPswInputFinishedListener mListener){
        this.fListener = mListener;
    }

    private byte flag = 0x00;
    private void setFlag(boolean isNull, int index) {
        // 得到 唯一一个 1的二进制数 00001000
        byte b = (byte) (1 << (index - 1));
        if (isNull) {// 指定 位 清零
            b = (byte) ~b; // 11110111
            flag = (byte) (flag & b);
        } else {// 制定位 赋值 1
            flag = (byte) (flag | b);
        }
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    public String getExampleString() {
        return mExampleString;
    }

    public void setExampleString(String exampleString) {
        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    public int getExampleColor() {
        return mExampleColor;
    }

    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    public float getExampleDimension() {
        return mExampleDimension;
    }

    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}
