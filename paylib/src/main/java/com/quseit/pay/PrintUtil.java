package com.quseit.pay;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.landicorp.android.eptapi.DeviceService;
import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.ReloginException;
import com.landicorp.android.eptapi.exception.RequestException;
import com.landicorp.android.eptapi.exception.ServiceOccupiedException;
import com.landicorp.android.eptapi.exception.UnsupportMultiProcess;
import com.landicorp.android.eptapi.utils.QrCode;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 文 件 名: PrintUtil
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 18:27
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class PrintUtil {
    private boolean loginSuccess;

    public PrintUtil(){

    }

    public void deviceLogin(Context context){
        try {
            DeviceService.login(context);
            loginSuccess = true;
        } catch (ServiceOccupiedException | RequestException | UnsupportMultiProcess | ReloginException e) {
            e.printStackTrace();
            loginSuccess = false;
        }
    }

    public boolean isLogined(){
        return loginSuccess;
    }

    public void print(final Context context,final String code,final int count){
        /** 1、创建 Printer.Progress 实例 */
        Printer.Progress progress = new Printer.Progress() {
            /** 2、在 Printer.Progress 的 doPrint 方法中设置签购单的打印样式 */
            @Override
            public void doPrint(Printer printer) throws Exception {

                /** 设置打印格式 */
                Printer.Format format = new Printer.Format();

                /** 中文字符打印，此处使用 16x16 点，1 倍宽&&1 倍高 */
                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(Printer.Format.HZ_DOT16x16);

                for (int i = 0; i < count; i++) {
                    /** 打印二维码 */
                    printer.printQrCode(Printer.Alignment.CENTER, new QrCode(code,
                            QrCode.ECLEVEL_Q), 300);
                    printer.printText(Printer.Alignment.CENTER, code+"\n");
                    printer.printText("\n");
                    String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
                    printer.printText(Printer.Alignment.CENTER, dateStr+"\n");

                    /**进纸5行 */
                    printer.feedLine(5);
                }

            }

            @Override
            public void onFinish(int code) {

                if (code == Printer.ERROR_NONE) {
                    Toast.makeText(context,"print success!",Toast.LENGTH_SHORT).show();
                    Log.d("print", "打印签购单成功!");
                } else {
                    Log.d("print", "[打印失败]" + getErrorDescription(code));
                    Toast.makeText(context,getErrorDescription(code),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCrash() {
                onDeviceServiceCrash();
            }
        };

        /** 3、启动打印 */
        try {
            progress.start();
        } catch (RequestException e) {
            e.printStackTrace();
            onDeviceServiceCrash();
        }
    }

    /** 根据错误码获取相应错误提示 * @param code 错误码
     * @return 错误提示
     */
    public String getErrorDescription(int code) {
        switch (code) {
            case Printer.ERROR_PAPERENDED:
                return "Paper-out, the operation is invalid this time";
            case Printer.ERROR_HARDERR:
                return "Hardware fault, can not find HP signal";
            case Printer.ERROR_OVERHEAT:
                return "Overheat";
            case Printer.ERROR_BUFOVERFLOW:
                return "The operation buffer mode position is out of range";
            case Printer.ERROR_LOWVOL:
                return "Low voltage protect";
            case Printer.ERROR_PAPERENDING:
                return "Paper-out, permit the latter operation";
            case Printer.ERROR_MOTORERR:
                return "The printer core fault (too fast or too slow)";
            case Printer.ERROR_PENOFOUND:
                return "Automatic positioning did not find the alignment position, the paper back to its original position";
            case Printer.ERROR_PAPERJAM:
                return "paper got jammed";
            case Printer.ERROR_NOBM:
                return "Black mark not found";
            case Printer.ERROR_BUSY:
                return "The printer is busy";
            case Printer.ERROR_BMBLACK:
                return "Black label detection to black signal";
            case Printer.ERROR_WORKON:
                return "The printer power is open";
            case Printer.ERROR_LIFTHEAD:
                return "Printer head lift";
            case Printer.ERROR_LOWTEMP:
                return "Low temperature protect";
        }
        return "unknown error (" + code + ")";
    }

    public void printQrcode(final Context context, final List<String> codes){
        /** 1、创建 Printer.Progress 实例 */
        Printer.Progress progress = new Printer.Progress() {
            /** 2、在 Printer.Progress 的 doPrint 方法中设置签购单的打印样式 */
            @Override
            public void doPrint(Printer printer) throws Exception {

                /** 设置打印格式 */
                Printer.Format format = new Printer.Format();

                /** 中文字符打印，此处使用 16x16 点，1 倍宽&&1 倍高 */
                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(Printer.Format.HZ_DOT16x16);

                for (String code:codes) {
                    /** 打印二维码 */
                    printer.printQrCode(Printer.Alignment.CENTER, new QrCode(code,
                            QrCode.ECLEVEL_Q), 300);
                    printer.printText(Printer.Alignment.CENTER, code+"\n");
                    printer.printText("\n");
                    String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
                    printer.printText(Printer.Alignment.CENTER, dateStr+"\n");

                    /**进纸5行 */
                    printer.feedLine(5);
                }

            }

            @Override
            public void onFinish(int code) {

                if (code == Printer.ERROR_NONE) {
                    Toast.makeText(context,"print success!",Toast.LENGTH_SHORT).show();
                    Log.d("print", "打印签购单成功!");
                } else {
                    Log.d("print", "[打印失败]" + getErrorDescription(code));
                    Toast.makeText(context,getErrorDescription(code),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCrash() {
                onDeviceServiceCrash();
            }
        };

        /** 3、启动打印 */
        try {
            progress.start();
        } catch (RequestException e) {
            e.printStackTrace();
            onDeviceServiceCrash();
        }
    }

    public void logout(){
        DeviceService.logout();
    }

    public void printPayInfo(final Context context, final PayInfoBean info) {
        /** 1、创建 Printer.Progress 实例 */
        Printer.Progress progress = new Printer.Progress() {
            /** 2、在 Printer.Progress 的 doPrint 方法中设置签购单的打印样式 */
            @Override
            public void doPrint(Printer printer) throws Exception {

                /** 设置打印格式 */
                Printer.Format format = new Printer.Format();
                /** 西文字符打印， 此处使用 5x7 点， 1 倍宽&&2 倍高打印签购单标题 */
                format.setAscSize(Printer.Format.ASC_DOT5x7);
                format.setAscScale(Printer.Format.ASC_SC1x2);
                printer.setFormat(format);
                printer.printText(Printer.Alignment.CENTER, info.getStoreName()+"\n");
                printer.printText("\n");

                /** 西文字符打印， 此处使用 5x7 点， 1 倍宽&&1 倍高打印签购单内容 */
                format.setAscScale(Printer.Format.ASC_SC1x1);
                printer.setFormat(format);
                printer.setAutoTrunc(false);
                printer.println("Transaction ID: "+info.getTransactionId());
                printer.println("Date: "+info.getTransactionAt());
                printer.println("Method: "+info.getPaymentMethod());
                printer.println("Status: "+info.getMessage());
                String total = String.format("%.2f",Float.parseFloat(info.getPaymentAmount())/100.0);
                printer.println("Net Total: RM "+total);
                printer.println("PRN ON: "+info.getDate());
                printer.printText("\n");
                printer.printText("--------------Remark------------");
                printer.printText("\n");
                printer.printText(info.getRemark());
                printer.printText("\n");
                /**进纸5行 */
                printer.feedLine(5);
            }

            @Override
            public void onFinish(int code) {

                if (code == Printer.ERROR_NONE) {
                    Log.d("print", "打印签购单成功!");
                } else {
                    Log.d("print", "[打印失败]" + getErrorDescription(code));
                    Toast.makeText(context,getErrorDescription(code),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCrash() {
                onDeviceServiceCrash();
            }
        };

        /** 3、启动打印 */
        try {
            progress.start();
        } catch (RequestException e) {
            e.printStackTrace();
            onDeviceServiceCrash();
        }
    }

    //打印收据
    public void printReceipt(){

    }

    private static void onDeviceServiceCrash() {
        Log.d("onPrintError", "error");
    }

    /**
     * 检查打印机是否能打印
     * */
    public void test(final PrintCallback callback){
        Printer.Progress progress = new Printer.Progress() {
            @Override
            public void doPrint(Printer printer) throws Exception {
                printer.println("");
            }

            @Override
            public void onFinish(int code) {
                if (code == Printer.ERROR_NONE) {
                    callback.onSuccess();
                } else {
                   callback.onError(getErrorDescription(code));
                }
            }

            @Override
            public void onCrash() {
                onDeviceServiceCrash();
            }
        };
        try {
            progress.start();
        } catch (RequestException e) {
            e.printStackTrace();
            onDeviceServiceCrash();
            callback.onError(e.getMessage());
        }
    }

    public interface PrintCallback{
        void onSuccess();
        void onError(String msg);
    }

}
