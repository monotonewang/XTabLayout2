package com.example.monotonewang.xtablayout;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ChannelAdapter extends FragmentPagerAdapter {

    private final String TAG = getClass().getSimpleName();
    private List<String> mTitle;
    private List<Fragment> fragments;
    private Context context;

    public ChannelAdapter(FragmentManager fm, List<String> mTitile, List<Fragment> fragments) {
        super(fm);
        this.mTitle = mTitile;
        this.fragments = fragments;
    }


    public ChannelAdapter(FragmentManager fm, List<Fragment> fragments, Context context) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
    }

    public View getTabView(int position, String string, XTabLayout xTabLayout) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_channel_tab, null);
        ImageView imageView = view.findViewById(R.id.iv_tab);
        TextView textView = view.findViewById(R.id.text_view);
        textView.setText(string);

        setTextViewId(R.id.text_view, xTabLayout);

        return view;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mTitle.get(position);
//
//    }


    private void settingMargin(ViewGroup.MarginLayoutParams layoutParams, int start, int end) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.setMarginStart(start);
            layoutParams.setMarginEnd(end);
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        } else {
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        }
    }


    public void setTextViewId(@IdRes int id, XTabLayout xTabLayout) {
        xTabLayout.setTextViewId(id);
    }

    public void setFirstIndicatorWidth(String name, XTabLayout xTabLayout) {
        Paint pFont = new Paint();
        pFont.setTextSize(sp2px(getContext(), 10));
        Rect rect = new Rect();
        //返回包围整个字符串的最小的一个Rect区域
        pFont.getTextBounds(name, 0, name.length(), rect);
        int strwid = rect.width();
        xTabLayout.setSelectedTabIndicatorWidth(strwid);
    }

    public Context getContext() {
        return context;
    }

    public void addIndicatorWidthListener(final XTabLayout xTabLayout) {
        xTabLayout.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                try {

                    TextView textView = tab.getCustomView().findViewById(xTabLayout.getTextViewId());
                    int width1 = textView.getWidth();
                    xTabLayout.setSelectedTabIndicatorWidth(width1);

                } catch (Exception e) {
                }
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
            }
        });
    }


    /**
     * sp转px
     */
    public int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public String getSelectText(XTabLayout xTabLayout) {
        int selectedTabPosition = xTabLayout.getSelectedTabPosition();
        XTabLayout.Tab tabAt = xTabLayout.getTabAt(selectedTabPosition);
        if (tabAt != null && tabAt.getCustomView() != null) {
            TextView textView = tabAt.getCustomView().findViewById(R.id.text_view);
            CharSequence text = textView.getText();
            return text.toString();
        } else {
            return "";
        }


    }
}


//            int dp10 = DensityUtils.dp2px(tabLayout.getContext(), 10);
//
//            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
//                View tabView = mTabStrip.getChildAt(i);
//
//                //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
//                Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
//                mTextViewField.setAccessible(true);
//
//                TextView mTextView = (TextView) mTextViewField.get(tabView);
//
//                tabView.setPadding(0, 0, 0, 0);
//
//                //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
//                int width = 0;
//                width = mTextView.getWidth();
//                if (width == 0) {
//                    mTextView.measure(0, 0);
//                    width = mTextView.getMeasuredWidth();
//                }
//
//                //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
//                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
//                params.width = width;
//                params.leftMargin = dp10;
//                params.rightMargin = dp10;
//                tabView.setLayoutParams(params);
//
//                tabView.invalidate();
//            }

//            }
//        });


