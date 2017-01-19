package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSubTable;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ruite.gem.modal.班牌基础.ClassSchedule;
import com.ruite.gem.modal.班牌基础.Timetable;

import java.util.List;
import java.util.Map;

import sprotecc.com.example.easyhealth.eh_sprotecc.Adapter.ClazzAdapter;
import sprotecc.com.example.easyhealth.eh_sprotecc.Adapter.SubTableAdapter;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.ClassSubTableThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

/**
 * Created by adminHjq on 2016/12/23.
 */
public class ClassSubTableFragment extends BaseFragment<ClassSubTableView,ClassSubTablePresenter<ClassSubTableView>> implements ClassSubTableView {
    private   ClassSubTablePresenter classSubTablePresenter;
    private View view;
    private ListView listView_sub;
    private Handler handler=new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classsubtable, container, false);
        classSubTablePresenter=new ClassSubTablePresenterImpl(this,handler);
        classSubTablePresenter.startServiceInfo();
        initView();
        return view;
    }
    @Override
    protected ClassSubTablePresenter<ClassSubTableView> createPresenter() {
        classSubTablePresenter=new ClassSubTablePresenterImpl(this,handler);
        return classSubTablePresenter;
    }
    /**
     * 控件初始化
     */
  public void initView(){
      listView_sub= (ListView) view.findViewById(R.id.list_sub);
  }
    /**
     * 显示课表在页面上v
     * @param list
     * @param longMapMap
     */
    @Override
    public void showSubTable(final List<Timetable> list, final Map<Long, Map<Integer, ClassSchedule>> longMapMap) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                SubTableAdapter subTableAdapter = new SubTableAdapter(list,longMapMap);
                listView_sub.setDivider(new ColorDrawable(Color.rgb(0, 255, 255)));
                listView_sub.setDividerHeight(1);
                listView_sub.setAdapter(subTableAdapter);
            }
        });

    }

    @Override
    public void showNoSubTable(final List<Timetable> list, final Map<Long, Map<Integer, ClassSchedule>> longMapMap) {

        if(handler!=null){
        handler.post(new Runnable() {
            @Override
            public void run() {
                SubTableAdapter subTableAdapter = new SubTableAdapter(list,longMapMap);
                listView_sub.setDivider(new ColorDrawable(Color.rgb(0, 255, 255)));
                listView_sub.setDividerHeight(1);
                listView_sub.setAdapter(subTableAdapter);
            }
        });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
