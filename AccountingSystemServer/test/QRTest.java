import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Hashtable;
import java.util.Map;

public class QRTest {
    @Test
    public void TT() {
        BufferedImage image = null;
        Result result = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            image = ImageIO.read(new File("C:\\Users\\ggq18\\Desktop\\20180725_161946.jpg"));
//            ImageIO.write(image, "jpg",out);
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            byte[] bytes = out.toByteArray();
//            new PlanarYUVLuminanceSource(bytes);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Map<DecodeHintType, Object> hints = new Hashtable<>();
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
            result = new MultiFormatReader().decode(bitmap, hints);
//            result = new MultiFormatReader().decode(bitmap, null);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
