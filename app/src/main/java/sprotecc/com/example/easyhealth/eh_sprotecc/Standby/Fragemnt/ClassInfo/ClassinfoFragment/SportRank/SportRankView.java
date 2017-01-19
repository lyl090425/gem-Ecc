package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.SportRank;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;

/**
 * Created by adminHjq on 2017/1/3.
 */
public interface SportRankView {
    public void showSportRank(List<TempSportRank> sportRankList);
    public void showActivityRank(List<TempSportRank> sportRankList);
}
