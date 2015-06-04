package com.gc.materialdesign.widgets;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gc.materialdesign.R;
import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;

public class ProgressDialog extends android.app.Dialog {

	Context context;
	View view;
	View backView;
	String title;
	TextView titleTextView;

	int progressColor = -1;

	public ProgressDialog(Context context, String title) {
		super(context, android.R.style.Theme_Translucent);
		this.title = title;
		this.context = context;
	}

	public ProgressDialog(Context context, String title, int progressColor) {
		super(context, android.R.style.Theme_Translucent);
		this.title = title;
		this.progressColor = progressColor;
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 去掉信息栏
		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_dialog);

		view = (RelativeLayout) findViewById(R.id.contentDialog);
		backView = (RelativeLayout) findViewById(R.id.dialog_rootView);
		/* 禁用触碰后dismiss */
		/*
		 * backView.setOnTouchListener(new OnTouchListener() {
		 * 
		 * @Override public boolean onTouch(View v, MotionEvent event) { if
		 * (event.getX() < view.getLeft() || event.getX() > view.getRight() ||
		 * event.getY() > view.getBottom() || event.getY() < view.getTop()) {
		 * dismiss(); } return false; } });
		 */

		this.titleTextView = (TextView) findViewById(R.id.title);
		setTitle(title);
		if (progressColor != -1) {
			ProgressBarCircularIndeterminate progressBarCircularIndeterminate = (ProgressBarCircularIndeterminate) findViewById(R.id.progressBarCircularIndetermininate);
			progressBarCircularIndeterminate.setBackgroundColor(progressColor);
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			return true;// 消费掉后退键
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void show() {
		// TODO 自动生成的方法存根
		super.show();
		// set dialog enter animations
		view.startAnimation(AnimationUtils.loadAnimation(context,
				R.anim.dialog_main_show_amination));
		backView.startAnimation(AnimationUtils.loadAnimation(context,
				R.anim.dialog_root_show_amin));
	}

	// GETERS & SETTERS

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		if (title == null)
			titleTextView.setVisibility(View.GONE);
		else {
			titleTextView.setVisibility(View.VISIBLE);
			titleTextView.setText(title);
		}
	}

	public TextView getTitleTextView() {
		return titleTextView;
	}

	public void setTitleTextView(TextView titleTextView) {
		this.titleTextView = titleTextView;
	}
	/* 注意：此处会引起窗体内存泄漏，建议永久注释 */
	// @Override
	// public void dismiss() {
	// Animation anim = AnimationUtils.loadAnimation(context,
	// R.anim.dialog_main_hide_amination);
	// anim.setAnimationListener(new AnimationListener() {
	//
	// @Override
	// public void onAnimationStart(Animation animation) {
	// }
	//
	// @Override
	// public void onAnimationRepeat(Animation animation) {
	// }
	//
	// @Override
	// public void onAnimationEnd(Animation animation) {
	// view.post(new Runnable() {
	// @Override
	// public void run() {
	// ProgressDialog.super.dismiss();
	// }
	// });
	//
	// }
	// });
	// Animation backAnim = AnimationUtils.loadAnimation(context,
	// R.anim.dialog_root_hide_amin);
	//
	// view.startAnimation(anim);
	// backView.startAnimation(backAnim);
	// }

}
