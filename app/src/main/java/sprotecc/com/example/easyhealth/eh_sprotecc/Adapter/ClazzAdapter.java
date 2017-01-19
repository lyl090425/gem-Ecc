package sprotecc.com.example.easyhealth.eh_sprotecc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ruite.gem.modal.组织信息.Clazz;
import com.ruite.gem.modal.组织信息.Grade;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

/**
 * Created by adminHjq on 2016/12/27.
 */
public class ClazzAdapter extends BaseAdapter {
    private List<Clazz> list;
    private LayoutInflater inflater;

    public ClazzAdapter(List<Clazz> list) {
        this.list = list;
        inflater = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(list!=null){
            return list.size();
        }
        return 1;
    }

    @Override
    public Object getItem(int position) {
        if(list!=null){
          return   list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        final Clazz clazz = list.get(position);



            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.gradeandclazzlistview, null);
            viewHolder.gradeName = (TextView) convertView.findViewById(R.id.gradeclazzlistview);
            convertView.setTag(viewHolder);
//        }else {
//            viewHolder=(ViewHolder) convertView.getTag();
//        }
        viewHolder.gradeName.setText(clazz.getName());
        return convertView;
    }

    class ViewHolder {
        TextView gradeName;

    }
}

