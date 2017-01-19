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
import com.ruite.gem.modal.班牌基础.Timetable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

/**
 * Created by adminHjq on 2016/12/28.
 */
public class SubTableAdapter extends BaseAdapter {
    private List<Timetable> list;//时刻表
    private Map<Long, Map<Integer, ClassSchedule>> longMapMap;//根据时刻对应的周几的课程
    private LayoutInflater inflater;
    private SimpleDateFormat df = new SimpleDateFormat("HH:mm");

    public SubTableAdapter(List<Timetable> list, Map<Long, Map<Integer, ClassSchedule>> longMapMap) {
        this.list = list;
        this.longMapMap = longMapMap;
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
            long key = list.get(position).getId();
            return longMapMap.get(key);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (list != null && longMapMap != null) {
            Log.i("数据测试-课表","a");
            ViewHolder viewHolder = null;
            long key = list.get(position).getId();
            final Map<Integer, ClassSchedule> classScheduleMap = longMapMap.get(key);//Integer->对应星期0-6一直对用星期一至星期天
            if (convertView != null) {
                viewHolder = (ViewHolder) convertView.getTag();
            } else {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.subtable, null);
                viewHolder.time = (TextView) convertView.findViewById(R.id.list_sub_text0);
                viewHolder.monday = (TextView) convertView.findViewById(R.id.list_sub_text1);
                viewHolder.monday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到相应课程
                        if(classScheduleMap!=null){
                        if(classScheduleMap.get(1)!=null){
                        ClassSchedule classSchedule = classScheduleMap.get(1);
                            //隐藏课表，显示课程简介
                        }
                        }
                    }
                });
                viewHolder.tuesday = (TextView) convertView.findViewById(R.id.list_sub_text2);
                viewHolder.tuesday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(classScheduleMap!=null){
                        if(classScheduleMap.get(2)!=null){
                            ClassSchedule classSchedule = classScheduleMap.get(2);
                            //隐藏课表，显示课程简介
                        }
                    }}
                });
                viewHolder.wednesday = (TextView) convertView.findViewById(R.id.list_sub_text3);
                viewHolder.wednesday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到相应课程
                        if(classScheduleMap!=null){
                        if(classScheduleMap.get(3)!=null){
                            ClassSchedule classSchedule = classScheduleMap.get(3);
                            //隐藏课表，显示课程简介
                        }
                    }}
                });
                viewHolder.thursday = (TextView) convertView.findViewById(R.id.list_sub_text4);
                viewHolder.thursday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到相应课程
                        if(classScheduleMap!=null){
                        if(classScheduleMap.get(4)!=null){
                            ClassSchedule classSchedule = classScheduleMap.get(4);
                            //隐藏课表，显示课程简介
                        }}
                    }
                });
                viewHolder.friday = (TextView) convertView.findViewById(R.id.list_sub_text5);
                viewHolder.friday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到相应课程
                        if(classScheduleMap!=null){
                        if(classScheduleMap.get(5)!=null){
                            ClassSchedule classSchedule = classScheduleMap.get(5);
                            //隐藏课表，显示课程简介
                        }}
                    }
                });
                convertView.setTag(viewHolder);
            }

            if(position%2==0){
                convertView.setBackgroundColor(Color.rgb(255, 245, 238));
            }else {
                convertView.setBackgroundColor(Color.rgb(240, 255, 255));
            }

            if (classScheduleMap != null) {
                if (classScheduleMap.size() != 0) {

                    for (int i = 0; i < classScheduleMap.size(); i++) {
                        int beginhour = 0;
                        int beginmin = 0;
                        int endhour = 0;
                        int endmin = 0;
                        String timename = "";
                        String betime = " ";
                        for (int j = 1; j < 6; j++) {
                            if (classScheduleMap.get(j) != null) {
                                beginhour = classScheduleMap.get(j).getTimetable().getBeginHour();
                                beginmin = classScheduleMap.get(j).getTimetable().getBeginMinute();
                                endhour = classScheduleMap.get(j).getTimetable().getEndHour();
                                endmin = classScheduleMap.get(j).getTimetable().getEndMinute();
                                timename = classScheduleMap.get(j).getTimetable().getName();
                                betime = timename + "(" + beginhour + ":" + beginmin + "-" + endhour + ":" + endmin + ")";

                                break;
                            }

                        }
                        viewHolder.time.setText(betime);
                        for (int j = 1; j < 6; j++) {
                            if (classScheduleMap.get(j) != null) {
                                ClassSchedule classSchedule1 = classScheduleMap.get(j);
                                switch (j) {
                                    case 1:
                                        viewHolder.monday.setText(classSchedule1.getCourse().getName());
                                        break;
                                    case 2:
                                        viewHolder.tuesday.setText(classSchedule1.getCourse().getName());
                                        break;
                                    case 3:
                                        viewHolder.wednesday.setText(classSchedule1.getCourse().getName());
                                        break;
                                    case 4:
                                        viewHolder.thursday.setText(classSchedule1.getCourse().getName());
                                        break;
                                    case 5:
                                        viewHolder.friday.setText(classSchedule1.getCourse().getName());
                                        break;
                                }

                            }
                        }
                        return convertView;
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
        TextView time;
        TextView monday;
        TextView tuesday;
        TextView wednesday;
        TextView thursday;
        TextView friday;
    }
}
