package com.service.ucenter.service.impl;

import com.atguigu.guli.common.util.utils.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.service.base.consts.ServiceConsts;
import com.service.base.exception.GlobalException;
import com.service.base.jwt.JwtHelper;
import com.service.base.jwt.JwtInfo;
import com.service.base.result.ResultCodeEnum;
import com.service.ucenter.mapper.entity.Member;
import com.service.ucenter.mapper.entity.RegisterForm;
import com.service.ucenter.mapper.MemberMapper;
import com.service.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author alpha
 * @since 2022-08-01
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void register(RegisterForm registerForm) {
        //1.验证参数
        String code = registerForm.getCode();
        String mobile = registerForm.getMobile();
        String nickname = registerForm.getNickname();
        String password = registerForm.getPassword();
        if (StringUtils.isEmpty(code) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(nickname) ||
                StringUtils.isEmpty(password)) {
            throw new GlobalException(ResultCodeEnum.PARAM_ERROR);
        }
        //2.验证验证码是否正确，防止表单重复提交
        Object redisCode = redisTemplate.opsForValue().get(ServiceConsts.SMS_CODE_PREFIX + mobile);
        if (redisCode == null || !code.equals(redisCode.toString())) {
            throw new GlobalException(ResultCodeEnum.CODE_ERROR);
        }
        //3.手机号码重复注册校验
        long count = this.count(new LambdaQueryWrapper<Member>().eq(Member::getMobile, mobile));
        if (count > 0) {
            throw new GlobalException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }
        //4.注册
        Member member = new Member();
        member.setAvatar("https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        member.setMobile(mobile);
        member.setNickname(nickname);
//        member.setPassword(password);
        String pwd = MD5.encrypt(ServiceConsts.MEMEBER_PWD_SALT + MD5.encrypt(password));
        member.setPassword(pwd);
        this.save(member);
        //5.删除redis中的缓存
        redisTemplate.delete(ServiceConsts.SMS_CODE_PREFIX+mobile);
    }

    @Override
    public String login(String mobile, String password) {
        //1.查询数据库
        Member member = this.getOne(new LambdaQueryWrapper<Member>()
                .eq(Member::getMobile, mobile));
//        member.getPassword().equals(password)
        //这里数据库中的密码是密文的，用户端传过来的是明文的，所以需要将明文的密码加密再对比
//        byte[] encode = Base64.getEncoder().encode(password.getBytes("UTF-8"));
        String encrypt = MD5.encrypt(ServiceConsts.MEMEBER_PWD_SALT + MD5.encrypt(password));
        if (!member.getPassword().equals(encrypt)) {
            throw new GlobalException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        //2.检验账号是否可用
        if (member.getDisabled()) {
            throw new GlobalException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }
        //3.创建token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());
        return JwtHelper.createToken(jwtInfo);
    }
}
