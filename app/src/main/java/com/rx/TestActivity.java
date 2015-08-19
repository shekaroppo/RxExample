//package com.rx;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.rx.BaseApplication;
//import com.rx.common.injection.component.BaseComponent;
//import com.rx.common.injection.component.DaggerDependenciesExampleComponent;
//import com.rx.common.injection.module.DependenciesExampleModule;
//import com.rx.guide.base.BaseActivity;
//import com.rx.guide.basic.RxAndroidPart1;
//import com.rx.guide.basic.RxAndroidPart2UsingAction1;
//import com.rx.guide.basic.RxAndroidPart3UsingLambda;
//import com.rx.guide.basic.RxAndroidPart4UsingChaining;
//import com.rx.guide.lifecycle.LeakingActivity;
//import com.rx.guide.lifecycle.LifecycleActivity;
//import com.rx.guide.network.RetrofitActivity;
//import com.rx.guide.operators.O3From;
//import com.rx.guide.operators.O4Buffers;
//import com.rx.guide.operators.O5Debounce;
//import com.rx.guide.operators.transformingObservables.FlatmapVsConcatmapFragment;
//import com.rx.guide.operators.transformingObservables.O1Map;
//import com.rx.guide.operators.transformingObservables.O2MapEmitingDiffrentType;
//
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
///**
// * Created by Shekar on 7/10/15.
// */
//public class TestActivity extends BaseActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test);
//        ButterKnife.bind(this);
//        BaseComponent component = BaseApplication.getComponent(this);
//        DaggerDependenciesExampleComponent.builder()
//                .dependenciesExampleModule(new DependenciesExampleModule())
//                .baseComponent(component)
//                .build().inject(this);
//    }
//
//    @OnClick(R.id.create)
//    void create() {
//        startActivity(new Intent(this, RxAndroidPart1.class));
//    }
//    @OnClick(R.id.action)
//    void action() {
//        startActivity(new Intent(this, RxAndroidPart2UsingAction1.class));
//    }
//    @OnClick(R.id.lambda)
//    void lambda() {
//        startActivity(new Intent(this, RxAndroidPart3UsingLambda.class));
//    }
//    @OnClick(R.id.chaining)
//    void chaining() {
//        startActivity(new Intent(this, RxAndroidPart4UsingChaining.class));
//    }
//
//
//    @OnClick(R.id.leaking_subscription)
//    void leakSubscription() {
//        startActivity(LeakingActivity.createIntent(this, false));
//    }
//    @OnClick(R.id.leaking_subscription_fixed)
//    void leakSubscriptionWithFix() {
//        startActivity(LeakingActivity.createIntent(this, true));
//    }
//    @OnClick(R.id.lifecycle_observable)
//    void lifecycleObservable() {
//        startActivity(new Intent(this, LifecycleActivity.class));
//    }
//
//
//    @OnClick(R.id.map)
//    void map() {
//        startActivity(new Intent(this, O1Map.class));
//    }
//    @OnClick(R.id.map_emitting_diffrent_type)
//    void mapEmittingDiffrentType() {
//        startActivity(new Intent(this, O2MapEmitingDiffrentType.class));}
//    @OnClick(R.id.from)
//    void from() {
//        startActivity(new Intent(this, O3From.class));
//    }
//    @OnClick(R.id.buffer)
//    void buffer() {
//        startActivity(new Intent(this, O4Buffers.class));
//    }
//    @OnClick(R.id.debounce)
//    void debounce() {
//        startActivity(new Intent(this, O5Debounce.class));
//    }
//
//    @OnClick(R.id.retrofit)
//    void retrofit() {
//        startActivity(new Intent(this, RetrofitActivity.class));
//    }
//
//    @OnClick(R.id.flatmap)
//    void flatmap() {
//        startActivity(new Intent(this, FlatmapVsConcatmapFragment.class));
//    }
//}
