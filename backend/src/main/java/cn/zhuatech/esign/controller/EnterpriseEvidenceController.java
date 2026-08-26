/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.esign.controller;
import cn.zhuatech.esign.common.ApiResponse;import cn.zhuatech.esign.service.EnterpriseEvidenceService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/esign") public class EnterpriseEvidenceController {private final EnterpriseEvidenceService service;public EnterpriseEvidenceController(EnterpriseEvidenceService service){this.service=service;}@PostMapping("/evidence-manifest") ApiResponse<EnterpriseEvidenceService.Manifest> build(@Valid @RequestBody EnterpriseEvidenceService.ManifestRequest request){return ApiResponse.ok(service.build(request));}}
