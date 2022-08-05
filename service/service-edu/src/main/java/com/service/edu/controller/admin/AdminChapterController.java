package com.service.edu.controller.admin;


import com.service.base.result.R;
import com.service.edu.entity.Chapter;
import com.service.edu.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
@RestController
@RequestMapping("/admin/edu/chapter")
@CrossOrigin
@Api("章节模块")
public class AdminChapterController {
    @Autowired
    private ChapterService chapterService;

    @ApiOperation(value = "新增章")
    @PostMapping("/save")
    public R save(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return R.ok();
    }
    @ApiOperation(value = "删除章")
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable("id") String id){
        chapterService.removeById(id);
        return R.ok();
    }
    @ApiOperation(value = "更新章")
    @PutMapping("/update")
    public R update(@RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return R.ok();
    }
    @ApiOperation(value = "查找章")
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") String id){
        Chapter chapter = chapterService.getById(id);
        return R.ok().data("item",chapter);
    }
}

