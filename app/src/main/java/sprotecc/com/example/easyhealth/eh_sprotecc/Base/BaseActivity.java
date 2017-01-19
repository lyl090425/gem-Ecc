package sprotecc.com.example.easyhealth.eh_sprotecc.Base;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;

/**
 * Created by adminHjq on 2016/12/8.
 */
public abstract class BaseActivity<T, V extends BasePresenter<T>> extends AppCompatActivity {
    private  V mPresenter;
    private  MyApplication myApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mPresenter=createPresenter();
        myApplication=GetMyApplication();
        mPresenter.attachView((T) this);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
    @Override
    public void onWindowFocusChanged(final boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View deco = getWindow().getDecorView();
            deco.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

            );
        }

    }



    protected  abstract  V createPresenter();

    protected  abstract  MyApplication GetMyApplication();
    protected  abstract  void ViewInitialization();
}
