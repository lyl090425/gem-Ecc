package sprotecc.com.example.easyhealth.eh_sprotecc.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.ruite.gem.modal.组织信息.Grade;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempHonour;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;
import sprotecc.com.example.easyhealth.eh_sprotecc.Tool.BitmapCache;

/**
 * Created by adminHjq on 2017/1/12.
 */
public class MyHonorAdapter extends BaseAdapter {
    private List<TempHonour> list;
    private LayoutInflater inflater;
    private RequestQueue mQueue;
    private  ImageLoader.ImageCache imageCache;

    public MyHonorAdapter(List<TempHonour> list,RequestQueue mQueue, ImageLoader.ImageCache imageCache) {
        this.list = list;
        inflater = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mQueue=mQueue;
        this.imageCache=imageCache;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }else {
        return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            if (list.size() != 0) {
                return list.get(position);
            }
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

            if (list.size() != 0) {
                TempHonour tempHonour = list.get(position);
                ViewHolder viewHolder = null;
//                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = inflater.inflate(R.layout.honnor, null);
                    viewHolder.honnreImage1 = (ImageView) convertView.findViewById(R.id.honor_I1);
                    viewHolder.honorName1 = (TextView) convertView.findViewById(R.id.honor_t1);
                    viewHolder.honorName2 = (TextView) convertView.findViewById(R.id.honor_t2);

                    convertView.setTag(viewHolder);
//                } else {
//                    viewHolder = (ViewHolder) convertView.getTag();
//                }
                if(position%2==0){

                }else {
                }

                long typeid=Long.parseLong(tempHonour.getTypeId());
                ImageLoader imageLoader = new ImageLoader(mQueue,new BitmapCache());
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(viewHolder.honnreImage1, R.drawable.classsport_name1, R.drawable.userhead);
                String url=MyApplication.honnorurl+((int)(typeid/500))+"/"+typeid;
                imageLoader.get(url, listener);
                viewHolder.honorName1.setText(tempHonour.getName());
                viewHolder.honorName2.setText("获得"+tempHonour.getCount()+"次");
                return convertView;
            }
        }else {
            ViewHolder viewHolder1 = null;
            if (convertView != null) {
                viewHolder1 = (ViewHolder) convertView.getTag();
            } else {
                viewHolder1 = new ViewHolder();
                convertView = inflater.inflate(R.layout.defaultlayout, null);
                convertView.setTag(viewHolder1);
            }

        } return convertView;

    }

    class ViewHolder {
        ImageView honnreImage1;
        TextView honorName1;
        TextView honorName2;

    }
}
