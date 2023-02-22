package com.dimata.service.general.model.body;

public record BookBody(
        String name,
        String description,
        String author
) {
    public boolean isValid(){
        return name != null &&
                description != null &&
                author != null;
    }
}
