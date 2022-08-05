package com.service.ucenter.controller.api;


import com.service.base.jwt.JwtHelper;
import com.service.base.jwt.JwtInfo;
import com.service.base.result.R;
import com.service.ucenter.controller.MemberController;
import com.service.ucenter.entity.RegisterForm;
import com.service.ucenter.service.MemberService;
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

    //解析token
    @GetMapping("getUserInfo")
    public R getUserInfo(HttpServletRequest request){
        JwtInfo info = JwtHelper.getJwtInfo(request);
        return R.ok().data("info",info);
    }
    //用户认证
    @PostMapping("/login")
    public R login(@RequestParam("mobile") String mobile,
                   @RequestParam("password") String password){

        String token = memberService.login(mobile,password);
        return R.ok().data("token",token);
    }
    //注册
    @PostMapping("register")
    public R register(@RequestBody  RegisterForm registerForm) {
        memberService.register(registerForm);
        return R.ok();
    }
}

