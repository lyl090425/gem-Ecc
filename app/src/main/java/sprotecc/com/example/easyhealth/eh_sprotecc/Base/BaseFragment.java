package sprotecc.com.example.easyhealth.eh_sprotecc.Base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;

/**
 * Created by adminHjq on 2016/12/21.
 */
public abstract class BaseFragment <T, V extends BasePresenter<T>> extends Fragment {
    private  V mPresenter;
    private MyApplication myApplication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter=createPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);


    }
    protected  abstract  V createPresenter();
}
