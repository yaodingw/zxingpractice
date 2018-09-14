package com.yaoding;

import com.yaoding.util.JsonUtil;
import com.yaoding.util.QRResult;
import com.yaoding.util.QRcode;
import com.yaoding.vo.Profile;
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
        String content=JsonUtil.toJson(Profile.create());

        String dir = "C:/yaodingw/test/profile.jpg";
        String logoImgPath = "C:/yaodingw/test/profile.png";
        File file = new File(dir);
        QRcode.encode(content, logoImgPath, new FileOutputStream(file), true);

        System.out.println();
    }

    @Test
    public void decodeTest() throws Exception {
        String dir = "C:/yaodingw/test/profile.jpg";
        String result=QRcode.decode(new FileInputStream(new File(dir)));
        System.out.println(result);
    }

}
