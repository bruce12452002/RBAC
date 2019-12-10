package com.og.ogfrauddetect.enums;

public enum OGRoleEnum {
    QUERY(1), INSERT(2), DELETE(3), LOG(4);

    public int roleId;

    OGRoleEnum(int roleId) {
        this.roleId = roleId;
    }
}
