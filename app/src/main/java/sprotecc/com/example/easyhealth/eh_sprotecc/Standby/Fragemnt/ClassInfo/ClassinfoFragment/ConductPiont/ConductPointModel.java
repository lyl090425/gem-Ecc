package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.ConductPiont;

import com.ruite.gem.modal.人员信息.ClassStudent;
import com.ruite.gem.modal.班牌基础.StudentEvaluate;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempConductPoint;

/**
 * Created by adminHjq on 2017/1/3.
 */
public interface ConductPointModel {
    //操行分相关
    public void startConductPointsThread();
    public void callbackConductPoints(List<ClassStudent> list);
    public void storeConductPoints(List<ClassStudent>  list);
    public List<TempConductPoint>  getTempConductPoints();
}
