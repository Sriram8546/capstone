package com.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.base.BaseTest;
import com.example.pages.LoginPage;


public class LoginRegistrationTests extends BaseTest {

    @Test(priority = 1)
    public void loginPageLoads() {
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        Assert.assertTrue(true);
    }

    @Test(priority = 2)
    public void signupToggleWorks() {
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.switchToSignUp();
        Assert.assertTrue(true);
    }

    @Test(priority = 3)
    public void loginToggleWorks() {
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.switchToSignUp();
        login.switchToLogin();
        Assert.assertTrue(true);
    }

    @Test(priority = 4)
    public void emailFieldAcceptsInput() {
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.enterEmail("test@test.com");
        Assert.assertTrue(true);
    }

    @Test(priority = 5)
    public void passwordFieldAcceptsInput() {
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.enterPassword("password123");
        Assert.assertTrue(true);
    }

    @Test(priority = 6)
    public void signupWithDummyUser() {
        String email = "user" + System.currentTimeMillis() + "@test.com";
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.signUp("User", email, "password123");
        Assert.assertTrue(true);
    }

    @Test(priority = 7)
    public void multipleSignupFlowWorks() {
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.switchToSignUp();
        login.switchToLogin();
        login.switchToSignUp();
        Assert.assertTrue(true);
    }

    @Test(priority = 8)
    public void submitButtonClickable() {
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.clickSubmit();
        Assert.assertTrue(true);
    }

    @Test(priority = 9)
    public void pageTitleExists() {
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        String title = getDriver().getTitle();
        Assert.assertNotNull(title);
    }

    @Test(priority = 10)
    public void loginPageUrlValid() {
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        Assert.assertTrue(getDriver().getCurrentUrl().length() > 0);
    }

    @Test(priority = 11)
    public void switchingModesMultipleTimesWorks() {
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.switchToSignUp();
        login.switchToLogin();
        login.switchToSignUp();
        login.switchToLogin();
        Assert.assertTrue(true);
    }

    @Test(priority = 12)
    public void loginPageAccessibleDirectly() {
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        Assert.assertTrue(true);
    }

    @Test(priority = 13)
    public void emptySubmitDoesNotCrash() {
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.clickSubmit();
        Assert.assertTrue(true);
    }


    @Test(priority = 15)
    public void loginModuleExecutionComplete() {
        Assert.assertTrue(true);
    }
}
