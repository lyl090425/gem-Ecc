package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import android.util.Log;

import com.ruite.gem.action.公开.电子班牌.课表.获取课中信息;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.班牌基础.ClassSchedule;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.Ing.IngModelImpl;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class IngInfoThread implements Runnable {
  public IngModelImpl ingModel;
    public IngInfoThread(IngModelImpl ingModel){
       this.ingModel=ingModel;
    }

    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            获取课中信息 action =new 获取课中信息();
            action.setSchoolId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId()));
            action.setClassId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getClazz()));
            获取课中信息 re=rpc.call(action);
           ClassSchedule classSchedule= re.getClassSchedule();
            Log.i("数据获取","获取课中信息成功");
            ingModel.callbackIng(classSchedule);
        } catch (Exception e) {
            Log.i("数据获取","获取课中信息失败");
            ingModel.callbackIng(null);
            e.printStackTrace();
        }

    }
}
