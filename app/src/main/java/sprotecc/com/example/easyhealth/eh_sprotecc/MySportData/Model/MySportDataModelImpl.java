package sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Model;

import com.ruite.gem.modal.人员信息.UserInfo;
import com.ruite.gem.modal.运动健康.运动.Sport;
import com.ruite.gem.spdtp.ECC_SPORTDATA;
import com.ruite.gem.spdtp.SPDTP_HW_ZSLYEYDATA;

import java.util.Date;
import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.PushCardThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.UploadSportData;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.UserInfoThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Presenter.MySportDataPresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/16.
 */
public class MySportDataModelImpl implements MySportDataModel {
    private MySportDataPresenterImpl mySportDataPresenter;
    //获取的人员信息
    private UserInfo userInfo;
    //打开返回的人员信息

    //上传


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public MySportDataModelImpl(MySportDataPresenterImpl mySportDataPresenter) {
        this.mySportDataPresenter = mySportDataPresenter;
    }


    /**
     * 启动获取用户信息的 线程，从网络获取
     *
     * @param usercode
     */
    @Override
    public void startGetUserInfoThread(String usercode) {

    }

    /**
     * userinfo回调函数
     *
     * @param userInfo
     */
    @Override
    public void callbackUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        mySportDataPresenter.sportDataService();

    }

    /**
     * 存储用户信息在本地
     *
     * @param list
     */
    @Override
    public void storeUserInfo(List<UserInfo> list) {
        DataDao.getInstance().storeUserInfo(list);

    }

    /**
     * 从本地数据库获得用户信息
     *
     * @param code
     * @return
     */
    @Override
    public UserInfo getUserInfo(String code) {
        return DataDao.getInstance().getUserInfoByCode(code);
    }

    /**
     * 启动打卡线程
     *
     * @param code
     * @param date
     */
    @Override
    public void startPushCardThread(String code, Date date) {

    }

    /**
     * 打卡的回调
     *
     * @param userInfo
     * @param date
     */
    @Override
    public void callbackPushCard(UserInfo userInfo, Date date) {

    }

    /**
     * 存储考勤信息在本地。
     *
     * @param userInfo
     * @param date
     */
    @Override
    public void storePushCardInfo(UserInfo userInfo, Date date) {

    }

    /**
     * 上传运动数据
     *
     * @param
     */
    @Override
    public void startUploadSportThread() {
//        //取出刷卡数据，准备为List<Sport>
//        ECC_SPORTDATA ecc_sportdata = MyApplication.getInstance().getEcc_sportdata();
//        List<Sport> list = ecc_sportdata.getSportData();
//        MyThreadPool.getInstance().createLongPool().execute(new UploadSportData(this, list));
    }

    @Override
    public void callbackUploadSport(boolean b) {

    }
}
