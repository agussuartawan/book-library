package com.dimata.service.general.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApplicationScoped
public class ResponseData<T> {

    private boolean status;
    private List<String> messages;
    private List<T> payload;

    public ResponseData(Set<? extends ConstraintViolation<?>> violations) {
        this.setStatus(false);
        this.setMessages(violations.stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList()
        ));
        this.setPayload(null);
    }

    public ResponseData(T dto)
    {
        this.status = true;
        this.payload = List.of(dto);
        this.messages = List.of("Book saved successfully");
    }
}
