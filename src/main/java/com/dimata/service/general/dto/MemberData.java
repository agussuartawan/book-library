package com.dimata.service.general.dto;

import com.dimata.service.general.model.entitiy.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@ApplicationScoped
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberData {

    private Long id;

    @NotBlank(message = "Member name cannot be empty")
    private String name;

    @NotBlank(message = "Member address cannot be empty")
    private String address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public MemberData(Member member)
    {
        this.setId(member.getId());
        this.setName(member.getName());
        this.setAddress(member.getAddress());
        this.setCreatedAt(member.getCreatedAt());
        this.setUpdatedAt(member.getUpdatedAt());
    }

}
