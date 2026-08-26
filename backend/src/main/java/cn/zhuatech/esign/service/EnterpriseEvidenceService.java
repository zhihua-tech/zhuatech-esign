/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.esign.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;import java.security.MessageDigest;import java.time.OffsetDateTime;import java.util.*;
@Service public class EnterpriseEvidenceService {
 public Manifest build(@Valid ManifestRequest req){
   List<String> missing=new ArrayList<>();StringBuilder canonical=new StringBuilder(req.envelopeNo()).append('|').append(req.documentSha256().toLowerCase());
   Set<String> signerIds=new HashSet<>();
   for(var signer:req.signers()){
     if(!signerIds.add(signer.signerId()))missing.add("重复签署人: "+signer.signerId());
     if(!signer.identityVerified())missing.add(signer.signerName()+"身份未核验");
     if(!signer.sealAuthorized())missing.add(signer.signerName()+"印章未授权");
     if(signer.signedAt()==null)missing.add(signer.signerName()+"缺少签署时间");
     canonical.append('|').append(signer.signerId()).append('|').append(signer.signatureSha256().toLowerCase())
       .append('|').append(signer.signedAt()).append('|').append(signer.clientIp());
   }
   if(req.trustedTimestamp()==null)missing.add("缺少可信时间戳");
   else canonical.append('|').append(req.trustedTimestamp());
   String manifestHash=sha256(canonical.toString());
   return new Manifest(req.envelopeNo(),req.documentSha256().toLowerCase(),manifestHash,req.signers().size(),missing,missing.isEmpty()?"ARCHIVE_READY":"EVIDENCE_INCOMPLETE");
 }
 private String sha256(String text){try{byte[] bytes=MessageDigest.getInstance("SHA-256").digest(text.getBytes(StandardCharsets.UTF_8));return java.util.HexFormat.of().formatHex(bytes);}catch(Exception e){throw new IllegalStateException(e);}}
 public record ManifestRequest(@NotBlank String envelopeNo,@NotBlank @Pattern(regexp="(?i)[0-9a-f]{64}") String documentSha256,
   OffsetDateTime trustedTimestamp,@NotEmpty List<@Valid SignerEvidence> signers){}
 public record SignerEvidence(@NotBlank String signerId,@NotBlank String signerName,boolean identityVerified,boolean sealAuthorized,
   OffsetDateTime signedAt,@NotBlank String clientIp,@NotBlank @Pattern(regexp="(?i)[0-9a-f]{64}") String signatureSha256){}
 public record Manifest(String envelopeNo,String documentSha256,String manifestSha256,int signerCount,List<String> missingEvidence,String decision){}
}
