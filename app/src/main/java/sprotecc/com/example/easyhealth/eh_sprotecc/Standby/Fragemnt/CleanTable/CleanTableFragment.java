package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.CleanTable;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ruite.gem.modal.班牌基础.DutyUser;

import java.util.List;
import java.util.Map;

import sprotecc.com.example.easyhealth.eh_sprotecc.Adapter.CleanTableAdapter;
import sprotecc.com.example.easyhealth.eh_sprotecc.Adapter.SubTableAdapter;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

/**
 * Created by adminHjq on 2016/12/23.
 */
public class CleanTableFragment extends BaseFragment<CleanTableView,CleanTablePresenter<CleanTableView>> implements CleanTableView {
    private  View view;
    CleanTablePresenter cleanTablePresenter;
    private ListView cleanTable;
    private Handler handler=new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cleantable, container, false);
        initView();
        cleanTablePresenter =new CleanTablePresenterImpl(this,handler);
        cleanTablePresenter.startServiceInfo();
        return view;
    }

    @Override
    protected CleanTablePresenter<CleanTableView> createPresenter() {
        return null;
    }
    public void initView(){
        cleanTable= (ListView) view.findViewById(R.id.clean_table);
    }
    @Override
    public void showCleanTable(final List<String> types, final Map<String, Map<Integer, List<DutyUser>>> dutyUsermap) {

        if(handler!=null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.i("数据显示-值日", "开始设置值日信息到页面上0");
                    CleanTableAdapter cleanTableAdapter = new CleanTableAdapter(types, dutyUsermap);
                    cleanTable.setDivider(new ColorDrawable(Color.rgb(0, 255, 255)));
                    cleanTable.setDividerHeight(1);
                    cleanTable.setAdapter(cleanTableAdapter);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
