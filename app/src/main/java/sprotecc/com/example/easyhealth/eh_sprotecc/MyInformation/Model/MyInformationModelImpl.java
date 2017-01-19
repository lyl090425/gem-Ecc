package sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.Model;

import com.ruite.gem.modal.班牌基础.StudentHonour;
import com.ruite.gem.modal.运动健康.运动.Sport;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.MyHonourThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.MySportHistoryThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempHonour;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportDataHistory;
import sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.Presenter.MyInformationPresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/19.
 */
public class MyInformationModelImpl implements MyInformationModel {
    private MyInformationPresenterImpl myInformationPresenter;
    private List<Sport> sportList;//历史
    private List<StudentHonour> honourList;//荣誉
    private List<Sport> mylist;

    public List<Sport> getMylist() {
        return mylist;
    }

    public void setMylist(List<Sport> mylist) {
        this.mylist = mylist;
    }

    public List<StudentHonour> getHonourList() {
        return honourList;
    }

    public void setHonourList(List<StudentHonour> honourList) {
        this.honourList = honourList;
    }

    public List<Sport> getSportList() {
        return sportList;
    }

    public void setSportList(List<Sport> sportList) {
        this.sportList = sportList;
    }

    public MyInformationModelImpl(MyInformationPresenterImpl myInformationPresenter) {
        this.myInformationPresenter = myInformationPresenter;

    }

    /**
     * 启动访问我的历史线程，380天的数据
     */
    @Override
    public void startMySportDataHistoryThread() {
        MyThreadPool.getInstance().createLongPool().execute(new MySportHistoryThread(this));
    }

    @Override
    public void callbackMySportDataHistory(List<Sport> list) {
        this.sportList = list;
        myInformationPresenter.MySportHistory();

    }

    /**
     * 存的到本地数据，我的运动历史
     *
     * @param list
     */
    @Override
    public void storeMySportDataHistory(List<Sport> list) {
        DataDao.getInstance().storeMySportDataHistory(list);
    }

    @Override
    public List<TempSportDataHistory> getTempWeekMySportHistory() {

        return DataDao.getInstance().getTempWeekMySprotdataHistory();
    }

    @Override
    public List<TempSportDataHistory> getTempMonthMySportHistory() {

        return DataDao.getInstance().getTempMonthMySprotdataHistory();
    }

    @Override
    public List<TempSportDataHistory> getTempYearMySportHistory() {
        return DataDao.getInstance().getTempYearMySprotdataHistory();
    }

    /**
     * 启动获取个人荣誉的线程
     */
    @Override
    public void startHonourThread() {
        MyThreadPool.getInstance().createLongPool().execute(new MyHonourThread(this));

    }

    @Override
    public void callbackHonourHistory(List<StudentHonour> list) {
        this.honourList=list;
        myInformationPresenter.MyHonor();
    }

    @Override
    public void storeHonour(List<StudentHonour> list) {
        DataDao.getInstance().storeMyHonour(list);

    }

    @Override
    public List<TempHonour> getTempHonour() {
        return DataDao.getInstance().getMyHonnor();
    }

    /**
     * 个人今日运动
     * @param list
     */
    @Override
    public void MyToDaySportData(List<Sport> list) {
     this.mylist=MyApplication.getInstance().getEcc_sportdata().getSportData();
        myInformationPresenter.myTodaySport();

    }
}
