package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import android.util.Log;

import com.ruite.gem.action.公开.学校.组织.列学校对应年级;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.组织信息.Grade;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Install.InstallModel;
import sprotecc.com.example.easyhealth.eh_sprotecc.Install.InstallModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Install.InstallView;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;


/**列学校对应年级
 * Created by adminHjq on 2016/12/26.
 */
public class GradeThread implements Runnable {
    InstallView installView;
    public GradeThread(InstallView installView){
        this.installView=installView;
    }
    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            列学校对应年级 action=new 列学校对应年级();
            String schoolId= DataDao.getInstance().getSoftInfoByFlag().getSchoolId();
            long l=Long.parseLong(schoolId);
            action.setSchoolId(l);
            列学校对应年级 re=rpc.call(action);
            List<Grade> list =re.getList();
            //回调
            Log.i("数据获取-设置","获取年级列表成功");
            installView.setGradeListView(list);
        } catch (Exception e) {
            Log.i("数据获取-设置","获取年级列表失败");
            installView.setGradeListView(null);
            e.printStackTrace();
        }
    }
}
