package com.example.korot.rx_login.app.utils;

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
    <T extends RealmObject> Observable<T> addUser(T object, Class<T> clazz);
    <T extends RealmObject> Observable<RealmResults<T>> getUser(Class<T> clazz);
    <T extends RealmObject> Observable<Class<T>> deleteUser(long id, Class<T> clazz);
    <T extends RealmObject> Observable<Class<T>> deleteAllUser(Class<T> clazz);
    <T extends RealmObject> Observable<T> getLastUser(Class<T> clazz);
}
