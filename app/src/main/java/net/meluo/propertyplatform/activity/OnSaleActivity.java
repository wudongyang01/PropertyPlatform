package net.meluo.propertyplatform.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dadao.d5.view.TitleBar;

import net.meluo.propertyplatform.R;

public class OnSaleActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_on_sale);
        initData();
        initView();

    }
    public static void actionStart(Context c ){
        c.startActivity(new Intent(c,OnSaleActivity.class));
    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub
        super.initView();
        mTitleBar = (TitleBar) this.findViewById(R.id.title_bar);
        mTitleBar.setTitleText(this.getResources().getString(R.string.app_name));
        mTitleBar.enableLeftButton(true);
        mTitleBar.getLeftButton().setOnClickListener(this);



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
        }
    }

}
