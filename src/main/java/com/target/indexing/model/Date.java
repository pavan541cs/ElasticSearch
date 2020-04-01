package com.target.indexing.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Date {
    private String date;

    public Date() {
        super();
    }

    public Date(String date) {
        this.date = date;
    }

    public String get$date() {
        return date;
    }

    public void set$date(String $date) {
        this.date = $date;
    }
}
