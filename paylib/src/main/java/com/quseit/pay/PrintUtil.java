package com.quseit.pay;

import android.content.Context;
import android.util.Log;

import com.landicorp.android.eptapi.DeviceService;
import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.ReloginException;
import com.landicorp.android.eptapi.exception.RequestException;
import com.landicorp.android.eptapi.exception.ServiceOccupiedException;
import com.landicorp.android.eptapi.exception.UnsupportMultiProcess;
import com.landicorp.android.eptapi.utils.QrCode;

import java.io.InputStream;

/**
 * 文 件 名: PrintUtil
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 18:27
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class PrintUtil {
    public static void print(final Context context, String content) {
        Printer.Progress progress = new Printer.Progress() {
            @Override
            public void doPrint(Printer printer) throws Exception {
                Printer.Format format = new Printer.Format();
                /** 打印图片 */
//                InputStream is = context.getAssets().open("landi.bmp");
//                try {
//                    printer.printImage(Printer.Alignment.CENTER, is);
//                } finally {
//                    is.close();
//                }
                /** 西文字符打印， 此处使用 5x7 点， 1 倍宽&&2 倍高打印签购单标题 */
                format.setAscSize(Printer.Format.ASC_DOT5x7);
                format.setAscScale(Printer.Format.ASC_SC1x2);
                printer.setFormat(format);
                printer.printText(Printer.Alignment.CENTER, "联迪支付\n");

//                printer.printQrCode(0, new QrCode("sdafsadf", QrCode.ECLEVEL_Q), 100);
//                printer.printQrCode(Printer.Alignment.CENTER, new QrCode("landi",
//                        QrCode.ECLEVEL_Q), 124);
//                printer.printQrCode(Printer.Alignment.RIGHT, new QrCode("landi",
//                        QrCode.ECLEVEL_Q), 124);
//                printer.printText(Printer.Alignment.CENTER, "------landicorp------\n");
//                printer.printText(Printer.Alignment.RIGHT, "www.landicorp.com\n");
                /**进纸5行 */
                printer.feedLine(5);
            }

            @Override
            public void onFinish(int i) {
                /** Printer.ERROR_NONE 即打印成功 */
                if (i == Printer.ERROR_NONE) {
                    Log.d("print", "error:" + i);
                } else {
                    //showMessage("[打印失败]" + getErrorDescription(code));
                    Log.d("print", "error:" + i);
                }
            }

            @Override
            public void onCrash() {

            }
        };

        try {
            progress.start();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

    public static void print(Context context){
        try {
            DeviceService.login(context);
        } catch (ServiceOccupiedException e) {
            e.printStackTrace();
        } catch (ReloginException e) {
            e.printStackTrace();
        } catch (UnsupportMultiProcess unsupportMultiProcess) {
            unsupportMultiProcess.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
/** 1、创建 Printer.Progress 实例 */ Printer.Progress progress = new Printer.Progress() {
            /** 2、在 Printer.Progress 的 doPrint 方法中设置签购单的打印样式 */ @Override
            public void doPrint(Printer printer) throws Exception {
/** 设置打印格式 */
                Printer.Format format = new Printer.Format();
/** 西文字符打印， 此处使用 5x7 点， 1 倍宽&&2 倍高打印签购单标题 */ format.setAscSize(Printer.Format.ASC_DOT5x7); format.setAscScale(Printer.Format.ASC_SC1x2);
                printer.setFormat(format);
                printer.printText(Printer.Alignment.CENTER, "联迪支付\n");
/** 西文字符打印， 此处使用 5x7 点， 1 倍宽&&1 倍高打印签购单内容 */ format.setAscScale(Printer.Format.ASC_SC1x1);
                printer.setFormat(format);
                printer.printText("--Public utility bill payment receipt--\n"); printer.printText("\n");
                printer.printText("Transaction : Repayment\n"); printer.printText("Credit Card No.: XXXX XXXX XXXX XXXX\n"); printer.printText("Term No.: 2200306\n"); printer.printText("Amount: RMB 100.00\n"); printer.printText("Reference No.: 191017234668\n");
                printer.printText("\n"); printer.printText("---The Client Stub---\n");
/** 中文字符打印，此处使用 16x16 点，1 倍宽&&1 倍高 */ format.setHzScale(Printer.Format.HZ_SC1x1); format.setHzSize(Printer.Format.HZ_DOT16x16); printer.printText("---福建联迪商用设备有限公司---\n"); printer.printText("\n");
/** 打印条码 */ printer.printBarCode("01234567890123456789");
/** 打印二维码 */
                printer.printQrCode(0, new QrCode("sdafsadf", QrCode.ECLEVEL_Q), 100); printer.printQrCode(Printer.Alignment.CENTER, new QrCode("landi",
                        QrCode.ECLEVEL_Q), 124); printer.printQrCode(Printer.Alignment.RIGHT, new QrCode("landi",
                        QrCode.ECLEVEL_Q), 124); printer.printText(Printer.Alignment.CENTER, "------landicorp------\n");
                printer.printText(Printer.Alignment.RIGHT, "www.landicorp.com\n");
/**进纸5行 */
                printer.feedLine(5); }
            @Override
            public void onFinish(int code) {
/** Printer.ERROR_NONE 即打印成功 */ if (code == Printer.ERROR_NONE) {
                    Log.d("print","打印签购单成功!"); }
                else { Log.d("print","[打印失败]"+getErrorDescription(code));
                } }
            /** 根据错误码获取相应错误提示 * @param code 错误码
             * @return 错误提示
             */
            public String getErrorDescription(int code) { switch(code) {
                case Printer.ERROR_PAPERENDED: return "Paper-out, the operation is invalid this time";
                case Printer.ERROR_HARDERR: return "Hardware fault, can not find HP signal"; case Printer.ERROR_OVERHEAT: return "Overheat";
                case Printer.ERROR_BUFOVERFLOW: return "The operation buffer mode position is out of range";
                case Printer.ERROR_LOWVOL: return "Low voltage protect";
                case Printer.ERROR_PAPERENDING: return "Paper-out, permit the latter operation";
                case Printer.ERROR_MOTORERR: return "The printer core fault (too fast or too slow)";
                case Printer.ERROR_PENOFOUND: return "Automatic positioning did not find the alignment position, the paper back to its original position"; case Printer.ERROR_PAPERJAM: return "paper got jammed";
                case Printer.ERROR_NOBM: return "Black mark not found";
                case Printer.ERROR_BUSY: return "The printer is busy";
                case Printer.ERROR_BMBLACK: return "Black label detection to black signal";
                case Printer.ERROR_WORKON: return "The printer power is open";
                case Printer.ERROR_LIFTHEAD: return "Printer head lift";
                case Printer.ERROR_LOWTEMP: return "Low temperature protect"; }
                return "unknown error ("+code+")";
            }
            @Override
            public void onCrash() { onDeviceServiceCrash();
            } };
/** 3、启动打印 */ try {
            progress.start();
        } catch (RequestException e) {
            e.printStackTrace();
            onDeviceServiceCrash(); }
    }

    private static void onDeviceServiceCrash() {
        Log.d("onPrintError","error");
    }


}
