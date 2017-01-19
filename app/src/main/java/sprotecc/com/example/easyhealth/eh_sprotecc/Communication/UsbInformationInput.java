package sprotecc.com.example.easyhealth.eh_sprotecc.Communication;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;

import java.util.HashMap;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.UsbCommunicationManager;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Model.StandbyModelImpl;

import static java.lang.Thread.sleep;

/**
 * 接收来自USB数据线信息的线程
 * Created by adminHjq on 2016/12/9.
 */
public class UsbInformationInput implements Runnable {
    public UsbCommunicationManager usbCommunication = new UsbCommunicationManager();
    //设备列表
    private HashMap<String, UsbDevice> deviceList;
    //USB管理器:负责管理USB设备的类
    private UsbManager manager;
    //找到的USB设备
    private UsbDevice mUsbDevice;
    //代表所有接口的所有节点
    private UsbEndpoint[][] endpoint = new UsbEndpoint[16][16];
    //
    private UsbDeviceConnection connection = null;
    //
    private UsbInterface[] usbinterface = null;
    //设备号
    private int VID = 1155;
    private int PID = 53456;
    //
    public byte[] mybuffer = new byte[2048];
    public StandbyModelImpl standbyModel1;

    public UsbInformationInput(StandbyModelImpl standbyModel1) {
        manager = usbCommunication.getUsbManager();
        deviceList = usbCommunication.GetUsbList(manager);
        mUsbDevice = usbCommunication.GetUsbDevice(VID, PID, deviceList);
        endpoint = usbCommunication.getUsbendpiont(mUsbDevice);
        connection = manager.openDevice(mUsbDevice);
        MyApplication.getInstance().setConnection(connection);
        MyApplication.getInstance().setEndpoint(endpoint);
        this.standbyModel1 = standbyModel1;
    }


    @Override
    public void run() {
        Log.i("测试", "USB线程开启");
        int datalength;
        while (true) {//程序死亡之前永不死机
            try {
                //延迟100ms接收，减少消耗，100ms不影响刷卡体验
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //mybuffer为接收到的数组
            if(connection!=null){
            datalength = connection.bulkTransfer(endpoint[1][1], mybuffer, 2048, 30);
                if(datalength>32){
                    //回调，将mybuffe注入
                    standbyModel1.callback(mybuffer);
                }
            }


        }
    }
}


