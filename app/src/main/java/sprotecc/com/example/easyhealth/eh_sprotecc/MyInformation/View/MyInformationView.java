package sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.View;

import com.ruite.gem.modal.运动健康.运动.Sport;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempHonour;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportDataHistory;

/**
 * Created by adminHjq on 2016/12/8.
 */
public interface MyInformationView {
    //周历史
    public void showWeekSportHistory(List<TempSportDataHistory> list);
    //月历史
    public void showMonthSportHistory(List<TempSportDataHistory> list);
    //年历史
    public void showYearSportHistory(List<TempSportDataHistory> list);
    //荣誉
    public void showHonour(List<TempHonour> list);
    //个人今日运动
    public void showMyTodaySport(List<Sport> list);
    public void showMyTodayActivity(List<Sport> list);

    //个人信息
    public void showMyInformation( );
}
