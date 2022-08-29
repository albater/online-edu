package com.service.ucenter.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.service.base.result.R;
import com.service.ucenter.mapper.entity.Member;
import com.service.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author alpha
 * @since 2022-08-01
 */
@RestController
@RequestMapping("/admin/ucenter/member")
public class AdminMemberController {
    @Autowired
    MemberService memberService;
    //查询指定日期注册的用户数量
    @GetMapping("getRegisterNum/{day}")
    public R getRegisterNum(@PathVariable("day") String day){
        long count = memberService.count(new QueryWrapper<Member>()
                .eq("date(gmt_create)", day));
        return R.ok().data("num",count);
    }
}

