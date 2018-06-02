package com.yaoding.util;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: yaodingw
 * Date: 2018-06-02
 * Time: 23:10
 */

public class QRResult {
    public QRResult(String message,int status) {
        this.message = message;
        this.status = status;
        this.txt = "";
    }
    public QRResult(String message,int status,String txt) {
        this.message = message;
        this.status = status;
        this.txt = txt;
    }
    //解码内容
    private String txt;
    //返回的消息内容
    private String message;
    //返回的状态码，200：成功，500：错误
    private int status ;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getTxt() {
        return txt;
    }
    public void setTxt(String txt) {
        this.txt = txt;
    }
}
