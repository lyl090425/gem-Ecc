package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import android.util.Log;

import com.ruite.gem.action.公开.电子班牌.操行分.获取班级学生操行分;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.人员信息.ClassStudent;
import com.ruite.gem.modal.班牌基础.StudentEvaluate;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassInfoModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.ConductPiont.ConductPointModelImpl;

/**
 * Created by adminHjq on 2016/12/27.
 */
public class ConductPointsThread implements Runnable {
    ConductPointModelImpl callbackConductPoints;
    public ConductPointsThread(ConductPointModelImpl callbackConductPoints){
         this.callbackConductPoints=callbackConductPoints;
    }
    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            获取班级学生操行分 action =new 获取班级学生操行分();
            long schoolid=Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId());
            String id= DataDao.getInstance().getSoftInfoByFlag().getClazz();//班级ID
            long clazzId=Long.parseLong(id);
            action.setClassId(clazzId);
            action.setSchoolId(schoolid);
            获取班级学生操行分 re=rpc.call(action);
            Log.i("数据获取","操行分获取成功");
            List<ClassStudent> list=re.getClassStudents();
            //回调
            callbackConductPoints.callbackConductPoints(list);
        } catch (Exception e) {
            Log.i("数据获取","操行分获取失败");
           callbackConductPoints.callbackConductPoints(null);
            e.printStackTrace();
        }


    }
}
