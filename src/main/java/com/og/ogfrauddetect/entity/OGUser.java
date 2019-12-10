package com.og.ogfrauddetect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("OG_User")
public class OGUser implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private String name;

    @TableField
    private String password;
}
