package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import android.util.Log;

import com.ruite.gem.action.公开.电子班牌.获取个人荣誉;
import com.ruite.gem.action.公开.电子班牌.获取个人荣誉统计信息;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.班牌基础.StudentHonour;
import com.ruite.gem.modal.运动健康.运动.Sport;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.Model.MyInformationModel;
import sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.Model.MyInformationModelImpl;

/**
 * Created by adminHjq on 2016/12/29.
 */
public class MyHonourThread implements Runnable {
    private MyInformationModelImpl myInformationModel;

    public MyHonourThread(MyInformationModelImpl myInformationModel) {
        this.myInformationModel = myInformationModel;
    }

    @Override
    public void run() {
        Rpc rpc = null;
        try {
            rpc = new Rpc(MyApplication.url);
            获取个人荣誉统计信息 ation =new 获取个人荣誉统计信息();
            ation.setCode(MyApplication.getInstance().getEcc_sportdata().getUserCode().trim());
            ation.setCodeType("学号");
            ation.setSchoolId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId()));
            获取个人荣誉统计信息 re=rpc.call(ation);
            List<StudentHonour> s= re.getList();
            Log.i("数据获取-荣誉","获取荣誉成功");
            //回调
            myInformationModel.callbackHonourHistory(s);
        } catch (Exception e) {
            Log.i("数据获取-荣誉","获取荣誉失败");
            myInformationModel.callbackHonourHistory(null);
            e.printStackTrace();
        }


    }
}
