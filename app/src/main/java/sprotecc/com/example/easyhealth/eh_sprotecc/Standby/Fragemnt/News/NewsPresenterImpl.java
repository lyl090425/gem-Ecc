package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.News;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.ClassNews;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.SchoolNews;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/23.
 */
public class NewsPresenterImpl extends BasePresenterImpl<NewsView> implements NewsPresenter<NewsView> {
    NewsView newsView;
    public  NewsPresenterImpl(NewsView newsView){
       this. newsView=newsView;
    }

    @Override
    public void startThread() {
        MyThreadPool.getInstance().createLongPool().execute(new ClassNews(newsView));
        MyThreadPool.getInstance().createLongPool().execute(new SchoolNews(newsView));
    }
}
