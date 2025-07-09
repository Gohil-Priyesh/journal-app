package com.priyesh.myFirstProject.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuotesResponse {

    /// if the api response is in snake case then use the @JsonProperty("status_code")
    private int statusCode;
    private DataInner data;
    private String message;
    private boolean success;

    @Getter
    @Setter
    public static class DataInner {
        private List<QuoteItem> data;
    }

    @Getter
    @Setter
    public static class QuoteItem {

        private String author;
        private String content;
    }
}
