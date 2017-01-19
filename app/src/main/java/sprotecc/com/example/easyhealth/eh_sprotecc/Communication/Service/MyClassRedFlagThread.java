package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import android.util.Log;

import com.ruite.gem.action.公开.电子班牌.流动红旗.获取班级流动红旗;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.班牌基础.ClassRedFlag;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.RedFlag.RedFlagView;

/**
 * Created by adminHjq on 2017/1/4.
 */
public class MyClassRedFlagThread implements Runnable {
    private RedFlagView redFlagView;

    public  MyClassRedFlagThread(RedFlagView redFlagView) {

        this.redFlagView = redFlagView;
    }

    @Override
    public void run() {
        try {
            Rpc rpc = new Rpc(MyApplication.url);
            获取班级流动红旗 action = new 获取班级流动红旗();
            action.setSchoolId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId()));
            action.setClassId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getClazz()));
            获取班级流动红旗 re = rpc.call(action);
            List<ClassRedFlag> classRedFlags = re.getClassRedFlags();
            Log.i("获取数据：我班流动红旗","成功");
            //回调
            redFlagView.showMyClassRedFlag(classRedFlags);
        } catch (Exception e) {
            //回调
            Log.i("获取数据：我班流动红旗","失败");
            redFlagView.showMyClassRedFlag(null);
            e.printStackTrace();
        }

    }
}
