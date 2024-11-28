package controllers;

public class Repository {

    // The request body structure that you send in the POST request
    public static class RepositoryRequest {
        private String name;
        private Owner ownerObject;

        // Constructor
        public RepositoryRequest(String name) {
            this.name = name;
        }

        // Getter
        public String getName() {
            return name;
        }

        // Setter
        public void setName(String name) {
            this.name = name;
        }

        public static class Owner {
            private String id;
            private String url;
        }
    }

    // The response structure that you expect from the API after the POST request
    public static class RepositoryResponse {
        private String id;
        private String name;

        // Getter and Setter for 'id'
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        // Getter and Setter for 'name'
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
