package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import android.util.Log;

import com.ruite.gem.action.公开.电子班牌.流动红旗.年级流动红旗排名;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.班牌基础.ClassRedFlag;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.RedFlag.RedFlagView;

/**
 * Created by adminHjq on 2017/1/4.
 */
public class GradeRedFlagRankThread implements Runnable {
      private RedFlagView redFlagView;
    public GradeRedFlagRankThread(RedFlagView redFlagView){
        this.redFlagView=redFlagView;

    }
    @Override
    public void run() {
        try {
            Rpc rpc = new Rpc(MyApplication.url);
            年级流动红旗排名 action = new 年级流动红旗排名();
            action.setSchoolId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId()));
            action.setGradeId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getGrade()));
            年级流动红旗排名 re = rpc.call(action);

            List<ClassRedFlag> classRedFlags = re.getClassRedFlags();
            //回调
            Log.i("获取数据：年级流动红旗排名","成功");
            redFlagView.showGradeRedFlagRank(classRedFlags);
        } catch (Exception e) {
            //回调
            Log.i("获取数据：年级流动红旗排名","失败");
            redFlagView.showGradeRedFlagRank(null);
            e.printStackTrace();
        }
    }
}
