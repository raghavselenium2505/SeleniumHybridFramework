package com.seleapi.tests.api;

import org.testng.annotations.Test;

import com.seleapi.api.APIRequestManager;

import io.restassured.response.Response;

public class SampleAPITest {

    @Test

    public void getUsersTest() {

        Response response =

                APIRequestManager.get(

                        "https://reqres.in/api/users?page=2");

        System.out.println(

                response.getBody().asPrettyString());
    }
}