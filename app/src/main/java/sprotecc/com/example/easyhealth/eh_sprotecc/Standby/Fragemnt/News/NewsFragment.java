package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.News;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruite.gem.modal.新闻.News;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Adapter.NewsAdapter;
import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

/**
 * Created by adminHjq on 2016/12/23.
 */
public class NewsFragment extends BaseFragment<NewsView, NewsPresenter<NewsView>> implements NewsView, View.OnClickListener {
    private View view;
    private ImageView schoolMsg, classMsg;
    private TextView schoolMsg1, classMsg1;
    private ListView schoolMsgNews, classMsgNews;
    private NewsPresenter mNewsPresenter;
    private WebView webView;
    private Drawable t1, t2;

    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        mNewsPresenter = new NewsPresenterImpl(this);
        mNewsPresenter.startThread();
        initViews();
        return view;
    }

    @Override
    protected NewsPresenter<NewsView> createPresenter() {

        return null;
    }

    /**
     * 初始化自身控件
     */
    private void initViews() {
        //按钮
        schoolMsg = (ImageView) view.findViewById(R.id.news_bnt1);
        classMsg = (ImageView) view.findViewById(R.id.news_bnt2);
        schoolMsg1 = (TextView) view.findViewById(R.id.news_btn11);
        classMsg1 = (TextView) view.findViewById(R.id.news_bnt22);
        //列表
        schoolMsgNews = (ListView) view.findViewById(R.id.news_re1);
        classMsgNews = (ListView) view.findViewById(R.id.news_re2);
        //按钮样式
        t1 = ContextCompat.getDrawable(view.getContext(), R.drawable.newst1);
        t2 = ContextCompat.getDrawable(view.getContext(), R.drawable.newst2);
        schoolMsg.setImageDrawable(t2);
        //给按钮添加监听
        classMsg.setOnClickListener(this);
        classMsg1.setOnClickListener(this);
        schoolMsg1.setOnClickListener(this);
        schoolMsg.setOnClickListener(this);

        webView= (WebView) view.findViewById(R.id.news_re3);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        schoolMsgNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                ListView listView = (ListView) parent;
                final News news = (News) listView.getItemAtPosition(position);
                long ids=news.getId();
                resetNewsRe();
                webView.setVisibility(View.VISIBLE);
                initWeb(ids);

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.news_bnt1||v.getId()==R.id.news_btn11){
            resetNewsNnt();
            schoolMsg.setImageDrawable(t2);
            resetNewsRe();
            schoolMsgNews.setVisibility(View.VISIBLE);
        }else if(v.getId()==R.id.news_bnt2||v.getId()==R.id.news_bnt22){
            resetNewsNnt();
            classMsg.setImageDrawable(t2);
            resetNewsRe();
            classMsgNews.setVisibility(View.VISIBLE);
        }



    }

    /**
     * 按钮初始化
     */
    private void resetNewsNnt() {
        schoolMsg.setImageDrawable(t1);
        classMsg.setImageDrawable(t1);

    }

    private void resetNewsRe() {
        schoolMsgNews.setVisibility(View.GONE);
        classMsgNews.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
    }

    //显示年级新闻列表
    @Override
    public void showSchoolNew(final List<News> list) {

        if(handler!=null){
        handler.post(new Runnable() {
            @Override
            public void run() {
                NewsAdapter newsAdapter=new NewsAdapter(list);
                schoolMsgNews.setDivider(new ColorDrawable(Color.rgb(0, 255, 255)));
                schoolMsgNews.setDividerHeight(1);
                schoolMsgNews.setAdapter(newsAdapter);
            }
        });
        }
    }

    //显示班级新闻列表
    @Override
    public void showClazzNew(final List<News> list) {

        if(handler!=null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    NewsAdapter newsAdapter = new NewsAdapter(list);
                    classMsgNews.setDivider(new ColorDrawable(Color.rgb(0, 255, 255)));
                    classMsgNews.setDividerHeight(1);
                    classMsgNews.setAdapter(newsAdapter);
                }
            });
        }
    }

    private void initWeb(long id){
        //WebView加载web资源
        String url= MyApplication.newshttp+id;
        Log.i("数据测试-新闻","新闻链接地址"+url);
        webView.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
