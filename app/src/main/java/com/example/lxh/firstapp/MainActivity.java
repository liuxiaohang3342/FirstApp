package com.example.lxh.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lxh.firstapp.base.core.http.HttpMethod;
import com.example.lxh.firstapp.base.core.http.ResponseTransformer;
import com.example.lxh.firstapp.base.core.http.SchedulerProvider;
import com.example.lxh.firstapp.base.network.Api;
import com.example.lxh.firstapp.bean.User;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpMethod.getInstance().createService(Api.class).getUser("0")
                .compose(ResponseTransformer.handleResult())
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User o) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        String[] items = {
                "1", "2", "3", "4", "5"
        };

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {

            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull String integer) throws Exception {
                return Observable.fromArray(integer);
            }
        }).map(new Function<String, Integer>() {

            @Override
            public Integer apply(@NonNull String s) throws Exception {
                return Integer.valueOf(s);
            }
        }).compose(new ObservableTransformer<Integer, Integer>() {
            @Override
            public ObservableSource<Integer> apply(@NonNull Observable<Integer> upstream) {
                return null;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
