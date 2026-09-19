/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.esign.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class SignatureEnvelopeReleaseService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result assess(Request request) {
        var blockers = new ArrayList<String>();
        var actions = new ArrayList<String>();
        if (request.envelopeId() == null || request.envelopeId().isBlank()) blockers.add("签署任务编号不能为空");
        if (!request.documentHashVerified()) blockers.add("待签文件摘要校验失败");
        if (!request.signerIdentityVerified()) blockers.add("签署人身份未核验");
        if (!request.certificateValid()) blockers.add("签名证书无效或已过期");
        if (!request.consentCaptured()) blockers.add("电子签署意愿未留证");
        if (!request.sealAuthorizationApproved()) blockers.add("印章使用授权未批准");
        if (!request.timestampServiceAvailable()) blockers.add("可信时间戳服务不可用");
        if (!request.auditReady()) blockers.add("签署审计证据不完整");
        if (!request.signingOrderConfigured()) actions.add("配置签署顺序");
        if (!request.retentionPolicyConfigured()) actions.add("配置签署证据留存策略");
        if (!request.callbackVerified()) actions.add("验证签署结果回调");
        var decision = !blockers.isEmpty() ? Decision.BLOCKED : actions.isEmpty() ? Decision.RELEASE : Decision.REVIEW;
        return new Result(decision, List.copyOf(blockers), List.copyOf(actions));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { RELEASE, REVIEW, BLOCKED }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(String envelopeId, boolean documentHashVerified, boolean signerIdentityVerified,
                          boolean signingOrderConfigured, boolean certificateValid, boolean consentCaptured,
                          boolean sealAuthorizationApproved, boolean timestampServiceAvailable,
                          boolean retentionPolicyConfigured, boolean callbackVerified, boolean auditReady) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(Decision decision, List<String> blockers, List<String> actions) {}
}
