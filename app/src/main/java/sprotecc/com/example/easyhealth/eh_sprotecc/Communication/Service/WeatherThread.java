package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;

import android.util.Log;


import com.ruite.gem.action.公开.天气.获取天气情况;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.APIReturn.AirApi;
import com.ruite.gem.modal.APIReturn.WeatherApi;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.View.StandbyView;

/**
 * Created by adminHjq on 2017/1/10.
 */
public class WeatherThread implements Runnable {
    StandbyView standbyView;
    public WeatherThread(StandbyView standbyView){
        this.standbyView=standbyView;
    }
    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            获取天气情况 action=new 获取天气情况();
            action.setNeedAir(true);
            action.setNeedWeather(true);
            获取天气情况 re=rpc.call(action);
            AirApi airApi= re.getAirApi();//空气情况
            WeatherApi weatherApi=  re.getWeatherApi();//天气情况
            String cityName=  re.getCityName();//所在城市
            Log.i("数据获取-天气","获取成功");
            standbyView.showWeather(airApi,weatherApi,cityName);
        } catch (Exception e) {
            Log.i("数据获取-天气","获取失败");
            standbyView.showWeather(null,null,null);
            e.printStackTrace();
        }

    }
}
