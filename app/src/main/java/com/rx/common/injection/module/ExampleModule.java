package com.rx.common.injection.module;
import com.rx.guide.network.adapter.ExampleAdapter;
import com.rx.guide.network.model.ExampleModel;
import com.rx.guide.network.presenter.ExamplePresenter;
import com.rx.guide.network.presenter.ExamplePresenterImpl;
import com.rx.common.injection.scope.ActivityScope;
import com.rx.guide.network.view.LceView;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

/**
 * Created by Sekhar on 4/8/15.
 */
@Module
public class ExampleModule {

    private LceView<List<ExampleModel>> view;

    public ExampleModule(LceView<List<ExampleModel>> view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public LceView<List<ExampleModel>> provideLceView() {
        return view;
    }

    @Provides
    @ActivityScope
    public ExampleAdapter provideCategoryAdapter() {
        return new ExampleAdapter();
    }

    @Provides
    @ActivityScope
    public ExamplePresenter provideCategoryPresenter(LceView<List<ExampleModel>> view, Observable<List<ExampleModel>> categoryObservable) {
        return new ExamplePresenterImpl(view, categoryObservable);
    }
}
