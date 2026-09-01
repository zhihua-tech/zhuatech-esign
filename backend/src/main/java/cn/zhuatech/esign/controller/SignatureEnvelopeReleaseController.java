/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.esign.controller;

import cn.zhuatech.esign.common.ApiResponse;
import cn.zhuatech.esign.service.SignatureEnvelopeReleaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enterprise/esign")
public class SignatureEnvelopeReleaseController {
    private final SignatureEnvelopeReleaseService service;
    public SignatureEnvelopeReleaseController(SignatureEnvelopeReleaseService service) { this.service = service; }

    @PostMapping("/envelope-release")
    public ApiResponse<?> assess(@RequestBody SignatureEnvelopeReleaseService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
