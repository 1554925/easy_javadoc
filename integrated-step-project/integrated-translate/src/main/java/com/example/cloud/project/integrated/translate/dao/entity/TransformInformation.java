package com.example.cloud.project.integrated.translate.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 14:30
 */
@TableName(TransformInformation.TABLE_NAME)
@Data
public class TransformInformation {
    public static final String TABLE_NAME = "TRANSFORM_INFORMATION";

    @TableId(type = IdType.AUTO)
    private Long id;

    private String type;

    private String status;

    private String sourceValue;

    private String text;

    private String targetValue;

    private String wordLength;

    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableLogic
    private Integer delFlag;


}
