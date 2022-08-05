package com.service.ucenter.controller.api;

import com.atguigu.guli.common.util.utils.HttpClientUtils;
import com.google.gson.Gson;
import com.service.base.exception.GlobalException;
import com.service.base.jwt.JwtHelper;
import com.service.base.jwt.JwtInfo;
import com.service.base.result.ResultCodeEnum;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Map;
import java.util.UUID;

/**
 * @author alpha
 * @className: ApiMenberController
 * @date 2022/8/2 20:32
 * @Description
 */
@Api("微信模块")
@Slf4j
@Controller
@RequestMapping("/api/ucenter/wx")
public class ApiWXController {
    @GetMapping("/login")
    public String wxLogin(HttpSession session) {
        try {
            String url = "https://open.weixin.qq.com/connect/qrconnect?" +
                    "appid=%s" +
                    "&redirect_uri=%s" +
                    "&response_type=code" +
                    "&scope=snsapi_login" +
                    "&state=%s" +
                    "#wechat_redirect ";
            String state = UUID.randomUUID().toString().replace("-", "");
            //将token存在共享域
            session.setAttribute("state", state);
            url = String.format(url,
                    "wxed9954c01bb89b47",
                    URLEncoder.encode("http://localhost:8160/api/ucenter/wx/callback", "UTF-8"),
                    state);

            return "redirect:" + url;
        } catch (UnsupportedEncodingException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            throw new GlobalException(ResultCodeEnum.UNKNOWN_REASON);
        }
    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpSession session) {
        try {
            Object sessionState = session.getAttribute("state");
            if (!sessionState.equals(state) || sessionState == null) {
                throw new GlobalException(ResultCodeEnum.ILLEGAL_CALLBACK_REQUEST_ERROR);
            }
            //state销毁
            session.removeAttribute("state");
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                    "appid=wxed9954c01bb89b47" +
                    "&secret=a7482517235173ddb4083788de60b90e" +
                    "&code=" + code +
                    "&grant_type=authorization_code";
            HttpClientUtils client = new HttpClientUtils(url);
            client.get();
            //获取请求得到的相应结果
            String content = client.getContent();
            if (StringUtils.isEmpty(content) || content.contains("errcode")) {
                log.error(content);
                throw new GlobalException(ResultCodeEnum.ILLEGAL_CALLBACK_REQUEST_ERROR);
            }
            Gson gson = new Gson();
            Map map = gson.fromJson(content, Map.class);
            log.info(map.toString());
            String accessToken = map.get("access_token").toString();
            String openid = map.get("openid").toString();
            url = "https://api.weixin.qq.com/sns/userinfo?" +
                    "access_token=" + accessToken +
                    "&openid=" + openid;
            client = new HttpClientUtils(url);
            client.get();
            content = client.getContent();
            if (StringUtils.isEmpty(content) || content.contains("errcode")) {
                log.error(content);
                throw new GlobalException(ResultCodeEnum.ILLEGAL_CALLBACK_REQUEST_ERROR);
            }
            map = gson.fromJson(content, Map.class);
            //解析出用户的数据
            String nickname = map.get("nickname").toString();
            String sex = map.get("sex").toString();
            String province = map.get("province").toString();
            String city = map.get("city").toString();
            String headimgurl = map.get("headimgurl").toString();
            String country = map.get("country").toString();
            log.info("用户数据：nickename={},sex={},province={},city={},headimgurl={},country={}",
                    nickname, sex, province, city, headimgurl, country);

            //wx用户创建token
            JwtInfo jwtInfo = new JwtInfo();
            jwtInfo.setAvatar(headimgurl);
            jwtInfo.setNickname(nickname);
            jwtInfo.setId(openid);
            String token = JwtHelper.createToken(jwtInfo);
            return "redirect:http://localhost:3000?token=" + token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ResultCodeEnum.ILLEGAL_CALLBACK_REQUEST_ERROR);
        }

    }
}
