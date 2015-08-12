package com.rx.datamanager;

import com.rx.model.ExampleModel;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface CategoryAPI {
    @GET("/categories/")
    Observable<List<ExampleModel>> getCategorys();
}