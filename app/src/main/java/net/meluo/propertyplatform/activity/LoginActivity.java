package net.meluo.propertyplatform.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dadao.d5.utl.Checking;
import com.dadao.d5.utl.WindowUtl;
import com.dadao.d5.view.DialogUtil;
import com.dadao.d5.view.TitleBar;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.way.util.L;
import com.way.util.PreferenceUtils;
import com.way.util.T;

import net.meluo.propertyplatform.R;
import net.meluo.propertyplatform.app.DDApp;
import net.meluo.propertyplatform.bean.User;
import net.meluo.propertyplatform.core.PreferenceConstants;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity {

    private TimeCount time;

    private Button btnLogin,mBtnCheck;
    private EditText edtId, edtPw;

    private String id;
    private String password;

    private static final int LOGIN = 0x001;
    private static final int COMPLETE = 0x002;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtl.setTranslucentStatus(this);
        setContentView(R.layout.a_login);
        initView();

    }

    protected void initView() {

        mTitleBar = (TitleBar) this.findViewById(R.id.title_bar);
        mTitleBar.setTitleText(this.getResources().getString(R.string.app_name));

        edtId = (EditText) this.findViewById(R.id.edt_id);
        edtPw = (EditText) this.findViewById(R.id.edt_pw);

        btnLogin = (Button) this.findViewById(R.id.al_btn_login);
        btnLogin.setOnClickListener(this);

        mBtnCheck = (Button) this.findViewById(R.id.al_btn_check);
        mBtnCheck.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        super.onClick(v);

        switch (v.getId()) {
            case R.id.al_btn_login:
                id = edtId.getText().toString();
                password = edtPw.getText().toString();
                if (TextUtils.isEmpty(id)) {
                    T.showShort(this, "请填写用户名");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    T.showShort(this, "请填写密码");
                    return;
                }

                break;
            case R.id.al_btn_check:
                getCheck();
                break;
        }
    }


    private void login(final String id, final String pw) {
        mLoadingDialog = DialogUtil
                .createLoadingDialog2(mContext, "loading...");
        mLoadingDialog.showAtLocation(this.getCurrentFocus(), Gravity.CENTER,
                0, 0);

        Ion.with(mContext, PreferenceConstants.BASE_URL + "/Account/login")
                .setBodyParameter("username", id).setBodyParameter("pwd", pw)
                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                mLoadingDialog.dismiss();
                if (e != null) {
                    T.showShort(mContext, R.string.net_error);
                    return;
                }
                if (result.isJsonNull()) {
                    T.showShort(mContext, R.string.net_error);
                    return;
                }
                if (result.get("retCode").getAsInt() != 0) {
                    if (!result.has("errMsg")) {
                        T.showShort(mContext, R.string.net_error);
                    } else {
                        T.showShort(mContext, result.get("errMsg")
                                .getAsString());
                    }
                    return;
                }
                save2Preferences(id, pw);
                DDApp.getInstance().user = new User();
                DDApp.getInstance().user.fromJSONString(result
                        .get("user").getAsJsonObject().toString());

            }
        });
    }

    private void getCheck() {

        if (Checking.isNullorBlank(edtId.getText().toString())) {
            T.showShort(mContext, "请输入手机号");
            return;
        }

        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
        time.start();

        Ion.with(this,
                PreferenceConstants.BASE_URL0 + "/Common/1/sms")
                .setMultipartParameter("mobile", edtId.getText().toString())
                .setMultipartParameter("f", "reg").asString()
                .setCallback(new FutureCallback<String>() {

                    @Override
                    public void onCompleted(Exception arg0, String data) {
                        time.cancel();
                        mBtnCheck.setClickable(true);
                        if (Checking.isNullorBlank(data)) {
                            mBtnCheck.setText("获取失败，点击重试");
                            return;
                        }
                        L.d("getCheck", data);
                        try {
                            JSONObject o = new JSONObject(data);
                            if (o.getInt("retcode") == 0) {
                                T.showShort(mContext, "获取成功，验证码稍后会发送到您的手机上。");
                                mBtnCheck.setText("重新获取");
                            } else {
                                // TODO 获取失败，通知用户再次获取
                                T.showShort(mContext, o.getString("errmsg"));
                                mBtnCheck.setText("获取失败，点击重试");
                            }

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });

    }




    private void save2Preferences(String id, String pw) {

        PreferenceUtils
                .setPrefString(mContext, PreferenceConstants.USER_ID, id);
        PreferenceUtils
                .setPrefString(mContext, PreferenceConstants.USER_PW, pw);

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        // JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // JPushInterface.onResume(this);
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            mBtnCheck.setText("重新验证");
            mBtnCheck.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            mBtnCheck.setClickable(false);
            mBtnCheck.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}
