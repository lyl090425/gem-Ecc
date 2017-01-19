package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.SportRank;

import com.ruite.gem.modal.运动健康.运动.SportRank;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class SportRankPresenterImpl extends BasePresenterImpl<SportRankView> implements SportRankPresenter<SportRankView> {
    SportRankView sportRankView;
    SportRankModelImpl sportRankModel=new SportRankModelImpl(this);
    public SportRankPresenterImpl(SportRankView sportRankView) {
        this.sportRankView = sportRankView;
    }

    @Override
    public void sportRank() {
        List<SportRank> list=sportRankModel.getListSportRank();
        List<SportRank> list2=sportRankModel.getListActivityRank();
        //存入数据库
        sportRankModel.storeSportRank(list);//班级前八名也在这里取了，不再额外请求线程
        sportRankModel.storeActivityRank(list2);
    }

    @Override
    public void startThread() {
        sportRankModel.startSportRankThread();
    }

    @Override
    public void showInfo() {
      //  运动排名
        List<TempSportRank> sportRankList=sportRankModel.getTempSportRank();
        sportRankView.showSportRank(sportRankList);
        //活动度排名
        List<TempSportRank> sportRankList2=sportRankModel.getTempActivityRank();
        sportRankView.showActivityRank(sportRankList2);
    }
}
