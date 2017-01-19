package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.View;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.ruite.gem.modal.APIReturn.AirApi;
import com.ruite.gem.modal.APIReturn.WeatherApi;
import com.ruite.gem.modal.APIReturn.WeatherInfo;
import com.ruite.gem.modal.新闻.News;
import com.ruite.gem.spdtp.HW_HEADER;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseActivity;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzAttendance;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.Install.InstallActivity;
import sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.View.MyInformationActivity;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassInfoFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.View.MySportDataActivity;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSport.ClassSportFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassSubTable.ClassSubTableFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.CleanTable.CleanTableFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.News.NewsFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.RedFlag.RedFlagFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Presenter.StandbyPresenter;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Presenter.StandbyPresenterImpl;

/**
 * 待机界面类
 * 继承BaseActivity,BaseActivity的onCreate创建StandbyPresenter,并调用方法attachView和StandbyView建立
 * 关联，同时在onDestroy中解除关联
 */
public class StandbyActivity extends BaseActivity<StandbyView, StandbyPresenter<StandbyView>> implements StandbyView {
    private StandbyPresenter standbyPresenter;
    private MyApplication myapplication;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private ClassInfoFragment mClassInfo;//班级基本信息
    private ClassSportFragment mClassSportFragment;//班级运动
    private CleanTableFragment mCleanTableFragment;//值日
    private ClassSubTableFragment mClassSubTableFragment;//课表
    private RedFlagFragment mRedFlagFragment;//红旗
    private NewsFragment mNewsFragment;//新闻
    private Timer timer;//计时器
    //考勤控件
    private TextView total, arrived, noArrived;
    //班级信息
    private TextView name, textView;
    //头条
    private TextView newstop;
    //天气
    private ImageView standby_weather_i;
    private TextView standby_weather_c, standby_weather_d;
    private Drawable qingtian, yintian, baoyu, xiaoyu,tongyong;//天气图片
    //设置
    private ImageView goset;
    //更新用的handler
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Log.i("数据刷新-考勤，天气，班级信息", "开始刷新");
                standbyPresenter.startServiceInfo();
                standbyPresenter.showServiceInfo();
                Log.i("数据刷新-考勤，天气，班级信息", "完成刷新");
            }
        }
    };
    //自动同步时间的计时器
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            SendTime sendtime = new SendTime();
            sendtime.start();
        }
    };
    //定时请求考勤，显示考勤，定时请求天气，显示天气。
    private TimerTask timerTask1 = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);
        }
    };


    //动usbReceiver
    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            context.unregisterReceiver(this);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standby_1);
        ViewInitialization();
        init();
        timer = new Timer(true);
        timer.schedule(timerTask, 5 * 1000, 5 * 60 * 60 * 1000);//启动5秒后同步时间，每五小时同步一次时间
        timer.schedule(timerTask1, 10 * 1000, 2 * 60 * 1000);//启动10秒后开始刷新数据，每2分钟刷新一次数据。
//        //启动数据访问线程
        standbyPresenter.startServiceInfo();
