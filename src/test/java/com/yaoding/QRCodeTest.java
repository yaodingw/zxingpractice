package com.yaoding;

import com.yaoding.util.QRResult;
import com.yaoding.util.QRcode;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: yaodingw
 * Date: 2018-06-02
 * Time: 22:39
 */
public class QRCodeTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testEncode() throws FileNotFoundException, Exception {
        String dir = "C:\\yaodingw\\test/1.jpg";
        String content = "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体这是标准的十五个字体这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体这是标准的十五个字体这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "这是标准的十五个字体" +
                "";
        String logoImgPath = "H:/Users/Administrator/Desktop/logo.png";
        File file = new File(dir);
        QRcode.encode(content, "", new FileOutputStream(file), true);
        byte[] bytetemp=content.getBytes(Charset.forName("utf-8"));
        System.out.println(content.length());
        System.out.println(bytetemp);
        System.out.println(bytetemp.length);
        String contenta=new String(bytetemp,"utf-8");
        System.out.println(contenta);


        System.out.println();
    }

    @Test
    public void decodeTest() throws Exception {
        String dir = "C:\\yaodingw\\test/1.jpg";
//        String content=QRcode.decode(new File(dir));
//        System.out.println(content);
        String result=QRcode.decode(new FileInputStream(new File(dir)));
        System.out.println(result);
    }

}
