package com.dimata.service.general.dto;

import com.dimata.service.general.model.entitiy.Member;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@ApplicationScoped
public class MemberData {

    private Long id;

    @NotBlank(message = "Member name cannot be empty")
    private String name;

    @NotBlank(message = "Member address cannot be empty")
    private String address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public MemberData(Member member) {
        this.setId(member.getId());
        this.setName(member.getName());
        this.setAddress(member.getAddress());
        this.setCreatedAt(member.getCreatedAt());
        this.setUpdatedAt(member.getUpdatedAt());
    }

    public MemberData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
