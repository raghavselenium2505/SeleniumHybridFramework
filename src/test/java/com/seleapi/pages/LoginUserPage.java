package com.seleapi.pages;

import org.openqa.selenium.By;

import com.seleapi.pages.base.BasePage;

public class LoginUserPage extends BasePage {

    private By username =
            By.id("username");

    private By password =
            By.id("password");

    private By loginButton =
            By.id("login");

    public void login(
            String user,
            String pass) {

        type(username, user);

        type(password, pass);

        click(loginButton);
    }
}