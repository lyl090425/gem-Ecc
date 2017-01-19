package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import android.util.Log;

import com.ruite.gem.action.公开.学校.组织.列年级对应班级;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.组织信息.Clazz;
import java.util.List;
import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Install.InstallModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Install.InstallView;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

/**
 * 获取班级编号
 * Created by adminHjq on 2016/12/27.
 */
public class ClazzThread implements Runnable{
    InstallView installView;
    long gradeId;
    public ClazzThread(InstallView installView,long GradeId){
        this.gradeId=GradeId;
        this.installView=installView;
    }
    @Override
    public void run() {

        try {
            Rpc rpc=new Rpc(MyApplication.url);
            列年级对应班级 action=new 列年级对应班级();
            String schoolId=MyApplication.getContext().getResources().getString(R.string.soft_schoolId);
            long s=Long.parseLong(schoolId);
            action.setSchoolId(s);
            action.setGradeId(gradeId);
            列年级对应班级 re=rpc.call(action);
            List<Clazz> list =re.getList();
            //回调
            Log.i("数据获取-设置","获取班级列表成功");
            installView.setClazzListView(list);
        } catch (Exception e) {
            Log.i("数据获取-设置","获取班级列表失败");
            installView.setClazzListView(null);
            e.printStackTrace();
        }

    }
}
