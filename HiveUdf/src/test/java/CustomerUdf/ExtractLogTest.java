package CustomerUdf;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExtractLogTest {

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
        ExtractLog extractLog = new ExtractLog();
        String line = "119.115.192.156 1550564211.912 120.79.2.76 /log.gif?en=e_pv&ver=1&pl=pc&sdk=js&uid=1B5E2747-7B95-4BA0-8A9F-9EBB618E6DB9&sid=C9CCDD83-9B5C-4E0E-9541-C4B3F587972C&b_rst=1366*768&b_usa=Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20WOW64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F67.0.3396.99%20Safari%2F537.36&l=zh-CN&ct=1550564214651&url=http%3A%2F%2Flocalhost%3A8080%2F%23%2Fhome%3F_k%3D65i5zc&ref=http%3A%2F%2Flocalhost%3A8080%2F&tt=React%20o2o%20demo";
        ArrayList<String> ss =extractLog.converline2field(line);
        ss.forEach(System.out::println);
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
}