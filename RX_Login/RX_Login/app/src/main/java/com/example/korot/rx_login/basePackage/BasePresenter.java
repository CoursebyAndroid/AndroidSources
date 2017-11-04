package com.example.korot.rx_login.basePackage;

import com.example.korot.rx_login.app.utils.INetworkCheck;
import com.example.korot.rx_login.app.utils.IRealmService;
import com.example.korot.rx_login.app.utils.IValidator;

/**
 * Created by korot on 01.10.2017.
 */

public abstract class BasePresenter<V extends IBaseView, I extends IInteractorContract> {

    protected V view;
    protected I interactor;
    protected IValidator validator;
    protected IRealmService realmService;
    protected INetworkCheck networkCheck;

    public void init(V view) {
        this.view = view;
    }


    public void dismiss() {
        this.view = null;
    }

}
