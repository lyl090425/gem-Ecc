package sprotecc.com.example.easyhealth.eh_sprotecc.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ruite.gem.modal.班牌基础.ClassSchedule;
import com.ruite.gem.modal.班牌基础.DutyUser;

import java.util.List;
import java.util.Map;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

/**
 * Created by adminHjq on 2017/1/4.
 */
public class CleanTableAdapter extends BaseAdapter {
    private List<String> types;
    private Map<String, Map<Integer, List<DutyUser>>> dutyUsermap;
    private LayoutInflater inflater;

    public CleanTableAdapter(List<String> types, Map<String, Map<Integer, List<DutyUser>>> dutyUsermap) {
        this.types = types;
        this.dutyUsermap = dutyUsermap;
        inflater = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        if (types != null) {
            if(types.size()==0){
                return 1;
            }
            return types.size();
        }
        return 1;
    }

    @Override
    public Object getItem(int position) {
        if (types != null) {
            String key = types.get(position);
            return dutyUsermap.get(key);
        }
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (types != null && dutyUsermap != null&&types.size()!=0) {
            ViewHolder viewHolder = null;
            String key = types.get(position);
            final Map<Integer, List<DutyUser>> cleantable = dutyUsermap.get(key);//
            if (convertView != null) {
                viewHolder = (ViewHolder) convertView.getTag();
            } else {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.cleantable, null);
                viewHolder.papa = (TextView) convertView.findViewById(R.id.list_clean_0);
                viewHolder.monday = (TextView) convertView.findViewById(R.id.list_clean_1);
                viewHolder.tuesday = (TextView) convertView.findViewById(R.id.list_clean_2);
                viewHolder.wednesday = (TextView) convertView.findViewById(R.id.list_clean_3);
                viewHolder.thursday = (TextView) convertView.findViewById(R.id.list_clean_4);
                viewHolder.friday = (TextView) convertView.findViewById(R.id.list_clean_5);
                convertView.setTag(viewHolder);
            }
            if (cleantable != null) {
                if (cleantable.size() != 0) {
                    for (int i = 0; i < cleantable.size(); i++) {

                        viewHolder.papa.setText(key);
                        for (int j = 1; j < 6; j++) {
                            String name1 = "";
                            if (cleantable.get(j) != null) {
                                List<DutyUser> list1 = cleantable.get(j);
                                for (int m = 0; m < list1.size(); m++) {
                                    name1 = name1 + list1.get(m).getUser().getName() + ",";
                                }
                                switch (j) {
                                    case 1:
                                        viewHolder.monday.setText(name1);
                                        break;
                                    case 2:
                                        viewHolder.tuesday.setText(name1);
                                        break;
                                    case 3:
                                        viewHolder.wednesday.setText(name1);
                                        break;
                                    case 4:
                                        viewHolder.thursday.setText(name1);
                                        break;
                                    case 5:
                                        viewHolder.friday.setText(name1);
                                        break;
                                }
                            }
                        } return convertView;
                    }
                } else {
                    return convertView;
                }
            } else {
                return convertView;
            }
        } else {
            ViewHolder viewHolder1 = null;
            if (convertView != null) {
                viewHolder1 = (ViewHolder) convertView.getTag();
            } else {
                viewHolder1 = new ViewHolder();
                convertView = inflater.inflate(R.layout.defaultlayout, null);
                convertView.setTag(viewHolder1);
            }

        }
        return convertView;
    }


    class ViewHolder {
        TextView papa;
        TextView monday;
        TextView tuesday;
        TextView wednesday;
        TextView thursday;
        TextView friday;
    }
}
