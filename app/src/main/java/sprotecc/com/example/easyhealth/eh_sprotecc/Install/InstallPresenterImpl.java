package sprotecc.com.example.easyhealth.eh_sprotecc.Install;

import com.ruite.gem.modal.组织信息.Clazz;
import com.ruite.gem.modal.组织信息.Grade;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.ClazzThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.GradeThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/26.
 */
public class InstallPresenterImpl extends BasePresenterImpl<InstallView> implements InstallPresenter<InstallView> {
   InstallModelImpl installModelimpl=new InstallModelImpl();
    InstallView installView;
    public InstallPresenterImpl(InstallView installView){
        this.installView=installView;
        //启动获取所有年级
        MyThreadPool.getInstance().createLongPool().execute(new GradeThread(installView));
    }

    /**
     *获取并回调gradelistview
     */
    @Override
    public   void Grade() {

    }

    /**
     * 获取并回调clazzlistview
     */
    @Override
    public void Clazz() {

    }

    @Override
    public void startClazzThread(long id) {
        MyThreadPool.getInstance().createLongPool().execute(new ClazzThread(installView,id));

    }


}
