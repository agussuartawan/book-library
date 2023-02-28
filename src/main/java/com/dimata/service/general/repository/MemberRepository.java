package com.dimata.service.general.repository;

import com.dimata.service.general.dto.MemberData;
import com.dimata.service.general.model.entitiy.Member;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MemberRepository implements PanacheRepository<Member> {

    public MemberData save(Member member, MemberData dto)
    {
        member.setName(dto.getName());
        member.setAddress(dto.getAddress());
        member.persistAndFlush();

        return new MemberData(member);
    }

}
