package com.rx.guide.lifecycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rx.R;
import com.rx.guide.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import timber.log.Timber;

public class LeakingActivity extends BaseActivity {

    private static final String FIX_LEAK = "FIX_LEAK";

    private Subscription mSubscription;

    public static Intent createIntent(Context context, boolean fixLeak) {
        Intent intent = new Intent(context, LeakingActivity.class);
        intent.putExtra(FIX_LEAK, fixLeak);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rx_leaking);

        // This is a hot Observable that never ends;
        // thus LeakingActivity can never be reclaimed
        mSubscription = Observable.interval(1, TimeUnit.SECONDS)
            .subscribe(new Action1<Long>() {
                @Override public void call(Long aLong) {
                    Timber.d("LeakingActivity received: " + aLong);
                }
            });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (getIntent().getBooleanExtra(FIX_LEAK, false)) {
            mSubscription.unsubscribe();
        }
    }
}
