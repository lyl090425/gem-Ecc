package sprotecc.com.example.easyhealth.eh_sprotecc.Application;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.ruite.gem.modal.人员信息.UserInfo;
import com.ruite.gem.spdtp.ECC_SPORTDATA;
import com.ruite.gem.spdtp.SPDTP_HW_ZSLYEYDATA;

import java.io.File;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

import sprotecc.com.example.easyhealth.eh_sprotecc.Tool.CrashHandler;

/**
 * Created by adminHjq on 2016/12/9.
 */
public class MyApplication extends Application {
    private static Context context;
    private ECC_SPORTDATA ecc_sportdata;//存储运动数据
    private UsbDeviceConnection connection;//收发数据接口
    private UsbEndpoint[][] endpoint = new UsbEndpoint[16][16];//端点
    private static MyApplication instance;

    private static String baseurl = "http://192.168.1.12:8899";
    //actions
    public static String url = baseurl + "/hessian";
    //头像
    public static String ppppp = baseurl + "/data/user_photo/";//用户ID/500+用户ID
    //新闻地址
    public static String newshttp = baseurl + "/gem-platform/news/news_view.html?id=";
    //相册图片地址
    public static String photourl = baseurl + "/data/photoalbum/";//图片ID/500+图片ID
    //简介地址
    public static String contenturl = baseurl + "/gem-platform/news/introduction_view.html?id=";
    //荣誉图片
    public static String honnorurl = baseurl + "/data/type_picture/";//图片ID/500+图片ID


    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instance = this;
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());

    }

    public UsbEndpoint[][] getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(UsbEndpoint[][] endpoint) {
        this.endpoint = endpoint;
    }

    public UsbDeviceConnection getConnection() {
        return connection;
    }

    public void setConnection(UsbDeviceConnection connection) {
        this.connection = connection;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MyApplication.context = context;
    }

    public ECC_SPORTDATA getEcc_sportdata() {
        return ecc_sportdata;
    }

    public void setEcc_sportdata(ECC_SPORTDATA ecc_sportdata) {
        this.ecc_sportdata = ecc_sportdata;
    }
}
