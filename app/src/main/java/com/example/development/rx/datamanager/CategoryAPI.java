package com.example.development.rx.datamanager;

import com.example.development.rx.model.ExampleModel;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface CategoryAPI {
    @GET("/categories/")
    Observable<List<ExampleModel>> getCategorys();
}