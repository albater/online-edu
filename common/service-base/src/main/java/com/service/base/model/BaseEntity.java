package com.service.base.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author alpha
 * @className: BaseEntity
 * @date 2022/7/17 22:27
 * @Description
 */
@Data
public class BaseEntity {
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "数据库自生成ID")
    private String id;
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
