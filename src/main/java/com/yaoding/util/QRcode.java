package com.yaoding.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: yaodingw
 * Date: 2018-06-02
 * Time: 22:29
 */
public class QRcode {
    private static final String CHARSET = "UTF-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 400;
    // LOGO宽度
    private static final int WIDTH = 80;
    // LOGO高度
    private static final int HEIGHT = 80;


    private static BufferedImage createImage(String content, String logoImgPath, boolean needCompress) throws WriterException, IOException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (logoImgPath == null || "".equals(logoImgPath)) {
            return image;
        }

        // 插入图片
        QRcode.insertImage(image, logoImgPath, needCompress);
        return image;
    }

    /**
     * @param source
     * @param logoImgPath
     * @param needCompress
     * @throws IOException
     */
    private static void insertImage(BufferedImage source, String logoImgPath, boolean needCompress) throws IOException {
        File file = new File(logoImgPath);
        if (!file.exists()) {
            return;
        }

        Image src = ImageIO.read(new File(logoImgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }

            if (height > HEIGHT) {
                height = HEIGHT;
            }

            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }

        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * @param content
     * @param logoImgPath
     * @param destPath
     * @param needCompress
     * @throws Exception TODO 生成带Logo的二维码
     */
    public static void encode(String content, String logoImgPath, String destPath, boolean needCompress) throws Exception {
        BufferedImage image = QRcode.createImage(content, logoImgPath, needCompress);
        FileUtil.mkdirs(destPath);
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }

    /**
     * @param content
     * @param destPath
     * @throws Exception 生成不带logo的二维码
     */
    public static void encode(String content, String destPath) throws Exception {
        QRcode.encode(content, null, destPath, false);
    }

    /**
     * @param content
     * @param logoImgPath
     * @param output
     * @param needCompress
     * @throws Exception TODO 生成带Logo的二维码，并输出到指定的输出流
     */
    public static void encode(String content, String logoImgPath, OutputStream output, boolean needCompress) throws Exception {
        BufferedImage image = QRcode.createImage(content, logoImgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * @param content
     * @param output
     * @throws Exception TODO 生成不带Logo的二维码，并输出到指定的输出流
     */

    public static void encode(String content, OutputStream output) throws Exception {
        QRcode.encode(content, null, output, false);
    }

    /**
     * TODO 二维码解析
     *
     * @param file
     * @return
     * @throws Exception
     */

    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * @param path
     * @return
     * @throws Exception
     */
    public static String decode(String path) throws Exception {
        return QRcode.decode(new File(path));
    }

    public static String decode(InputStream input) {
        BufferedImage image;
        try {

            image = ImageIO.read(input);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Map<DecodeHintType, Object> hints = new LinkedHashMap<DecodeHintType, Object>();
            // 解码设置编码方式为：utf-8，
            hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
//优化精度
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
//复杂模式，开启PURE_BARCODE模式
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            Result result = new MultiFormatReader().decode(bitmap, hints);
            String txt = result.getText();
            return txt;
        } catch (Exception e) {
            return "error";
        }
    }


}
