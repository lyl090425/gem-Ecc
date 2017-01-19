package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.ConductPiont;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruite.gem.modal.班牌基础.StudentEvaluate;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.formatter.ColumnChartValueFormatter;
import lecho.lib.hellocharts.formatter.SimpleColumnChartValueFormatter;
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
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempConductPoint;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportDataHistory;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class ConductPointFragment extends BaseFragment<ConductPointView,ConductPointPresenter<ConductPointView>> implements ConductPointView {

   private ConductPointPresenter conductPiontPresenter;
  private View view;
    private ColumnChartView columnChartView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classinfo_conductpointsrank, container, false);
        initView();
        conductPiontPresenter=new ConductPointPresenterImpl(this);
        conductPiontPresenter.startThread();
        conductPiontPresenter.showInfo();
        return view;
    }


    @Override
    protected ConductPointPresenter<ConductPointView> createPresenter() {

        return null;
    }
    public void initView(){
        columnChartView= (ColumnChartView) view.findViewById(R.id.cdprColumnChartView2);
    }
    @Override
    public void showConductPoint(List<TempConductPoint> studentEvaluateLis) {
        if(studentEvaluateLis!=null){
            if(studentEvaluateLis.size()!=0){
        initConductPointChart(studentEvaluateLis,columnChartView);
            }
        }
    }
    public void initConductPointChart(List<TempConductPoint> list, ColumnChartView columnChartView) {

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
                values.add(new SubcolumnValue(Integer.parseInt(list.get(i).getScore()), ChartUtils.pickColor()));
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
        Axis axisY = new Axis().setHasLines(true);
        axisY.setName("操行分");//轴名称
        axisY.hasLines();//是否显示网格线
        axisY.setTextColor(Color.BLACK);//颜色

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
        v.right = 7;
        columnChartView.setCurrentViewport(v);
    }


}
