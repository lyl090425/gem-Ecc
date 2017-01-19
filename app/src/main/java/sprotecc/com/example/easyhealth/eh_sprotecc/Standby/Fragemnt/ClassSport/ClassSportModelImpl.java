package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSport;

import com.ruite.gem.modal.运动健康.运动.Sport;
import com.ruite.gem.modal.运动健康.运动.SportRank;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.ClassSportHistoryThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.SportRank8Thread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportDataHistory;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/23.
 */
public class ClassSportModelImpl implements ClassSportModel {
    private ClassSportPresenterImpl classSportPresenter;
   private List<SportRank> list;
    private List<Sport> sportList;

    public List<Sport> getSportList() {
        return sportList;
    }

    public void setSportList(List<Sport> sportList) {
        this.sportList = sportList;
    }

    public List<SportRank> getList() {
        return list;
    }

    public void setList(List<SportRank> list) {
        this.list = list;
    }
    public ClassSportModelImpl( ClassSportPresenterImpl classSportPresenter){
       this.classSportPresenter=classSportPresenter;
    }


    /**
     * 取运动排名数据
     * @return
     */
    @Override
    public List<TempSportRank> getTemp8SportRank() {

        return   DataDao.getInstance().getTempSportRank();
    }

    /**
     * 启动获取365天历史的数据的线程
     */
    @Override
    public void startClassSportHistory() {
    MyThreadPool.getInstance().createLongPool().execute(new ClassSportHistoryThread(this));
    }

    /**
     * 历史数据线程的回到哦
     * @param list
     */
    @Override
    public void callbackClassSportHistoryThread(List<Sport> list) {
        this.sportList=list;
        classSportPresenter.classSportHistory();

    }

    /**
     * 存入365天历史的数据
     * @param list
     */
    @Override
    public void storeTempClassSportHistory(List<Sport> list) {
        DataDao.getInstance().storeClassSportDataHistory(list);
    }

    /**
     * 周
     * @return
     */
    @Override
    public List<TempSportDataHistory> getTempWeekClassSportHistory() {

        return  DataDao.getInstance().getTempWeekClassSportDataHistory();
    }

    /**
     * 月
     * @return
     */
    @Override
    public List<TempSportDataHistory> getTempMonthClassSportHistory() {
        return DataDao.getInstance().getTempMonthClassSportDataHistory();
    }

    /**
     * 年
     * @return
     */
    @Override
    public List<TempSportDataHistory> getTempYearClassSportHistory() {
        return DataDao.getInstance().getTempYearClassSportDataHistory();
    }

}
