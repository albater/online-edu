package com.service.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private String id;

    private Date gmtCreate;
    private Date gmtModified;
}
