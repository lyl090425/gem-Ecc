package sprotecc.com.example.easyhealth.eh_sprotecc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ruite.gem.modal.新闻.News;
import com.ruite.gem.modal.班牌基础.DutyUser;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

/**
 * Created by adminHjq on 2017/1/10.
 */
public class NewsAdapter extends BaseAdapter {
    private List<News> list;
    private LayoutInflater inflater;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);//日期格式
    public NewsAdapter(List<News> list) {
        this.list = list;
        inflater = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 1;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (list != null) {
            ViewHolder viewHolder = null;
            News news = list.get(position);
            if (convertView != null) {
                viewHolder = (ViewHolder) convertView.getTag();
            } else {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.news, null);
                viewHolder.r = (TextView) convertView.findViewById(R.id.news_list1);
                viewHolder.title = (TextView) convertView.findViewById(R.id.news_list2);
                viewHolder.date = (TextView) convertView.findViewById(R.id.news_list3);
                convertView.setTag(viewHolder);
            }
            if(news!=null){

                int i=position+1;
                viewHolder.r.setText(i+"");

                viewHolder.title.setText(news.getTitle());//标题
                viewHolder.date.setText(sdf.format(  news.getPublishDate()));//发布时间
            }
                return convertView;


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
        TextView r;
        TextView title;
        TextView date;
    }
}
