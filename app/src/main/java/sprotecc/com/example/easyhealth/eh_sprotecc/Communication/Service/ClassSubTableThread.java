package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import android.util.Log;

import com.ruite.gem.action.公开.电子班牌.课表.获取班级课表;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.班牌基础.ClassSchedule;
import com.ruite.gem.modal.班牌基础.Timetable;

import java.util.List;
import java.util.Map;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSubTable.ClassSubTableModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSubTable.ClassSubTableView;

/**
 * 课表
 * Created by adminHjq on 2016/12/27.
 */
public class ClassSubTableThread implements Runnable {
    ClassSubTableView classSubTableView;
    public ClassSubTableThread(ClassSubTableView classSubTableView){
        this.classSubTableView=classSubTableView;
    }
    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            获取班级课表 action =new 获取班级课表();
            String cid,sid;
            cid= DataDao.getInstance().getSoftInfoByFlag().getClazz();
            sid= DataDao.getInstance().getSoftInfoByFlag().getSchoolId();
            long clazzId,schoolId;
            clazzId=Long.parseLong(cid);
            schoolId=Long.parseLong(sid);
            action.setClassId(clazzId);
            action.setSchoolId(schoolId);
            获取班级课表 re= rpc.call(action);
            List<Timetable> list= re.getTimeTables();
            Map<Long, Map<Integer, ClassSchedule>> longMapMap= re.getSchedulesMap();
          Log.i("数据获取","获取班级课表成功");
//            //回调
           classSubTableView.showSubTable(list,longMapMap);
        } catch (Exception e) {
            Log.i("数据获取","获取班级课表失败");
           classSubTableView.showNoSubTable(null,null);
            e.printStackTrace();
        }
    }
}
