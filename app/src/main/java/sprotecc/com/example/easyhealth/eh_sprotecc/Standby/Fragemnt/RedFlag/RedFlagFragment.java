package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.RedFlag;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruite.gem.modal.班牌基础.ClassRedFlag;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

/**
 * Created by adminHjq on 2016/12/23.
 */
public class RedFlagFragment extends BaseFragment<RedFlagView, RedFlagPresenter<RedFlagView>> implements RedFlagView {
    private View view;
    private RedFlagPresenter redFlagPresenter;
    private Handler m = new Handler();

    private TextView m1, m2, m3, m4;
    private TextView type1, type2, type3, type4;

    private ColumnChartView columnChartView;
    private RelativeLayout relativeLayout;

    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_redflag, container, false);
        initView();
        redFlagPresenter = new RedFlagPresenterImpl(this, m);
        redFlagPresenter.myClassredFlag();
        redFlagPresenter.gradeRedFlagRank();
        return view;
    }

    public void initView() {
        //面数
        m1 = (TextView) view.findViewById(R.id.redflag_m1);
        m2 = (TextView) view.findViewById(R.id.redflag_m2);
        m3 = (TextView) view.findViewById(R.id.redflag_m3);
        m4 = (TextView) view.findViewById(R.id.redflag_m4);
        //红旗类型
        type1 = (TextView) view.findViewById(R.id.redflag_text1);
        type2 = (TextView) view.findViewById(R.id.redflag_text2);
        type3 = (TextView) view.findViewById(R.id.redflag_text3);
        type4 = (TextView) view.findViewById(R.id.redflag_text4);

        columnChartView = (ColumnChartView) view.findViewById(R.id.redRankColumnChartView);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.redflag_default);
    }

    @Override
    protected RedFlagPresenter<RedFlagView> createPresenter() {
        return null;
    }

    @Override
    public void showMyClassRedFlag(final List<ClassRedFlag> list) {

        if(handler!=null) {
            handler.post(new Runnable() {
                @Override
                public void run() {

                    if (list != null) {
                        Log.i("数据测试：流动红旗", list.size() + "");
                        if (list.size() != 0) {
                            for (int i = 0; i < list.size(); i++) {
                                if (i == 0) {
                                    m1.setText(list.get(i).getCount() + "");
                                    type1.setText(list.get(i).getType().getName());
                                }
                                if (i == 1) {
                                    m2.setText(list.get(i).getCount() + "");
                                    type2.setText(list.get(i).getType().getName());
                                }
                                if (i == 2) {
                                    m3.setText(list.get(i).getCount() + "");
                                    type3.setText(list.get(i).getType().getName());
                                }
                                if (i == 3) {
                                    m4.setText(list.get(i).getCount() + "");
                                    type4.setText(list.get(i).getType().getName());
                                }
                            }
                        } else {
                            m1.setText("**");
                            m2.setText("**");
                            m3.setText("**");
                            m4.setText("**");
//    }
                            //红旗类型
                            type1.setText("暂无数据1");
                            type2.setText("暂无数据2");
                            type3.setText("暂无数据3");
                            type4.setText("暂无数据4");
                        }

                    } else {
                        m1.setText("**");
                        m2.setText("**");
                        m3.setText("**");
                        m4.setText("**");
//    }
                        //红旗类型
                        type1.setText("暂无数据1");
                        type2.setText("暂无数据2");
                        type3.setText("暂无数据3");
                        type4.setText("暂无数据4");
                    }
                }
            });
        }
    }

    @Override
    public void showGradeRedFlagRank(final List<ClassRedFlag> list) {

        if(handler!=null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (list != null) {
                        Log.i("班级流动红旗排名", "班级数" + list.size());
                        if (list.size() != 0) {
                            relativeLayout.setGravity(View.GONE);
                            initGradeRedRank(list, columnChartView);
                        } else {

                        }
                    } else {

                    }
                }
            });
        }
    }

    /**
     * 初始化柱状图
     *
     * @param list
     * @param columnChartView
     */
    public void initGradeRedRank(final List<ClassRedFlag> list, final ColumnChartView columnChartView) {

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
                values.add(new SubcolumnValue(list.get(i).getCount(), ChartUtils.pickColor()));
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
            axisValues.add(new AxisValue(i).setLabel(list.get(i).getClazz().getName()));
        }
        //创建一个带有之前圆柱对象column集合的ColumnChartData
        ColumnChartData data;
        data = new ColumnChartData(columns);

        //定义x轴y轴相应参数
        Axis axisX = new Axis();
        Axis axisY = new Axis();  //Y轴
        axisY.setName("红旗数");//y轴标注
        axisY.setHasTiltedLabels(false);
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
        v.right = 5;
        columnChartView.setCurrentViewport(v);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
