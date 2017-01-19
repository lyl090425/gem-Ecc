package sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Presenter;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenter;

/**
 * Created by adminHjq on 2016/12/16.
 */
public interface MySportDataPresenter<T> extends BasePresenter<T>{
    //本地
    public void sportData();

    //从网络获取信息的回调
    public void sportDataService();
    //打卡信息处理
    public void pushCard();
    // 上报运动信息
    public void upLoadSportData();

}
