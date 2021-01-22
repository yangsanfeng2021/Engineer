package cn.ysf.common.model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import cn.ysf.common.activity.PreviewImageActivity;

public class JsInterface {

    private final Context context;
//    private final ArrayList<String> imgList = new ArrayList<>();

    public JsInterface(Context context) {
        this.context = context;
    }

   /* @JavascriptInterface
    public void showSource(String html) {
        imgList = RegexUtils.find(RegexUtils.PATTERN_IMAGE_URL, html, 1);
    }*/

   /* @JavascriptInterface
    public void showSource(String htmlStr) {
       // List<String> pics = new ArrayList<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
//            Matcher m = Pattern.compile("img src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                imgList.add(m.group(1));
            }
        }
    }*/

    @JavascriptInterface
    public void open(String[] imgList, String url) {
        if (imgList == null || imgList.length == 0) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(PreviewImageActivity.PREVIEW_IMAGE_CURRENT_URL, url);
        bundle.putStringArrayList(PreviewImageActivity.PREVIEW_IMAGE_ALL_URL, new ArrayList<>(Arrays.asList(imgList)));
        PreviewImageActivity.startActivity(context, bundle);
    }

    @JavascriptInterface
    public void onLongClick(String url) {
        Toast.makeText(context, "长按图片啦", Toast.LENGTH_LONG).show();
    }
}
