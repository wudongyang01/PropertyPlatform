package net.meluo.propertyplatform.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dadao.d5.view.TitleBar;

import net.meluo.propertyplatform.R;
import net.meluo.propertyplatform.bean.City;

public class CityWalkIndexActivity extends BaseActivity {

    Button mBtnNY, mBtnSF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_city_walk);
        initData();
        initView();

    }

    public static void actionStart(Context c) {
        c.startActivity(new Intent(c, CityWalkIndexActivity.class));
    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub
        super.initView();
        mTitleBar = (TitleBar) this.findViewById(R.id.title_bar);
        mTitleBar.setTitleText(this.getResources().getString(R.string.app_name));
        mTitleBar.enableLeftButton(true);
        mTitleBar.getLeftButton().setOnClickListener(this);

        mBtnNY = (Button) this.findViewById(R.id.ai_btn_ny);
        mBtnNY.setOnClickListener(this);
        mBtnSF = (Button) this.findViewById(R.id.ai_btn_sf);
        mBtnSF.setOnClickListener(this);
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

            case R.id.btn_left:
                this.finish();
                break;
            case R.id.ai_btn_ny:
                CityActivity.actionStart(this, new City("纽约"));
                break;
            case R.id.ai_btn_sf:
                CityActivity.actionStart(this, new City("旧金山"));
                break;
        }
    }

}
