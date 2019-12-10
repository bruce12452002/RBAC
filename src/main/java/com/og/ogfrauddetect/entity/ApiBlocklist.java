package com.og.ogfrauddetect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ApiBlocklist implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private String username;

    @TableField
    private Date createtime;

    @TableField
    private String remark;
}
