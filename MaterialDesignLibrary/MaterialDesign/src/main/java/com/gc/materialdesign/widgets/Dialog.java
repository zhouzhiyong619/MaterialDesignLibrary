package com.gc.materialdesign.widgets;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gc.materialdesign.R;
import com.gc.materialdesign.views.ButtonFlat;

public class Dialog extends android.app.Dialog {

    Context mContext;
    View mRootView;
    View mContentView;
    ImageView mIcon;
    ScrollView mScrollView;
    TextView mMsg;
    TextView mTitle;
    FrameLayout mInnerView;

    ButtonFlat mOkBtn;
    ButtonFlat mCancelBtn;

    public Dialog(Context context) {
        this(context, "", "");
    }

    public Dialog(Context context, String title, String message) {
        super(context, R.style.CustomDialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        mTitle = (TextView) findViewById(R.id.title);
        mIcon = (ImageView)findViewById(R.id.icon);
        mRootView = findViewById(R.id.dialog_rootView);
        mContentView = findViewById(R.id.contentDialog);
        mInnerView = (FrameLayout) findViewById(R.id.inner_view);
        mMsg = (TextView) findViewById(R.id.message);
        mCancelBtn = (ButtonFlat) findViewById(R.id.button_cancel);
        mOkBtn = (ButtonFlat) findViewById(R.id.button_accept);
        mScrollView = (ScrollView) findViewById(R.id.message_scrollView);
        mContext = context;
        setTitle(title);
        setMessage(message);
        this.setCanceledOnTouchOutside(true);

    }


    public ButtonFlat getCancelBtn() {
        return mCancelBtn;
    }

    public ButtonFlat getOkBtn() {
        return mOkBtn;
    }


    public void setTitle(CharSequence title) {
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText(title);
    }

    public void setIcon(int resId){
        mIcon.setVisibility(View.VISIBLE);
        mIcon.setImageResource(resId);
    }

    public void setMessage(CharSequence msg) {
        mScrollView.setVisibility(View.VISIBLE);
        mMsg.setText(msg);
    }

    public void setMessage(int msg) {
        mScrollView.setVisibility(View.VISIBLE);
        mMsg.setText(msg);
    }

    public void setContentView(View view) {
        mInnerView.removeAllViews();
        mInnerView.addView(view);
        mScrollView.setVisibility(View.VISIBLE);
    }

    public void setOkBtn(int id, View.OnClickListener listener) {
        setOkBtn(id, listener, true);
    }

    public void setOkBtn(int id, View.OnClickListener listener, boolean isAutoDismiss) {
        mOkBtn.setVisibility(View.VISIBLE);
        mOkBtn.setOnClickListener(isAutoDismiss ? new ExternalListener(listener) : listener);
        mOkBtn.setText(mContext.getString(id));
    }

    public void setOkBtnBg(int bgId) {
        mOkBtn.setBackgroundResource(bgId);
    }

    public void setCancelBtn(int id, View.OnClickListener listener) {
        setCancelBtn(id, listener, true);
    }

    public void setCancelBtn(int id, View.OnClickListener listener, boolean isAutoDismiss) {
        mCancelBtn.setVisibility(View.VISIBLE);
        mCancelBtn.setOnClickListener(isAutoDismiss ? new ExternalListener(listener) : listener);
        mCancelBtn.setText(mContext.getString(id));
    }


    private class ExternalListener implements View.OnClickListener {
        private View.OnClickListener mListener;

        public ExternalListener(View.OnClickListener listener) {
            mListener = listener;
        }

        @Override
        public void onClick(View v) {
            dismiss();
            if (mListener != null) {
                mListener.onClick(v);
            }
        }
    }
}
