package com.lbg.feature.bquery.bdd.utils;

@FunctionalInterface
public interface ExceptionBearingAction<T> {

    T doAction() throws Exception;
}
