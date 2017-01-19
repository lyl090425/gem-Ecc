package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;

import android.util.Log;

import com.ruite.gem.action.公开.人事.查询用户信息;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.人员信息.UserInfo;

import java.util.ArrayList;
import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Model.MySportDataModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.View.MySportDataView;

/**
 * Created by adminHjq on 2016/12/28.
 */
public class UserInfoThread implements Runnable {
    MySportDataView mySportDataView;
    String userCode ;
    public UserInfoThread(MySportDataView mySportDataView,String userCode){
       this.mySportDataView=mySportDataView;
        this.userCode=userCode;
    }
    @Override
    public void run() {
        try {
            Rpc rpc =new Rpc(MyApplication.url);
            查询用户信息 action =new 查询用户信息();
            action.setCode(userCode);
            action.setSchoolId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId()));
            action.setCodeType("学号");
            //待定
            查询用户信息 re=rpc.call(action);
            UserInfo userInfo=re.getUser();
            //保存在本地（需要保存又需要马上显示的）
            List<UserInfo> userInfos=new ArrayList<UserInfo>();
            userInfos.add(userInfo);
           DataDao.getInstance().storeUserInfo(userInfos);
            Log.i("数据查询","查询刷卡人用户信息成功");
            //回调
            mySportDataView.showSportDataHTTP(userInfo,MyApplication.getInstance().getEcc_sportdata());
        } catch (Exception e) {
            Log.i("数据查询","查询刷卡人用户信息失败");
            //回调
            mySportDataView.showSportDataMoshengren(MyApplication.getInstance().getEcc_sportdata());
            e.printStackTrace();
        }

    }
}
