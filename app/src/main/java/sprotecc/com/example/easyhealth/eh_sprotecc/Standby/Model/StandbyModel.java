package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Model;

import com.ruite.gem.modal.人员信息.UserInfo;
import com.ruite.gem.modal.组织信息.Clazz;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzAttendance;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.Hardware;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.SoftInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzInfo;

/**
 * Created by adminHjq on 2016/12/9.
 */
public interface StandbyModel {
   //USB 通信相关
    public void startUsbInformationThread( );
    public void callback(byte[] bytes);
   //软件信息相关
    public SoftInfo getSoftInfoBySql();
    public SoftInfo getSoftInfoByCon();
    public void setSoftInfoInSqlite(SoftInfo softInfo);
     //硬件信息相关
    public Hardware getHardInfoBySql();
    public Hardware getHardwareInfoByCon();
    public void setHardwareInfoInSqlite(Hardware hardware);
   //考勤相关
    public void startAttendanceThread();
    public void callbackAttendance(TempClazzAttendance clazzAttendance);
    public void storeAttendance(TempClazzAttendance clazzAttendance);
    public TempClazzAttendance getTempAttendance( );
   //获取班级用户列表相关
   public void startUserListThread();
 public void callbackUserList(List<UserInfo> userInfos);
 public void storeUserList(List<UserInfo> userInfos);
    //班级信息
    public void startClazzInfoThread();
    public void callbackClazzInfo(Clazz clazz);
    public void storeClazzInfo(Clazz clazz);
    public TempClazzInfo getTempCLazzInfo( );





}
