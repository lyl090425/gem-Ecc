package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;

import android.util.Log;

import com.ruite.gem.action.公开.大屏.获取学校新闻;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.新闻.News;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;

import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.View.StandbyView;

/**
 * Created by adminHjq on 2017/1/10.
 */
public class TopNews implements Runnable {
    StandbyView standbyView;
    public TopNews (StandbyView standbyView){
        this.standbyView=standbyView;
    }

    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            获取学校新闻 action=new 获取学校新闻();
            action.setSchoolId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getSchoolId()));
            获取学校新闻 re=rpc.call(action);
            List<News> list=re.getList();
            if(list!=null){
                Log.i("数据获取-新闻","第一，条数："+list.size());
            }
            standbyView.showNew(list);
        } catch (Exception e) {
            Log.i("数据获取-新闻","top");
            standbyView.showNew(null);
            e.printStackTrace();
        }
    }
}
