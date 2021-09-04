package config;

public class APIConstants {

    public static class PetStoreURL {
        public static final String BASE_URL = "https://petstore.swagger.io/v2/";
        public static final String PET_SUFFIX = "pet/";
        public static final String STORE_SUFFIX = "store/";
        public static final String USER_SUFFIX = "user/";

        public static class Endpoint {
            public static final String UPLOAD_IMAGE = "uploadImage";
            public static final String FIND_BY_STATUS = "findByStatus";
            public static final String INVENTORY = "inventory";
            public static final String ORDER = "order";
            public static final String CREATE_WITH_LIST = "createWithList";
            public static final String LOGIN = "login";
            public static final String LOGOUT = "logout";
            public static final String CREATE_WITH_ARRAY = "createWithArray";
        }
    }
}
