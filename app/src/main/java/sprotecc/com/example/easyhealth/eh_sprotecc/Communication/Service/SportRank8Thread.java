package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import com.ruite.gem.action.公开.电子班牌.运动.获取班级运动排行榜;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.运动健康.运动.SportRank;

import java.util.Date;
import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassInfoModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSport.ClassSportModelImpl;

/**
 * Created by adminHjq on 2016/12/29.
 */
public class SportRank8Thread implements Runnable {
    ClassSportModelImpl classSportModel;
    public SportRank8Thread(ClassSportModelImpl classSportModel){
        this.classSportModel=classSportModel;
    }
    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            获取班级运动排行榜 action =new 获取班级运动排行榜();
            Date date=new Date();
            java.sql.Date sd = new java.sql.Date(date.getTime());
            action.setDate(sd);//日期
            String id= DataDao.getInstance().getSoftInfoByFlag().getClazz();
            long clazzId=Long.parseLong(id);
            action.setClassId(clazzId);//班级ID
            获取班级运动排行榜 re=rpc.call(action);
            List<SportRank> sportRanks=re.getSportRanks();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
