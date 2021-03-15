package com.online.eduservice.oss.controller;


import com.online.commonutils.Result;
import com.online.eduservice.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
@Api(description= "文件上传功能")
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像的方法
    @PostMapping
    @ApiOperation(value = "上传头像方法")
    public Result uploadOssFile(
            @ApiParam(name = "file",value = "需要上传的文件",required = true) @PathVariable MultipartFile file) {//获取上传文件  MultipartFile

        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return Result.ok().data("url",url);
    }
}
