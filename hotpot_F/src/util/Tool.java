package util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by ggq on 2017/6/21.
 */
public class Tool {

    /*
            解析request流
         */
    public List readIo(HttpServletRequest request) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");

        List items = null;
        try {
            //获得请求中的全部数据
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return items;
    }

    //用来接受上传的图片并且保存
    public void saveFile(FileItem upLoadFileItem, String Number, File uploadPath) {
        String filePath = uploadPath + File.separator + Number + ".jpg";
        File storeFile = new File(filePath);
        // 在控制台输出文件的上传路径
//            System.out.println(filePath);
        // 保存文件到硬盘
        try {
            upLoadFileItem.write(storeFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
