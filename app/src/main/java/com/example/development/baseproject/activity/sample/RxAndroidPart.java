package com.example.development.baseproject.activity.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.development.baseproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Sekhar on 3/29/15.
 */
public class RxAndroidPart extends Activity {

    @Bind(R.id.rx_textview)
    TextView title;

    // Observable<List<String>> query(String text);

    public static void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                System.out.println("Hello " + s + "!");
            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_android_main);
        ButterKnife.bind(this);
//        Observable.just("Hello, world!")
//                .map(s -> s + " -Dan")
//                .subscribe(s -> title.setText(s));

//        Observable.just("Hello, world!")
//        .map(s -> s.hashCode())
//                .subscribe(s ->title.setText(Integer.toString(s)));

        Observable.just("Hello, world!")
                .map(s -> s + " -Dan")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> title.setText(s));


//        Observable.just("Hello, world!")
//                .flatMap(urls -> Observable.from())
//                .flatMap(url -> getTitle(url))
//                .subscribe(title -> System.out.println(title));
//
//        hello("1","2");


//        Observable<List<String>> query("hello world")
//                .flatMap(new Func1<List<String>, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(List<String> urls) {
//                        return Observable.from(urls);
//                    }
//                })
//                .subscribe(url -> System.out.println(url));


    }


}
