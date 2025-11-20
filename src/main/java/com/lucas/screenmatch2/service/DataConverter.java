package com.lucas.screenmatch2.service;

import tools.jackson.databind.ObjectMapper;

public class DataConverter implements IDataConverter {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> tClass) {
        return mapper.readValue(json, tClass);
    }



}
