package com.example.korot.rx_login.app.utils;

import com.example.korot.rx_login.app.model.UserRealm;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by korot on 24.09.2017.
 */

public interface IRealmService {
    Realm get();
    void closeRealm();
    void refresh();
    <T extends RealmObject> Observable<T> addObject(T object, Class<T> clazz);
    <T extends RealmObject> Observable<RealmResults<T>> getObject(Class<T> clazz);
    <T extends RealmObject> Observable<Class<T>> deleteObject(long id, Class<T> clazz);
    <T extends RealmObject> Observable<Class<T>> deleteAllObject(Class<T> clazz);
    <T extends RealmObject> Observable<T> getLastObject(Class<T> clazz);
    <T extends RealmObject> Observable<T> addArryaObject(T[] object, Class<T> clazz);


}
