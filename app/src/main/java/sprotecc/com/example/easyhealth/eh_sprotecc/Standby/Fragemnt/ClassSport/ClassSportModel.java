package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSport;

import com.ruite.gem.modal.运动健康.运动.Sport;
import com.ruite.gem.modal.运动健康.运动.SportRank;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportDataHistory;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;

/**
 * Created by adminHjq on 2016/12/21.
 */
public interface ClassSportModel {

    public List<TempSportRank> getTemp8SportRank();

    //班级一年历史，周历史，月历史，都查询取出
    public void startClassSportHistory();
    public void callbackClassSportHistoryThread(List<Sport> list);
    public void storeTempClassSportHistory(List<Sport> list);
    public List<TempSportDataHistory> getTempWeekClassSportHistory();
    public List<TempSportDataHistory> getTempMonthClassSportHistory();
    public List<TempSportDataHistory> getTempYearClassSportHistory();

}
