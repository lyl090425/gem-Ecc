package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSubTable;

import com.ruite.gem.modal.班牌基础.ClassSchedule;
import com.ruite.gem.modal.班牌基础.Timetable;

import java.util.List;
import java.util.Map;

import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.ClassSubTableThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassInfoModel;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassInfoModelImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/23.
 */
public class ClassSubTableModelImpl implements ClassSubTableModel{
    private ClassSubTablePresenterImpl classSubTablePresenter;
    private List<Timetable> list;
    private Map<Long, Map<Integer, ClassSchedule>> longMapMap;

    public List<Timetable> getList() {
        return list;
    }

    public void setList(List<Timetable> list) {
        this.list = list;
    }

    public Map<Long, Map<Integer, ClassSchedule>> getLongMapMap() {
        return longMapMap;
    }

    public void setLongMapMap(Map<Long, Map<Integer, ClassSchedule>> longMapMap) {
        this.longMapMap = longMapMap;
    }

    public ClassSubTableModelImpl(ClassSubTablePresenterImpl classSubTablePresenter){
        this.classSubTablePresenter=classSubTablePresenter;
    }

    @Override
    public void startSubTableThread() {
    }

    /**
     * 线程的回调
     * @param list
     * @param longMapMap
     */
    @Override
    public void callbackSubTable(List<Timetable> list, Map<Long, Map<Integer, ClassSchedule>> longMapMap) {

    }

    /**
     * 存入到数据库
     * @param list
     * @param longMapMap
     */
    @Override
    public void storeSubTable(List<Timetable> list, Map<Long, Map<Integer, ClassSchedule>> longMapMap) {

    }

    /**
     * 从数据库取出
     * @return
     */
    @Override
    public List<ClassSchedule> getTempSubTable() {
        return null;
    }
}
