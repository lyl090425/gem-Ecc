package sprotecc.com.example.easyhealth.eh_sprotecc.Tool;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.TextView;
/**
 * 自动适配类 默认基准分辨率为[宽720、高1280、密度2和320]，基准参数不同时可调用setStandardData(...)方法设置基准参数
 * 系统只需要基准分辨率下一套UI图、一套dimens即可,本类会根据系统当前分辨率进行缩放
 * 程序在初始化时需调用AdaptationTools.init(Context)方法，获取当前设备相关数据
 * 在onCreate方法或其它适合的地方调用AdaptationTools.adapter(rootView),rootView为要适配的根view
 * 需要适配的View须指定基准分辨率下宽高固定dp值
 * ，当View的宽高指定为[FILL_PARENT|MATCH_PARENT|WRAP_CONTENT]时不会进行适配
 * 所有View的margin、padding的值会自动进行适配 本类还提供了直接计算缩放的方法等请进行查看 特殊情况：当控件非继承自View时不适用此类
 *
 * @author xiaoyi
 *
 */
public final class AdaptationTools {
    /**
     * 基准分辨率的宽
     */
    public static double STANDARD_SCREEN_WIDTH = 1920;
    /**
     * 基准分辨率的高
     */
    public static double STANDARD_SCREEN_HEIGHT = 1008;
    /**
     * 系统当前的分辨率的宽
     */
    public static double CURRENT_SCREEN_WIDTH;
    /**
     * 系统当前的分辨率的高
     */
    public static double CURRENT_SCREEN_HEIGHT;
    /**
     * 基准屏幕密度（0.75 / 1.0 / 1.5）
     */
    public static double STANDARD_DENSITY =1;
    /**
     * 基准屏幕密度DPI（120 / 160 / 240）
     */
    public static int STANDARD_DENSITYDPI = 240;
    /**
     * 当前屏幕密度（0.75 / 1.0 / 1.5）
     */
    private static double CURRENT_DENSITY;
    /**
     * 当前屏幕密度DPI（120 / 160 / 240）
     */
    public static int CURRENT_DENSITYDPI;
    /**
     * 当前手机放大缩小比率
     */
    public static float fontScale;
    /**
     * 宽度比率
     */
    public static double WIDTH_RATE;
    /**
     * 高度比率
     */
    public static double HEIGHT_RATE;
    /**
     * 密度比率
     */
    public static double DENSITY_RATE;

    /**
     * 设置基准手机各项参数，默认以720*1280为准
     *
     * @param width
     * @param height
     * @param density
     * @param densityDpi
     */
    public static void setStandardData(double width, double height, double density, int densityDpi) {
        STANDARD_SCREEN_WIDTH = width;
        STANDARD_SCREEN_HEIGHT = height;
        STANDARD_DENSITY = density;
        STANDARD_DENSITYDPI = densityDpi;
    }

    /**
     * 初始化获得系统当前手机的各项参数
     *
     * @param context
     */
    public static void init(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        CURRENT_SCREEN_WIDTH = metric.widthPixels; // 屏幕宽度（像素）
        CURRENT_SCREEN_HEIGHT = metric.heightPixels; // 屏幕高度（像素）
        CURRENT_DENSITY = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
        CURRENT_DENSITYDPI = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）

        WIDTH_RATE = (CURRENT_SCREEN_WIDTH * 1f) / STANDARD_SCREEN_WIDTH;// 宽度比率
        HEIGHT_RATE = (CURRENT_SCREEN_HEIGHT * 1f) / STANDARD_SCREEN_HEIGHT;// 高度比率
        DENSITY_RATE = (STANDARD_DENSITYDPI * 1f) / CURRENT_DENSITYDPI;// 密度比率
        fontScale = context.getResources().getDisplayMetrics().scaledDensity;// 字体缩放值
    }

    /**
     * 根据当前px值获得系统基准dip
     *
     * @param pxValue
     * @return
     */
    public static int getStandardDip(double pxValue) {
        return (int) (pxValue / CURRENT_DENSITY + 0.5f);
    }

    /**
     * 根据当前px值获得缩放后的px值
     */
    public static int getStandardPx(double pxValue) {
        return (int) (getStandardDip(pxValue) * STANDARD_DENSITY + 0.5f);
    }

