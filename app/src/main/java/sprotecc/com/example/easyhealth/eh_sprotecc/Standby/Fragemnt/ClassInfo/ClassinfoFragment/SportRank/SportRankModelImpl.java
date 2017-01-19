package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.SportRank;

import com.ruite.gem.modal.运动健康.运动.SportRank;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.SportRankThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class SportRankModelImpl implements SportRankModel {
    private SportRankPresenterImpl sportRankPresenter;
    private List<SportRank> listSportRank;
    private List<SportRank> listActivityRank;

    public List<SportRank> getListActivityRank() {
        return listActivityRank;
    }

    public void setListActivityRank(List<SportRank> listActivityRank) {
        this.listActivityRank = listActivityRank;
    }

    public List<SportRank> getListSportRank() {
        return listSportRank;
    }

    public void setListSportRank(List<SportRank> listSportRank) {
        this.listSportRank = listSportRank;
    }

    public SportRankModelImpl(SportRankPresenterImpl sportRankPresenter){
       this.sportRankPresenter=sportRankPresenter;
    }
    @Override
    public void startSportRankThread() {
        MyThreadPool.getInstance().createLongPool().execute(new SportRankThread(this));
    }

    @Override
    public void callbackSportRank(List<SportRank> list,List<SportRank> list1) {
        this.listSportRank = list;
        this.listActivityRank=list1;
        sportRankPresenter .sportRank();
    }

    @Override
    public void storeSportRank(List<SportRank> list) {
        DataDao.getInstance().storeTempSportRank(list);
    }

    @Override
    public void storeActivityRank(List<SportRank> list) {
        DataDao.getInstance().storeTempActivityRank(list);
    }

    @Override
    public List<TempSportRank> getTempSportRank() {
        List<TempSportRank> list = DataDao.getInstance().getTempSportRank();
        return list;
    }

    @Override
    public List<TempSportRank> getTempActivityRank() {
        List<TempSportRank> list=DataDao.getInstance().getTempActivityRank();
        return list;
    }


}
