package com.example.kunalparte.themoviedbtask.movies.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataTypeConverters {
    @TypeConverter
    public static String inteeristToString(List<Integer> list) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        String json = gson.toJson(list, type);
        return json;
    }


    @TypeConverter
    public static List<Integer> stringToIntegerlList(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        List<Integer> measurements = gson.fromJson(json, type);
        return measurements;
    }
}
