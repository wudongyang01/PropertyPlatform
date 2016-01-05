package net.meluo.propertyplatform.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dadao.d5.utl.Checking;
import com.way.util.PreferenceUtils;

import net.meluo.propertyplatform.R;
import net.meluo.propertyplatform.app.DDApp;
import net.meluo.propertyplatform.app.DDEvent;
import net.meluo.propertyplatform.bean.User;
import net.meluo.propertyplatform.core.PreferenceConstants;

public class splashScreen extends BaseActivity {
	/**
	 * Called when t he activity is first created.
	 */

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.a_splash);

		String savedId = PreferenceUtils.getPrefString(mContext,
				PreferenceConstants.USER_ID, "");
		if (!Checking.isNullorBlank(savedId)) {

			acLogin();
		} else {
			new Handler().postDelayed(new Runnable() {
				public void run() {
					/*
					 * Create an Intent that will start the Main WordPress
					 * Activity.
					 */
					Intent mainIntent = new Intent(splashScreen.this,
							IndexActivity.class);
					splashScreen.this.startActivity(mainIntent);
					splashScreen.this.finish();

				}
			}, 2900);
		}

	}

	private void acLogin() {
		// 默认账号
		String savedId = PreferenceUtils.getPrefString(mContext,
				PreferenceConstants.USER_ID, "");
		String savePw = PreferenceUtils.getPrefString(mContext,
				PreferenceConstants.USER_PW, "");
		if (!Checking.isNullorBlank(savedId)) {

			tem_login(savedId, savePw);
		}

	}

	private void tem_login(final String id, final String pw) {
		DDApp.getInstance().user = new User();
		DDApp.getInstance().user.setMobile("15910637070");
		DDApp.getInstance().user.setNick("chulizi@126.com");
		DDApp.getInstance().user.setName(id);
		DDApp.getInstance().user.setSex(0);

		new Handler().postDelayed(new Runnable() {
			public void run() {

				Intent mainIntent = new Intent(splashScreen.this,
						IndexActivity.class);
				splashScreen.this.startActivity(mainIntent);
				splashScreen.this.finish();

			}
		}, 2900);

	}

	@Override
	public void onEvent(DDEvent event) {
		// TODO Auto-generated method stub
		super.onEvent(event);
	}

}