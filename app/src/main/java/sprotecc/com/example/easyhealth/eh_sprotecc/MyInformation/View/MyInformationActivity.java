package sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.View;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.ruite.gem.modal.人员信息.UserInfo;
import com.ruite.gem.modal.运动健康.运动.Sport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import sprotecc.com.example.easyhealth.eh_sprotecc.Adapter.CircleImageView;
import sprotecc.com.example.easyhealth.eh_sprotecc.Adapter.MyHonorAdapter;
import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseActivity;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempHonour;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempMySport;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportDataHistory;
import sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.Presenter.MyInformationPresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;
import sprotecc.com.example.easyhealth.eh_sprotecc.Tool.BitmapCache;

public class MyInformationActivity extends BaseActivity<MyInformationView, MyInformationPresenterImpl> implements MyInformationView, View.OnClickListener {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);//日期格式
    private MyInformationPresenterImpl myInformationPresenter;
    //周月年按钮
    private ImageView btn_week, btn_month, btn_year;
    private Drawable wmy_no, wmy_yes;
    //周月年显示控件
    private LineChartView line_week, line_month, line_year;
    //我的今日运动空间
    private LineChartView mySport, myActivty;
    //我的基本信息
    private TextView name, hight, weight, usercodeT;
    private CircleImageView circleImageView;
    //计时器，用于控制页面显示时间后自己结束掉自己。
    private Timer timer;
    //自动回到主界面的计时器
    private TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            MyInformationActivity.this.finish();
        }
    };
    //回到主页按钮
    private ImageView backmain;
    //建议须知
    private  TextView d1,d2;
    //荣誉
    private ListView list_honnr;
    private RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getContext());
    //ImageLoader的参数，暂时为空，后续再开发
    ImageLoader.ImageCache imageCache= new ImageLoader.ImageCache(){
        @Override
        public Bitmap getBitmap(String s) {
            return null;
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        ViewInitialization();
        myInformationPresenter.startServiceInfo();
        myInformationPresenter.showServiceInfo();
        showMyInformation();
        timer = new Timer(true);
        timer.schedule(timerTask, 60*1000);//60秒后自动结束

    }

    @Override
    protected MyInformationPresenterImpl createPresenter() {
        myInformationPresenter = new MyInformationPresenterImpl(this);
        return myInformationPresenter;
    }

    @Override
    protected MyApplication GetMyApplication() {
        return null;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void ViewInitialization() {
        btn_week = (ImageView) findViewById(R.id.btn_week);
        btn_week.setOnClickListener(this);
        btn_month = (ImageView) findViewById(R.id.btn_month);
        btn_month.setOnClickListener(this);
        btn_year = (ImageView) findViewById(R.id.btn_year);
        btn_year.setOnClickListener(this);

        wmy_no = ContextCompat.getDrawable(this, R.drawable.classsport_name1);
        wmy_yes = ContextCompat.getDrawable(this, R.drawable.classsport_name2);

        line_week = (LineChartView) findViewById(R.id.myHistory_l1);
        line_month = (LineChartView) findViewById(R.id.myHistory_l2);
        line_year = (LineChartView) findViewById(R.id.myHistory_l3);

        btn_week.setImageDrawable(wmy_yes);
        mySport = (LineChartView) findViewById(R.id.mytoday_sport);
        myActivty = (LineChartView) findViewById(R.id.mytoday_activity);
        backmain = (ImageView) findViewById(R.id.backmain);
        backmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timer!=null){
                    timer.cancel();
                    timer=null;
                }
                MyInformationActivity.this.finish();
            }
        });

        name = (TextView) findViewById(R.id.myinformation_name);
        hight = (TextView) findViewById(R.id.myinformation_hight);
        weight = (TextView) findViewById(R.id.myinformation_weight);
        usercodeT = (TextView) findViewById(R.id.myinformation_usercode);
        circleImageView = (CircleImageView) findViewById(R.id.myinformation_touxiang);

        d1= (TextView) findViewById(R.id.decvice1);
        d2= (TextView) findViewById(R.id.decvice2);

        list_honnr= (ListView) findViewById(R.id.wocaonimagebi);

    }

    @Override
    public void showWeekSportHistory(List<TempSportDataHistory> list) {
        List<TempSportDataHistory> showList = new ArrayList<>();
        //显示在页面上
        boolean by = false;
        for (int i = 0; i < 7; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -(7 - i - 1));//-6-0
            Date time = cal.getTime();
            String eq1 = sdf.format(time);
            for (int j = 0; j < list.size(); j++) {
                String eq2 = list.get(j).getTime();
                if (eq1.equals(eq2)) {
                    showList.add(list.get(j));
                    by = true;
                }
            }
            if (by) {
            } else {
                TempSportDataHistory t = new TempSportDataHistory(eq1, "0");
                showList.add(t);
            }
            by = false;
        }
        Log.i("数据-显示", "班级周历史数据的条数：" + showList.size() + "");
        initLineChart(showList, line_week);
    }

    @Override
    public void showMonthSportHistory(List<TempSportDataHistory> list) {
        List<TempSportDataHistory> showList = new ArrayList<>();

        //显示在页面上
        boolean by = false;
        for (int i = 0; i < 30; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -(30 - i - 1));//-6-0
            Date time = cal.getTime();
            String eq1 = sdf.format(time);
            for (int j = 0; j < list.size(); j++) {
                String eq2 = list.get(j).getTime();
                if (eq1.equals(eq2)) {
                    showList.add(list.get(j));
                    by = true;
                }
            }
            if (by) {
            } else {
                TempSportDataHistory t = new TempSportDataHistory(eq1, "0");
                showList.add(t);
            }
            by = false;
        }
        Log.i("数据-显示", "班级30天历史数据的条数：" + showList.size() + "");
        //显示在页面上
        initLineChart(showList, line_month);
    }

    @Override
    public void showYearSportHistory(List<TempSportDataHistory> list) {
        initLineChart(list, line_year);
    }

    /**
     * 显示荣誉
     * @param list
     */
    @Override
    public void showHonour(List<TempHonour> list) {
        MyHonorAdapter myHonorAdapter=new MyHonorAdapter(list,mQueue,imageCache);
        list_honnr.setDivider(new ColorDrawable(Color.rgb(0, 255, 255)));
        list_honnr.setDividerHeight(1);
        list_honnr.setAdapter(myHonorAdapter);

    }

    @Override
    public void showMyTodaySport(List<Sport> list) {
        List<TempMySport> showList = new ArrayList<>();
        String s = list.get(0).getSportData();
        String[] sports = s.split(",");
        int a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, a6 = 0, a7 = 0, a8 = 0;
        for (int i = 0; i < sports.length; i++) {
            if (i % 2 == 0 && i <= 36) {//第一阶段
                a1 = a1 + Integer.parseInt(sports[i]);
            } else if (i % 2 == 0 && i > 36 && i <= 72) {//第二阶段
                a2 = a2 + Integer.parseInt(sports[i]);
            } else if (i % 2 == 0 && i > 72 && i <= 108) {//第三阶段
                a3 = a3 + Integer.parseInt(sports[i]);
            } else if (i % 2 == 0 && i > 108 && i <= 144) {//第四阶段
                a4 = a4 + Integer.parseInt(sports[i]);
            } else if (i % 2 == 0 && i <= 180 && i > 144) {//第五阶段
                a5 = a5 + Integer.parseInt(sports[i]);
            } else if (i % 2 == 0 && i <= 216 && i > 180) {//第六阶段
                a6 = a6 + Integer.parseInt(sports[i]);
            } else if (i % 2 == 0 && i <= 252 && i > 216) {//第七阶段
                a7 = a7 + Integer.parseInt(sports[i]);
            } else if (i % 2 == 0 && i <= 288 && i > 252) {//第八阶段
                a8 = a8 + Integer.parseInt(sports[i]);
            }
        }
        String[] time = {"00:00-03:00", "03:00-06:00", "06:00-9:00", "9:00-12:00"
                , "12:00-15:00", "15:00-18:00", "18:00-21:00", "21:00-23:59"};
        String[] bushu = {a1 + "", a2 + "", a3 + "", a4 + "", a5 + "", a6 + "", a7 + "", a8 + ""};
        for (int i = 0; i < time.length; i++) {
            TempMySport sport = new TempMySport(time[i], bushu[i]);
            showList.add(sport);
        }
        int step=a1+a2+a3+a4+a5+a6+a7+a8;
        if(step>=15000){//过渡运动
            d1.setText(getResources().getString(R.string.sportData_d5));
        }else if(step<15000&&step>=12000) {//最佳运动步数
            d1.setText(getResources().getString(R.string.sportData_d4));
        }else if(step<12000&&step>=9500){//良好运动步数
            d1.setText(getResources().getString(R.string.sportData_d3));
        }else if(step<9500&&step>=7000){//欠佳运动步数
            d1.setText(getResources().getString(R.string.sportData_d2));
        }else if(step<7000){//运动步数不足，属于久坐
            d1.setText(getResources().getString(R.string.sportData_d1));
        }

        initLineMyChart(showList, mySport, 1);
    }

    @Override
    public void showMyTodayActivity(List<Sport> list) {
        List<TempMySport> showList = new ArrayList<>();
        String s = list.get(0).getSportData();
        String[] sports = s.split(",");
        int a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, a6 = 0, a7 = 0, a8 = 0;
        for (int i = 0; i < sports.length; i++) {
            if (i % 2 != 0 && i <= 36) {//第一阶段
                a1 = a1 + Integer.parseInt(sports[i]);
            } else if (i % 2 != 0 && i > 36 && i <= 72) {//第二阶段
                a2 = a2 + Integer.parseInt(sports[i]);
            } else if (i % 2 != 0 && i > 72 && i <= 108) {//第三阶段
                a3 = a3 + Integer.parseInt(sports[i]);
            } else if (i % 2 != 0 && i > 108 && i <= 144) {//第四阶段
                a4 = a4 + Integer.parseInt(sports[i]);
            } else if (i % 2 != 0 && i <= 180 && i > 144) {//第五阶段
                a5 = a5 + Integer.parseInt(sports[i]);
            } else if (i % 2 != 0 && i <= 216 && i > 180) {//第六阶段
                a6 = a6 + Integer.parseInt(sports[i]);
            } else if (i % 2 != 0 && i <= 252 && i > 216) {//第七阶段
                a7 = a7 + Integer.parseInt(sports[i]);
            } else if (i % 2 != 0 && i <= 288 && i > 252) {//第八阶段
                a8 = a8 + Integer.parseInt(sports[i]);
            }
        }
        String[] time = {"00:00-03:00", "03:00-05:00", "05:00-8:00", "8:00-12:00"
                , "12:00-15:00", "15:00-18:00", "18:00-21:00", "21:00-23:59"};
        String[] bushu = {a1 + "", a2 + "", a3 + "", a4 + "", a5 + "", a6 + "", a7 + "", a8 + ""};
        for (int i = 0; i < time.length; i++) {
            TempMySport sport = new TempMySport(time[i], bushu[i]);
            showList.add(sport);
        }
        int step=a1+a2;
        if(step>=1500){//浅睡眠
            d2.setText(getResources().getString(R.string.sleep_d3));
        }else if(step<1500&&step>=300) {//完美睡眠
            d2.setText(getResources().getString(R.string.sleep_d2));
        }else if(step<300){//深度睡眠
            d2.setText(getResources().getString(R.string.sleep_d1));
        }
        initLineMyChart(showList, myActivty, 2);

    }

    @Override
    public void showMyInformation() {
      UserInfo userInfo= DataDao.getInstance().getUserInfoByCode(MyApplication.getInstance().getEcc_sportdata().getUserCode().trim());
        name .setText(userInfo.getName());
        hight.setText(userInfo.getHeight()+"cm");
        weight.setText(userInfo.getWeight()+"kg");
        usercodeT.setText(userInfo.getCode());
        long userid=userInfo.getId();
        Log.i("用户信息","我的唯一ID"+userid);
        ImageLoader imageLoader = new ImageLoader(mQueue,new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(circleImageView, R.drawable.classsport_name1, R.drawable.userhead);
        String url=MyApplication.ppppp+((int)(userid/500))+"/"+userid;
        imageLoader.get(url, listener);
    }

    //画历史折线图
    private void initLineChart(List<TempSportDataHistory> list, LineChartView lineChart) {
        List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();//X轴数据
        List<PointValue> mPointValues = new ArrayList<PointValue>();//Y轴数据
        for (int i = 0; i < list.size(); i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(list.get(i).getTime()));//初始化X轴标题
            mPointValues.add(new PointValue(i, Integer.parseInt(list.get(i).getBushu())));//初始化Y轴数据
        }
        Line line = new Line(mPointValues).setColor(Color.parseColor("#46a3ff"));  //折线的颜色（蓝色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）

        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.BLACK);  //设置字体颜色(黑色)
