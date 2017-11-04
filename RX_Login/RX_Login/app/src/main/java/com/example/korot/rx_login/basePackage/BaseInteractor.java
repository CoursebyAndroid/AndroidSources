package com.example.korot.rx_login.basePackage;

import com.example.korot.rx_login.app.utils.IApiServise;

/**
 * Created by korot on 01.10.2017.
 */

public abstract class BaseInteractor<I extends  IInteractorContract>{

    protected IApiServise api;
}

