package com.rx.lifecycle;

/**
 * Created by Shekar on 8/12/15.
 */

import android.os.Bundle;

import com.rx.R;
import com.trello.rxlifecycle.components.RxActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * Created by Shekar on 8/11/15.
 */

public class LifecycleActivity extends RxActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rx_lifecycle);

        // RxActivity binds automatically unsubscribe when the corresponding
        // lifecycle event occurs - in this case, onDestroy.
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(this.<Long>bindToLifecycle())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Timber.d("LifecycleActivity received: " + aLong);
                    }
                });
    }

}

