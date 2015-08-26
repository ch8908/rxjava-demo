package com.demo.rxjavaexample;

import android.test.AndroidTestCase;
import com.demo.rxjavaexample.app.AccountDaemon;
import com.demo.rxjavaexample.model.Account;
import rx.Subscription;
import rx.observers.TestSubscriber;

/**
 * Created by Kros on 8/26/15.
 */
public class AccountDaemonTests extends AndroidTestCase {
    private AccountDaemon accountDaemon;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        accountDaemon = new AccountDaemon();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testLogin_empty_email() throws Exception {
        Account account = Account.createLoginAccount(null, null);
        try {
            accountDaemon.login(account).toBlocking().single();
            fail("method should throw exception");
        } catch (Throwable ex) {
            assertEquals("Email or password can not be empty.", ex.getLocalizedMessage());
        }
    }

    public void testLogin() throws Exception {
        Account account = Account.createLoginAccount("email", "password");
        Account result = accountDaemon.login(account).toBlocking().single();
        assertEquals("email", result.getEmail());
        assertEquals("password", result.getPassword());
    }

    public void testLogin_test_subscriber() {
        TestSubscriber<Account> testSubscriber = new TestSubscriber<>();
        Account account = Account.createLoginAccount("email", "password");
        accountDaemon.login(account).subscribe(testSubscriber);

        Account expect = Account.createLoginAccount("email", "password");
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(expect);
    }
}
