package com.example.leshchenko.authsosialprojectmodule.dao;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;



public interface IRealmService {

    Realm get();
    void closeRealm();
    void refresh();
    <T extends RealmObject> Observable<T> addUser(T object, Class<T> clazz);
    <T extends RealmObject> Observable<RealmResults<T>> getUser(Class<T> clazz);
    <T extends RealmObject> Observable<Class<T>> deleteUser(long id, Class<T> clazz);
}
