package com.example.korot.rx_login.authActivity.sosial;


import com.example.korot.rx_login.app.model.TestRealm;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import java.util.ArrayList;

public interface ISocialController {
    void onSelect(int select);
    void onResultGoogle(GoogleSignInResult res);
    void onTestArryaRealm(ArrayList<TestRealm> arr);
}
