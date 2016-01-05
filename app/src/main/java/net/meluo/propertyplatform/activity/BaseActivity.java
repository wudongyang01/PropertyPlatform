package net.meluo.propertyplatform.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.PopupWindow;

import com.dadao.d5.utl.WindowUtl;
import com.dadao.d5.view.TitleBar;
import com.ypy.eventbus.EventBus;

import net.meluo.propertyplatform.R;
import net.meluo.propertyplatform.app.DDEvent;
import net.meluo.propertyplatform.fragment.BaseFragment;

import java.util.ArrayList;


public class BaseActivity extends FragmentActivity implements
        View.OnClickListener {
    public static ArrayList<BackPressHandler> mListeners = new ArrayList<BackPressHandler>();
    Context mContext;

    PopupWindow mLoadingDialog = null;
    TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub

        super.onCreate(arg0);
        WindowUtl.setTranslucentStatus(this);
        mContext = this;
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    protected void initView() {

    }

    protected void initData() {

    }

    public void ReplaceFragmentToStack(int id, BaseFragment fragment,
                                       boolean isAddToStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        // ft.setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out);
        ft.replace(id, fragment);
        if (isAddToStack) {
            ft.addToBackStack("fragment");
        }

        ft.commit();
    }

    public void addFragmentToStack(int id, BaseFragment fragment,
                                   boolean isAddToStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);

        ft.add(id, fragment);
        if (isAddToStack) {
            ft.addToBackStack("fragment");
        }

        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mListeners.size() > 0)
            for (BackPressHandler handler : mListeners) {
                handler.activityOnResume();
            }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mListeners.size() > 0)
            for (BackPressHandler handler : mListeners) {
                handler.activityOnPause();
            }
    }

    public interface BackPressHandler {

        void activityOnResume();

        void activityOnPause();

    }

    public void onEvent(DDEvent event) {
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

}
