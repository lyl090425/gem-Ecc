package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSport;

import com.ruite.gem.modal.运动健康.运动.Sport;
import com.ruite.gem.modal.运动健康.运动.SportRank;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportDataHistory;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;

/**
 * Created by adminHjq on 2016/12/21.
 */
public class ClassSportPresenterImpl extends BasePresenterImpl<ClassSportView> implements  ClassSportPresenter<ClassSportView> {
    ClassSportModelImpl classSportModel=new ClassSportModelImpl(this);
    ClassSportView classSportView;
    public ClassSportPresenterImpl(ClassSportView classSportView){
        this.classSportView=classSportView;
    }

    @Override
    public void startServiceInfo() {
        //班级历史线程
        classSportModel.startClassSportHistory();
    }

    @Override
    public void showServiceInfo() {
        //显示班级前八名
        List<TempSportRank> list= classSportModel.getTemp8SportRank();
        classSportView.show8SportRank(list);
        //显示周
        List<TempSportDataHistory> sportListWeek=classSportModel.getTempWeekClassSportHistory();
        classSportView.showWeekSportHistory(sportListWeek);
        //显示月
        List<TempSportDataHistory> sportListMonth=classSportModel.getTempMonthClassSportHistory();
        classSportView.showMonthSportHistory(sportListMonth);
        //显示年
        List<TempSportDataHistory> sportListYear=classSportModel.getTempYearClassSportHistory();
        classSportView.showYearSportHistory(sportListYear);

    }



    @Override
    public void classSportHistory() {
        List<Sport> list=classSportModel.getSportList();
        //存入数据库
        classSportModel.storeTempClassSportHistory(list);

    }
}
