/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.esign.domain;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class DomainCatalog {
    private final Map<String,WorkflowAction> actions=new LinkedHashMap<>();
    public DomainCatalog(){
        actions.put("SEND", new WorkflowAction("SEND", "发起签署", List.of("草稿"), "待签署"));
actions.put("ACCEPT", new WorkflowAction("ACCEPT", "首方签署", List.of("待签署"), "签署中"));
actions.put("COMPLETE", new WorkflowAction("COMPLETE", "完成签署", List.of("签署中"), "已签署"));
actions.put("ARCHIVE", new WorkflowAction("ARCHIVE", "证据归档", List.of("已签署"), "已归档"));
    }
    public String systemName(){return "知华科技电子签章与签署管理系统";}
    public String scene(){return "签署模板、签署任务、印章授权、签署顺序与证据归档管理";}
    public String initialStatus(){return "草稿";}
    public String partyLabel(){return "签署方";} public String amountLabel(){return "关联金额";}
    public String quantityLabel(){return "签署人数";} public String dueLabel(){return "签署截止日";}
    public List<ModuleDefinition> modules(){return List.of(
        new ModuleDefinition("TEMPLATE","签署模板","维护合同、确认书和授权书模板"),
    new ModuleDefinition("ENVELOPE","签署任务","发起文件签署并跟踪各方状态"),
    new ModuleDefinition("SEAL","印章与授权","管理印章、用印申请和授权范围"),
    new ModuleDefinition("EVIDENCE","证据与归档","保存签署摘要、时间戳和验证记录")
    );}
    public Map<String,WorkflowAction> actions(){return Collections.unmodifiableMap(actions);}
    public record ModuleDefinition(String code,String name,String description){}
    public record WorkflowAction(String code,String label,List<String> from,String to){}
}
