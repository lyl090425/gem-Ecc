package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSport;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenter;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;

/**
 * Created by adminHjq on 2016/12/21.
 */
public interface ClassSportPresenter<T> extends BasePresenter<T>{
    //启动线程
    public void startServiceInfo();
    //显示内容
    public void showServiceInfo();


    //班级历史
    public void classSportHistory();
}
