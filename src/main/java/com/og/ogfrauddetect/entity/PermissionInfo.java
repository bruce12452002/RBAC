package com.og.ogfrauddetect.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PermissionInfo implements BaseEntity {
    private String name;
    private String password;
    private String addDelete;
    private String queryLog;
}
