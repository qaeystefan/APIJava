package controllers;

public class Repository {

    // The request body structure that you send in the POST request
    public static class RepositoryRequest {
        private String name;
        private Owner ownerObject;
        private String content;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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
        private String content;

        // Getter and Setter for 'id'
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }


    }
}
