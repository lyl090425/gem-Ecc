package sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.View;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruite.gem.modal.人员信息.UserInfo;
import com.ruite.gem.spdtp.ECC_SPORTDATA;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseActivity;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.PushCardThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.UserInfoThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.MyInformation.View.MyInformationActivity;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Presenter.MySportDataPresenter;
import sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.Presenter.MySportDataPresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;
import sprotecc.com.example.easyhealth.eh_sprotecc.Tool.SportDataUtil;

public class MySportDataActivity extends BaseActivity<MySportDataView, MySportDataPresenter<MySportDataView>> implements MySportDataView {
    private MySportDataPresenter mySportDataPresenter;
    private MyApplication myApplication;
   private  Handler mHandler=new Handler();
  private DecimalFormat df   = new DecimalFormat("######0.0");
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //根布局s
    private View MySportDataActivity;
    //名字
    private TextView name;
    //体温
    private TextView temp;
    //运动步数
    private TextView ydbs;
    //活动度
    private TextView hhd;
    //卡路里
    private TextView kkl;
    //打开成功标志
    private TextView isSwingcard;
    private TextView isUpSport;

    //运动里程
    private TextView distance;
    //运动速度
    private TextView speed;
    //运动时间
    private TextView time;
    //跳像下一个界面的按钮
    private ImageView imageView4;
    private TextView textView3;

