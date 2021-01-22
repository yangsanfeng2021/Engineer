package cn.ysf.common.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    /**
     * 检索HTML img标签
     */
    public static final String REGEX_IMAGE_LABEL = "<img.*src=(.*?)[^>]*?>";
    /**
     * 检索HTML img标签src属性
     * public static final String REGEX_IMAGE_URL = "https:\"?(.*?)(\"|>|\\s+)";
     */
    public static final String REGEX_IMAGE_URL = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";

    public static final String REGEX_IMAGE_URL2 = "https:\"?(.*?)(\"|>|\\s+)";

    /**
     * 从HTML检索图片地址
     */
    public static final Pattern PATTERN_IMAGE_URL = Pattern.compile(REGEX_IMAGE_URL, Pattern.UNIX_LINES);

    public static List<String> find(Pattern pattern, CharSequence content, int g) {
        if (pattern == null || TextUtils.isEmpty(content)) {
            return null;
        }
        List<String> list = new ArrayList<>();
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            list.add(matcher.group(g));
        }
        return list;
    }
}
