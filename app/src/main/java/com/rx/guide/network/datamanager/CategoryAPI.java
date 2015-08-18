package com.rx.guide.network.datamanager;

import com.rx.guide.network.model.ExampleModel;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface CategoryAPI {
    @GET("/categories/")
    Observable<List<ExampleModel>> getCategorys();
}