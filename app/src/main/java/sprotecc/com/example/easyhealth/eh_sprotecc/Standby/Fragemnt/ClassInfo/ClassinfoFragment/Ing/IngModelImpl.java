package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.Ing;

import com.ruite.gem.modal.班牌基础.ClassSchedule;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.IngInfoThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempIngInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class IngModelImpl  implements  IngModel{
  public IngPresenterImpl ingPresenter;
    private ClassSchedule classSchedule;

    public ClassSchedule getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(ClassSchedule classSchedule) {
        this.classSchedule = classSchedule;
    }

    public  IngModelImpl(IngPresenterImpl ingPresenter){
       this.ingPresenter=ingPresenter;
    }
    @Override
    public void startIngThread() {
        MyThreadPool.getInstance().createLongPool().execute(new IngInfoThread(this));
    }

    @Override
    public void callbackIng(ClassSchedule list) {
         this.classSchedule=list;
        ingPresenter.ingInfo();
    }

    @Override
    public void storeIng(ClassSchedule list) {
        DataDao.getInstance().storeTempIng(list);

    }

    @Override
    public TempIngInfo getTempIng() {

        return  DataDao.getInstance().getTempIng();
    }

}
