package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Model;

import com.ruite.gem.modal.人员信息.UserInfo;
import com.ruite.gem.modal.组织信息.Clazz;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.AttendanceThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.ClazzInfoListThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.ClazzInfoThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.UsbCommunicationManager;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.UsbInformationInput;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.ConfigurationManager.ConfigurationManager;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzAttendance;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.Hardware;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.SoftInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Presenter.StandbyPresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * StandbyModelImpl和StandbyPresenterImpl相互依赖
 * Created by adminHjq on 2016/12/8.
 */
public class StandbyModelImpl implements StandbyModel {

    StandbyPresenterImpl standbyPresenterImpl;
    //储存接收到消息
    byte[] bytes;
    //班级学生列表
    List<UserInfo> list;
    //班级信息
    Clazz clazz;
    //考勤信息
    TempClazzAttendance clazzAttendance;
    //USB管理者
    UsbCommunicationManager communicationManager;
    //配置文件管理者
    ConfigurationManager configurationManager;
    //数据库信息管理者
    DataDao dataDao;

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public List<UserInfo> getList() {
        return list;
    }

    public void setList(List<UserInfo> list) {
        this.list = list;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public TempClazzAttendance getClazzAttendance() {
        return clazzAttendance;
    }

    public void setClazzAttendance(TempClazzAttendance clazzAttendance) {
        this.clazzAttendance = clazzAttendance;
    }
    public StandbyModelImpl(StandbyPresenterImpl standbyPresenterImpl) {
        this.standbyPresenterImpl = standbyPresenterImpl;
        communicationManager = new UsbCommunicationManager();
        configurationManager = new ConfigurationManager();
        dataDao = DataDao.getInstance();

    }

    @Override
    public void startUsbInformationThread() {
        //启动线程，接收来自USB的信息
        MyThreadPool.getInstance().createLongPool().execute(new UsbInformationInput(StandbyModelImpl.this));
    }

    @Override
    public void callback(byte[] bytes) {
        this.bytes = bytes;
        //接收到正确消息后，presenter处理
        standbyPresenterImpl.SwingCard();
    }

    /**
     * 查询是否存在软件基本信息
     *
     * @return SoftInfo
     */
    @Override
    public SoftInfo getSoftInfoBySql() {
        return dataDao.getSoftInfoByFlag();
    }

    /**
     * 从配置文件获取软件初始信息
     * @return
     */
    @Override
    public SoftInfo getSoftInfoByCon() {

        return configurationManager.getSoftInfo();
    }

    /**
     * 储存软件信息到SQL里面
     *
     * @param softInfo
     */
    @Override
    public void setSoftInfoInSqlite(SoftInfo softInfo) {
        dataDao.storeSoftInfo(softInfo);
    }

    /**
     * 获取数据库里面是否存在硬件信息
     * @return
     */
    @Override
    public Hardware getHardInfoBySql() {
        return dataDao.getHardwareInfoByflag();
    }

    /**
     * 从配置文件获取配置信息
     * @return
     */
    @Override
    public Hardware getHardwareInfoByCon() {
        return configurationManager.getHardware();
    }

    @Override
    public void setHardwareInfoInSqlite(Hardware hardware) {
        dataDao.storeHardware(hardware);

    }


    /**
     * 启动线程，获取考勤信息
     */
    @Override
    public void startAttendanceThread() {
        String id= DataDao.getInstance().getSoftInfoByFlag().getClazz();
        long clazzId=Long.parseLong(id);
      MyThreadPool.getInstance().createLongPool().execute(new AttendanceThread(this,clazzId));
    }

    /**
     * 考勤线程回调函数
     * @param clazzAttendance
     */
    @Override
    public void callbackAttendance(TempClazzAttendance clazzAttendance) {
        this.clazzAttendance=clazzAttendance;
        standbyPresenterImpl.Attendance();

    }

    /**
     * 存储考勤信息
     * @param clazzAttendance
     */
    @Override
    public void storeAttendance(TempClazzAttendance clazzAttendance) {
        DataDao.getInstance().storeTempAttendance(clazzAttendance);

    }

    /**
     * 获取考勤信息
     * @return
     */
    @Override
    public TempClazzAttendance getTempAttendance() {

        return  DataDao.getInstance().getTempClazzAttendance();
    }

    @Override
    public void startUserListThread() {
        MyThreadPool.getInstance().createLongPool().execute(new ClazzInfoListThread(this));
    }

    @Override
    public void callbackUserList(List<UserInfo> userInfos) {
                this.list=userInfos;
        standbyPresenterImpl.userList();
    }

    @Override
    public void storeUserList(List<UserInfo> userInfos) {
    DataDao.getInstance().storeUserInfo(userInfos);
    }

    @Override
    public void startClazzInfoThread() {
MyThreadPool.getInstance().createLongPool().execute(new ClazzInfoThread(this));
    }

    @Override
    public void callbackClazzInfo(Clazz clazz) {
        this.clazz=clazz;
        standbyPresenterImpl.clazzInfo();
    }

    @Override
    public void storeClazzInfo(Clazz clazz) {
DataDao.getInstance().storeTempClazzInfo(clazz);
    }

    @Override
    public TempClazzInfo getTempCLazzInfo() {

        return    DataDao.getInstance().getTempClazzInfo();
    }


}
