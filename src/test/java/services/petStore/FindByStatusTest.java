package services.petStore;

import helpers.common.HelperMethods;
import helpers.petStore.PetStoreHelper;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;

public class FindByStatusTest {
    public PetStoreHelper petStoreHelper = new PetStoreHelper();

    @BeforeClass
    public static void background() {
        //  do before here
    }

    @Test
    public void happyPathTest() {
        Response response = petStoreHelper.findByStatus("available");

        HelperMethods.checkStatus(response, 200);
        JSONArray jsonArray = new JSONArray(response.body().asString());
        List<String> responseList = new ArrayList<String>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            responseList.add((jsonArray.getJSONObject(i).getString("status")));
        }

        org.junit.Assert.assertThat(responseList, hasItems("available"));
    }

    @Test
    public void invalidStatusTest() {
        Response response = petStoreHelper.findByStatus("sfd");
        HelperMethods.checkStatus(response, 200);
        Assert.assertEquals(response.asString().replace("[]", ""), "");
    }

    @Test
    public void invalidStatusTypeTest() {
        Response response = petStoreHelper.findByStatus("1");

        HelperMethods.checkStatus(response, 200);
        JSONArray jsonArray = new JSONArray(response.body().asString());
        List<String> responseList = new ArrayList<String>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            responseList.add((jsonArray.getJSONObject(i).getString("status")));
        }

        org.junit.Assert.assertThat(responseList, hasItems("1"));
    }

    @Test
    public void requiredFieldStatusTest() {
        Response response = petStoreHelper.findByStatus("");

        HelperMethods.checkStatus(response, 200);
        JSONArray jsonArray = new JSONArray(response.body().asString());
        List<String> responseList = new ArrayList<String>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            responseList.add((jsonArray.getJSONObject(i).getString("status")));
        }

        org.junit.Assert.assertThat(responseList, hasItems(""));
    }
}
