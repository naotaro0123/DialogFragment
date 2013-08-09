package com.example.dialogfragment;

import android.os.Bundle;
import android.view.Menu;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener{
    private TextView text;      // テキスト
    private Button startBtn;    // ボタン

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // テキスト
        text = (TextView)findViewById(R.id.textView1);
        // ボタン
        startBtn = (Button) findViewById(R.id.button1);
        startBtn.setOnClickListener(this);
    }

    /*
     * ボタンクリックイベント
     */
	@Override
    public void onClick(View v) {
        // スタートボタン
        if (v == startBtn) {
            CustomDialogFragment dialog = new CustomDialogFragment();
            dialog.show(getSupportFragmentManager(), "dialog");
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
    @SuppressLint("ValidFragment")
    public class CustomDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Dialog dialog = new Dialog(getActivity());
            // ダイアログのアニメーション
            dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_CustomDialog;
            // タイトル非表示
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            // フルスクリーン
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            dialog.setContentView(R.layout.dialog_custom);
            // 背景を透明にする
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // ダイアログの外側クリックしてもdismissさせない
            dialog.setCanceledOnTouchOutside(false);
            // メッセージを渡す
            TextView message = (TextView) dialog.findViewById(R.id.message);
            message.setText(text.getText());
            // OK ボタンのリスナ
            dialog.findViewById(R.id.positive_button).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            // Close ボタンのリスナ
            dialog.findViewById(R.id.close_button).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            return dialog;
        }
    }
}
