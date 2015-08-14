package com.rx.operators.transformingObservables;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.rx.BaseActivity;
import com.rx.R;
import com.rx.executor.JobExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Shekar on 8/13/15.
 * http://fernandocejas.com/2015/01/11/rxjava-observable-tranformation-concatmap-vs-flatmap/
 */
public class FlatmapVsConcatmap  extends BaseActivity {

    @Bind(R.id.tv_streamOriginalOrder) TextView tv_streamOriginalOrder;
    @Bind(R.id.tv_flatMapResult) TextView tv_flatMapResult;
    @Bind(R.id.tv_concatMapResult) TextView tv_concatMapResult;

    private  Executor jobExecutor;
    final StringBuilder stringBuilder = new StringBuilder(40);

    private ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10));
    private static final String SEPARATOR = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_flatmap_vs_concatmap);
        ButterKnife.bind(this);
        jobExecutor = JobExecutor.getInstance();
        populateData();

    }
    private void populateData() {
        StringBuilder stringBuilder = new StringBuilder(15);
        for (int number : numbers) {
            stringBuilder.append(number);
            stringBuilder.append(SEPARATOR);
        }
        this.tv_streamOriginalOrder.setText(stringBuilder.toString());
    }

    @OnClick(R.id.btn_flatMap) void onFlatMapClick() {
        stringBuilder.delete(0,stringBuilder.length());
        Observable.from(numbers)
                .flatMap(SQUARE_OF_NUMBER)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flatmapObserver);
    }
    @OnClick(R.id.btn_concatMap) void onConcatMapClick() {
        stringBuilder.delete(0,stringBuilder.length());
        Observable.from(numbers)
                .concatMap(SQUARE_OF_NUMBER)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(concatMapObserver);
    }
    private final Func1<Integer, Observable<Integer>> SQUARE_OF_NUMBER =
            new Func1<Integer, Observable<Integer>>() {
                @Override public Observable<Integer> call(Integer number) {
                    return squareOfAsync(number);
                }
            };
    final Observer<Integer> flatmapObserver = new Observer<Integer>() {
        @Override public void onNext(Integer number) {
            stringBuilder.append(number);
            stringBuilder.append(SEPARATOR);
        }

        @Override public void onCompleted() {
            tv_flatMapResult.setText(stringBuilder.toString());
            showToast("flatMap() Sequence Completed!!!");
        }

        @Override public void onError(Throwable e) {
            // handle the exception
        }
    };

    final Observer<Integer> concatMapObserver = new Observer<Integer>() {
        @Override public void onNext(Integer number) {
            stringBuilder.append(number);
            stringBuilder.append(SEPARATOR);
        }

        @Override public void onCompleted() {
            tv_concatMapResult.setText(stringBuilder.toString());
            showToast("concatMap() Sequence Completed!!!");
        }

        @Override public void onError(Throwable e) {
            // handle the exception
        }
    };

    public Observable<Integer> squareOfAsync(int number) {
        return Observable.just(number * number).subscribeOn(Schedulers.from(jobExecutor));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
