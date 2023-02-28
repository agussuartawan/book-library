package com.dimata.service.general.model.body;

import com.dimata.service.general.dto.ResponseData;

public record BookBody(
        String name,
        String description,
        String author
) {
    public boolean isValid()
    {
        return name != null &&
                description != null &&
                author != null;
    }
}
