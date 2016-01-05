package net.meluo.propertyplatform.core;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import com.way.util.PreferenceUtils;
import com.way.util.T;

import net.meluo.propertyplatform.app.DDApp;

public class User {
	public static final boolean isFirst(Context context) {

		return PreferenceUtils.getPrefBoolean(context, "IS_FIRST", true);
	}

	public static final void setFirst(Context context, boolean isFirst) {

		PreferenceUtils.setPrefBoolean(context, "IS_FIRST", isFirst);
	}

	public static final boolean isLogin() {
		return DDApp.getInstance().user != null;
	}

	public static final void updateToServer(final Context context) {
		JSONObject user = new JSONObject();
		try {
			user.put("name", DDApp.getInstance().user.getName());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ion.with(context, PreferenceConstants.BASE_URL + "/1/Clients")
				.setBodyParameter("items", user.toString()).asJsonObject()
				.setCallback(new FutureCallback<JsonObject>() {

					@Override
					public void onCompleted(Exception arg0, JsonObject result) {

						if (result == null) {
							T.showShort(context, "网络错误，请稍后重试");
							return;
						}
						if (result.isJsonNull()) {
							T.showShort(context, "网络错误，请稍后重试");
							return;
						}

						if (result.get("retcode").getAsInt() != 0) {
							T.showShort(context, "网络错误，请稍后重试");
							return;
						}

					}
				});

	}

}
