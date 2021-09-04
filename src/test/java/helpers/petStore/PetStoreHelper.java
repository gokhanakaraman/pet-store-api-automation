package helpers.petStore;

import client.RestAssuredClient;
import config.APIConstants;
import io.restassured.response.Response;
import model.petStore.request.PetRequest;

import java.util.HashMap;
import java.util.Map;

public class PetStoreHelper extends RestAssuredClient {

    public PetStoreHelper() {
        super(APIConstants.PetStoreURL.BASE_URL);
    }

    public Response addNewPet(String id, String categoryId, String categoryName, String name, String[] photoUrls, PetRequest.Tags tag, String status) {
        PetRequest.Category category = PetRequest.Category.newBuilder()
                .setId(categoryId)
                .setName(categoryName).build();
        PetRequest.Tags tags = PetRequest.Tags.newBuilder()
                .setTag(tag).build();

        PetRequest petRequest = PetRequest.newBuilder()
                .withCategory(category)
                .setId(id)
                .setName(name)
                .setPhotoUrls(photoUrls)
                .setTags(tags)
                .setStatus(status).build();

        return post(APIConstants.PetStoreURL.PET_SUFFIX, null, null, petRequest);
    }

    public Response findPet(String petId) {
        return get(APIConstants.PetStoreURL.PET_SUFFIX + petId, null, null, null);
    }

    public Response findByStatus(String status) {
        Map<String, String> params = new HashMap<String, String>() {{
            put("status", status);
        }};
        return get(APIConstants.PetStoreURL.PET_SUFFIX + APIConstants.PetStoreURL.Endpoint.FIND_BY_STATUS, params, null, null);
    }
}
