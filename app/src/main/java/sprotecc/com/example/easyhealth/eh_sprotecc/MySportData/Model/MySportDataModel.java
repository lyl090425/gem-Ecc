package sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Model;

import com.ruite.gem.modal.人员信息.UserInfo;
import com.ruite.gem.modal.运动健康.运动.SportRank;

import java.util.Date;
import java.util.List;

/**
 * Created by adminHjq on 2016/12/16.
 */
public interface MySportDataModel {

    //用户信息相关
    public void startGetUserInfoThread(String usercode);
    public void callbackUserInfo(UserInfo userInfo);
    public void storeUserInfo(List<UserInfo> list);//单个用户也采用List.
    public UserInfo getUserInfo(String code);
    //打卡相关
    public void     startPushCardThread(String code, Date date);
    public void callbackPushCard(UserInfo userInfo,Date date );
    public void storePushCardInfo(UserInfo userInfo,Date date);//单个用户也采用List.

    //运动数据上传
    public void startUploadSportThread();
    public void callbackUploadSport(boolean b);

}
