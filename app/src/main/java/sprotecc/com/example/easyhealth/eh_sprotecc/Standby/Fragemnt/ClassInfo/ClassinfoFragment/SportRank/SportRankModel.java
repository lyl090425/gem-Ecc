package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.SportRank;

import com.ruite.gem.modal.运动健康.运动.SportRank;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;

/**
 * Created by adminHjq on 2017/1/3.
 */
public interface SportRankModel {
    public void startSportRankThread();
    public void callbackSportRank(List<SportRank> list,List<SportRank> list2);
    public void storeSportRank(List<SportRank> list);
    public void storeActivityRank(List<SportRank> list);

    public List<TempSportRank> getTempSportRank();
    public List<TempSportRank> getTempActivityRank();



}
