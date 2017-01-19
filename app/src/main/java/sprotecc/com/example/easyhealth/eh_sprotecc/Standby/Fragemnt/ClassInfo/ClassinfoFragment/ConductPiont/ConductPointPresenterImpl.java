package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.ConductPiont;

import com.ruite.gem.modal.人员信息.ClassStudent;
import com.ruite.gem.modal.班牌基础.StudentEvaluate;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempConductPoint;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class ConductPointPresenterImpl extends BasePresenterImpl<ConductPointView> implements ConductPointPresenter<ConductPointView> {
private ConductPointView conductPiontView;
    private ConductPointModelImpl conductPointModel =new ConductPointModelImpl(this);
    public ConductPointPresenterImpl(ConductPointView conductPiontView){
        this.conductPiontView=conductPiontView;

}

    @Override
    public void conductPoints() {
        List<ClassStudent> list =conductPointModel.getListStudentEvaluate();

        conductPointModel.storeConductPoints(list);
    }

    @Override
    public void startThread() {
        conductPointModel.startConductPointsThread();
    }

    @Override
    public void showInfo() {
//        //操行分
        List<TempConductPoint> studentEvaluateList=conductPointModel.getTempConductPoints();
        conductPiontView.showConductPoint(studentEvaluateList);
    }
}
