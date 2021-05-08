package com.ctg.custpost.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CheckInfoDto {

    /**
     * 证件号
     */
    private String idNum;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * open_id
     */
    private String open_id;
}
