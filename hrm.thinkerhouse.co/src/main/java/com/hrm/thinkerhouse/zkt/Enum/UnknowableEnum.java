package com.hrm.thinkerhouse.zkt.Enum;

public enum UnknowableEnum {
	unknown;

    public static UnknowableEnum _missing_(String value) {
        return UnknowableEnum.unknown;
    }
}
