package com.example.monotonewang.xtablayout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();

        XTabLayout xTabLayout = findViewById(R.id.tabsFour);
        ViewPager viewPager = findViewById(R.id.view_pager);


        ChannelAdapter channelAdapter = new ChannelAdapter(getSupportFragmentManager(), getFragmentList(), getContext());
        viewPager.setAdapter(channelAdapter);

        String name = "我要中国";
        channelAdapter.setFirstIndicatorWidth(name, xTabLayout);

        xTabLayout.setupWithViewPager(viewPager);

        channelAdapter.addIndicatorWidthListener(xTabLayout);

        for (int i = 0; i < xTabLayout.getTabCount(); i++) {

            XTabLayout.Tab tab = xTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(channelAdapter.getTabView(i, list.get(i), xTabLayout));
            }
        }
    }

    private void initDatas() {
        list = new ArrayList<>();
        list.add("葡萄大师");
        list.add("香蕉");
        list.add("猕猴桃");
        list.add("无敌大菠萝");

    }

    private Context getContext() {
        return MainActivity.this;
    }

    private List<Fragment> getFragmentList() {
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(ChannelFragment.newInstance(list.get(0)));
        listFragment.add(ChannelFragment.newInstance(list.get(1)));
        listFragment.add(ChannelFragment.newInstance(list.get(2)));
        listFragment.add(ChannelFragment.newInstance(list.get(3)));
        return listFragment;
    }
}
