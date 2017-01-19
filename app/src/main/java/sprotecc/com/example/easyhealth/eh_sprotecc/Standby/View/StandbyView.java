package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.View;

import com.ruite.gem.modal.APIReturn.AirApi;
import com.ruite.gem.modal.APIReturn.WeatherApi;
import com.ruite.gem.modal.新闻.News;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzAttendance;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzInfo;

/**
 * Created by adminHjq on 2016/12/8.
 */
public interface StandbyView {

    public void gotoMySportDataActivity();
    public void showInitialization();
    public void showAttendance(TempClazzAttendance attendance);
    public  void showClazzInfo(TempClazzInfo clazzInfo);
    public  void showNew(List<News> list);
    public void showWeather(AirApi airApi, WeatherApi weatherApi,String cityName);





}
