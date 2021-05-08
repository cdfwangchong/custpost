package com.ctg.custpost.pojo.dto;


import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class SigPicDto {
    private String id;
    /** 文件名 */
    private String fileName;
    /** 业务类型 */
    private String businessType;
    /** 上传时间 */
    private Long createdTime;
    /** 文件内容 */
    private byte[] content;
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

    private String open_id;
}
