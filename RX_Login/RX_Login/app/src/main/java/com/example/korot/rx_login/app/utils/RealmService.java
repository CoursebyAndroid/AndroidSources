package com.example.korot.rx_login.app.utils;


import com.example.korot.rx_login.app.model.TestRealm;
import com.example.korot.rx_login.app.model.UserRealm;
import java.util.ArrayList;
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
    public <T extends RealmObject> Observable<T> addObject(T object, Class<T> clazz) {

        ArrayList<TestRealm>obj = new ArrayList<>();
        mRealm.beginTransaction();
        mRealm.copyToRealm(obj);
        mRealm.commitTransaction();

       if(object instanceof UserRealm){ (
               (UserRealm) object).setId("sosialToken");
       }
        if(object instanceof TestRealm){
           ((TestRealm) object).setId(((TestRealm) object).getId());
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
    public <T extends RealmObject> Observable<T> addArryaObject(T[] object, Class<T> clazz) {


        return null;
    }

    @Override
    public <T extends RealmObject> Observable<RealmResults<T>> getObject(Class<T> clazz) {
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
    public <T extends RealmObject> Observable<Class<T>> deleteObject(long id, Class<T> clazz) {
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
    public <T extends RealmObject> Observable<Class<T>> deleteAllObject(Class<T> clazz) {
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
    public <T extends RealmObject> Observable<T> getLastObject(Class<T> clazz) {
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
