package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import sprotecc.com.example.easyhealth.eh_sprotecc.Adapter.MyViewPager_classinfo_Adapter;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseFragment;

import sprotecc.com.example.easyhealth.eh_sprotecc.R;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.ConductPiont.ConductPointFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.Ing.IngFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.Photo.PhotoFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.SportRank.SportRankFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSport.ClassSportFragment;

/**
 * Created by adminHjq on 2016/12/21.
 */
public class ClassInfoFragment extends BaseFragment<ClassInfoView, ClassInfoPresenter<ClassInfoView>> implements ClassInfoView, View.OnClickListener {

    private ClassInfoPresenter classInfoPresenter;
    /**
     * 父类控件（）
     */
    private ViewGroup viewPager;
    ArrayList<Fragment> views = new ArrayList<Fragment>();
    private View view;
    private ImageView sportRank, cRank, photo, ing;
    private ImageView[] imageViews;
    private TextView tSportRank, tCRank, tPhoto, tIng;
    private Drawable drawable_yes, drawable_no;

    private  Fragment viewsport ;
    private Fragment viewconduct ;
    private  Fragment viewphoto ;
    private  Fragment viewIng ;

    /**
     * 运动排名控件
     */


    /**
     * 操行分控件
     */



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classinfo, container, false);
        classInfoPresenter = new ClassInfoPresenterImpl(this);
        //初始化操作
      initViewPager();
        initView();
        Log.i("内存检测", "acao !!");
        return view;
    }

    @Override
    protected ClassInfoPresenter<ClassInfoView> createPresenter() {
        classInfoPresenter = new ClassInfoPresenterImpl(this);
        return classInfoPresenter;
    }

    /**
     * 初始化基本控件
     * //
     */
    private void initView() {
        /**
         * 父类控件初始化
         */
        //导航栏按钮
        sportRank = (ImageView) view.findViewById(R.id.classinfo_btn1);
        cRank = (ImageView) view.findViewById(R.id.classinfo_btn2);
        photo = (ImageView) view.findViewById(R.id.classinfo_btn3);
        ing = (ImageView) view.findViewById(R.id.classinfo_btn4);
        sportRank.setOnClickListener(this);
        cRank.setOnClickListener(this);
        photo.setOnClickListener(this);
        ing.setOnClickListener(this);
        //导航栏文字
        tSportRank = (TextView) view.findViewById(R.id.classinfo_text1);
        tCRank = (TextView) view.findViewById(R.id.classinfo_text2);
        tPhoto = (TextView) view.findViewById(R.id.classinfo_text3);
        tIng = (TextView) view.findViewById(R.id.classinfo_text4);
        //将按钮放在数组中
        imageViews = new ImageView[]{sportRank, cRank, photo, ing};
        //初始化颜色和背景
        resetButtonBackground();
        resetTextSize();
        //导航栏的2种变化图案
        drawable_yes = ContextCompat.getDrawable(view.getContext(), R.drawable.classinfo_yes);
        drawable_no = ContextCompat.getDrawable(view.getContext(), R.drawable.classinfo_no);
        //设置默认的导航栏的样式
        sportRank.setImageDrawable(drawable_yes);
        tSportRank.setTextSize(15);

    }


    /**
     * 初始化
     */
    private void initViewPager() {

        if (viewsport == null) {
            Log.i("内存检测", "add new viewsport !!");
            viewsport=new SportRankFragment();
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.classinfo_viewpager, viewsport,"viewsport").commit();
        } else {
            Log.i("内存检测", "found existing viewsport, no need to add it again !!");
        }
        getChildFragmentManager().beginTransaction().show(viewsport).commit();
        //默认显示的内容




    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        viewsport=null;
        viewconduct=null;
        viewphoto=null;
        viewIng=null;
    }

    /**
     * 初始化导航栏按钮背景
     */
    public void resetButtonBackground() {
        sportRank.setImageDrawable(drawable_no);
        cRank.setImageDrawable(drawable_no);
        photo.setImageDrawable(drawable_no);
        ing.setImageDrawable(drawable_no);
    }

    public void resetTextSize() {
        tSportRank.setTextSize(15);
        tCRank.setTextSize(15);
        tPhoto.setTextSize(15);
        tIng.setTextSize(15);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.classinfo_btn1:
                hideF();
                resetButtonBackground();
                resetTextSize();
                imageViews[0].setImageDrawable(drawable_yes);
                tSportRank.setTextSize(18);
                if (viewsport == null) {
                    Log.i("内存检测", "add new viewsport ");
                    viewsport=new SportRankFragment();
                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.classinfo_viewpager, viewsport,"viewsport").commit();
                } else {
                    Log.i("内存检测", "found existing viewsport, no need to add it again !!");
                }
                getChildFragmentManager().beginTransaction().show(viewsport).commit();
                break;
            case R.id.classinfo_btn2:
                hideF();
                resetButtonBackground();
                resetTextSize();
                imageViews[1].setImageDrawable(drawable_yes);
                tCRank.setTextSize(18);
                if (viewconduct == null) {
                    Log.i("内存检测", "add new viewconduct");
                    viewconduct   =new ConductPointFragment();
                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.classinfo_viewpager, viewconduct,"viewconduct").commit();
                } else {
                    Log.i("内存检测", "found existing viewconduct, no need to add it again !!");
                }
                getChildFragmentManager().beginTransaction().show(viewconduct).commit();
                break;
            case R.id.classinfo_btn3:
                hideF();
                resetButtonBackground();
                resetTextSize();
                imageViews[2].setImageDrawable(drawable_yes);
                tPhoto.setTextSize(18);
                if (viewphoto == null) {
                    Log.i("内存检测", "add new viewphoto !!");
                    viewphoto   =new PhotoFragment();
                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.classinfo_viewpager, viewphoto,"viewphoto").commit();
                } else {
                    Log.i("内存检测", "found existing viewphoto, no need to add it again !!");
                }
                getChildFragmentManager().beginTransaction().show(viewphoto).commit();
                break;
            case R.id.classinfo_btn4:
                hideF();
                resetButtonBackground();
                resetTextSize();
                imageViews[3].setImageDrawable(drawable_yes);
                tIng.setTextSize(18);
//                viewPager.setCurrentItem(3);
                 //因为上课是实时变化的，所以这里每次都采用new一个新的出来。
                    Log.i("内存检测", "add new viewIng !!");
                    viewIng   =new IngFragment();
                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.classinfo_viewpager, viewIng,"viewIng").commit();

                getChildFragmentManager().beginTransaction().show(viewIng).commit();
                break;
        }
    }
public  void hideF(){
    if(viewsport != null){
        getChildFragmentManager().beginTransaction().hide(viewsport).commit();
    }
    if(viewconduct != null){
        getChildFragmentManager().beginTransaction().hide(viewconduct).commit();
    }
    if(viewphoto != null){
        getChildFragmentManager().beginTransaction().hide(viewphoto).commit();
    }
    if(viewIng != null){
        getChildFragmentManager().beginTransaction().hide(viewIng).commit();
    }
}

}
