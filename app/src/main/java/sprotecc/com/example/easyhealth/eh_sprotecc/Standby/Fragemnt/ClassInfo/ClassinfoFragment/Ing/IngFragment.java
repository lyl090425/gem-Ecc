package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.Ing;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import sprotecc.com.example.easyhealth.eh_sprotecc.Adapter.CircleImageView;
import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempIngInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class IngFragment extends BaseFragment<IngView, IngPresenter<IngView>> implements IngView {
    private IngPresenter ingPresenter;
    private View view;
    //老师简介
    WebView teacherInfo;
    //班级简介
    WebView subInfo;
    //老师名字
    TextView teachername;
    //老师头像
    CircleImageView teachertouxiang;

    Drawable laos;
    private RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getContext());
    ImageLoader.ImageCache imageCache= new ImageLoader.ImageCache(){
        @Override
        public Bitmap getBitmap(String s) {
            return null;
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classinfo_ing, container, false);
        initView();
        ingPresenter = new IngPresenterImpl(this);
        ingPresenter.startThread();
        ingPresenter.showInfo();
        return view;
    }

    @Override
    protected IngPresenter<IngView> createPresenter() {
        ingPresenter = new IngPresenterImpl(this);
        return ingPresenter;
    }

    public void initView() {
        teacherInfo = (WebView) view.findViewById(R.id.ing_teacherinfo);
        WebSettings webSettings = teacherInfo.getSettings();
        webSettings.setJavaScriptEnabled(true);
            subInfo = (WebView) view.findViewById(R.id.ing_subinfo);
        WebSettings webSettings1 = subInfo.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        teachername = (TextView) view.findViewById(R.id.ing_teachername);
        teachertouxiang = (CircleImageView) view.findViewById(R.id.ing_touxiang);

        laos= ContextCompat.getDrawable(view.getContext(), R.drawable.userhead);
    }

    @Override
    public void showIng(TempIngInfo tempIngInfo) {
      if(tempIngInfo!=null){
        teachername.setText(tempIngInfo.getName());//老师名字
          int tcontentid=Integer.parseInt(tempIngInfo.gettContent());//老师简介ID
          int scontentid=Integer.parseInt(tempIngInfo.getsContent());//课程简介ID
          initWeb(teacherInfo,tcontentid);
          initWeb(subInfo,scontentid);
          long userid=Long.parseLong( tempIngInfo.getUserid());
          ImageLoader imageLoader = new ImageLoader(mQueue,imageCache);
          ImageLoader.ImageListener listener = ImageLoader.getImageListener(teachertouxiang, R.drawable.classsport_name1, R.drawable.userhead);
          String url= MyApplication.ppppp+((int)(userid/500))+"/"+userid;
          imageLoader.get(url, listener);

           }else {
          teachertouxiang.setImageDrawable(laos);
      }

    }
    private void initWeb(WebView webView,long id){
        //WebView加载web资源
        String url= MyApplication.contenturl+id;
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
}
