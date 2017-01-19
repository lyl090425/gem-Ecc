package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSport;

import com.ruite.gem.modal.运动健康.运动.Sport;
import com.ruite.gem.modal.运动健康.运动.SportRank;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportDataHistory;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;

/**
 * Created by adminHjq on 2016/12/21.
 */
public interface ClassSportView {
    //显示班级前八名
    public void show8SportRank(List<TempSportRank> list);
    //周历史
    public void showWeekSportHistory(List<TempSportDataHistory> list);
    //月历史
    public void showMonthSportHistory(List<TempSportDataHistory> list);
    //年历史
    public void showYearSportHistory(List<TempSportDataHistory> list);
}
