package cn.itcast.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     *传统文件上传
     * @return
     */
    @RequestMapping(value = "/fileUpLoad1")
    public String fileUpLoad1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传..");
        //使用fileupload组件完成文件上传
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断，该路径是否存在
        File file = new File(path);
        if (!file.exists()){
            //创建该文件夹
            file.mkdirs();
        }
        //解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> items = upload.parseRequest(request);
        for (FileItem item : items) {
            //进行判断，当前item对象是否是上传文件项
            if (item.isFormField()){
                //说明是普通表单项
            }else {
                //说明是上传文件项
                //获取上传文件的名称
                String filename = item.getName();
                //把文件的名称设置唯一值，uuid
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid + "_" + filename;
                //完成文件上传
                item.write(new File(path,filename));
                //删除临时文件
                item.delete();
            }

        }

        return "success";
    }
    /**
     *SpringMVC文件上传
     * @return
     */
    @RequestMapping(value = "/fileUpLoad2")
    public String fileUpLoad2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("springmvc文件2上传..");
        //使用fileupload组件完成文件上传
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断，该路径是否存在
        File file = new File(path);
        if (!file.exists()){
            //创建该文件夹
            file.mkdirs();
        }
        //说明是上传文件项
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;
        //完成文件上传
        upload.transferTo(new File(path,filename));
        return "success";
    }
    /**
     *SpringMVC跨服务器文件文件上传
     * @return
     */
    @RequestMapping(value = "/fileUpLoad3")
    public String fileUpLoad3(MultipartFile upload) throws Exception {
        System.out.println("springmvc跨服务器文件3上传..");
        //定义上传文件服务器路径
        String path = "http://localhost:9090/uploads/";
        //说明是上传文件项
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;
        //完成文件上传,跨服务器上传
        //创建客户端的对象
        Client client = Client.create();
        //和图片服务器进行连接
        WebResource webResource = client.resource(path + filename);
        //上传图片文件
        webResource .put(upload.getBytes());
        return "success";
    }
}
