package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo;

import com.ruite.gem.modal.班牌基础.StudentEvaluate;
import com.ruite.gem.modal.运动健康.运动.SportRank;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.ConductPointsThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.SportRankThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempConductPoint;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/21.
 */
public class ClassInfoModelImpl implements ClassInfoModel {
    ClassInfoPresenterImpl classInfoPresenter;


    public ClassInfoModelImpl(ClassInfoPresenterImpl classInfoPresenter) {
        this.classInfoPresenter = classInfoPresenter;
    }







}