    //计时器，用于控制页面显示时间后自己结束掉自己。
    private Timer timer;
    //自动回到主界面的计时器
    private TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            MySportDataActivity.this.finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sport_data);
        timer = new Timer(true);
        timer.schedule(timerTask, 6* 1000);//6秒后自动结束
        ViewInitialization();

        mySportDataPresenter.sportData();

    }

    @Override
    protected MySportDataPresenter createPresenter() {
        mySportDataPresenter = new MySportDataPresenterImpl(MySportDataActivity.this,mHandler);
        return mySportDataPresenter;
    }

    @Override
    protected MyApplication GetMyApplication() {
        myApplication = (MyApplication) getApplication();
        return myApplication;

    }

    /**
     * 初始化控件
     */
    @Override
    protected void ViewInitialization() {
        Log.i("测试","我的运动数据页面控件初始化");
        name = (TextView) findViewById(R.id.my_sport_data_name);
        temp = (TextView) findViewById(R.id.my_sport_data_temp);
        ydbs = (TextView) findViewById(R.id.my_sport_data_ydbs);
        hhd = (TextView) findViewById(R.id.my_sport_data_hhd);
        kkl = (TextView) findViewById(R.id.my_sport_data_kkl);
        isSwingcard = (TextView) findViewById(R.id.my_sport_data_isswingcard);
        isUpSport= (TextView) findViewById(R.id.my_sport_data_isuploadsportdata);
        distance = (TextView) findViewById(R.id.my_sport_data_sportdistance);
        speed = (TextView) findViewById(R.id.my_sport_data_sportspeed);
        time = (TextView) findViewById(R.id.my_sport_data_sporttime);


        textView3= (TextView) findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String abv="陌生人";
                String abc=name.getText().toString();
                if(abv.equals(abc)){
                    Toast.makeText(MySportDataActivity.this,"没有权限",Toast.LENGTH_LONG).show();
                }else {
                Intent intent = new Intent(MySportDataActivity.this, MyInformationActivity.class);
                startActivity(intent);
                MySportDataActivity.this.finish();
                }
            }
        });
        imageView4= (ImageView) findViewById(R.id.imageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String abv="陌生人";
                String abc=name.getText().toString();
                if(abv.equals(abc)){
                    Toast.makeText(MySportDataActivity.this,"没有权限",Toast.LENGTH_LONG).show();
                }else {
                Intent intent = new Intent(MySportDataActivity.this, MyInformationActivity.class);
                startActivity(intent);
                MySportDataActivity.this.finish();
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
        timer.cancel();
        timer=null;
        }
        MySportDataActivity.this.finish();

    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
        MySportDataActivity.this.finish();

    }



    /**
     * 本地信息获取成功
     * @param
     */
    @Override
    public void showSportData(UserInfo userInfo,ECC_SPORTDATA ecc_sportdata) {
        //当前运动情况
         int[] nowdata=ecc_sportdata.getNowdata();
        String bs=nowdata[0]+"";
        String hhdd=nowdata[1]+"";
        String dis=nowdata[2]+"";
        String kkll=nowdata[3]+"";
        String timea=df.format(SportDataUtil.getsporttime(nowdata[0]))+"";
        String speeda= df.format(SportDataUtil.getsportspeed(Double.parseDouble(dis),SportDataUtil.getsporttime(nowdata[0])))+"";
        ydbs.setText(bs);//步数
        hhd.setText(hhdd);//活动度
        distance.setText(dis+"米");//距离
        kkl.setText(kkll);//卡路里

        time.setText("约"+timea+"min");//运动时间
        speed.setText(speeda+"m/min");//运动速度
        //用户基本信息
        name.setText(userInfo.getName());
        //体温?


    }

    /**
     * 获取网络成功
     * @param userInfo
     * @param ecc_sportdata
     */
    @Override
    public void showSportDataHTTP(final UserInfo userInfo, final ECC_SPORTDATA ecc_sportdata) {
        if(mHandler!=null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    //当前运动情况
                    int[] nowdata = ecc_sportdata.getNowdata();
                    String bs = nowdata[0] + "";
                    String hhdd = nowdata[1] + "";
                    String dis = nowdata[2] + "";
                    String kkll = nowdata[3] + "";
                    String timea = df.format(SportDataUtil.getsporttime(nowdata[0])) + "";
                    String speeda = df.format(SportDataUtil.getsportspeed(Double.parseDouble(dis), SportDataUtil.getsporttime(nowdata[0]))) + "";
                    ydbs.setText(bs);//步数
                    hhd.setText(hhdd);//活动度
                    distance.setText(dis + "米");//距离
                    kkl.setText(kkll);//卡路里

                    time.setText("约" + timea + "min");//运动时间
                    speed.setText(speeda + "m/min");//运动速度
                    //用户基本信息
                    name.setText(userInfo.getName());
                    //体温?
                }
            });
        }
    }

    /**
     *本地没有用户，网络也不存在该用户的页面显示
     * @param
     */
    @Override
    public void showSportDataMoshengren(final ECC_SPORTDATA ecc_sportdata) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //当前运动情况
                int[] nowdata=ecc_sportdata.getNowdata();
                String bs=nowdata[0]+"";
                String hhdd=nowdata[1]+"";
                String dis=nowdata[2]+"";
                String kkll=nowdata[3]+"";
                String timea=df.format(SportDataUtil.getsporttime(nowdata[0]))+"";
                String speeda= df.format(SportDataUtil.getsportspeed(Double.parseDouble(dis),SportDataUtil.getsporttime(nowdata[0])))+"";
                ydbs.setText(bs);//步数
                hhd.setText(hhdd);//活动度
                distance.setText(dis+"米");//距离
                kkl.setText(kkll);//卡路里
                time.setText("约"+timea+"min");//运动时间
                speed.setText(speeda+"m/min");//运动速度
                //用户基本信息
                name.setText("陌生人");
            }
        });


    }

    /**
     * 显示打卡结果
     * @param userInfo
     * @param date
     */
    @Override
    public void showPushCard(final UserInfo userInfo, final Date date) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(userInfo!=null&& date!=null){
                    //显示打卡成功
                    isSwingcard.setText("打卡成功，打卡时间："+sdf.format(date));
                }else  {
                    isSwingcard.setText("打卡失败，请重试");
                }
            }
        });


    }

    @Override
    public void showUpload(final int b) {
        if(mHandler!=null){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(b==1){
                    isUpSport.setText("上传运动数据成功");
                }else{
                    isUpSport.setText("上传运动数据失败");
                }
            }
        });
        }
        //显示上传是否成功

    }

}
