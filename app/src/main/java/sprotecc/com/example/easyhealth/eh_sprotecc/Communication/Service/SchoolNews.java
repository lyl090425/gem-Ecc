package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;

import android.util.Log;

import com.ruite.gem.action.公开.大屏.获取学校新闻;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.新闻.News;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.News.NewsView;

/**
 * Created by adminHjq on 2017/1/10.
 */
public class SchoolNews implements Runnable {
    NewsView newsView;
    public SchoolNews (NewsView newsView){
        this.newsView=newsView;
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
            Log.i("数据获取-新闻","成功学校获取新闻，条数："+list.size());
             }
            newsView.showSchoolNew(list);
        } catch (Exception e) {
            Log.i("数据获取-新闻","获取学校新闻失败");
            newsView.showSchoolNew(null);
            e.printStackTrace();
        }
    }
}
