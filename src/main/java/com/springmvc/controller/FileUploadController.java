package com.springmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileUploadController {

    @GetMapping("/test")
    public String test1(){
        return "test";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/upload")
    public String toFileUpload(){
        return "file_upload";
    }

    @GetMapping("/upload_ajax")
    public String toFileUploadAjax(){
        return "file_upload_ajax";
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public String fileUpload(MultipartFile file, HttpServletRequest request){

            //上传到指定路径(服务器下upload目录里)
            String realPath = request.getSession().getServletContext().getRealPath("/upload/");
            //保证上传文件夹存在
            File upload = new File(realPath);
            if (!upload.exists()){
                upload.mkdir();
            }
            //处理文件名
            String oldName = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());

        try {
            file.transferTo(new File(realPath,fileName));
            //如果上传成功，就把路径返回
            return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/upload/"+fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @PostMapping("/upload_ajax")
    @ResponseBody
    public String fileUploadByAjax(HttpServletRequest request,MultipartFile pic){
        //上传到指定路径(服务器下upload目录里)
        String realPath = request.getSession().getServletContext().getRealPath("/upload/");
        //保证上传文件夹存在
        File upload = new File(realPath);
        if (!upload.exists()){
            upload.mkdir();
        }
        //处理文件名
        String oldName = pic.getOriginalFilename();
        String newName = UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());

        try {
            pic.transferTo(new File(realPath,newName));
            String fileName = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/upload/"+newName;
            //后台String转换成json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(newName);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
