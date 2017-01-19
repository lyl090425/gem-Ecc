package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.CleanTable;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenter;

/**
 * Created by adminHjq on 2016/12/23.
 */
public interface CleanTablePresenter<T> extends BasePresenter<T> {

    //启动该启动的数据获取线程
    public void startServiceInfo();
    //值日表
    public void cleanTable();
}
