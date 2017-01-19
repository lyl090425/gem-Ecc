package sprotecc.com.example.easyhealth.eh_sprotecc.Install;

import com.ruite.gem.modal.组织信息.Clazz;
import com.ruite.gem.modal.组织信息.Grade;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.ClazzThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.GradeThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/26.
 */
public class InstallModelImpl implements  InstallModel{


    List<Grade> listGrade;



    List<Clazz> listClazz;
    InstallPresenterImpl installPresenter;
    public List<Grade> getListGrade() {
        return listGrade;
    }

    public void setListGrade(List<Grade> list) {
        this.listGrade = list;
    }
    public List<Clazz> getListClazz() {
        return listClazz;
    }

    public void setListClazz(List<Clazz> listClazz) {
        this.listClazz = listClazz;
    }
    @Override
    public void getGrade() {

    }

    @Override
    public void getClazz(long id) {


    }

    @Override
    public void callBackGrade(List<Grade> list) {
        this.listGrade=list;
        installPresenter.Grade();
    }

    @Override
    public void callBackClazz(List<Clazz> list) {
        this.listClazz=list;
        installPresenter.Clazz();
    }


}
