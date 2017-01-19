package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import android.util.Log;

import com.ruite.gem.action.公开.电子班牌.获取班级学生;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.人员信息.UserInfo;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Model.StandbyModelImpl;

/**
 * Created by adminHjq on 2017/1/5.
 */
public class ClazzInfoListThread implements Runnable {
     private StandbyModelImpl standbyModel;
    public ClazzInfoListThread(StandbyModelImpl standbyModel){
        this.standbyModel=standbyModel;
    }
    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            获取班级学生 action =new 获取班级学生();
            action.setSchoolId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId()));
            action.setClassId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getClazz()));

            获取班级学生 re=rpc.call(action);
            List<UserInfo> list=re.getInfos();
            Log.i("数据测试","获取到的用户数量："+list.size()+"");
            standbyModel.callbackUserList(list);
        } catch (Exception e) {
            Log.i("数据测试","获取班级用户列表失败");
            standbyModel.callbackUserList(null);
            e.printStackTrace();
        }


    }
}
