package com.dimata.service.general.dto;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ResponseData<T> {

    private boolean status;
    private List<String> messages;
    private List<T> payload;

    public ResponseData(Set<? extends ConstraintViolation<?>> violations) {
        this.setStatus(false);
        this.setMessages(violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()
                ));
        this.setPayload(null);
    }

    public ResponseData(T dto) {
        this.status = true;
        this.payload = List.of(dto);
        this.messages = List.of("Book saved successfully");
    }

    public ResponseData(boolean status, List<String> messages, List<T> payload) {
        this.status = status;
        this.messages = messages;
        this.payload = payload;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<T> getPayload() {
        return payload;
    }

    public void setPayload(List<T> payload) {
        this.payload = payload;
    }
}
