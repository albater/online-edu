package com.service.oss.controller;

import com.service.base.result.R;
import com.service.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author alpha
 * @className: OssController
 * @date 2022/7/22 14:56
 * @Description
 */
@Api("阿里云OSS服务")
@RestController
@CrossOrigin
@RequestMapping("/admin/oss")
public class OssController {
    @Autowired
    OssService ossService;

    @ApiOperation(value = "单个文件上传")
    @PostMapping("/upload")
    public R upload(@ApiParam(value = "文件", required = true)
                    @RequestParam("file") MultipartFile file,
                    @ApiParam(value = "目录", required = true)
                    @RequestParam("module") String module) {
        String path = ossService.upload(file, module);
        return R.ok().data("path", path);
    }

    @ApiOperation(value = "单个文件删除")
    @DeleteMapping("delete")
    public R delete(@ApiParam(value = "文件路径", required = true)
                    @RequestParam("path") String path,
                    @ApiParam(value = "删除文件所在目录", required = true)
                    @RequestParam("module") String module) {
        ossService.delete(path, module);
        return R.ok();
    }
}
