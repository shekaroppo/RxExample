package com.rx.activity.operators;

import android.os.Bundle;
import android.widget.TextView;

import com.rx.R;
import com.rx.activity.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by Sekhar on 4/3/15.
 */
public class O3From extends BaseActivity {
    private TextView mTextView;
    private ArrayList<Integer> number = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13,14,15,16,17,18,19,20));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rx_android_main);
        mTextView = (TextView) findViewById(R.id.rx_textview);
        Observable.from(number).flatMap(i -> Observable.just(square(i)))
                .filter(number -> number != 25)
                .take(15)
                .repeat(5)
                .distinct()
                .distinctUntilChanged()
                .sample(1, TimeUnit.SECONDS)// Take last element and emit it in 1 sec
                .throttleFirst(1,TimeUnit.SECONDS)// Take first element and emit it in 1 sec
                .subscribe(s -> System.out.println("======" + s));
        ;
    }

    private int square(Integer i) {
        return i * i;
    }
    public void setUI(Integer i) {
        mTextView.setText(i.toString());
    }
}
