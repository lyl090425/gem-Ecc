package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import android.util.Log;

import com.ruite.gem.action.公开.电子班牌.运动.获取班级运动排行榜;
import com.ruite.gem.comm.Rpc;

import java.util.Date;
import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassInfoModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.SportRank.SportRankModelImpl;

/**
 * 获取班级运动排行榜
 * Created by adminHjq on 2016/12/27.
 */
public class SportRankThread implements Runnable {
   SportRankModelImpl sportRankModel;
    public SportRankThread(SportRankModelImpl sportRankModel){
this.sportRankModel=sportRankModel;
    }
    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            获取班级运动排行榜 action =new 获取班级运动排行榜();
            Date date=new Date();
            java.sql.Date sd = new java.sql.Date(date.getTime());
            action.setDate(sd);//日期
            String id=DataDao.getInstance().getSoftInfoByFlag().getClazz();
            long clazzId=Long.parseLong(id);
            action.setClassId(clazzId);//班级ID
            获取班级运动排行榜 re=rpc.call(action);
            List<com.ruite.gem.modal.运动健康.运动.SportRank> sportRanks=re.getSportRanks();
            List<com.ruite.gem.modal.运动健康.运动.SportRank> activityRanks = re.getActivitySportRanks();
            Log.i("数据测试","获取班级运动排名："+sportRanks.size()+"");
            Log.i("数据测试","获取班级活动度排名："+activityRanks.size()+"");
            //回调
            sportRankModel.callbackSportRank(sportRanks,activityRanks);

        } catch (Exception e) {
            sportRankModel.callbackSportRank(null,null);
            e.printStackTrace();
        }

    }
}
