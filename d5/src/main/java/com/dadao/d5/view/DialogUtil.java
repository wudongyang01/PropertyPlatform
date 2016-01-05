package com.dadao.d5.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dadao.d5.R;

public class DialogUtil {

	public static PopupWindow createLoadingDialog2(Context context, String msg) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.moudle_loading, null);// 得到加载view
		RelativeLayout layout = (RelativeLayout) v
				.findViewById(R.id.loading_wrap);// 加载布局
		TextView tv = (TextView) layout.findViewById(R.id.ml_tv_loading);
		tv.setText(msg);
		PopupWindow popPosition = new PopupWindow(v, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		popPosition.setFocusable(true);
		popPosition.setBackgroundDrawable(new BitmapDrawable());
		popPosition.setAnimationStyle(R.style.AnimFade);
		return popPosition;
	}
}
