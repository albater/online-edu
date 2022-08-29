package com.service.ucenter.controller.api;


import com.service.base.dto.MemberDto;
import com.service.base.jwt.JwtHelper;
import com.service.base.jwt.JwtInfo;
import com.service.base.result.R;
import com.service.ucenter.mapper.entity.Member;
import com.service.ucenter.mapper.entity.RegisterForm;
import com.service.ucenter.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author alpha
 * @since 2022-08-01
 */
@RestController
@CrossOrigin
@RequestMapping("/api/ucenter/member")
public class ApiMemberController {
    @Autowired
    private MemberService memberService;


    //4. 创建订单查询用户个人信息
    @GetMapping("getMemberDto/{id}")
    public R getMemberDto(@PathVariable("id")String id){
        Member member = memberService.getById(id);
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member,memberDto);
        return R.ok().data("item",memberDto);

    }
    //3. 解析token
    @GetMapping("getUserInfo")
    public R getUserInfo(HttpServletRequest request){
        JwtInfo info = JwtHelper.getJwtInfo(request);
        return R.ok().data("info",info);
    }
    //2. 用户认证
    @PostMapping("/login")
    public R login(@RequestParam("mobile") String mobile,
                   @RequestParam("password") String password){

        String token = memberService.login(mobile,password);
        return R.ok().data("token",token);
    }
    //1. 注册
    @PostMapping("register")
    public R register(@RequestBody  RegisterForm registerForm) {
        memberService.register(registerForm);
        return R.ok();
    }
}

