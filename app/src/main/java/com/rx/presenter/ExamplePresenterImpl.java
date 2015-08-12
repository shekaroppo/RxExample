package com.rx.presenter;


import android.util.Log;

import com.rx.model.ExampleModel;
import com.rx.view.LceView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Sekhar on 4/6/15.
 */
public class ExamplePresenterImpl implements ExamplePresenter {

    private LceView mLceView;
    private CompositeSubscription compositeSubscription;
    Observable<List<ExampleModel>> mCategoryObservable;

    public ExamplePresenterImpl(LceView mLceView, Observable<List<ExampleModel>> categoryObservable) {
        this.mLceView = mLceView;
        mCategoryObservable = categoryObservable;
    }


    @Override
    public void loadData() {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        mLceView.showProgress();
        compositeSubscription.add(mCategoryObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<ExampleModel>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mLceView.hideProgress();
                mLceView.showError();
            }

            @Override
            public void onNext(List<ExampleModel> categoryModels) {
                mLceView.hideProgress();
                mLceView.showContent(categoryModels);
            }
        }));
    }

    @Override
    public void unsubscribeFromDataStore() {
        Log.d("TAG", "unsubscribeFromDataStore ");
        compositeSubscription.unsubscribe();
        compositeSubscription.clear();
        compositeSubscription = null;
    }
}
