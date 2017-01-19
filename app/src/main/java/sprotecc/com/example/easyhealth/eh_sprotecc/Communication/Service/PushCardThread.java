package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;

import android.util.Log;

import com.ruite.gem.action.公开.考勤.添加考勤记录;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.人员信息.UserInfo;

import java.util.Date;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Model.MySportDataModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.View.MySportDataView;

/**
 * 打卡
 * Created by adminHjq on 2016/12/28.
 */
public class PushCardThread implements Runnable{
    MySportDataView mySportDataView;
    String usercode;
    Date date;
    public PushCardThread( MySportDataView mySportDataView,String usercode,Date date){
        this.mySportDataView=mySportDataView;
        this.usercode=usercode;
        this.date=date;

    }
    @Override
    public void run() {
        Rpc rpc= null;
        try {
            rpc = new Rpc(MyApplication.url);
            添加考勤记录 action =new 添加考勤记录();
            action.setUserCode(usercode);
            action.setSchoolId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId()));
            action.setCodeType("学号");
            action.setSignDate(date);
            添加考勤记录 re=rpc.call(action);
            UserInfo userInfo= re.getUserInfo();//刷卡人
            Date date1= re.getSignTime();//刷卡时间
            Log.i("数据查询","上传考勤成功");
            mySportDataView.showPushCard(userInfo,date1);
            //回调
//            mySportDataModel.callbackPushCard(userInfo,date1);
        } catch (Exception e) {
            Log.i("数据查询","上传考勤失败");
            mySportDataView.showPushCard(null,null);
            e.printStackTrace();
        }




    }
}
