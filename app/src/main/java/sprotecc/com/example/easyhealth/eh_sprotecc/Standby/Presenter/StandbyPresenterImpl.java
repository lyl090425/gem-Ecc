package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Presenter;


import android.util.Log;
import android.view.View;

import com.ruite.gem.modal.人员信息.UserInfo;
import com.ruite.gem.modal.组织信息.Clazz;
import com.ruite.gem.spdtp.ECC_SPORTDATA;
import com.ruite.gem.spdtp.SPDTP_HW_ZSLYEYDATA;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.TopNews;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.WeatherThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzAttendance;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.Hardware;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.SoftInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Model.StandbyModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.View.StandbyActivity;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.View.StandbyView;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * StandbyActivity主导器
 * Created by adminHjq on 2016/12/8.
 */
public class StandbyPresenterImpl extends BasePresenterImpl<StandbyView> implements StandbyPresenter<StandbyView> {
    //待机页面View
    StandbyView standbyView;
    //获取待机页面的Modle
    StandbyModelImpl standbyModelImpl = new StandbyModelImpl(this);

    public StandbyPresenterImpl(StandbyActivity standbyActivity) {
        this.standbyView = standbyActivity;
        //启动USB接收数据的线程
         standbyModelImpl.startUsbInformationThread();
        //启动获取班级用户列表进程，更新班级人员信息
        standbyModelImpl.startUserListThread();
    }

    @Override
    /**
     *刷卡
     */
    public void SwingCard() {

        byte[] bytes = standbyModelImpl.getBytes();
        int i = bytes.length;
        //这里还存在许多操作是没有过滤的，在进行第一次验收后加上数据接收判断的类，来对未来应付各种各样的数据
        // 对象化需要判断是实例成什么样的对象
        //数据对象化
        ECC_SPORTDATA ecc_sportdata = new ECC_SPORTDATA();
        ecc_sportdata.wrap(bytes);
        //保存数据在application中，全局使用，每次刷卡都先将其置空，再重新写入新值
        MyApplication.getInstance().setEcc_sportdata(null);
        MyApplication.getInstance().setEcc_sportdata(ecc_sportdata);
        //跳转到其他页面
        standbyView.gotoMySportDataActivity();
        Log.i("测试", "用户code：" + ecc_sportdata.getUserCode().trim());
        Log.i("测试", "获取的字节长度为：" + i);
    }


    @Override
    /**
     * 初始化工作
     */
    public void setInitialization() {
        //初始化软件新
        SoftInfo softInfo = standbyModelImpl.getSoftInfoBySql();//获取数据库里的软件信息
        if (softInfo != null) {//已有信息,不需要在插入
        } else {//插入配置文件信息
            Log.i("测试","插入软件信息");
            softInfo = standbyModelImpl.getSoftInfoByCon();
            standbyModelImpl.setSoftInfoInSqlite(softInfo);
        }

        //初始化硬件信息
        Hardware hardware = standbyModelImpl.getHardInfoBySql();//获取数据库里的硬件信息
        if (hardware != null) {//已有信息,不需要在插入
        } else {//插入配置文件信息
            Log.i("测试","插入硬件信息");
            hardware = standbyModelImpl.getHardwareInfoByCon();
            standbyModelImpl.setHardwareInfoInSqlite(hardware);
        }

        //调用VIew,再页面设置相关提示操作
        standbyView.showInitialization();


    }


    /**
     * 考勤
     */
    @Override
    public void Attendance() {
        TempClazzAttendance clazzAttendance=standbyModelImpl.getClazzAttendance();
        //存储数据库
        standbyModelImpl.storeAttendance(clazzAttendance);
    }

    /**
     * 定时启动获取信息的线程
     */
    @Override
    public void startServiceInfo() {
        //考勤线程
        standbyModelImpl.startAttendanceThread();
        //班级信息线程
        standbyModelImpl.startClazzInfoThread();
        //头条线程
        MyThreadPool.getInstance().createLongPool().execute(new TopNews(standbyView));
        //天气线程
        MyThreadPool.getInstance().createLongPool().execute(new WeatherThread(standbyView));

    }

    @Override
    public void showServiceInfo() {
        TempClazzAttendance clazzAttendance=standbyModelImpl.getTempAttendance();
        standbyView.showAttendance(clazzAttendance);

        TempClazzInfo c=standbyModelImpl.getTempCLazzInfo();
        standbyView.showClazzInfo(c);
    }

    @Override
    public void userList() {
        List<UserInfo> list=standbyModelImpl.getList();
        //存入数据库
        standbyModelImpl.storeUserList(list);
    }

    @Override
    public void clazzInfo() {
        Clazz clazz=standbyModelImpl.getClazz();
        standbyModelImpl.storeClazzInfo(clazz);
    }


}
