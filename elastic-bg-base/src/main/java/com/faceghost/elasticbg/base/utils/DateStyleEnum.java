package com.faceghost.elasticbg.base.utils;

import java.io.Serializable;

public enum DateStyleEnum {

	 yyyymmdd("0", "yyyyMMdd"), yyyy_mm_dd_hh_mm_ss("1", "yyyy-MM-dd HH:mm:ss"),yyyy_MM_dd("2","yyyy-MM-dd");

    private final Serializable key;

    private final Serializable value;


    private DateStyleEnum( Serializable key, Serializable value ) {
        this.key = key;
        this.value = value;
    }


    public Serializable key() {
        return key;
    }


    public Serializable value() {
        return value;
    }
}
