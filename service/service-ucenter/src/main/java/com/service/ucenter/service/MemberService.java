package com.service.ucenter.service;

import com.service.ucenter.mapper.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.service.ucenter.mapper.entity.RegisterForm;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author alpha
 * @since 2022-08-01
 */
public interface MemberService extends IService<Member> {

    void register(RegisterForm registerForm);

    String login(String mobile, String password);
}
