package sprotecc.com.example.easyhealth.eh_sprotecc.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * classinfo的ViewPager适配器
 * Created by adminHjq on 2016/12/21.
 */
public class MyViewPager_classinfo_Adapter extends FragmentPagerAdapter {
    List<Fragment> list ;

    public MyViewPager_classinfo_Adapter(FragmentManager fm, List<Fragment> list) {

        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


}
