package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;

import android.util.Log;

import com.ruite.gem.action.公开.学校.考勤.统计班级出勤情况;
import com.ruite.gem.comm.Rpc;


import java.util.Date;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzAttendance;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Model.StandbyModelImpl;

/**
 * Created by adminHjq on 2016/12/27.
 */
public class AttendanceThread implements Runnable {
    private  long clazzId;
   private StandbyModelImpl standbyModel;
    public AttendanceThread(StandbyModelImpl standbyModel,long id){
        this.standbyModel=standbyModel;
        this.clazzId=id;

    }
    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            统计班级出勤情况 action=new 统计班级出勤情况();
            action.setSchoolId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId()));
            action.setClazzId(clazzId);
            action.setDate(new Date());
            统计班级出勤情况 re=rpc.call(action);
            int ar=re.getArrived();//已到
            int to=re.getTotal();//应到
            int no=to-ar;//未到
            Log.i("数据查询：考勤","获取考勤成功"+to+"："+ar+":"+no);
            TempClazzAttendance clazzAttendance=new TempClazzAttendance(to,ar,no);
            standbyModel.callbackAttendance(clazzAttendance);
        } catch (Exception e) {
            Log.i("数据查询：考勤","获取考勤失败");
            standbyModel.callbackAttendance(null);
            e.printStackTrace();
        }


    }
}
