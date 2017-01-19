package sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by adminHjq on 2016/12/13.
 */
public class MyThreadPool  {
    private  static MyThreadPool myThreadPool;
    private MyThreadPool(){}
    private ThreadPoolProxy longPoolProxy;
    private ThreadPoolProxy shortPoolProxy;
    public static  MyThreadPool getInstance(){
        if(myThreadPool==null){
            synchronized (MyThreadPool.class){
                if(myThreadPool==null){
                    myThreadPool=new MyThreadPool();
                }
            }
        }
        return myThreadPool;
    }
    public synchronized ThreadPoolProxy createLongPool(){
        if (longPoolProxy == null) {
            //(int corePoolSize 线程池大小, int maximumPoolSize 最大值, long keepAliveTime 存活时间)
            longPoolProxy = new ThreadPoolProxy(30, 30, 5000);
        }
        return longPoolProxy;
    }
    public synchronized ThreadPoolProxy createShortPool(){
        if (shortPoolProxy == null) {
            shortPoolProxy = new ThreadPoolProxy(30, 30, 5000);
        }
        return shortPoolProxy;
    }

    public class ThreadPoolProxy {
        private ThreadPoolExecutor pool;
        private int corePoolSize; //线程数
        private int maximumPoolSize; //线程满了后额外开的线程窗口
        private long keepAliveTime;//没有线程执行时存活时间

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }
        /**
         * 执行线程
         * @param runnable
         */
        public void execute(Runnable runnable) {
            if (pool == null) {
                //最多可有多少个线程排队
                BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(15);
                pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue);
            }
            pool.execute(runnable);
        }

        /**
         * 取消线程
         *
         * @param runnable
         */
        public void cancel(Runnable runnable) {
            if (pool != null) {
                pool.remove(runnable);
            }
        }
    }
    }