//        //数据显示
        standbyPresenter.showServiceInfo();

    }

    public void init() {
        //注册广播
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        registerReceiver(usbReceiver, filter);
        //获取application
        myapplication = (MyApplication) getApplication();
        //配置信息初始化
        standbyPresenter.setInitialization();
    }


    /**
     * 获取StandbyPresenter
     *
     * @return
     */
    @Override
    protected StandbyPresenter createPresenter() {
        standbyPresenter = new StandbyPresenterImpl(StandbyActivity.this);
        return standbyPresenter;

    }

    @Override
    protected MyApplication GetMyApplication() {
        myapplication = (MyApplication) getApplication();
        return myapplication;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void ViewInitialization() {
        //fragment初始化
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        mClassInfo = new ClassInfoFragment();
        mClassSportFragment = new ClassSportFragment();
        //默认显示的内容
        transaction.replace(R.id.standby_framegroup, mClassInfo);
        transaction.commit();
        //控件初始化
        //考勤
        total = (TextView) findViewById(R.id.standby_total);
        arrived = (TextView) findViewById(R.id.standby_arrved);
        noArrived = (TextView) findViewById(R.id.standby_noarrved);
        //班级信息
        name = (TextView) findViewById(R.id.standby_clazzname);
        textView = (TextView) findViewById(R.id.standby_clazztext);
        //头条
        newstop = (TextView) findViewById(R.id.news_top);
//        textView.setBackgroundColor(Color.rgb(222,222,222));
        //天气
        standby_weather_i = (ImageView) findViewById(R.id.standby_we_image);
        standby_weather_c = (TextView) findViewById(R.id.standby_we_c);
        standby_weather_d = (TextView) findViewById(R.id.standby_we_d);
        qingtian = ContextCompat.getDrawable(MyApplication.getContext(), R.drawable.qingtian);
        yintian = ContextCompat.getDrawable(MyApplication.getContext(), R.drawable.yintian);
        baoyu = ContextCompat.getDrawable(MyApplication.getContext(), R.drawable.baoyu);
        xiaoyu = ContextCompat.getDrawable(MyApplication.getContext(), R.drawable.xiaoyu);
        tongyong= ContextCompat.getDrawable(MyApplication.getContext(), R.drawable.tongyong);
        //设置
        goset= (ImageView) findViewById(R.id.standby_gouset);
        goset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText et = new EditText(StandbyActivity.this);
                new AlertDialog.Builder(StandbyActivity.this).setTitle("设置采集终端归属").setIcon(android.R.drawable.ic_dialog_info).setView(et).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        String password = et.getText().toString();
                        if (password.equals("123456")) {
                            Intent intent = new Intent(StandbyActivity.this, InstallActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(StandbyActivity.this, "密码错误", Toast.LENGTH_LONG).show();
                        }
                    }
                }).setNegativeButton("取消", null).show();
            }
        });
    }


    @Override
    public void gotoMySportDataActivity() {
        Intent intent = new Intent(StandbyActivity.this, MySportDataActivity.class);
        startActivity(intent);

    }

    @Override
    public void showInitialization() {

    }

    @Override
    public void showAttendance(TempClazzAttendance clazzAttendance) {
        if (clazzAttendance != null) {
            String yi = clazzAttendance.getArrived() + "";//已到
            String wei = clazzAttendance.getNoArrived() + "";//未到
            String zg = clazzAttendance.getTotal() + "";//应道
            total.setText(zg);
            arrived.setText(yi);
            noArrived.setText(wei);
            Log.i("测试", "设置考勤信息");
        }
    }

    @Override
    public void showClazzInfo(TempClazzInfo clazzInfo) {
        if (clazzInfo != null) {
            name.setText(clazzInfo.getName());
            textView.setText(clazzInfo.getText());
        } else {
            name.setText("等待刷新");
            textView.setText("等待刷新");
        }
    }

    @Override
    public void showNew(final List<News> list) {

        if(mHandler!=null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (list != null) {
                        if (list.size() != 0) {
                            newstop.setText(list.get(0).getTitle());
                        }
                    }
                }
            });
        }
    }

    @Override
    public void showWeather(final AirApi airApi, final WeatherApi weatherApi, final String cityName) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (weatherApi != null) {

                    String resultcode2 = weatherApi.getResultcode();
                    if (resultcode2.equals("200")) {//获取天气情况成功
                        WeatherInfo weatherInfo = weatherApi.getResult();
                        if (weatherInfo != null) {
                            weatherInfo.getSk().getTemp();//及时温度
                            weatherInfo.getToday().getDressing_advice();//建议
                            String state3 = weatherInfo.getToday().getWeather();//天气
                              Log.i("数据测试—天气","天气描述"+state3);
                            if (weatherInfo.getToday() != null) {
                                standby_weather_c.setText(weatherInfo.getToday().getTemperature());
                                standby_weather_d.setText("穿衣建议：" + weatherInfo.getToday().getDressing_advice());
                                if (state3.contains("小雨")) {
                                    standby_weather_i.setImageDrawable(xiaoyu);
                                } else if (state3.contains("大雨")||state3.contains("暴雨")) {
                                    standby_weather_i.setImageDrawable(baoyu);
                                } else if (state3.contains("晴")) {
                                    standby_weather_i.setImageDrawable(qingtian);
                                } else if (state3.contains("阴")) {
                                    standby_weather_i.setImageDrawable(yintian);
                                }else {
                                    standby_weather_i.setImageDrawable(tongyong);
                                }
                            }
                        } else {
                            Log.i("数据测试——天气-温度", "weatherInfo为空值");
                        }

                    } else {
                        Log.i("数据测试——天气-温度", resultcode2);
                    }
                } else {
                    Log.i("数据测试——天气-温度", "空的？");
                }
            }
        });

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    /**
     * 按钮切换fragment
     *
     * @param v
     */
    public void onChangeFragment(View v) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();

        switch (v.getId()) {
            case R.id.btn_11://按钮1-1
                if (mClassInfo == null) {
                    mClassInfo = new ClassInfoFragment();
                }
                transaction.replace(R.id.standby_framegroup, mClassInfo);
                break;
            case R.id.btn_12://按钮1-2
                if (mClassSubTableFragment == null) {
                    mClassSubTableFragment = new ClassSubTableFragment();
                }
                transaction.replace(R.id.standby_framegroup, mClassSubTableFragment);
                break;
            case R.id.btn_21://按钮2-1
                if (mCleanTableFragment == null) {
                    mCleanTableFragment = new CleanTableFragment();
                }
                transaction.replace(R.id.standby_framegroup, mCleanTableFragment);
                break;
            case R.id.btn_22://按钮2-2
                if (mRedFlagFragment == null) {
                    mRedFlagFragment = new RedFlagFragment();
                }
                transaction.replace(R.id.standby_framegroup, mRedFlagFragment);
                break;
            case R.id.btn_31://按钮3-1
                if (mClassSportFragment == null) {
                    mClassSportFragment = new ClassSportFragment();
                }
                transaction.replace(R.id.standby_framegroup, mClassSportFragment);
                break;
            case R.id.btn_32://按钮3-2
                Toast.makeText(StandbyActivity.this,"敬请期待",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_more://新闻按钮
                if (mNewsFragment == null) {
                    mNewsFragment = new NewsFragment();
                }
                transaction.replace(R.id.standby_framegroup, mNewsFragment);
                break;
        }
        // transaction.addToBackStack();
        // 事务提交
        transaction.commit();
    }

    class SendTime extends Thread {
        byte[] by;

        public SendTime() {
            // TODO Auto-generated constructor stub
            byte[] buffer = new byte[HW_HEADER.HEADER_SIZE];
            HW_HEADER header = new HW_HEADER();
            header.wrap(buffer, 0);
            header.init();
            header.setCommand((short) 0x0010);
            header.setTime(new Date());
            this.by = buffer;
        }

        public void run() {
            MyApplication.getInstance().getConnection().bulkTransfer(MyApplication.getInstance().getEndpoint()[1][0], by, by.length, 3000);
            Log.i("数据下发", "发送");
        }
    }

}
