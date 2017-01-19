package sprotecc.com.example.easyhealth.eh_sprotecc.Communication;


import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;

/**
 * 1、UsbManager:获得Usb的状态，与连接的Usb设备通信。
 * 2、UsbDevice：Usb设备的抽象，它包含一个或多个UsbInterface，而每个UsbInterface包含多个UsbEndpoint。Host与其通信，先打开UsbDeviceConnection，使用UsbRequest在一个端点（endpoint）发送和接收数据。
 * 3、UsbInterface：定义了设备的功能集，一个UsbDevice包含多个UsbInterface，每个Interface都是独立的。
 * 4、UsbEndpoint：endpoint是interface的通信通道。
 * 5、UsbDeviceConnection：host与device建立的连接，并在endpoint传输数据。
 * 6、UsbRequest：usb 请求包。可以在UsbDeviceConnection上异步传输数据。注意是只在异步通信时才会用到它。
 * 7、UsbConstants：usb常量的定义，对应Linux/usb/ch9.h
 * Created by adminHjq on 2016/12/9.
 */
public class UsbCommunicationManager {
    //USB广播


    /**
     * 获取USBmanager
     *
     * @return
     */
    public UsbManager getUsbManager() {
        UsbManager manager = (UsbManager) MyApplication.getContext().getSystemService(MyApplication.getContext().USB_SERVICE);
        return manager;
    }

    /**
     * 获取设备列表
     *
     * @param manager
     * @return
     */
    public HashMap<String, UsbDevice> GetUsbList(UsbManager manager) {
        Log.i("测试", "找的设备数量：" + manager.getDeviceList().size() + "");
        return manager.getDeviceList();
    }

    /**
     * 获取指定设备。
     *
     * @param pid
     * @param vid
     * @return
     */
    public UsbDevice GetUsbDevice(int vid, int pid, HashMap<String, UsbDevice> deviceList) {
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        UsbDevice device = null;
        int i = 1;
        while (deviceIterator.hasNext()) {
            device = deviceIterator.next();
            Log.i("测试", "遍历设备第" + i + "次：" + "vid=" + device.getVendorId() + "     pid=" + device.getProductId());
            i++;
            if (device.getVendorId() == vid && device.getProductId() == pid) {
                //返回指定的设备
                Log.i("测试", "找到合适的设备");
                return device;
            }
        }
        //没有插入设备或者没有插入指定设备
        Log.i("测试", "没有找到合适的设备");
        return null;
    }

    /**
     * @param device
     * @return
     */
    public UsbEndpoint[][] getUsbendpiont(UsbDevice device) {
        if (device == null) {
            return null;
        } else {
            UsbInterface[] usbinterface;
            UsbEndpoint[][] endpoint = new UsbEndpoint[16][16];//每个USB设备最多16个端点,这个二维数组存放第几个接口的第几个端点
            usbinterface = new UsbInterface[device.getInterfaceCount()];
            Log.i("测试", "设备接口数："+device.getInterfaceCount());

            //遍历每个接口的端点，存入usbEndpiont[][]
            for (int i = 0; i < device.getInterfaceCount(); i++) {
                usbinterface[i] = device.getInterface(i);
                for (int j = 0; j < usbinterface[i].getEndpointCount(); j++) {
                    endpoint[i][j] = usbinterface[i].getEndpoint(j);
                }

            }
            return endpoint;
        }
    }

    public UsbInterface[] getUsbInterface(UsbDevice device) {

            UsbInterface[] usbinterface;
            usbinterface = new UsbInterface[device.getInterfaceCount()];

            //遍历每个接口
            for (int i = 0; i < device.getInterfaceCount(); i++) {
                usbinterface[i] = device.getInterface(i);
            }
            return usbinterface;

    }


}
