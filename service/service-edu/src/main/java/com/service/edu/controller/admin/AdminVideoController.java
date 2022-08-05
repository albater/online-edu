package com.service.edu.controller.admin;


import com.service.base.result.R;
import com.service.edu.entity.Chapter;
import com.service.edu.entity.Video;
import com.service.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
@CrossOrigin
@Api("视频模块")
@RestController
@RequestMapping("/admin/edu/video")
public class AdminVideoController {
    @Autowired
    private VideoService videoService;
    @ApiOperation(value = "新增视频")
    @PostMapping("/save")
    public R save(@RequestBody Video video){
        videoService.save(video);
        return R.ok();
    }
    @ApiOperation(value = "删除视频")
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable("id") String id){
        videoService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "更新视频")
    @PutMapping("/update")
    public R update(@RequestBody Video video){
        videoService.updateById(video);
        return R.ok();
    }
    @ApiOperation(value = "查找视频")
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") String id){
        return R.ok().data("item",videoService.getById(id));
    }
}

