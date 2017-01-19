package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;

import android.util.Log;

import com.ruite.gem.action.公开.API.终端上报运动数据;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.运动健康.运动.Sport;
import com.ruite.gem.modal.运动健康.运动.SportData;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Model.MySportDataModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.View.MySportDataView;

/**
 * Created by adminHjq on 2016/12/30.
 */
public class UploadSportData implements Runnable {
    MySportDataView mySportDataView;
    List<Sport> sportList;

    public UploadSportData(MySportDataView mySportDataView, List<Sport> list) {
        this.mySportDataView = mySportDataView;
        this.sportList = list;
    }

    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            终端上报运动数据 action =new 终端上报运动数据();
            action.setSchoolId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId()));
            Log.i("数据查询",Long.parseLong(DataDao.getInstance().getHardwareInfoByflag().getSchoolId())+"：上传数据时的学校ID");
            action.setSports(sportList);
            终端上报运动数据 re=rpc.call(action);
            re.getIsolation();
            //回调
            Log.i("数据查询","上传运动数成功");
            mySportDataView.showUpload(1);
//            mySportDataModel.callbackUploadSport(true);
        } catch (Exception e) {
            //回调
            Log.i("数据查询","上传运动数失败");
            mySportDataView.showUpload(0);
//            mySportDataModel.callbackUploadSport(false);
            e.printStackTrace();
        }


    }
}
