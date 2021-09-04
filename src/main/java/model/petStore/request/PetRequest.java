package model.petStore.request;

import java.util.ArrayList;
import java.util.List;

public class PetRequest {

    public String id;

    private PetRequest(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.photoUrls = builder.photoUrls;
        this.tags = builder.tags;
        this.status = builder.status;
        this.category = builder.category;
    }

    public static class Category {
        public String id;
        public String name;

        private Category(Builder builder) {
            this.id = builder.id;
            this.name = builder.name;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static final class Builder {
            private String id;
            private String name;

            private Builder() {
            }

            public Builder setId(String id) {
                this.id = id;
                return this;
            }

            public Builder setName(String name) {
                this.name = name;
                return this;
            }

            public Category build() {
                return new Category(this);
            }
        }
    }

    public static class Tags {
        public String id;
        public String name;

        public Tags(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Tags(Builder builder) {
            this.id = builder.id;
            this.name = builder.name;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static final class Builder {
            private String id;
            private String name;

            private Builder() {
            }

            public Builder setId(String id) {
                this.id = id;
                return this;
            }

            public Builder setName(String name) {
                this.name = name;
                return this;
            }

            public Builder setTag(Tags val) {
                id = val.id;
                name = val.name;
                return this;
            }

            public Tags build() {
                return new Tags(this);
            }
        }
    }

    public String name;
    public String[] photoUrls;
    public List<Tags> tags;
    public String status;
    public Category category;

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String name;
        private String[] photoUrls;
        private List<Tags> tags = new ArrayList<>();
        private String status;
        public Category category;

        private Builder() {
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder withCategory(Category val) {
            category = val;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPhotoUrls(String[] photoUrls) {
            this.photoUrls = photoUrls;
            return this;
        }

        public Builder setTags(Tags val) {
            tags.add(val);
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public PetRequest build() {
            return new PetRequest(this);
        }
    }
}
