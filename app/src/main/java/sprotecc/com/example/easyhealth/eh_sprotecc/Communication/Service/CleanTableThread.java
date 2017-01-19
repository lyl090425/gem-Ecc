package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import android.util.Log;

import com.ruite.gem.action.公开.电子班牌.值日.获取班级值日;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.班牌基础.DutyUser;

import java.util.List;
import java.util.Map;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.CleanTable.CleanTableModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.CleanTable.CleanTableView;

/**
 * Created by adminHjq on 2016/12/30.
 */
public class CleanTableThread implements Runnable {
    CleanTableView cleanTableView;
    public CleanTableThread( CleanTableView cleanTableView){
        this.cleanTableView=cleanTableView;
    }
    @Override
    public void run() {
        try {
            Rpc rpc =new Rpc(MyApplication.url);
            获取班级值日 action=new 获取班级值日();
            action.setClassId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getClazz()));
            action.setSchoolId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId()));
            获取班级值日 re=rpc.call(action);
            List<String> lsit=re.getTypes();
            Map<String, Map<Integer, List<DutyUser>>> dutyUsermap=re.getDutyUsermap();
            //回调
//            cleanTableModel.callbackCleanTable(lsit,dutyUsermap);
            Log.i("数据获取","获取值日表成功");
            cleanTableView.showCleanTable(lsit,dutyUsermap);
        } catch (Exception e) {
            //回调
            Log.i("数据获取","获取值日表失败");
            cleanTableView.showCleanTable(null,null);
//            cleanTableModel.callbackCleanTable(null,null);
            e.printStackTrace();
        }


    }
}
