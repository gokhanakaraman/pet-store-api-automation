package helpers.common;

import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class HelperMethods {

    public static void checkStatus(Response res, int statusCode) {
        assertEquals("Status Check Fail!", statusCode, res.getStatusCode());
    }
}
