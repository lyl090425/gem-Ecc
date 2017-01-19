package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import com.ruite.gem.action.公开.电子班牌.运动.获取班级运动历史数据;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.运动健康.运动.Sport;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSport.ClassSportModelImpl;

/**
 * 直接获取365天的，保存在本地，灵活操作
 * Created by adminHjq on 2016/12/29.
 */
public class ClassSportHistoryThread implements Runnable {
    ClassSportModelImpl classSportModel;

    public ClassSportHistoryThread(ClassSportModelImpl classSportModel) {
        this.classSportModel = classSportModel;
    }

    @Override
    public void run() {
        try {
            Rpc rpc = new Rpc(MyApplication.url);
            获取班级运动历史数据 action = new 获取班级运动历史数据();
            String cid = DataDao.getInstance().getSoftInfoByFlag().getClazz();
            long clazzId = Long.parseLong(cid);
            action.setClassId(clazzId);
            action.setEndDate(new Date());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -380);
            Date time = cal.getTime();//保证大于1年
            action.setBeginDate(time);
            获取班级运动历史数据 re=rpc.call(action);
            List<Sport> list=re.getSports();
            //回调
            classSportModel.callbackClassSportHistoryThread(list);
        } catch (Exception e) {
            classSportModel.callbackClassSportHistoryThread(null);
            e.printStackTrace();
        }


    }
}