    /**
     * 根据已知基准dip获得当前应该显示的宽度px大小
     *
     * @param standardDPValue
     * @return
     */
    public static int getCurrentPxForWidth(double standardDPValue) {
        return (int) ((standardDPValue * STANDARD_DENSITY + 0.5f) * getWidthSizeRate());
    }

    /**
     * 根据已知基准dip获得当前应该显示的高度px大小
     *
     * @param standardDPValue
     * @return
     */
    public static int getCurrentPxForHeight(double standardDPValue) {
        return (int) ((standardDPValue * STANDARD_DENSITY + 0.5f) * getHeightSizeRate());
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int pxTosp(float pxValue) {
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int spTopx(float spValue) {
        return (int) (spValue * fontScale + 0.5f);
    }

    public static double getWidthSizeRate() {
        return WIDTH_RATE;
    }

    public static double getHeightSizeRate() {
        return HEIGHT_RATE;
    }

    /**
     * 设置字体大小
     *
     * @param standardSize
     *            原始大小
     * @return int
     */
    public static int setTextSize(float standardSize) {
        return (int) (standardSize * WIDTH_RATE * DENSITY_RATE);
    }

    /**
     * 适配
     *
     * @param rootView
     *            根View
     */
    @TargetApi(15)
    public static void adapter(View rootView) {
        int bottom = (int) (getStandardPx(rootView.getPaddingBottom()) * getHeightSizeRate());
        int left = (int) (getStandardPx(rootView.getPaddingLeft()) * getWidthSizeRate());
        int right = (int) (getStandardPx(rootView.getPaddingRight()) * getWidthSizeRate());
        int top = (int) (getStandardPx(rootView.getPaddingTop()) * getHeightSizeRate());
        rootView.setPadding(left, top, right, bottom);
        if (rootView instanceof ViewGroup) {
            ViewGroup layout = (ViewGroup) rootView;
            adapterLayoutParams((ViewGroup.LayoutParams) layout.getLayoutParams());
            for (int i = 0; i < layout.getChildCount(); i++) {
                adapter(layout.getChildAt(i));
            }
        } else if (rootView instanceof View) {
            View view = (View) rootView;
            adapterLayoutParams((ViewGroup.LayoutParams) view.getLayoutParams());
            if (view instanceof TextView) {
                float spValue = ((TextView) view).getTextSize();
                int f = setTextSize(spValue);
                ((TextView) view).setTextSize(pxTosp(f));
            } else if (view instanceof GridView) {
                @SuppressWarnings("unused")
                GridView mGridView = (GridView) view;
                try {// 版本问题
                    // mGridView.setVerticalSpacing((int)(getStandardPx(mGridView.getVerticalSpacing())*getHeightSizeRate()));
                    // mGridView.setHorizontalSpacing((int)(getStandardPx(mGridView.getHorizontalSpacing())*getWidthSizeRate()));
                    // mGridView.setColumnWidth((int)(getStandardPx(mGridView.getColumnWidth())*getWidthSizeRate()));
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * 适配布局参数
     *
     * @param lp
     */
    public static void adapterLayoutParams(ViewGroup.LayoutParams lp) {
        if (null != lp) {
            if (lp.width != LayoutParams.MATCH_PARENT && lp.width != LayoutParams.MATCH_PARENT && lp.width != LayoutParams.WRAP_CONTENT) {
                lp.width = (int) (getStandardPx(lp.width) * getWidthSizeRate());
            }
            if (lp.height != LayoutParams.MATCH_PARENT && lp.height != LayoutParams.MATCH_PARENT && lp.height != LayoutParams.WRAP_CONTENT) {
                lp.height = (int) (getStandardPx(lp.height) * getHeightSizeRate());
            }
            if (lp instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
                mlp.topMargin = (int) (getStandardPx(mlp.topMargin) * getHeightSizeRate());
                mlp.bottomMargin = (int) (getStandardPx(mlp.bottomMargin) * getHeightSizeRate());
                mlp.rightMargin = (int) (getStandardPx(mlp.rightMargin) * getWidthSizeRate());
                mlp.leftMargin = (int) (getStandardPx(mlp.leftMargin) * getWidthSizeRate());
            }
        }
    }
}

