package cn.ysf.common.util;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;

import java.util.Random;

import cn.ysf.common.R;

/**
 * 生产随机数
 */
public class RandomUtils {

    private RandomUtils() {}

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param len 随机字符串长度
     * @return
     */
    public static String randomS(int len) {
        String allChar = UtilsInit.getInstance().getContext().getString(R.string.all_char);
        return randomC(len, allChar);
    }

    /**
     * 从特定元字符串生成指定长度的随机字符串
     *
     * @param len      目标字符串长度
     * @param baseChar 元字符串
     * @return
     */
    public static String randomC(int len, String baseChar) {
        if (len <= 0 || TextUtils.isEmpty(baseChar)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(baseChar.charAt(random.nextInt(baseChar.length())));
        }
        return sb.toString();
    }

    /**
     * 如：100000到999999的随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int randomRange(int min, int max) {
        return new Random().nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 生产验证码Bitmap
     *
     * @param code
     * @param width
     * @param height
     * @return
     */
    public static Bitmap bitmapCode(String code, int width, int height) {
        if (TextUtils.isEmpty(code)) {
            throw new IllegalArgumentException("code为空");
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.rgb(0xDF, 0xDF, 0xDF));
        Paint paint = new Paint();
        paint.setTextSize((float) (height * 0.4));

        int mPaddingLeft = 0, mPaddingTop;
        Random mRandom = new Random(System.currentTimeMillis());
        //画验证码
        for (int i = 0; i < code.length(); i++) {
            paint.setColor(getTextRandomColor(mRandom));
            mPaddingLeft += width * 0.12 + mRandom.nextInt(20);
            mPaddingTop = (int) (height * 0.6 + mRandom.nextInt(20));
            canvas.drawText(code.charAt(i) + "", mPaddingLeft, mPaddingTop, paint);
        }
        //画三条干扰线，颜色和位置也可以提前初始化
        for (int i = 0; i < 3; i++) {
            paint.setStrokeWidth(1);
            paint.setColor(getTextRandomColor(mRandom));

            int startX = mRandom.nextInt(width);
            int startY = mRandom.nextInt(height);
            int stopX = mRandom.nextInt(width);
            int stopY = mRandom.nextInt(height);
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
        //画20个干扰点
        for (int i = 0; i < 20; i++) {
            paint.setColor(getTextRandomColor(mRandom));
            canvas.drawPoint(mRandom.nextInt(width), mRandom.nextInt(height), paint);
        }
        canvas.save();
        canvas.restore();
        return bitmap;
    }

    /**
     * 文本色随机
     *
     * @return
     */
    private static int getTextRandomColor(Random mRandom) {
        int r = mRandom.nextInt(90) + 40;
        int g = mRandom.nextInt(90) + 40;
        int b = mRandom.nextInt(90) + 40;
        return Color.rgb(r, g, b);
    }
}
