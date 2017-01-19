package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;

import android.util.Log;

import com.ruite.gem.action.公开.学校.班级.获取班级信息;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.组织信息.Clazz;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Model.StandbyModelImpl;

/**
 * 获取班级基本信
 * Created by adminHjq on 2017/1/10.
 */
public class ClazzInfoThread implements  Runnable{
    private StandbyModelImpl standbyModel;
    public ClazzInfoThread (StandbyModelImpl standbyModel){
        this.standbyModel=standbyModel;

    }
    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            获取班级信息 action =new 获取班级信息();
            action.setClassId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getClazz()));
            获取班级信息 re=rpc.call(action);
            Clazz clazz=re.getClazz();
            Log.i("数据获取-班级信息","班级信息获取成功");
            standbyModel.callbackClazzInfo(clazz);
        } catch (Exception e) {
            Log.i("数据获取-班级信息","班级信息获取失败");
            standbyModel.callbackClazzInfo(null);
            e.printStackTrace();
        }


    }
}
