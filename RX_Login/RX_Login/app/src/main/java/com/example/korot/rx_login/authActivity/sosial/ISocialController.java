package com.example.korot.rx_login.authActivity.sosial;


import com.google.android.gms.auth.api.signin.GoogleSignInResult;

/**
 * Created by Root on 03.11.2017.
 */

public interface ISocialController {
    void onSelect(int select);
    void onResultGoogle(GoogleSignInResult res);
}
