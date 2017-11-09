package com.example.korot.rx_login.app.utils;


import com.example.korot.rx_login.app.model.UserRealm;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RealmService implements IRealmService {
    private Realm mRealm;

    public RealmService(Realm realm) {
        this.mRealm = realm;
    }

    @Override
    public Realm get() {
        return mRealm;
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }

    @Override
    public void refresh() {
        mRealm.refresh();
    }

    @Override
    public <T extends RealmObject> Observable<T> addUser(T object, Class<T> clazz) {
        long id = 0;

        try {
            id = object instanceof UserRealm ? mRealm.where(clazz).max("id").intValue() :  mRealm.where(clazz).max("id").intValue() + 1;
        } catch (Exception e) {
            id = 0L;
        }

        ((UserRealm) object).setId(id);
         return Observable.just(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .flatMap(t -> Observable.just(t)
                        .doOnSubscribe(mRealm::beginTransaction)
                        .doOnUnsubscribe(() -> {
                            mRealm.commitTransaction();
                        })
                        .doOnError(Throwable::printStackTrace)
                        .doOnNext(next -> mRealm.copyToRealmOrUpdate(next))
                );
    }

    @Override
    public <T extends RealmObject> Observable<RealmResults<T>> getUser(Class<T> clazz) {
        return Observable.just(clazz)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(t -> Observable.just(t)
                        .doOnSubscribe(mRealm::beginTransaction)
                        .doOnUnsubscribe(() -> {
                            mRealm.commitTransaction();
                        })
                        .map(type -> mRealm.where(type).findAll()));
    }

    @Override
    public <T extends RealmObject> Observable<Class<T>> deleteUser(long id, Class<T> clazz) {
        return Observable.just(clazz)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRealm::beginTransaction)
                .doOnUnsubscribe(() -> {
                    mRealm.commitTransaction();
                })
                .doOnError(Throwable::printStackTrace)
                .doOnNext(type -> mRealm.where(type).equalTo("id", id).findFirst().removeFromRealm());
    }

    @Override
    public <T extends RealmObject> Observable<Class<T>> deleteAllUser(Class<T> clazz) {
        return Observable.just(clazz)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRealm::beginTransaction)
                .doOnUnsubscribe(() -> {
                    mRealm.commitTransaction();
                })
                .doOnError(Throwable::printStackTrace)
                .doOnNext(type -> mRealm.where(type).findAll().clear());
    }

    @Override
    public <T extends RealmObject> Observable<T> getLastUser(Class<T> clazz) {
        return Observable.just(clazz)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(t -> Observable.just(t)
                        .doOnSubscribe(mRealm::beginTransaction)
                        .doOnUnsubscribe(() -> {
                            mRealm.commitTransaction();
                        })
                        .map(type -> mRealm.where(type).findAll().last()));
    }
}
