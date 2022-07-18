package com.service.base.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author alpha
 * @className: MybatisPlusMetaObjectHandler
 * @date 2022/7/18 11:04
 * @Description
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("gmtCreate",new Date());
        metaObject.setValue("gmtModified",new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("gmtModified",new Date());
    }
}
