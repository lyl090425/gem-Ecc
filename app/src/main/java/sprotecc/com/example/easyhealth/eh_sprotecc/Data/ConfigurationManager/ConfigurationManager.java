package sprotecc.com.example.easyhealth.eh_sprotecc.Data.ConfigurationManager;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.Hardware;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.SoftInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

/**
 * 提供操作配置文件的一些方法
 * Created by adminHjq on 2016/12/12.
 */
public class ConfigurationManager {
    /**
     * 获取软件初始化的信息
     *
     * @return String[]
     */
    public SoftInfo getSoftInfo() {
        SoftInfo softInfo = new SoftInfo();
        //软件版本号
        String version = MyApplication.getInstance().getResources().getString(R.string.soft_versionId);
        //软件所在年级
        String clazz = MyApplication.getInstance().getResources().getString(R.string.soft_class);
        //软件所在班级
        String grade = MyApplication.getInstance().getResources().getString(R.string.soft_grade);
        //软件所在学校Id
        String schoolId=MyApplication.getInstance().getString(R.string.soft_schoolId);
        softInfo.setClazz(clazz);
        softInfo.setGrade(grade);
        softInfo.setVersion(version);
        softInfo.setSchoolId(schoolId);
        return softInfo;
    }

    /**
     * 获取硬件配置信息
     * @return
     */
    public Hardware getHardware(){
         String schoolId=MyApplication.getInstance().getResources().getString(R.string.hardware_schoolId);
        //学校地址
         String schoolAddress=MyApplication.getInstance().getResources().getString(R.string.hardware_address);
        //学校名字
         String schoolName=MyApplication.getInstance().getResources().getString(R.string.hardware_schoolName);
        Hardware hardware=new Hardware();
        hardware.setSchoolAddress(schoolAddress);
        hardware.setSchoolId(schoolId);
        hardware.setSchoolName(schoolName);
        return hardware;
    }


}