//        axisX.setName("date");  //表格名称
        axisX.setTextSize(8);//设置字体大小
//        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("步数");//y轴标注
        axisY.setTextColor(Color.BLACK); //设置字体颜色(黑色)
        axisY.setTextSize(8);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        axisY.setMaxLabelChars(5);//Y轴数字的最大位数
        //data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
//       lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right = 5;
        lineChart.setCurrentViewport(v);
    }

    //画我的线图
    private void initLineMyChart(List<TempMySport> list, LineChartView lineChart, int a) {
        List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();//X轴数据
        List<PointValue> mPointValues = new ArrayList<PointValue>();//Y轴数据
        for (int i = 0; i < list.size(); i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(list.get(i).getTime()));//初始化X轴标题
            mPointValues.add(new PointValue(i, Integer.parseInt(list.get(i).getBushu())));//初始化Y轴数据
        }
        Line line = new Line(mPointValues).setColor(Color.parseColor("#46a3ff"));  //折线的颜色（蓝色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）

        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.BLACK);  //设置字体颜色(黑色)
//        axisX.setName("date");  //表格名称
        axisX.setTextSize(8);//设置字体大小
//        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        if (a == 1) {
            axisY.setName("步数");//y轴标注
        } else {
            axisY.setName("活动度");
        }
        axisY.setTextColor(Color.BLACK); //设置字体颜色(黑色)
        axisY.setTextSize(8);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        axisY.setMaxLabelChars(5);//Y轴数字的最大位数
        //data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
