package net.meluo.propertyplatform.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.meluo.propertyplatform.R;

public class InfoActivity extends BaseActivity {

    Button mBtnLogin, mBtnIndex1, mBtnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_info);
        initData();
        initView();

    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub
        super.initView();
        mBtnLogin = (Button) this.findViewById(R.id.ai_btn_login);
        mBtnLogin.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        // TODO Auto-generated method stub
        super.initData();


    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        super.onClick(v);

        switch (v.getId()) {
            case R.id.ai_btn_login:
                this.startActivity(new Intent(this, LoginActivity.class));
                break;

        }
    }
}
