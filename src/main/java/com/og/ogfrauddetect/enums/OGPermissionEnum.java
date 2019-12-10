package com.og.ogfrauddetect.enums;

public enum OGPermissionEnum {
    QUERY_BLACK_ONE(2),
    QUERY_BLACK_ALL(3),
    INSERT_BLACK(4),
    DELETE_BLACK(5),
    QUERY_LOG_ONE(6),
    QUERY_LOG_ALL(7);

    public int permissionNum;

    OGPermissionEnum(int permissionNum) {
        this.permissionNum = permissionNum;
    }
}
