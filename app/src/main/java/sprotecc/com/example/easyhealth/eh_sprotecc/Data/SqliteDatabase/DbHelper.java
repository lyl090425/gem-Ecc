package sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * Created by adminHjq on 2016/12/12.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "eh_ecc_data.db";
    public final static int DATABASE_VERSION = 1;//数据库版本号
    //硬件信息
    public static final String TABLE_HARdWAREINFO = "hardwareinfo";//表名字

    public static final String HARDWAREINFO_ID = "id";//内部编号
    public static final String HARDWAREINFO_ADDRESS = "address";//地址
    public static final String HARDWAREINFO_SCHOOLID = "schoolId";//学校编号
    public static final String HARDWAREINFO_SCHOOLNAME = "schoolName";//学校名称
    public static final String HARDWAREINFO_FLAG = "flag";//学校名称

    //硬件信息


    //软件信息
    public static final String TABLE_SOFTINFO = "softinfo";//表名字
    public static final String SOFTINFO_ID = "id";//内部编号
    public static final String SOFTINFO_VERSION = "version";//版本号
    public static final String SOFTINFO_CLAZZ = "clazz";//班级级
    public static final String SOFTINFO_GRADE = "grade";//年级
    public static final String SOFTINFO_SCHOOLID = "schoolId";//学校编号
    public static final String SOFTINFO_FLAG = "flag";//是否已经初始化记号
    //软件信息

    //用户信息
    public static final String TABLE_USERINFO = "userinfo";//表名字
    public static final String USERINFO_ID = "id";//内部编号
    public static final String USERINFO_USERID = "userid";//服务器用户ID编号1
    public static final String USERINFO_USERCODE = "usercode";//用户识别码
    public static final String USERINFO_USERNAME = "username";//用户名称3
    public static final String USERINFO_USERHEIGHT = "userheight";//用户身高4
    public static final String USERINFO_USERWEIGHT = "userweight";//用户体重5
    public static final String USERINFO_CLAZZ = "clazz";//用户所在年级
    public static final String USERINFO_GRADE = "grade";//用户所在班级

    //用户信息

    //体温数据
    public static final String TABLE_TEMPERATURE = "temperature";//表名字

    public static final String TEMPERATURE_ID = "id";//内部ID
    public static final String TEMPERATURE_USERCODE = "usercode";//用户识别码(证件号)
    public static final String TEMPERATURE_DATE = "date";//日期
    public static final String TEMPERATURE_DATA = "data";//数据
    public static final String TEMPERATURE_HIGHT = "htemp";//最高体温
    public static final String TEMPERATURE_LOW = "ltemp";//最低体温
    //体温数据

    //运动数据
    public static final String TABLE_SPORTDATA = "sportdata";//表名字

    public static final String SPORTDATA_ID = "id";//内部编号
    public static final String SPORTDATA_USERCODE = "user_code";//用户编号
    public static final String SPORTDATA_DATE = "date";//运动数据日期
    public static final String SPORTDATA_SPORTS_DATA = "data";//运动数据
    //运动数据

    //考勤
    public static final String TABLE_ATTENDANCE = "attendanceinfo";//表名字
    public static final String ATTENDANCE_ID = "id";
    public static final String ATTENDANCE_USERID = "userid";
    public static final String ATTENDANCE_USERCODE = "usercode";
    public static final String ATTENDANCE_FLAG = "flag";
    public static final String ATTENDANCE_FIRST_ONE = "time_first_one";
    public static final String ATTENDANCE_FIRST_TWO = "time_first_two";
    public static final String ATTENDANCE_SENCOND_ONE = "time_second_one";
    public static final String ATTENDANCE_SENCOND_TWO = "time_second_two";
    public static final String ATTENDANCE_THIRD_ONE = "time_third_one";
    public static final String ATTENDANCE_THIRD_TWO = "time_third_two";
    //考勤


    /**
     * 临时数据表
     * 每隔一段时间，页面显示内容中这里取出
     * 包括
     */
    //临时班级考勤
    public static final String TABLE_TEMPORARY_ATTENDANCE = "temporary_attendance";//表名字
    public static final String TEMPORARY_ATTENDANCE_ID = "id";
    public static final String TEMPORARY_ATTENDANCE_TOTAL = "total";//应道
    public static final String TEMPORARY_ATTENDANCE_AR = "arrived";//已到
    public static final String TEMPORARY_ATTENDANCE_NO = "no_arrived";//未到
    //临时运动排名1(没有设计好，待优化)
    public static final String TABLE_TEMPORARY_SPORTRANK = "temporary_sportrank";
    public static final String TEMPORARY_SPORTRANK_ID = "id";
    public static final String TEMPORARY_SPORTRANK_RANK = "rank";//名次
    public static final String TEMPORARY_SPORTRANK_NAME = "name";//姓名
    public static final String TEMPORARY_SPORTRANK_COUNT = "count";//总步数或者总运动量
    public static final String TEMPORARY_SPORTRANK_USERID="userid";//用于请求用户头像
    //临时活动度排名
    public static final String TABLE_TEMPORARY_ACTIVITYRANK = "temporary_activitytrank";
    public static final String TEMPORARY_ACTIVITYRANK_ID = "id";
    public static final String TEMPORARY_ACTIVITYRANK_RANK = "rank";//名次
    public static final String TEMPORARY_ACTIVITYRANK_NAME = "name";//姓名
    public static final String TEMPORARY_ACTIVITYRANK_COUNT = "count";//总步数或者总运动量
    public static final String TEMPORARY_ACTIVITYRANK_USERID="userid";//用于请求用户头像
    //临时操行分
    public static final String TABLE_TEMPORARY_CONDUCTPOINT = "temporary_condpiont";
    public static final String TEMPORARY_CONDUCTPOINT_ID = "id";
    public static final String TEMPORARY_CONDUCTPOINT_NAME = "name";//姓名
    public static final String TEMPORARY_CONDUCTPOINT_SCORE = "score";//分数

    //临时运动排名2(没有设计好，待优化)(以优化，不再使用该表)
    public static final String TABLE_TEMPORARY_SPORT8RANK = "temporary_sport8rank";
    public static final String TEMPORARY_SPORT8RANK_ID = "id";
    public static final String TEMPORARY_SPORT8RANK_RANK = "rank";//名次
    public static final String TEMPORARY_SPORT8RANK_NAME = "name";//姓名
    public static final String TEMPORARY_SPORT8RANK_COUNT = "count";//总步数或者总运动量
    //临时班级历史数据运动表
    public static final String TABLE_TEMPORARY_CLASSHISTORY = "temporary_classhistory";
    public static final String TEMPORARY_CLASSHISTORY_ID = "id";
    public static final String TEMPORARY_CLASSHISTORY_DATE = "date";//时间——
    public static final String TEMPORARY_CLASSHISTORY_STEP = "count";//步数
    //临时个人历史数据运动表
    public static final String TABLE_TEMPORARY_MYHISTORY = "temporary_myhistory";
    public static final String TEMPORARY_MYHISTORY_ID = "id";
    public static final String TEMPORARY_MYHISTORY_DATE = "date";//时间—
    public static final String TEMPORARY_MYHISTORY_STEP = "count";//步数
    //临时荣誉表
    public static final String TABLE_TEMPORARY_MYHONOUR = "temporary_myhonour";
    public static final String TEMPORARY_MYHONOUR_ID = "id";
    public static final String TEMPORARY_MYHONOUR_NAME = "name";//
    public static final String TEMPORARY_MYHONOUR_TYPEID = "typeid";//
    public static final String TEMPORARY_MYHONOUR_COUNT = "count";//
    //临时课中表
    public static final String TABLE_TEMPORARY_ING = "temporary_ing";
    public static final String TEMPORARY_ING_ID = "id";
    public static final String TEMPORARY_ING_NAME = "name";
    public static final String TEMPORARY_ING_TCONTENT = "tcontent";//老师简介——》int-》通过换算，结果再换算出来
    public static final String TEMPORARY_ING_SCONTENT = "scontent";//课程简介
    public static final String TEMPORARY_ING_TEACHERID = "teacherid";//老师ID，用于请求老师头像

    //临时班级信息
    public static final String TABLE_TEMPORARY_CLAZZINFO = "temporary_classinfo";
    public static final String TTEMPORARY_CLAZZINFO_ID = "id";
    public static final String TTEMPORARY_CLAZZINFO_NAME = "name";
    public static final String TTEMPORARY_CLAZZINFO_TEXT = "clazzcontent";//老师简介——》int-》通过换算，结果再换算出来


    /**
     * 打开或创建数据库
     *
     * @param context
     */
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建硬件信息表
        String createsportadata = "create table " + TABLE_HARdWAREINFO
                + "("
                + HARDWAREINFO_ID + " integer primary key autoincrement,"
                + HARDWAREINFO_ADDRESS + " varchar(48),"
                + HARDWAREINFO_SCHOOLID + "  varchar(20),"
                + HARDWAREINFO_SCHOOLNAME + " varchar(20),"
                + HARDWAREINFO_FLAG + " INTEGER"
                + " );";
        //软件信息表
        String createsoftinfo = "create table " + TABLE_SOFTINFO
                + "("
                + SOFTINFO_ID + " integer primary key autoincrement,"
                + SOFTINFO_VERSION + " varchar(20),"
                + SOFTINFO_CLAZZ + " varchar(20),"
                + SOFTINFO_GRADE + " varchar(20),"
                + SOFTINFO_SCHOOLID + " varchar(20),"
                + SOFTINFO_FLAG + " INTEGER"
                + " );";
        //用户信息
        String createuserinfo = "create table " + TABLE_USERINFO
                + "("
                + USERINFO_ID + " integer primary key autoincrement,"
                + USERINFO_USERID + " varchar(20),"
                + USERINFO_USERCODE + "  varchar(20),"
                + USERINFO_USERNAME + "  varchar(20),"
                + USERINFO_USERHEIGHT + "  varchar(20),"
                + USERINFO_USERWEIGHT + "  varchar(20),"
                + USERINFO_CLAZZ + "  varchar(20),"
                + USERINFO_GRADE + "  varchar(20)"
                + " );";
        //体温数据
        String createtemperture = "create table " + TABLE_TEMPERATURE
                + "("
                + TEMPERATURE_ID + " integer primary key autoincrement,"
                + TEMPERATURE_USERCODE + "  INTEGER,"
                + TEMPERATURE_DATE + "  INTEGER,"
                + TEMPERATURE_DATA + "  varchar(20),"
                + TEMPERATURE_HIGHT + "  varchar(20),"
                + TEMPERATURE_LOW + "  varchar(20)"
                + " );";

        //运动数据
        String createsportdata = "create table " + TABLE_SPORTDATA
                + "("
                + SPORTDATA_ID + " integer primary key autoincrement,"
                + SPORTDATA_USERCODE + " INTEGER,"
                + SPORTDATA_DATE + " INTEGER,"
                + SPORTDATA_SPORTS_DATA + " varchar(20)"
                + " );";
        //考勤
        String createattendance = "create table " + TABLE_ATTENDANCE
                + "("
                + ATTENDANCE_ID + " integer primary key autoincrement,"
                + ATTENDANCE_USERID + " INTEGER,"
                + ATTENDANCE_USERCODE + " varchar(20),"
                + ATTENDANCE_FLAG + " INTEGER,"
                + ATTENDANCE_FIRST_ONE + "  varchar(20),"
                + ATTENDANCE_FIRST_TWO + "  varchar(20),"
                + ATTENDANCE_SENCOND_ONE + "  varchar(20),"
                + ATTENDANCE_SENCOND_TWO + "  varchar(20),"
                + ATTENDANCE_THIRD_ONE + "  varchar(20),"
                + ATTENDANCE_THIRD_TWO + "  varchar(20)"
                + " );";
        //创建基础数据表
        db.execSQL(createsportadata);
        db.execSQL(createsoftinfo);
        db.execSQL(createuserinfo);
        db.execSQL(createtemperture);
        db.execSQL(createsportdata);
        db.execSQL(createattendance);

        //临时考勤数据表
        String createstempattendance = "create table " + TABLE_TEMPORARY_ATTENDANCE
                + "("
                + TEMPORARY_ATTENDANCE_ID + " integer primary key autoincrement,"
                + TEMPORARY_ATTENDANCE_TOTAL + " varchar(20),"
                + TEMPORARY_ATTENDANCE_AR + "  varchar(20),"
                + TEMPORARY_ATTENDANCE_NO + " varchar(20)"
                + " );";

        //临时运动排名数据表1
        String createstempsportrank = "create table " + TABLE_TEMPORARY_SPORTRANK
                + "("
                + TEMPORARY_SPORTRANK_ID + " integer primary key autoincrement,"
                + TEMPORARY_SPORTRANK_RANK + " varchar(20),"
                + TEMPORARY_SPORTRANK_NAME + "  varchar(20),"
                + TEMPORARY_SPORTRANK_COUNT + " varchar(20),"
                + TEMPORARY_SPORTRANK_USERID + " varchar(20)"
                + " );";
        //临时操行分创建语
        String createstempconductpoint = "create table " + TABLE_TEMPORARY_CONDUCTPOINT
                + "("
                + TEMPORARY_CONDUCTPOINT_ID + " integer primary key autoincrement,"
                + TEMPORARY_CONDUCTPOINT_NAME + "  varchar(20),"
                + TEMPORARY_CONDUCTPOINT_SCORE + "  varchar(20)"
                + " );";
        //临时运动排名数据表2
        String createstempsport8rank = "create table " + TABLE_TEMPORARY_SPORT8RANK
                + "("
                + TEMPORARY_SPORT8RANK_ID + " integer primary key autoincrement,"
                + TEMPORARY_SPORT8RANK_RANK + " varchar(20),"
                + TEMPORARY_SPORT8RANK_NAME + "  varchar(20),"
                + TEMPORARY_SPORT8RANK_COUNT + " varchar(20)"
                + " );";
        //临时班级历史数据运动表
        String createstempclasshistory = "create table " + TABLE_TEMPORARY_CLASSHISTORY
                + "("
                + TEMPORARY_CLASSHISTORY_ID + " integer primary key autoincrement,"
                + TEMPORARY_CLASSHISTORY_DATE + " date,"
                + TEMPORARY_CLASSHISTORY_STEP + " varchar(20)"
                + " );";
        //临时个人历史数据运动表
        String createstempmyhistory = "create table " + TABLE_TEMPORARY_MYHISTORY
                + "("
                + TEMPORARY_MYHISTORY_ID + " integer primary key autoincrement,"
                + TEMPORARY_MYHISTORY_DATE + " date,"
                + TEMPORARY_MYHISTORY_STEP + " varchar(20)"
                + " );";
        //临时荣誉表
        String createstempmyhonour = "create table " + TABLE_TEMPORARY_MYHONOUR
                + "("
                + TEMPORARY_MYHONOUR_ID + " integer primary key autoincrement,"
                + TEMPORARY_MYHONOUR_NAME + " varchar(20),"
                + TEMPORARY_MYHONOUR_TYPEID + " varchar(20),"
                + TEMPORARY_MYHONOUR_COUNT + " varchar(20)"
                + " );";

        //临时活动度
        String createstempactivityrank = "create table " + TABLE_TEMPORARY_ACTIVITYRANK
                + "("
                + TEMPORARY_ACTIVITYRANK_ID + " integer primary key autoincrement,"
                + TEMPORARY_ACTIVITYRANK_RANK + " varchar(20),"
                + TEMPORARY_ACTIVITYRANK_NAME + "  varchar(20),"
                + TEMPORARY_ACTIVITYRANK_COUNT + " varchar(20),"
                + TEMPORARY_ACTIVITYRANK_USERID + " varchar(20)"
                + " );";

        //临时课中简介
        String createstemping = "create table " + TABLE_TEMPORARY_ING
                + "("
                + TEMPORARY_ING_ID + " integer primary key autoincrement,"
                + TEMPORARY_ING_NAME + " varchar(20),"
                + TEMPORARY_ING_TCONTENT + " varchar(20),"
                + TEMPORARY_ING_SCONTENT + "  varchar(20),"
                + TEMPORARY_ING_TEACHERID + "  varchar(20)"
                + " );";
        //临时班级信息
        String createstempclazzinfo = "create table " + TABLE_TEMPORARY_CLAZZINFO
                + "("
                + TTEMPORARY_CLAZZINFO_ID + " integer primary key autoincrement,"
                + TTEMPORARY_CLAZZINFO_NAME + " varchar(20),"
                + TTEMPORARY_CLAZZINFO_TEXT + " varchar(20)"
                + " );";

        db.execSQL(createstempattendance);
        db.execSQL(createstempsportrank);
        db.execSQL(createstempconductpoint);
        db.execSQL(createstempsport8rank);
        db.execSQL(createstempclasshistory);
        db.execSQL(createstempmyhistory);
        db.execSQL(createstempmyhonour);
        db.execSQL(createstempactivityrank);
        db.execSQL(createstemping);
        db.execSQL(createstempclazzinfo);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
