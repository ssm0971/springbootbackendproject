package com.example.hope_dog.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShelterResponse {
    private Response response;

    @Data
    public static class Response {
        private Header header;
        private Body body;

        @Data
        public static class Header {
            private long reqNo;
            private String resultCode;
            private String resultMsg;
        }

        @Data
        public static class Body {
            private Item items;

            @Data
            public static class Item {
                private List<ShelterInfo> item;
            }
        }
    }
}

