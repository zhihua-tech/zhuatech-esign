/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.esign.config;
import cn.zhuatech.esign.model.*;
import cn.zhuatech.esign.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Configuration public class DataInitializer {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Bean CommandLineRunner seed(BusinessRecordRepository records,SystemSettingRepository settings){return args->{
        if(records.count()>0)return;
            settings.save(new SystemSetting("signOrder","顺序签署"));
    settings.save(new SystemSetting("sealApproval","法务+印章管理员"));
    settings.save(new SystemSetting("evidenceRetention","10年"));
    settings.save(new SystemSetting("providerMode","预留第三方CA接口"));
            records.save(new BusinessRecord("ESG-20260826-001","TEMPLATE","软件服务合同标准签署模板","法务中心","合同管理员","已归档",new BigDecimal("0"),2,LocalDate.now().plusDays(180),"正常","版本3.2，已完成法务审批"));
    records.save(new BusinessRecord("ESG-20260826-002","ENVELOPE","华东客户年度服务合同签署","客户与知华科技","销售运营","签署中",new BigDecimal("680000"),3,LocalDate.now().plusDays(2),"关注","客户方第二签署人待处理"));
    records.save(new BusinessRecord("ESG-20260826-003","SEAL","上海主体合同章用印申请","上海主体","印章管理员","待签署",new BigDecimal("320000"),1,LocalDate.now().plusDays(1),"正常","授权范围与合同金额一致"));
    records.save(new BusinessRecord("ESG-20260826-004","EVIDENCE","渠道框架协议签署证据包","渠道合作伙伴","档案管理员","已归档",new BigDecimal("180000"),4,LocalDate.now().plusDays(-12),"正常","摘要、时间戳与操作轨迹完整"));
    };}
}
