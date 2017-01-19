package sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.Model;

import com.ruite.gem.modal.班牌基础.StudentHonour;
import com.ruite.gem.modal.运动健康.运动.Sport;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempHonour;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportDataHistory;

/**
 * Created by adminHjq on 2016/12/19.
 */
public interface MyInformationModel {
    //个人运动历史
    public  void startMySportDataHistoryThread();
    public void callbackMySportDataHistory(List<Sport> list);
    public void storeMySportDataHistory(List<Sport> list);
    public List<TempSportDataHistory> getTempWeekMySportHistory();
    public List<TempSportDataHistory> getTempMonthMySportHistory();
    public List<TempSportDataHistory> getTempYearMySportHistory();
    //个人荣誉
    public  void startHonourThread();
    public void callbackHonourHistory(List<StudentHonour> list);
    public void storeHonour(List<StudentHonour> list);
    public List<TempHonour> getTempHonour();

    //个人运动数据（来自本地）
    public void MyToDaySportData(List<Sport> list);

}
