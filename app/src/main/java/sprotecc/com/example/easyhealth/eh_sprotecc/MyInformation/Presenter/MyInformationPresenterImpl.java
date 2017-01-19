package sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.Presenter;

import com.ruite.gem.modal.班牌基础.StudentHonour;
import com.ruite.gem.modal.运动健康.运动.Sport;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempHonour;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportDataHistory;
import sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.Model.MyInformationModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.View.MyInformationView;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Model.MySportDataModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Presenter.MySportDataPresenter;

/**
 * Created by adminHjq on 2016/12/19.
 */
public class MyInformationPresenterImpl extends BasePresenterImpl<MyInformationView> implements MyInformationPresenter<MyInformationView> {
MyInformationView myInformationView;
    MyInformationModelImpl myInformationModel=new MyInformationModelImpl(this);
public MyInformationPresenterImpl(MyInformationView myInformationView ){
   this.myInformationView= myInformationView;
}

    @Override
    public void startServiceInfo() {
        //个人历史
        myInformationModel.startMySportDataHistoryThread();
        //个人荣誉
        myInformationModel.startHonourThread();
        //个人信息

    }

    @Override
    public void showServiceInfo() {
        //显示周
        List<TempSportDataHistory> sportListWeek=myInformationModel.getTempWeekMySportHistory();
        myInformationView.showWeekSportHistory(sportListWeek);
        //显示月
        List<TempSportDataHistory> sportLisMonth=myInformationModel.getTempMonthMySportHistory();
        myInformationView.showMonthSportHistory(sportLisMonth);
        //显示年
        List<TempSportDataHistory> sportListYear=myInformationModel.getTempYearMySportHistory();
        myInformationView.showYearSportHistory(sportListYear);
        //显示荣誉
        List<TempHonour> listh=myInformationModel.getTempHonour();
        myInformationView.showHonour(listh);
        //显示今天的运动情况
        myInformationView.showMyTodaySport( MyApplication.getInstance().getEcc_sportdata().getSportData());
        myInformationView.showMyTodayActivity( MyApplication.getInstance().getEcc_sportdata().getSportData());
        //显示形成性评价
        //显示个人信息

    }

    /**
     * 返回的我的历史数据
     */
    @Override
    public void MySportHistory() {
        List<Sport> list=myInformationModel.getSportList();
        //存入本地数据库
        myInformationModel.storeMySportDataHistory(list);

    }

    @Override
    public void MyHonor() {
        List<StudentHonour> list=myInformationModel.getHonourList();
        //存储到本地
        myInformationModel.storeHonour(list);
    }

    @Override
    public void myTodaySport() {
         List<Sport> list=myInformationModel.getMylist();
        myInformationView.showMyTodaySport(list);
    }
}
