package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.ConductPiont;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenter;

/**
 * Created by adminHjq on 2017/1/3.
 */
public interface ConductPointPresenter<T> extends BasePresenter<T>{

    public void conductPoints();

    public void startThread();
    public void showInfo();
}
