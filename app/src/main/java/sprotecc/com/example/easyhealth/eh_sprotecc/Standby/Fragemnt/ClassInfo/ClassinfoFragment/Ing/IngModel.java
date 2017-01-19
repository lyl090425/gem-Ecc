package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.Ing;

import com.ruite.gem.modal.班牌基础.ClassSchedule;
import com.ruite.gem.modal.班牌基础.StudentHonour;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempHonour;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempIngInfo;

/**
 * Created by adminHjq on 2017/1/3.
 */
public interface IngModel {
    //课中信息
    public  void startIngThread();
    public void callbackIng(ClassSchedule list);
    public void storeIng(ClassSchedule list);
    public TempIngInfo getTempIng();
}
