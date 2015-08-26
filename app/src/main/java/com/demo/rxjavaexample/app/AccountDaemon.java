package com.demo.rxjavaexample.app;

import android.text.TextUtils;
import com.demo.rxjavaexample.model.Account;
import rx.Observable;

/**
 * Created by Kros on 8/26/15.
 */
public class AccountDaemon {
    public AccountDaemon() {
    }

    public Observable<Account> login(final Account account) {
        return Observable.just(account).map(account1 -> {
            checkAccount(account);
            return account;
        });
    }

    private void checkAccount(Account account) throws IllegalArgumentException {
        if (TextUtils.isEmpty(account.getEmail())
                || TextUtils.isEmpty(account.getPassword())) {
            throw new IllegalArgumentException("Email or password can not be empty.");
        }
    }
}
