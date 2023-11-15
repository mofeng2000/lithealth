package com.lit.health_mobile.pojo;

import lombok.Data;

/**
 * 检查项
 */
@Data
//@TableName("checkitem")//对应表名
public class CheckItem{
    private Integer id;//主键
    private String code;//项目编码
    private String name;//项目名称
    private String sex;//适用性别
    private String age;//适用年龄（范围），例如：20-50
    private Float price;//价格
    private String type;//检查项类型，分为检查和检验两种类型
    private String remark;//项目说明
    private String attention;//注意事项
}
