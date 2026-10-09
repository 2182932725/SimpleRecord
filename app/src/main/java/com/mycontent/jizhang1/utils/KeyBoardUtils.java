package com.mycontent.jizhang1.utils;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.mycontent.jizhang1.R;

public class KeyBoardUtils {
    private final Keyboard k1;    //自定义的键盘
    private KeyboardView keyboardView;   //KeyboardView是库中的一个类
    private EditText editText;


    public interface OnEnsureListener{
        public void onEnsure();
    }
    OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public KeyBoardUtils(KeyboardView keyboardView, EditText editText) {
        this.keyboardView = keyboardView;
        this.editText = editText;
        this.editText.setInputType(InputType.TYPE_NULL);   //取消弹出系统键盘（需要弹出我们自己定义的键盘）
        k1 = new Keyboard(this.editText.getContext(), R.xml.key);


        this.keyboardView.setKeyboard(k1);
        this.keyboardView.setEnabled(true);   //让自定义键盘可以使用
        this.keyboardView.setPreviewEnabled(false);  //让自定义键盘优先使用
        this.keyboardView.setOnKeyboardActionListener(listener);   //设置键盘按钮被点击了的监听
    }

    KeyboardView.OnKeyboardActionListener listener=new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int i) {
        }

        @Override
        public void onRelease(int i) {
        }

        @Override
        public void onKey(int i, int[] ints) {
            Editable editable=editText.getText();
            int start = editText.getSelectionStart();
            switch (i) {
                case Keyboard.KEYCODE_DELETE:   //点击了删除键
                    if (editable!=null&&editable.length()>0){
                        if (start>0){
                            editable.delete(start-1,start);
                        }
                    }
                    break;
                case Keyboard.KEYCODE_CANCEL:    //点击了清零
                    editable.clear();
                    break;
                case Keyboard.KEYCODE_DONE:     //点击了完成
                    onEnsureListener.onEnsure();    //通过接口回调的方法，通过点击确定，可以调用这个方法
                    break;
                default:
                    editable.insert(start,Character.toString((char)i));
                    break;

            }
        }

        @Override
        public void onText(CharSequence charSequence) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };


    //显示键盘
    public void showKeyboard(){
        int visibility=keyboardView.getVisibility();
        if(visibility== View.INVISIBLE||visibility==View.GONE){
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    //隐藏键盘
    public void hideKeyboard(){
        int visibility = keyboardView.getVisibility();
        if (visibility==View.VISIBLE||visibility==View.INVISIBLE){
            keyboardView.setVisibility(View.GONE);
        }
    }
}
