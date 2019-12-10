package com.og.ogfrauddetect.enums;

public enum OperatorEnum {
    INSERT(1), DELETE(2), QUERY(4);

    public int operatorNum;

    OperatorEnum(int operatorNum) {
        this.operatorNum = operatorNum;
    }
}
