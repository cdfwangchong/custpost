package com.ctg.custpost.pojo.until;

import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Accessors(chain = true)
public class SigPic implements Comparable<SigPic>{
    @Id
    private String id;
    /** 文件名 */
    private String fileName;
    /** 业务类型 */
    private String businessType;
    /** 上传时间 */
    private Long createdTime;
    /** 文件内容 */
    private Binary content;
    /** 文件类型 */
    private String contentType;
    /** 文件大小 */
    private long size;
    /** 特征标记 */
    private String flagOne; //身份证号
    /** 特征标记 */
    private String flagTwo; //门店号
    /** 特征标记 */
    private String flagThree;//日期

    @Override
    public int compareTo(SigPic o) {
        return this.createdTime>o.createdTime?1:this.createdTime==o.createdTime?0:-1;
    }
}