//       lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right = 8;
        lineChart.setCurrentViewport(v);
    }

    public void resetButtonBackground() {
        btn_week.setImageDrawable(wmy_no);
        btn_month.setImageDrawable(wmy_no);
        btn_year.setImageDrawable(wmy_no);
    }

    //按钮监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_week:
                resetButtonBackground();
                line_week.setVisibility(View.VISIBLE);
                line_month.setVisibility(View.GONE);
                line_year.setVisibility(View.GONE);
                btn_week.setImageDrawable(wmy_yes);
                btn_month.setImageDrawable(wmy_no);
                btn_year.setImageDrawable(wmy_no);

                break;
            case R.id.btn_month:
                resetButtonBackground();
                line_week.setVisibility(View.GONE);
                line_month.setVisibility(View.VISIBLE);
                line_year.setVisibility(View.GONE);
                btn_week.setImageDrawable(wmy_no);
                btn_month.setImageDrawable(wmy_yes);
                btn_year.setImageDrawable(wmy_no);
                break;
            case R.id.btn_year:
                resetButtonBackground();
                line_week.setVisibility(View.GONE);
                line_month.setVisibility(View.GONE);
                line_year.setVisibility(View.VISIBLE);
                btn_week.setImageDrawable(wmy_no);
                btn_month.setImageDrawable(wmy_no);
                btn_year.setImageDrawable(wmy_yes);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(timer!=null){
            timer.cancel();
            timer=null;
            timerTask.cancel();
            timerTask=null;
        }
        MyInformationActivity.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer=null;
            timerTask.cancel();
            timerTask=null;
        }
        MyInformationActivity.this.finish();
    }
    @Override
    public void onUserInteraction() {//重置定时器
        timerTask.cancel();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                MyInformationActivity.this.finish();
            }
        };

        timer.schedule(timerTask, 60 * 1000);
        Log.i("定时器", "定时器重置");
    }
}
