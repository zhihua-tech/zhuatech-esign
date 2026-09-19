/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.esign.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class SignatureEnvelopeReleaseServiceTest {
    private final SignatureEnvelopeReleaseService service = new SignatureEnvelopeReleaseService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void releasesTrustedSignatureEnvelope() {
        var result = service.assess(new SignatureEnvelopeReleaseService.Request("ENV-100", true, true, true,
                true, true, true, true, true, true, true));
        assertThat(result.decision()).isEqualTo(SignatureEnvelopeReleaseService.Decision.RELEASE);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void routesOperationalConfigurationToReview() {
        var result = service.assess(new SignatureEnvelopeReleaseService.Request("ENV-101", true, true, false,
                true, true, true, true, false, false, true));
        assertThat(result.actions()).hasSize(3);
        assertThat(result.decision()).isEqualTo(SignatureEnvelopeReleaseService.Decision.REVIEW);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksUntrustedSignatureEnvelope() {
        var result = service.assess(new SignatureEnvelopeReleaseService.Request("", false, false, false,
                false, false, false, false, false, false, false));
        assertThat(result.blockers()).hasSize(8);
        assertThat(result.decision()).isEqualTo(SignatureEnvelopeReleaseService.Decision.BLOCKED);
    }
}
