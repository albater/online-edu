package com.service.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.service.base.exception.GlobalException;
import com.service.base.result.ResultCodeEnum;
import com.service.vod.service.VodService;
import com.service.vod.util.VodProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author alpha
 * @className: VodServiceImpl
 * @date 2022/7/29 21:04
 * @Description
 */
@Service
@Slf4j
public class VodServiceImpl implements VodService {
    @Autowired
    private VodProperties vodProperties;

    private String accessKeyId;
    private String accessKeySecret;
    private String workFlowId;
    private String templateGroupId;

    //对象初始化之前调用
    @PostConstruct
    public void init() {
        accessKeyId = vodProperties.getKeyId();
        accessKeySecret = vodProperties.getKeySecret();
        workFlowId = vodProperties.getWorkflowId();
        templateGroupId = vodProperties.getTemplateGroupId();

    }

    @Override
    public String upload(MultipartFile video) {
        try {
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret,
                    video.getOriginalFilename(),
                    video.getOriginalFilename(), video.getInputStream());
            //设置描述
            request.setDescription("服务器上传测试");
            // 设置工作流
            request.setWorkflowId(workFlowId);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID

            if (response.isSuccess()) {
                return response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                log.error("VideoId=" + response.getVideoId() +"ErrorCode=" + response.getCode() +"ErrorMessage=" + response.getMessage());
                throw new GlobalException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
            }
        } catch (IOException e) {
            throw new GlobalException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }
    }

    @Override
    public String getPlayAuth(String videoId) {
        DefaultProfile profile = DefaultProfile.getProfile(templateGroupId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);
            request.setAuthInfoTimeout(600L);//播放凭证的过期时间：默认是100秒
            response = client.getAcsResponse(request);
            //播放凭证
//            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            return  response.getPlayAuth();
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
            throw new GlobalException(ResultCodeEnum.FETCH_PLAYAUTH_ERROR);
        }
    }

}
