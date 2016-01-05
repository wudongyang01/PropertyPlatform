package net.meluo.propertyplatform.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import net.meluo.propertyplatform.R;

public class IndexActivity extends BaseActivity {

    Button mBtnIndex0, mBtnIndex1, mBtnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_index);
        initData();
        initView();

    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub
        super.initView();
        mBtnInfo = (Button) this.findViewById(R.id.ai_btn_info);
        mBtnIndex0 = (Button) this.findViewById(R.id.ai_btn_0);
        mBtnIndex1 = (Button) this.findViewById(R.id.ai_btn_1);

        mBtnInfo.setOnClickListener(this);
        mBtnIndex0.setOnClickListener(this);
        mBtnIndex1.setOnClickListener(this);

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
            case R.id.ai_btn_info:
                this.startActivity(new Intent(this, InfoActivity.class));
                break;
            case R.id.ai_btn_0:
                CityWalkIndexActivity.actionStart(this);
                break;
            case R.id.ai_btn_1:
                OnSaleActivity.actionStart(this);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //EventBus.getDefault().post(new DDEvent(DDEvent.E_APP_HIDE));
            moveTaskToBack(false);
            return true;
        }
        return true;
    }
}
