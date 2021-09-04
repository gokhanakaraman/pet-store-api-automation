package services.petStore;

import helpers.common.HelperMethods;
import helpers.petStore.PetStoreHelper;
import io.restassured.response.Response;
import model.petStore.request.PetRequest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FindPetTest {
    public PetStoreHelper petStoreHelper = new PetStoreHelper();

    @BeforeClass
    public static void background() {
        //  do before here
    }

    @Test
    public void happyPathTest() {
        Response response = petStoreHelper.findPet("1");

        HelperMethods.checkStatus(response, 200);
        Assert.assertEquals(response.jsonPath().getString("id"), "1");
        Assert.assertEquals(response.jsonPath().getString("name"), "nina");
    }

    @Test
    public void invalidIdTest() {
        Response response = petStoreHelper.findPet("dsfsdf");

        HelperMethods.checkStatus(response, 404);
        Assert.assertEquals(response.jsonPath().getString("type"), "unknown");
        Assert.assertTrue(response.jsonPath().getString("message").contains("java.lang.NumberFormatException: For input string"));
    }

    @Test
    public void emptyIdTest() {
        Response response = petStoreHelper.findPet("");

        HelperMethods.checkStatus(response, 405);
    }
}
