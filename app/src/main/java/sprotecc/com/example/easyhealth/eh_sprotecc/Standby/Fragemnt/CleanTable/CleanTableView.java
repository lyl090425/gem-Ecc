package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.CleanTable;

import com.ruite.gem.modal.班牌基础.DutyUser;

import java.util.List;
import java.util.Map;

/**
 * Created by adminHjq on 2016/12/23.
 */
public interface CleanTableView {
    //显示值日
    public void showCleanTable(List<String> types,
    Map<String, Map<Integer, List<DutyUser>>> dutyUsermap);
}
