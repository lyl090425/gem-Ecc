package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSubTable;

import android.os.Handler;

import com.ruite.gem.modal.班牌基础.ClassSchedule;
import com.ruite.gem.modal.班牌基础.Timetable;

import java.util.List;
import java.util.Map;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.ClassSubTableThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassInfoModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/23.
 */
public class ClassSubTablePresenterImpl extends BasePresenterImpl<ClassSubTableView>implements ClassSubTablePresenter <ClassSubTableView>{
    ClassSubTableView classSubTableView;
    ClassSubTableModelImpl classInfoModel=new ClassSubTableModelImpl(this);
    Handler handler;
    public ClassSubTablePresenterImpl(ClassSubTableView classSubTableView, Handler handler){
        this.classSubTableView=classSubTableView;
        this.handler=handler;
    }

    /**
     * 启动需要的线程
     */
    @Override
    public void startServiceInfo() {
        //课表详情
       MyThreadPool.getInstance().createShortPool().execute(new ClassSubTableThread(classSubTableView));
    }

    @Override
    public void showServiceInfo() {

    }

    @Override
    public void subTable() {
        List<Timetable> list=classInfoModel.getList();
        Map<Long, Map<Integer, ClassSchedule>> longMapMap=classInfoModel.getLongMapMap();
        //存入数据库（比较复杂，暂时未做，等稳定后再做存储）
        classInfoModel.storeSubTable(list,longMapMap);
        //显示到页面上
        classSubTableView.showSubTable(list,longMapMap);

    }
}
