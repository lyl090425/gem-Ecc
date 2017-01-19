package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.Ing;

import com.ruite.gem.modal.班牌基础.ClassSchedule;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.IngInfoThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempIngInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class IngPresenterImpl extends BasePresenterImpl<IngView> implements IngPresenter<IngView> {
    private IngView ingView;
   private  IngModelImpl ingModel =new IngModelImpl(this);
    public IngPresenterImpl(IngView ingView) {
        this.ingView = ingView;

    }

    @Override
    public void ingInfo() {
        ClassSchedule classSchedule=ingModel.getClassSchedule();
        //存在本地
        ingModel.storeIng(classSchedule);

    }

    @Override
    public void startThread() {
        ingModel.startIngThread();

    }

    @Override
    public void showInfo() {
        //显示
        TempIngInfo tempIngInfo  = ingModel.getTempIng();
        ingView.showIng(tempIngInfo);
    }
}
