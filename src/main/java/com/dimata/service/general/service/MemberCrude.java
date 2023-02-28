package com.dimata.service.general.service;

import com.dimata.service.general.dto.MemberData;
import com.dimata.service.general.model.entitiy.Member;
import com.dimata.service.general.repository.MemberRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class MemberCrude {

    @Inject
    MemberRepository memberRepository;

    public MemberData create(MemberData dto)
    {
        Objects.requireNonNull(dto);
        return memberRepository.save(new Member(), dto);
    }

    public MemberData update(MemberData dto, long id)
    {
        Objects.requireNonNull(dto);

        var member = Member.findByIdOptional(id)
                .map(Member.class::cast)
                .orElseThrow(() -> new NullPointerException("Member not found."));

        return memberRepository.save(member, dto);
    }

    public List<MemberData> listAll()
    {
        return memberRepository.streamAll()
                .map(member -> {
                    MemberData dto = new MemberData();
                    dto.setId(member.getId());
                    dto.setName(member.getName());
                    dto.setAddress(member.getAddress());
                    dto.setCreatedAt(member.getCreatedAt());
                    dto.setUpdatedAt(member.getUpdatedAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<MemberData> search(String keyword)
    {
        return memberRepository.find(
                "name LIKE CONCAT('%', ?1,'%') OR address LIKE CONCAT('', ?1, '')", keyword)
                .stream()
                .map(member -> {
                    MemberData dto = new MemberData();
                    dto.setId(member.getId());
                    dto.setName(member.getName());
                    dto.setAddress(member.getAddress());
                    dto.setCreatedAt(member.getCreatedAt());
                    dto.setUpdatedAt(member.getUpdatedAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public void deleteById(long id)
    {
        var member = memberRepository.findByIdOptional(id)
                .map(Member.class::cast)
                .orElseThrow(() -> new NullPointerException("Member not found"));
        memberRepository.delete(member);
    }

    public MemberData findById(long id)
    {
        var member = memberRepository.findByIdOptional(id)
                .map(Member.class::cast)
                .orElseThrow(() -> new NullPointerException("Member not found"));

        return new MemberData(
                member.getId(),
                member.getName(),
                member.getAddress(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }

}
