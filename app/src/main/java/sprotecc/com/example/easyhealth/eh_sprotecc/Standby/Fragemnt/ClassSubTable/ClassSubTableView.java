package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSubTable;

import com.ruite.gem.modal.班牌基础.ClassSchedule;
import com.ruite.gem.modal.班牌基础.Timetable;

import java.util.List;
import java.util.Map;

/**
 * Created by adminHjq on 2016/12/23.
 */
public interface ClassSubTableView {
    //显示课表
    public void showSubTable(List<Timetable> list, Map<Long, Map<Integer, ClassSchedule>> longMapMap);
    public void showNoSubTable(List<Timetable> list, Map<Long, Map<Integer, ClassSchedule>> longMapMap);

}
