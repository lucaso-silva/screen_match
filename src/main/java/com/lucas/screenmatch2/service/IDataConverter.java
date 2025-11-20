package com.lucas.screenmatch2.service;

public interface IDataConverter {
    <T> T getData(String json, Class<T> tClass);
}
