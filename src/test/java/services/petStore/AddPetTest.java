package services.petStore;

import helpers.common.HelperMethods;
import helpers.petStore.PetStoreHelper;
import io.restassured.response.Response;
import model.petStore.request.PetRequest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddPetTest {
    public PetStoreHelper petStoreHelper = new PetStoreHelper();

    @BeforeClass
    public static void background() {
        //  do before here
    }

    @Test
    public void happyPathTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("1", "trained");
        String[] photoUrls = new String[]{"url1", "url2", "url3"};

        Response response = petStoreHelper.addNewPet("1", "12", "dog", "nina", photoUrls, tag1, "available");

        HelperMethods.checkStatus(response, 200);
        Assert.assertEquals(response.jsonPath().getString("id"), "1");
        Assert.assertEquals(response.jsonPath().getString("category.name"), "dog");
        Assert.assertEquals(response.jsonPath().getString("name"), "nina");
        Assert.assertEquals(response.jsonPath().getString("status"), "available");
    }

    @Test
    public void invalidIdTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("1", "trained");
        String[] photoUrls = new String[]{"url1", "url2", "url3"};

        Response response = petStoreHelper.addNewPet("sdf", "12", "dog", "nina", photoUrls, tag1, "available");

        HelperMethods.checkStatus(response, 500);
        Assert.assertEquals(response.jsonPath().getString("type"), "unknown");
        Assert.assertEquals(response.jsonPath().getString("message"), "something bad happened");
    }

    @Test
    public void invalidNameTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("1", "trained");
        String[] photoUrls = new String[]{"url1", "url2", "url3"};

        Response response = petStoreHelper.addNewPet("1", "12", "dog", "1", photoUrls, tag1, "available");

        HelperMethods.checkStatus(response, 200);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("category.name"), "dog");
        Assert.assertEquals(response.jsonPath().getString("name"), "1");
        Assert.assertEquals(response.jsonPath().getString("status"), "available");
    }

    @Test
    public void invalidStatusTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("1", "trained");
        String[] photoUrls = new String[]{"url1", "url2", "url3"};

        Response response = petStoreHelper.addNewPet("1", "12", "dog", "nina", photoUrls, tag1, "111");

        HelperMethods.checkStatus(response, 200);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("category.name"), "dog");
        Assert.assertEquals(response.jsonPath().getString("name"), "nina");
        Assert.assertEquals(response.jsonPath().getString("status"), "111");
    }

    @Test
    public void invalidCategoryTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("1", "trained");
        String[] photoUrls = new String[]{"url1", "url2", "url3"};

        Response response = petStoreHelper.addNewPet("1", "12", "111", "nina", photoUrls, tag1, "available");

        HelperMethods.checkStatus(response, 200);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("category.name"), "111");
        Assert.assertEquals(response.jsonPath().getString("name"), "nina");
        Assert.assertEquals(response.jsonPath().getString("status"), "available");
    }

    @Test
    public void invalidTagTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("tag", "111");
        String[] photoUrls = new String[]{"url1", "url2", "url3"};

        Response response = petStoreHelper.addNewPet("1", "12", "111", "nina", photoUrls, tag1, "available");

        HelperMethods.checkStatus(response, 500);
        Assert.assertEquals(response.jsonPath().getString("type"), "unknown");
        Assert.assertEquals(response.jsonPath().getString("message"), "something bad happened");
    }

    @Test
    public void invalidPhotoUrlTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("tag", "111");
        String[] photoUrls = new String[]{"1", "2", "3"};

        Response response = petStoreHelper.addNewPet("1", "12", "111", "nina", photoUrls, tag1, "available");

        HelperMethods.checkStatus(response, 500);
        Assert.assertEquals(response.jsonPath().getString("type"), "unknown");
        Assert.assertEquals(response.jsonPath().getString("message"), "something bad happened");
    }

    @Test
    public void emptyIdTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("1", "trained");
        String[] photoUrls = new String[]{"url1", "url2", "url3"};

        Response response = petStoreHelper.addNewPet("", "12", "dog", "nina", photoUrls, tag1, "available");

        HelperMethods.checkStatus(response, 200);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("category.name"), "dog");
        Assert.assertEquals(response.jsonPath().getString("name"), "nina");
        Assert.assertEquals(response.jsonPath().getString("status"), "available");
    }

    @Test
    public void emptyNameTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("1", "trained");
        String[] photoUrls = new String[]{"url1", "url2", "url3"};

        Response response = petStoreHelper.addNewPet("1", "12", "dog", "", photoUrls, tag1, "available");

        HelperMethods.checkStatus(response, 200);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("category.name"), "dog");
        Assert.assertEquals(response.jsonPath().getString("name"), "");
        Assert.assertEquals(response.jsonPath().getString("status"), "available");
    }

    @Test
    public void emptyPhotoUrlTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("1", "trained");
        String[] photoUrls = new String[]{"", "", ""};

        Response response = petStoreHelper.addNewPet("1", "12", "dog", "nina", photoUrls, tag1, "available");

        HelperMethods.checkStatus(response, 200);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("category.name"), "dog");
        Assert.assertEquals(response.jsonPath().getString("name"), "nina");
        Assert.assertEquals(response.jsonPath().getString("status"), "available");
        Assert.assertNotNull(response.jsonPath().getString("photoUrls"));
    }

    @Test
    public void emptyTagsTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("", "");
        String[] photoUrls = new String[]{"url1", "url2", "url3"};

        Response response = petStoreHelper.addNewPet("1", "12", "dog", "nina", photoUrls, tag1, "available");

        HelperMethods.checkStatus(response, 200);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("category.name"), "dog");
        Assert.assertEquals(response.jsonPath().getString("name"), "nina");
        Assert.assertEquals(response.jsonPath().getString("status"), "available");
        Assert.assertNotNull(response.jsonPath().getString("tags"));
    }

    @Test
    public void emptyStatusTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("1", "trained");
        String[] photoUrls = new String[]{"url1", "url2", "url3"};

        Response response = petStoreHelper.addNewPet("1", "12", "dog", "nina", photoUrls, tag1, "");

        HelperMethods.checkStatus(response, 200);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("category.name"), "dog");
        Assert.assertEquals(response.jsonPath().getString("name"), "nina");
        Assert.assertEquals(response.jsonPath().getString("status"), "");
        Assert.assertNotNull(response.jsonPath().getString("tags"));
    }

    @Test
    public void emptyCategoryTest() {
        PetRequest.Tags tag1 = new PetRequest.Tags("1", "trained");
        String[] photoUrls = new String[]{"url1", "url2", "url3"};

        Response response = petStoreHelper.addNewPet("1", "", "", "nina", photoUrls, tag1, "pending");

        HelperMethods.checkStatus(response, 200);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("category.name"), "");
        Assert.assertEquals(response.jsonPath().getString("name"), "nina");
        Assert.assertEquals(response.jsonPath().getString("status"), "pending");
        Assert.assertNotNull(response.jsonPath().getString("tags"));
    }
}
