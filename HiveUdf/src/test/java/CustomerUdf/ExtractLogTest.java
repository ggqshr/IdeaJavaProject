package CustomerUdf;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static CustomerUdf.UtilBean.TimeStamp2Date;


public class ExtractLogTest {
    ExtractLog extractLog = new ExtractLog();

    @Test
    public void evaluate() throws UnsupportedEncodingException {
        /*
                           119.115.192.156
                          1550564211.912
                          120.79.2.76
                          /log.gif?
                          en=e_pv&
                          ver=1&
                          pl=pc&
                          sdk=js&
                          uid=1B5E2747-7B95-4BA0-8A9F-9EBB618E6DB9&
                          sid=C9CCDD83-9B5C-4E0E-9541-C4B3F587972C&
                          b_rst=1366*768&
                          b_usa=Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20WOW64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F67.0.3396.99%20Safari%2F537.36&
                          l=zh-CN&
                          ct=1550564214651&
                          url=http%3A%2F%2Flocalhost%3A8080%2F%23%2Fhome%3F_k%3D65i5zc&
                          ref=http%3A%2F%2Flocalhost%3A8080%2F&
                          tt=React%20o2o%20demo
         */
        String line = "119.115.192.156 1550564211.912 120.79.2.76 /log.gif?en=e_pv&ver=1&pl=pc&sdk=js&uid=1B5E2747-7B95-4BA0-8A9F-9EBB618E6DB9&sid=C9CCDD83-9B5C-4E0E-9541-C4B3F587972C&b_rst=1366*768&b_usa=Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20WOW64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F67.0.3396.99%20Safari%2F537.36&l=zh-CN&ct=1550564214651&url=http%3A%2F%2Flocalhost%3A8080%2F%23%2Fhome%3F_k%3D65i5zc&ref=http%3A%2F%2Flocalhost%3A8080%2F&tt=React%20o2o%20demo";
        HashMap<String, String> ss = UtilBean.converline2field(line);
        ss.forEach((k, v) -> {
            System.out.println(k + "@@" + v);
        });
//        converline2field(line);
    }


    @Test
    public void testformat() throws UnsupportedEncodingException {
        String[] fieldName = {
                "eventName",
                "version",
                "platform",
                "sdk",
                "uuid",
                "sessionId",
                "memberId",
                "resolution",
                "userAgent",
                "language",
                "clientTime",
                "currentUrl",
                "referrerUrl",
                "title"
        };

        Arrays.asList(Arrays.copyOfRange(fieldName, 0, 1)).forEach(System.out::println);

    }

    String line = "183.209.148.168 1551082999.508 120.79.2.76 /log.gif?en=e_pv&ver=1&pl=pc&sdk=js&uid=A749FB5B-A097-4C9B-B2B0-DD530172B361&sid=D0376A3A-6BD5-4860-AEC1-061801AE709E&b_rst=1536*864&b_usa=Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20Win64%3B%20x64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F69.0.3497.81%20Safari%2F537.36&l=zh-CN&ct=1551082997632&url=http%3A%2F%2Flocalhost%3A8080%2Ftracker%2Ftest04.html&ref=http%3A%2F%2Flocalhost%3A8080%2Ftracker%2Ftest02.html&tt=lzh";

    @Test
    public void testLine() {
        String line = "119.115.192.156 1550564211.912 120.79.2.76 /log.gif?en=e_pv&ver=1&pl=pc&sdk=js&uid=1B5E2747-7B95-4BA0-8A9F-9EBB618E6DB9&sid=C9CCDD83-9B5C-4E0E-9541-C4B3F587972C&b_rst=1366*768&b_usa=Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20WOW64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F67.0.3396.99%20Safari%2F537.36&l=zh-CN&ct=1550564214651&url=http%3A%2F%2Flocalhost%3A8080%2F%23%2Fhome%3F_k%3D65i5zc&ref=http%3A%2F%2Flocalhost%3A8080%2F&tt=React%20o2o%20demo";
        Pattern compile = Pattern.compile("&tt=(.+?)$");
        Matcher matcher = compile.matcher(line);
        matcher.find();
        System.out.println(matcher.group(1));
    }


    @Test
    public void formatTest() {
        System.out.println(TimeStamp2Date("1550564211912", "yyyy-MM-dd HH:mm:ss:SSS"));
    }

    @Test
    public void testRandom() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(3));
        }
    }

    @Test
    public void testCurrentCline() {
        System.out.println(UtilBean.TimeStamp2Date("1550564214651", "yyyy-MM-dd HH:mm:ss:SSS"));
    }

    @Test
    public void testExtract() {
        extractLog.evaluate(line).forEach((k,v)->{
            System.out.println(k + "@@" + v);
        });
    }
}