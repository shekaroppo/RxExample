package com.rx.activity.operators;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.TextView;

import com.rx.R;
import com.rx.activity.BaseActivity;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by Shekar on 8/11/15.
 */
public class O5Debounce extends BaseActivity {

    @Bind(R.id.search_edit_text)
    EditText mSearchEditText;
    @Bind(R.id.searched_text)
    TextView mSearchedText;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_debounce);
        ButterKnife.bind(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        mSubscription = getBufferedSubscription();
    }

    @Override
    public void onPause() {
        super.onPause();
        mSubscription.unsubscribe();
    }

    private Subscription getBufferedSubscription() {

        return RxTextView.textChangeEvents(mSearchEditText)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TextViewTextChangeEvent>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                        setUI(textViewTextChangeEvent.text().toString());
                    }
                });
    }

    public void setUI(String msg) {
        Timber.d(msg);
        if (isCurrentlyOnMainThread()) {
            mSearchedText.setText(msg);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    mSearchedText.setText("Switched to main thread" + msg);
                }
            });
        }
    }

    private boolean isCurrentlyOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}

