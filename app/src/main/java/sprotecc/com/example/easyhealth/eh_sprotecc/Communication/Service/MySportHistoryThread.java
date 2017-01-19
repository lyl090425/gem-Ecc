package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;


import android.util.Log;

import com.nostra13.universalimageloader.utils.L;
import com.ruite.gem.action.公开.电子班牌.运动.获取学生运动历史数据;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.运动健康.运动.Sport;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.Model.MyInformationModelImpl;

/**
 * Created by adminHjq on 2016/12/29.
 */
public class MySportHistoryThread implements Runnable {

    private MyInformationModelImpl myInformationModel;

    public MySportHistoryThread(MyInformationModelImpl myInformationModel) {
        this.myInformationModel = myInformationModel;
    }
//    //input
//    private String code;
//    private String codeType;
//    private long schoolId;
//    private Date beginDate;
//    private Date endDate;
    @Override
    public void run() {
        try {
            Rpc rpc =new Rpc(MyApplication.url);
            获取学生运动历史数据 ation =new 获取学生运动历史数据();
            ation.setCode(MyApplication.getInstance().getEcc_sportdata().getUserCode().trim());
            ation.setCodeType("学号");
            ation.setSchoolId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId()));
            ation.setEndDate(new Date());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -380);
            Date time = cal.getTime();//保证大于1年
            ation.setBeginDate(time);
            Log.i("数据查询：我的运动历史",MyApplication.getInstance().getEcc_sportdata().getUserCode().trim()+":"+time);
            获取学生运动历史数据 re=rpc.call(ation);
            List<Sport> s= re.getSports();
            Log.i("数据查询：我的运动历史","获取我的历史成功"+s.size());
            //回调
            myInformationModel.callbackMySportDataHistory(s);
        } catch (Exception e) {
            Log.i("数据查询：我的运动历史","获取我的历史失败");
            myInformationModel.callbackMySportDataHistory(null);
            e.printStackTrace();
        }

    }
}
