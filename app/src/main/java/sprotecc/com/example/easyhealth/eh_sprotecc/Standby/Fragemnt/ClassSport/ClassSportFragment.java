package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSport;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportDataHistory;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;
import sprotecc.com.example.easyhealth.eh_sprotecc.Tool.DayManager;

/**
 * Created by adminHjq on 2016/12/21.
 */
public class ClassSportFragment extends BaseFragment<ClassSportView, ClassSportPresenter<ClassSportView>> implements ClassSportView, View.OnClickListener {
    private ClassSportPresenter classSportPresenter;
    private LineChartView lineChartView_h1, lineChartView_h2, lineChartView_h3;
    private ColumnChartView columnChartView;
    private View view;
    private ImageView btn1, btn2, btn3;
    private Drawable wmy_no, wmy_yes;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);//日期格式

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classsport, container, false);
        initView();
        classSportPresenter = new ClassSportPresenterImpl(this);
        classSportPresenter.startServiceInfo();
        classSportPresenter.showServiceInfo();

        return view;
    }

    @Override
    protected ClassSportPresenter<ClassSportView> createPresenter() {
        classSportPresenter = new ClassSportPresenterImpl(this);
        return classSportPresenter;
    }

    private void initView() {
        lineChartView_h1 = (LineChartView) view.findViewById(R.id.classsport_linerlayout_h1);
        lineChartView_h2 = (LineChartView) view.findViewById(R.id.classsport_linerlayout_h2);
        lineChartView_h3 = (LineChartView) view.findViewById(R.id.classsport_linerlayout_h3);
        columnChartView = (ColumnChartView) view.findViewById(R.id.cdprColumnChartView);
        wmy_no = ContextCompat.getDrawable(view.getContext(), R.drawable.classsport_name1);
        wmy_yes = ContextCompat.getDrawable(view.getContext(), R.drawable.classsport_name2);

        btn1 = (ImageView) view.findViewById(R.id.classsport_btn1);
        btn1.setOnClickListener(this);
        btn2 = (ImageView) view.findViewById(R.id.classsport_btn2);
        btn2.setOnClickListener(this);
        btn3 = (ImageView) view.findViewById(R.id.classsport_btn3);
        btn3.setOnClickListener(this);

        btn1.setImageDrawable(wmy_yes);

    }

    @Override
    public void show8SportRank(List<TempSportRank> list) {
        Log.i("数据—显示",list.size()+"");
        List<TempSportRank> showList=new ArrayList<TempSportRank>();
        for (int i = 0; i < list.size(); i++) {
            showList.add(list.get(i));
        }
        //显示在页面上
        initSprot8RankChart(showList, columnChartView);

    }

    @Override
    public void showWeekSportHistory(List<TempSportDataHistory> list){
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
        Log.i("数据-显示","班级周历史数据的条数："+showList.size()+"");
        initLineChart(showList, lineChartView_h1);
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
        Log.i("数据-显示","班级30天历史数据的条数："+showList.size()+"");
        //显示在页面上
        initLineChart(showList, lineChartView_h2);

    }

    @Override
    public void showYearSportHistory(List<TempSportDataHistory> list) {

        //显示在页面上
        initLineChart(list, lineChartView_h3);

    }


    /**
     * 绘制折线图的方法
     *
     * @param list
     */
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
        axisX.setTextSize(7);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
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

    /**
     * 初始化柱状图
     *
     * @param list
     * @param columnChartView
     */
    public void initSprot8RankChart(List<TempSportRank> list, ColumnChartView columnChartView) {
        // 使用的 X列，每列1个subcolumn。
        int numSubcolumns = 1;
        int numColumns = list.size();
        //定义一个圆柱对象集合
        List<Column> columns = new ArrayList<Column>();
        //子列数据集合
        List<SubcolumnValue> values;
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        //遍历列数numColumns
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            //遍历每一列的每一个子列
            for (int j = 0; j < numSubcolumns; ++j) {
                //为每一柱图添加颜色和数值
                Log.i("数据显示", list.get(i).getCount() + "");
                values.add(new SubcolumnValue(Integer.parseInt(list.get(i).getCount()), ChartUtils.pickColor()));
            }
            //创建Column对象
            Column column = new Column(values);
            //这一步是能让圆柱标注数据显示带小数.
            //作者回答https://github.com/lecho/hellocharts-android/issues/185
//            ColumnChartValueFormatter chartValueFormatter = new SimpleColumnChartValueFormatter(2);
//            column.setFormatter(chartValueFormatter);
            //是否有数据标注
            column.setHasLabels(true);

            //是否是点击圆柱才显示数据标注
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
            //给x轴坐标设置描述
            axisValues.add(new AxisValue(i).setLabel(list.get(i).getName()));
        }
        //创建一个带有之前圆柱对象column集合的ColumnChartData
        ColumnChartData data;
        data = new ColumnChartData(columns);

        //定义x轴y轴相应参数
        Axis axisX = new Axis();
        Axis axisY = new Axis();  //Y轴
        axisY.setName("步数");//y轴标注
        axisY.setTextColor(Color.BLACK); //设置字体颜色(黑色)
        axisY.setTextSize(8);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        axisY.setMaxLabelChars(5);//Y轴数字的最大位数

        axisX.hasLines();
        axisX.setTextColor(Color.BLACK);
        axisX.setValues(axisValues);
//        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        //把X轴Y轴数据设置到ColumnChartData 对象中
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        //设置行为属性，支持缩放、滑动以及平移
        columnChartView.setInteractive(true);
        columnChartView.setZoomType(ZoomType.HORIZONTAL);
        columnChartView.setMaxZoom((float) 2);//最大方法比例
        columnChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        columnChartView.setColumnChartData(data);

        Viewport v = new Viewport(columnChartView.getMaximumViewport());
        v.left = 0;
        v.right = 10;
        columnChartView.setCurrentViewport(v);
    }

    /**
     * 初始化按钮颜色
     */
    public void resetButtonBackground() {
        btn1.setImageDrawable(wmy_no);
        btn2.setImageDrawable(wmy_no);
        btn3.setImageDrawable(wmy_no);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.classsport_btn1:
                resetButtonBackground();
                lineChartView_h1.setVisibility(View.VISIBLE);
                lineChartView_h2.setVisibility(View.GONE);
                lineChartView_h3.setVisibility(View.GONE);
                btn1.setImageDrawable(wmy_yes);
                btn2.setImageDrawable(wmy_no);
                btn3.setImageDrawable(wmy_no);

                break;
            case R.id.classsport_btn2:
                resetButtonBackground();
                lineChartView_h1.setVisibility(View.GONE);
                lineChartView_h2.setVisibility(View.VISIBLE);
                lineChartView_h3.setVisibility(View.GONE);
                btn1.setImageDrawable(wmy_no);
                btn2.setImageDrawable(wmy_yes);
                btn3.setImageDrawable(wmy_no);
                break;
            case R.id.classsport_btn3:
                resetButtonBackground();
                lineChartView_h1.setVisibility(View.GONE);
                lineChartView_h2.setVisibility(View.GONE);
                lineChartView_h3.setVisibility(View.VISIBLE);
                btn1.setImageDrawable(wmy_no);
                btn2.setImageDrawable(wmy_no);
                btn3.setImageDrawable(wmy_yes);
                break;
        }
    }
}
