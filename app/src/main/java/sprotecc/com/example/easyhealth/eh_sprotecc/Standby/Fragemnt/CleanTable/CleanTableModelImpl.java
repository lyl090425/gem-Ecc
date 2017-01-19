package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.CleanTable;

import com.ruite.gem.modal.班牌基础.DutyUser;

import java.util.List;
import java.util.Map;

import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.CleanTableThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/23.
 */
public class CleanTableModelImpl implements CleanTableModel {
    CleanTablePresenterImpl cleanTablePresenter;


    public CleanTableModelImpl(CleanTablePresenterImpl cleanTablePresenter) {
        this.cleanTablePresenter = cleanTablePresenter;
    }

    @Override
    public void startCleanTableThread() {

    }

    @Override
    public void callbackCleanTable(List<String> types, Map<String, Map<Integer, List<DutyUser>>> dutyUsermap) {

    }

    @Override
    public void storeTempCleanTable() {
       //暂时没做
    }
}
