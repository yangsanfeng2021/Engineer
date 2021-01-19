package cn.ysf.common.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码、解析二维码
 * 二维码，又称二维条码，它是用特定的几何图形按一定规律在平面（二维方向）上分布的黑白相间的图形。
 * 相比一维的条码，二维码能够在横向和纵向两个方位同时表达信息，因此能在很小的面积内表达大量的信息，同时可以有较高的容错能力。
 * 同样的信息编码，二维码的空间利用率高，可容错，易扫描
 * <p>
 * 使用二维码的优势:
 * 高密度编码，信息容量大。
 * 编码范围广。
 * 容错能力强，具有纠错功能。
 * 译码可靠性高。
 * 可引入加密措施。
 * 成本低，易制作，持久耐用。
 */
public class QRCodeUtils {
    private static final String TAG = QRCodeUtils.class.getSimpleName();

    private QRCodeUtils() {
    }

    /**
     * 生成二维码
     * 黑白色块
     *
     * @param content
     * @param width
     * @param height
     * @return
     */
    public static Bitmap createQRImage(String content, int width, int height) {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //容错率，也就是纠错水平，二维码破损一部分也能扫码就归功于容错率，容错率可分为L、 M、 Q、 H四个等级，其分别占比为：L：7% M：15% Q：25% H：35%。传null时，默认使用 “L”
        //当然容错率越高，二维码能存储的内容也随之变小。
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
        //设置空白边距的宽度
        hints.put(EncodeHintType.MARGIN, 1);
        return createQRImage(content, width, height, Color.BLACK, Color.WHITE, hints);
    }

