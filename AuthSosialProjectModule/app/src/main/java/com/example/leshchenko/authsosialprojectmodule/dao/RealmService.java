package com.example.leshchenko.authsosialprojectmodule.dao;


import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RealmService implements IRealmService{

    private Realm mRealm;

    public RealmService(final Realm realm) {
        mRealm = realm;
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
        long id;
        try {
            id = mRealm.where(clazz).max("realmId").intValue() + 1;
        } catch (Exception e) {
            id = 0L;
        }

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
                .doOnNext(type -> mRealm.where(type).equalTo("realmId", id).findFirst().removeFromRealm());
    }
}