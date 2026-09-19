/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.esign.service;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.*;
import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class DomainInsightService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String,Object> analyze(InsightRequest req){
        Map<String,Object> result=new LinkedHashMap<>();
        List<String> missing=new ArrayList<>();
if(!req.contentHashPresent())missing.add("文件摘要");if(!req.timestampPresent())missing.add("可信时间戳");if(!req.allSigned())missing.add("全部签署动作");if(!req.identityVerified())missing.add("身份核验");if(!req.sealAuthorized())missing.add("印章授权");
result.put("valid",missing.isEmpty());result.put("missingEvidence",missing);result.put("decision",missing.isEmpty()?"ARCHIVE_READY":"SUPPLEMENT");
        return result;
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private BigDecimal rate(long numerator,long denominator){return denominator==0?BigDecimal.ZERO:BigDecimal.valueOf(numerator).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(denominator),2,RoundingMode.HALF_UP);}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record InsightRequest(boolean contentHashPresent, boolean timestampPresent, boolean allSigned, boolean identityVerified, boolean sealAuthorized){}
}