    /**
     * 生成二维码
     * 黑色色块和白素色块，我们常见的二维码一般是黑白两色的，也就是这两个色块，可以自己传入两个颜色，so，彩色二维码不就实现了。
     * 字符集/字符转码格式，通常使用UTF-8，格式不对可能导致乱码。传null时，默认使用 “ISO-8859-1”
     * 位矩阵对象中bitMatrix.get(x, y)方法可判断是黑色色块还是白色色块，根据不同色块给数组元素赋我们传入的颜色值
     *
     * @param content
     * @param width
     * @param height
     * @param colorB  黑色块颜色
     * @param colorW  白色块颜色
     * @param hints   These are a set of hints that you may pass to Writers to specify their behavior.
     * @return
     */
    public static Bitmap createQRImage(String content, int width, int height, int colorB, int colorW, Map<EncodeHintType, Object> hints) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        MultiFormatWriter formatWriter = new MultiFormatWriter();
        try {
            BitMatrix encode = formatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = colorB;
                    } else {
                        pixels[i * width + j] = colorW;
                    }
                }
            }
            //4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成二维码
     * 黑色色块和白素色块，我们常见的二维码一般是黑白两色的，也就是这两个色块，可以自己传入两个颜色，so，彩色二维码不就实现了。
     * 字符集/字符转码格式，通常使用UTF-8，格式不对可能导致乱码。传null时，默认使用 “ISO-8859-1”
     * 位矩阵对象中bitMatrix.get(x, y)方法可判断是黑色色块还是白色色块，根据不同色块给数组元素赋我们传入的颜色值
     *
     * @param content
     * @param width
     * @param height
     * @param colorB  黑色块颜色,尽量选用深色图片，否则影响扫描精度。
     * @param colorW  白色块颜色
     * @return
     */
    public static Bitmap createQRImage(String content, int width, int height, @NonNull Bitmap colorB, int colorW) {
        MultiFormatWriter formatWriter = new MultiFormatWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //容错率，也就是纠错水平，二维码破损一部分也能扫码就归功于容错率，容错率可分为L、 M、 Q、 H四个等级，其分别占比为：L：7% M：15% Q：25% H：35%。传null时，默认使用 “L”
        //当然容错率越高，二维码能存储的内容也随之变小。
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置空白边距的宽度
        hints.put(EncodeHintType.MARGIN, 2);

        //首先通过Bitmap的createScaledBitmap(Bitmap src, int dstWidth, int dstHeight, boolean filter)方法从当前位图，按一定的比例创建一个新的位图，
        //该方法需要传入四个参数，第一个参数就是当前图片，第二个和第三个参数是新位图长宽（这里传入二维码的长宽，保证图的大小一样），最后一个参数直接传false。
        colorB = Bitmap.createScaledBitmap(colorB, width, height, false);

        try {
            BitMatrix encode = formatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        // 黑色色块像素设置
                        pixels[i * width + j] = colorB.getPixel(j, i);
                    } else {
                        // 白色色块像素设置
                        pixels[i * width + j] = colorW;
                    }
                }
            }
            //4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向二维码中间添加logo图片(图片合成)
     *
     * @param srcBitmap  原图片（生成的简单二维码图片）
     * @param logoBitmap logo图片
     * @return
     */
    public static Bitmap addLogo(@NonNull Bitmap srcBitmap, @NonNull Bitmap logoBitmap) {
        return addLogoRect(srcBitmap, logoBitmap, 0.2F, 36, 36);
    }

    /**
     * 向二维码中间添加logo图片(图片合成)
     *
     * @param qrCode      原图片（生成的简单二维码图片）
     * @param logo        logo图片
     * @param logoPercent 百分比 (用于调整logo图片在原图片中的显示大小, 取值范围[0,1] )
     * @param rx          The x-radius of the oval used to round the corners
     * @param ry          The y-radius of the oval used to round the corners
     * @return
     */
    public static Bitmap addLogoRect(Bitmap qrCode, Bitmap logo, @FloatRange(from = 0.0, to = 1.0) float logoPercent, float rx, float ry) {
        if (qrCode == null || logo == null) {
            return null;
        }
        //获取图片的宽高
        int srcWidth = qrCode.getWidth();
        int srcHeight = qrCode.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0 || logoWidth == 0 || logoHeight == 0)
            return qrCode;

        logo = cutToSquareBitmap(logo, Math.min(logoWidth, logoHeight));//裁剪
        logoWidth = logo.getWidth();
        logoHeight = logo.getHeight();

        int logoLeft = (srcWidth - logoWidth) / 2;//Logo位置
        int logoTop = (srcHeight - logoHeight) / 2;
        int logoBorderSize = (int) (3 / logoPercent);//logo边框
        //2. 计算画布缩放的宽高比
        float scaleWidth = srcWidth * logoPercent / logoWidth;
        float scaleHeight = srcHeight * logoPercent / logoHeight;

        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(qrCode, 0, 0, null);
            canvas.scale(scaleWidth, scaleHeight, srcWidth / 2, srcHeight / 2);//缩放

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setAntiAlias(true);
            paint.setColor(Color.WHITE);
            int borderLeft = logoLeft - logoBorderSize;
            int borderRight = logoLeft + logoWidth + logoBorderSize;
            int borderTop = logoTop - logoBorderSize;
            int borderBottom = logoTop + logoHeight + logoBorderSize;
            canvas.drawRoundRect(new RectF(borderLeft, borderTop, borderRight, borderBottom), rx, ry, paint);
            canvas.drawBitmap(logo, logoLeft, logoTop, null);

            canvas.save();
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }
        return bitmap;
    }

    /**
     * 裁剪为正方形位图
     *
     * @param bitmap
     * @param reqLength 希望取得的长度
     * @return
     */
    private static Bitmap cutToSquareBitmap(@NonNull Bitmap bitmap, int reqLength) {
        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();

        if (widthOrg >= reqLength && heightOrg >= reqLength) {
            //压缩到一个最小长度是edgeLength的bitmap
            int longerEdge = reqLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg);
            int scaledWidth = widthOrg > heightOrg ? longerEdge : reqLength;
            int scaledHeight = widthOrg > heightOrg ? reqLength : longerEdge;
            Bitmap scaledBitmap;

            try {
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            } catch (Exception e) {
                return null;
            }

            //从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - reqLength) / 2;
            int yTopLeft = (scaledHeight - reqLength) / 2;

            try {
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, reqLength, reqLength);
                scaledBitmap.recycle();
            } catch (Exception e) {
                return null;
            }
        }

        return result;
    }

    /**
     * 生成条形码
     *
     * @param contents
     * @param desiredWidth
     * @param desiredHeight
     * @return
     */
    public static Bitmap createBarCode(String contents, int desiredWidth, int desiredHeight) {
        MultiFormatWriter formatWriter = new MultiFormatWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //容错率，也就是纠错水平，二维码破损一部分也能扫码就归功于容错率，容错率可分为L、 M、 Q、 H四个等级，其分别占比为：L：7% M：15% Q：25% H：35%。传null时，默认使用 “L”
        //当然容错率越高，二维码能存储的内容也随之变小。
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置空白边距的宽度
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            BitMatrix result = formatWriter.encode(contents, BarcodeFormat.CODE_128, desiredWidth, desiredHeight, hints);
            int width = result.getWidth();
            int height = result.getHeight();
            int[] pixels = new int[width * height];
            // All are 0, or black, by default
            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = result.get(x, y) ? 0xff000000 : 0xFFFFFFFF;
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析图片二维码
     *
     * @param qrCodeImg
     * @return
     */
    public static String parseQRCode(@NonNull Bitmap qrCodeImg) {
        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        hints.put(DecodeHintType.POSSIBLE_FORMATS, BarcodeFormat.QR_CODE);

        try {
            Result result = new QRCodeReader().decode(Bitmap2BinaryBitmap(qrCodeImg), hints);
            return result.getText();
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * Bitmap2BinaryBitmap
     *
     * @param bitmap
     * @return
     */
    private static BinaryBitmap Bitmap2BinaryBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int[] pixels = new int[width * height];
        //获取像素
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
        return new BinaryBitmap(new HybridBinarizer(source));
    }
}
