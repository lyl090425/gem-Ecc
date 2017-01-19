package sprotecc.com.example.easyhealth.eh_sprotecc.Communication;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;

import com.ruite.gem.spdtp.HW_HEADER;
import com.ruite.gem.spdtp.SPDTP_COMMAND;

import java.util.Date;
import java.util.HashMap;

import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Model.StandbyModelImpl;

import static java.lang.Thread.sleep;

/**
 * Created by adminHjq on 2017/1/6.
 */
public class UsbInformationOutPut  implements Runnable {
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

    public UsbInformationOutPut() {
        manager = usbCommunication.getUsbManager();
        deviceList = usbCommunication.GetUsbList(manager);
        mUsbDevice = usbCommunication.GetUsbDevice(VID, PID, deviceList);
        endpoint = usbCommunication.getUsbendpiont(mUsbDevice);
        connection = manager.openDevice(mUsbDevice);
    }


    @Override
    public void run() {
        byte[] buffer = new byte[HW_HEADER.HEADER_SIZE];
        HW_HEADER header = new HW_HEADER();
        header.wrap(buffer, 0);
        header.init();
        header.setCommand((short) 0x0010);
        header.setTime(new Date());
        Log.i("数据下发","同步时间");
        connection.bulkTransfer(endpoint[1][0], buffer, buffer.length, 3000);
    }
}
