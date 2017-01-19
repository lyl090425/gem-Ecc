package sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.Presenter;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenter;

/**
 * Created by adminHjq on 2016/12/19.
 */
public interface MyInformationPresenter<T> extends BasePresenter<T>{
    //启动线程
    public void startServiceInfo();
    //显示内容
    public void showServiceInfo();


    //个人历史
    public void MySportHistory();
    //个人荣誉
    public void MyHonor();
    //个人今日运动
    public void myTodaySport();
}
