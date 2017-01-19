package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Presenter;

import android.os.Handler;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenter;

/**
 * Created by adminHjq on 2016/12/8.
 */
public interface StandbyPresenter<T> extends BasePresenter<T> {

    //获取USB传输的信息
    public  void SwingCard();
    //初始化
    public void setInitialization();
    //考勤
    public void Attendance();
    //定时从服务器获取数据，存储到本地数据库。
    public void startServiceInfo();
    //定时从数据库更新数据到页面
    public void showServiceInfo();
    //班级用户
    public void userList();
    //班级信息
    public void clazzInfo();


}
