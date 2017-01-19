package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.SportRank;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import sprotecc.com.example.easyhealth.eh_sprotecc.Adapter.CircleImageView;
import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;
import sprotecc.com.example.easyhealth.eh_sprotecc.Tool.BitmapCache;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class SportRankFragment extends BaseFragment<SportRankView, SportRankPresenter<SportRankView>> implements SportRankView {
    SportRankPresenter sportRankPresenter;
    private View view;
    private TextView left1, left2, left3;
    private TextView right1, right2, right3;

    private TextView leftname1, leftname2, leftname3;
    private TextView rightname1, rightname2, rightname3;

    private CircleImageView left_T1, left_T2, left_T3;
    private CircleImageView right_T1, right_T2, right_T3;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Log.i("数据刷新", "运动排名开始刷新");
                sportRankPresenter.startThread();
                sportRankPresenter.showInfo();
                Log.i("数据刷新", "运动排名完成刷新");
            }
        }
    };

    //用于获取网络头像的加载队列
    private RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getContext());
    //ImageLoader的参数，暂时为空，后续再开发
    ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
        @Override
        public Bitmap getBitmap(String s) {
            return null;
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
        }
    };
    private Timer timer;
    //定时请求考勤，显示考勤，定时请求天气，显示天气。

    @Override
    protected SportRankPresenter createPresenter() {
        sportRankPresenter = new SportRankPresenterImpl(this);
        return sportRankPresenter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classinfo_sportrank, container, false);
        initView();
        sportRankPresenter = new SportRankPresenterImpl(this);
        sportRankPresenter.startThread();
        sportRankPresenter.showInfo();
        timer = new Timer(true);
        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {

                if (mHandler != null) {
                    Message message = new Message();
                    message.what = 1;
                    mHandler.sendMessage(message);
                }
            }
        };
        timer.schedule(timerTask1, 60 * 1000, 60 * 1000);
        return view;
    }

    /**
     * 初始化控件
     */
    public void initView() {
        //左边头像
        left_T1 = (CircleImageView) view.findViewById(R.id.left_d1);
        left_T2 = (CircleImageView) view.findViewById(R.id.left_d2);
        left_T3 = (CircleImageView) view.findViewById(R.id.left_d3);
        //左边运动数字
        left1 = (TextView) view.findViewById(R.id.left_1);
        left2 = (TextView) view.findViewById(R.id.left_2);
        left3 = (TextView) view.findViewById(R.id.left_3);
        //左边的名字
        leftname1 = (TextView) view.findViewById(R.id.left_name1);
        leftname2 = (TextView) view.findViewById(R.id.left_name2);
        leftname3 = (TextView) view.findViewById(R.id.left_name3);
        //右边头像
        right_T1 = (CircleImageView) view.findViewById(R.id.right_d1);
        right_T2 = (CircleImageView) view.findViewById(R.id.right_d2);
        right_T3 = (CircleImageView) view.findViewById(R.id.right_d3);
        //右边运动数字
        right1 = (TextView) view.findViewById(R.id.right_1);
        right2 = (TextView) view.findViewById(R.id.right_2);
        right3 = (TextView) view.findViewById(R.id.right_3);
        //右边的名字
        rightname1 = (TextView) view.findViewById(R.id.right_name1);
        rightname2 = (TextView) view.findViewById(R.id.right_name2);
        rightname3 = (TextView) view.findViewById(R.id.right_name3);


    }

    @Override
    public void showSportRank(List<TempSportRank> list) {
        //显示运动排名(步数))()
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRank().equals("1")) {
                left1.setText(list.get(i).getCount());
                leftname1.setText(list.get(i).getName());
                long userid = Long.parseLong(list.get(i).getUserid());
                ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(left_T1, R.drawable.classsport_name1, R.drawable.userhead);
                String url = MyApplication.ppppp + ((int) (userid / 500)) + "/" + userid;
                imageLoader.get(url, listener);
            } else if (list.get(i).getRank().equals("2")) {
                left2.setText(list.get(i).getCount());
                leftname2.setText(list.get(i).getName());
                long userid = Long.parseLong(list.get(i).getUserid());
                ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(left_T2, R.drawable.classsport_name1, R.drawable.userhead);
                String url = MyApplication.ppppp + ((int) (userid / 500)) + "/" + userid;
                imageLoader.get(url, listener);
            } else if (list.get(i).getRank().equals("3")) {
                left3.setText(list.get(i).getCount());
                leftname3.setText(list.get(i).getName());
                long userid = Long.parseLong(list.get(i).getUserid());
                ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(left_T3, R.drawable.classsport_name1, R.drawable.userhead);
                String url = MyApplication.ppppp + ((int) (userid / 500)) + "/" + userid;
                imageLoader.get(url, listener);
            }
        }
    }

    @Override
    public void showActivityRank(List<TempSportRank> list) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRank().equals("1")) {
                right1.setText(list.get(i).getCount());
                rightname1.setText(list.get(i).getName());
                long userid = Long.parseLong(list.get(i).getUserid());
                ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(right_T1, R.drawable.classsport_name1, R.drawable.userhead);
                String url = MyApplication.ppppp + ((int) (userid / 500)) + "/" + userid;
                imageLoader.get(url, listener);
            } else if (list.get(i).getRank().equals("2")) {
                right2.setText(list.get(i).getCount());
                rightname2.setText(list.get(i).getName());
                long userid = Long.parseLong(list.get(i).getUserid());
                ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(right_T2, R.drawable.classsport_name1, R.drawable.userhead);
                String url = MyApplication.ppppp + ((int) (userid / 500)) + "/" + userid;
                imageLoader.get(url, listener);
            } else if (list.get(i).getRank().equals("3")) {
                right3.setText(list.get(i).getCount());
                rightname3.setText(list.get(i).getName());
                long userid = Long.parseLong(list.get(i).getUserid());
                ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(right_T3, R.drawable.classsport_name1, R.drawable.userhead);
                String url = MyApplication.ppppp + ((int) (userid / 500)) + "/" + userid;
                imageLoader.get(url, listener);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

    }
}
