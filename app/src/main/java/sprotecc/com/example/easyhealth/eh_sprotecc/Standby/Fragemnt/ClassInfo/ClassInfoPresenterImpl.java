package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo;

import com.ruite.gem.modal.班牌基础.StudentEvaluate;
import com.ruite.gem.modal.运动健康.运动.SportRank;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempConductPoint;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;


/**
 * Created by adminHjq on 2016/12/21.
 */
public class ClassInfoPresenterImpl extends BasePresenterImpl<ClassInfoView> implements ClassInfoPresenter<ClassInfoView> {
    ClassInfoView classInfoView;
    ClassInfoModelImpl classInfoModel= new ClassInfoModelImpl(this);
    public ClassInfoPresenterImpl(ClassInfoFragment classInfoFragment){
      this.classInfoView=classInfoFragment;
    }


}
