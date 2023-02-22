package com.dimata.service.general.model.body;

public record MemberBody(
        String name,
        String address
) {

    public boolean isValid() {
        return name != null &&
                address != null;
    }

}


