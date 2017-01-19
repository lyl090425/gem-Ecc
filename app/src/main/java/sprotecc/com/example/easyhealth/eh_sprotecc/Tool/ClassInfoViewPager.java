package sprotecc.com.example.easyhealth.eh_sprotecc.Tool;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 禁止左右滑动的ViewPager
 * Created by adminHjq on 2016/12/22.
 */
public class ClassInfoViewPager extends ViewPager {

    public ClassInfoViewPager(Context context) {
        super(context);
    }

    public ClassInfoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}