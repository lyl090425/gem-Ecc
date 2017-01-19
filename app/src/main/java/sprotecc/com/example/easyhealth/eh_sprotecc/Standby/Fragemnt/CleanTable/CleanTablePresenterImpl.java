package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.CleanTable;

import android.os.Handler;

import com.ruite.gem.modal.班牌基础.DutyUser;

import java.util.List;
import java.util.Map;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.CleanTableThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassInfoPresenter;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/23.
 */
public class CleanTablePresenterImpl extends BasePresenterImpl<CleanTableView> implements CleanTablePresenter<CleanTableView> {
    CleanTableView cleanTableView;
    CleanTableModelImpl cleanTableModel = new CleanTableModelImpl(this);
    Handler handler;

    public CleanTablePresenterImpl(CleanTableView cleanTableView, Handler handler) {
        this.cleanTableView = cleanTableView;
        this.handler=handler;
    }

    @Override
    public void startServiceInfo() {
        MyThreadPool.getInstance().createLongPool().execute(new CleanTableThread(cleanTableView));

    }

    @Override
    public void cleanTable() {
//        List<String> types=cleanTableModel.getTypes();
//        Map<String, Map<Integer, List<DutyUser>>> dutyUsermap=cleanTableModel.getDutyUsermap();
//        //显示
//        cleanTableView.showCleanTable(types,dutyUsermap);
    }
}
