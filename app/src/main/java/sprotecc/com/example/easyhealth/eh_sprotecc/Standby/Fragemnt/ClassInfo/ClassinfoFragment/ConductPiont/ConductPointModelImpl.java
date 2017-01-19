package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.ConductPiont;

import com.ruite.gem.modal.人员信息.ClassStudent;
import com.ruite.gem.modal.班牌基础.StudentEvaluate;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.ConductPointsThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempConductPoint;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class ConductPointModelImpl implements ConductPointModel {
   private ConductPointPresenterImpl conductPointPresenter;
  private   List<ClassStudent> listStudentEvaluate;

    public List<ClassStudent> getListStudentEvaluate() {
        return listStudentEvaluate;
    }

    public void setListStudentEvaluate(List<ClassStudent> listStudentEvaluate) {
        this.listStudentEvaluate = listStudentEvaluate;
    }

    public ConductPointModelImpl(ConductPointPresenterImpl conductPointPresenter){
        this.conductPointPresenter=conductPointPresenter;
    }

    @Override
    public void startConductPointsThread() {
        MyThreadPool.getInstance().createLongPool().execute(new ConductPointsThread(this));
    }

    @Override
    public void callbackConductPoints(List<ClassStudent> list) {
        this.listStudentEvaluate = list;
        conductPointPresenter.conductPoints();
    }

    @Override
    public void storeConductPoints(List<ClassStudent> list) {
        DataDao.getInstance().storeTempConductPiont(list);
    }

    @Override
    public List<TempConductPoint> getTempConductPoints() {
        return     DataDao.getInstance().getTempStudentEvaluate();
    }
}
