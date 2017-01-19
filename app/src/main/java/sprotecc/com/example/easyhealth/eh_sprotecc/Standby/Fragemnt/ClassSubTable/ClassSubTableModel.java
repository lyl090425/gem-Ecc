package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSubTable;

import com.ruite.gem.modal.班牌基础.ClassSchedule;
import com.ruite.gem.modal.班牌基础.StudentEvaluate;
import com.ruite.gem.modal.班牌基础.Timetable;

import java.util.List;
import java.util.Map;

/**
 * Created by adminHjq on 2016/12/23.
 */
public interface ClassSubTableModel {


    //课表相关
    public void startSubTableThread();
    public void callbackSubTable(List<Timetable> list, Map<Long, Map<Integer, ClassSchedule>> longMapMap);
    public void storeSubTable(List<Timetable> list, Map<Long, Map<Integer, ClassSchedule>> longMapMap);
    public List<ClassSchedule>  getTempSubTable();
}
