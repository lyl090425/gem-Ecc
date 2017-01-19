package sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Presenter;

import android.os.Handler;
import android.widget.TabHost;
import android.widget.Toast;

import com.ruite.gem.modal.人员信息.UserInfo;
import com.ruite.gem.spdtp.ECC_SPORTDATA;
import com.ruite.gem.spdtp.SPDTP_HW_ZSLYEYDATA;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.PushCardThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.UploadSportData;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.UserInfoThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Model.MySportDataModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.View.MySportDataView;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/8.
 */
public class MySportDataPresenterImpl extends BasePresenterImpl<MySportDataView> implements MySportDataPresenter<MySportDataView>{

    MySportDataView mySportDataView;
    Handler handler;
    MySportDataModelImpl mySportDataModel=new MySportDataModelImpl(this);

    public MySportDataPresenterImpl(MySportDataView mySportDataView, Handler handler){
        this.mySportDataView=mySportDataView;
        this.handler=handler;
    }

    /**
     * 从本地获取到用户信息，成功后设置信息到界面上，如果本地不存在，则请求网络。并在sportDataService
     * 中设置正确的信息到页面上
     */
    @Override
    public void sportData() {
        ECC_SPORTDATA ecc_sportdata=MyApplication.getInstance().getEcc_sportdata();
        //启动打卡线程
        MyThreadPool.getInstance().createLongPool().execute(new PushCardThread(mySportDataView,MyApplication.getInstance().getEcc_sportdata().getUserCode().trim(),new Date()));
        //启动上传数据的线程
        MyThreadPool.getInstance().createLongPool().execute(new UploadSportData(mySportDataView,MyApplication.getInstance().getEcc_sportdata().getSportData()));
//        mySportDataModel.startUploadSportThread();
        //从本地查询用户
        UserInfo userInfo=mySportDataModel.getUserInfo(ecc_sportdata.getUserCode().trim());
        //如果存在本地用户
        if(userInfo!=null){
            //设置到页面上
            mySportDataView.showSportData(userInfo,ecc_sportdata);
        }
        //如果不存在本地用户（有新的手环，用户信息还没有存在本地数据库里面）
       else{
         //从网络获取到用户信息,信息返回在sportDataService（）中处理
            MyThreadPool.getInstance().createShortPool().execute(new UserInfoThread(mySportDataView,MyApplication.getInstance().getEcc_sportdata().getUserCode().trim()));
//            mySportDataModel.startGetUserInfoThread(MyApplication.getInstance().getEcc_sportdata().getUserCode().trim());
        }




    }

    /**
     * 从网络上获取到用户信息后，把运动信息设置到界面上
     */
    @Override
    public void sportDataService() {

    }

    /**
     * 打卡处理
     */
    @Override
    public void pushCard() {

    }

    /**
     * 上运动信息处理
     */
    @Override
    public void upLoadSportData() {


    }

}
