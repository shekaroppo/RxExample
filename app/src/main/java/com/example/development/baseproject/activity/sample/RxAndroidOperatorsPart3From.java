package com.example.development.baseproject.activity.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.development.baseproject.R;

import java.util.ArrayList;
import java.util.Arrays;

import rx.Observable;

/**
 * Created by Sekhar on 4/3/15.
 */
public class RxAndroidOperatorsPart3From extends Activity {
    private TextView mTextView;
    private ArrayList<Integer> number = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rx_android_main);
        mTextView = (TextView) findViewById(R.id.rx_textview);
        Observable.from(number).flatMap(i -> Observable.just(square(i)))
                .filter(number -> number != 25)
                .take(5)
                .subscribe(s -> System.out.println(s));
        ;
    }

    private int square(Integer i) {
        return i * i;
    }
}
