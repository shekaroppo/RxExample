package com.rx.network.datamanager;

import com.rx.network.model.ExampleModel;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface CategoryAPI {
    @GET("/categories/")
    Observable<List<ExampleModel>> getCategorys();
}