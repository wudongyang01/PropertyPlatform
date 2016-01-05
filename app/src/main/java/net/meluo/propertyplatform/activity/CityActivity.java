package net.meluo.propertyplatform.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.dadao.d5.view.TitleBar;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import net.meluo.propertyplatform.R;
import net.meluo.propertyplatform.bean.City;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityActivity extends BaseActivity {
    private City mCity;
    private SwipeRefreshLayout refreshLayout;

    private ArrayList<String> mDatas = new ArrayList<String>();

    private ConvenientBanner convenientBanner;

    private List<String> networkImages;
    private String[] images = {"http://pic.nipic.com/2008-05-07/200857115246609_2.jpg",
            "http://pic.nipic.com/2007-09-05/20079510185936_2.jpg",
            "http://img101.mypsd.com.cn/20120728/1/Mypsd_176980_201207281709280125B.jpg"
    };

    private Button mBtnCity,mBtnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_city);
        initData();
        initView();
        initImageLoader();
    }

    public static void actionStart(Context c, City city) {
        Intent i = new Intent(c, CityActivity.class);
        i.putExtra("data", city);
        c.startActivity(i);
    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub
        super.initView();
        mTitleBar = (TitleBar) this.findViewById(R.id.title_bar);
        mTitleBar.setTitleText(mCity.getName());
        mTitleBar.enableLeftButton(true);
        mTitleBar.getLeftButton().setOnClickListener(this);

        mBtnCity = (Button)this.findViewById(R.id.ac_btn_city);
        mBtnCity.setOnClickListener(this);
        mBtnMenu = (Button)this.findViewById(R.id.ac_btn_menu);
        mBtnMenu.setOnClickListener(this);

        convenientBanner = (ConvenientBanner) this.findViewById(R.id.ac_convenientBanner);
        networkImages = Arrays.asList(images);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages)
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
    }

    @Override
    protected void initData() {
        // TODO Auto-generated method stub
        super.initData();
        this.mCity = (City) this.getIntent().getSerializableExtra("data");
    }

    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.mipmap.ic_default_adimage)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        super.onClick(v);

        switch (v.getId()) {

            case R.id.btn_left:
                this.finish();
                break;
            case R.id.ac_btn_city:

                break;
            case R.id.ac_btn_menu:

                break;
        }
    }

    // 开始自动翻页
    @Override
    protected void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(3000);
    }

    // 停止自动翻页
    @Override
    protected void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }

}
