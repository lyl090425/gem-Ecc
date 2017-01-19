package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSubTable;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenter;

/**
 * Created by adminHjq on 2016/12/23.
 */
public interface ClassSubTablePresenter<T> extends BasePresenter<T> {
    //启动该Fragement的线程
    public  void startServiceInfo();
    //显示
    public  void showServiceInfo();
    //课表
    public void subTable();
}
