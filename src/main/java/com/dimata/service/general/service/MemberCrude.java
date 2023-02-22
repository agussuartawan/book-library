package com.dimata.service.general.service;

import com.dimata.service.general.model.body.MemberBody;
import com.dimata.service.general.model.entitiy.Member;
import com.dimata.service.general.repository.MemberRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class MemberCrude {

    @Inject
    MemberRepository memberRepository;

    public Member create(MemberBody body)
    {
        Objects.requireNonNull(body);

        if(!body.isValid())
        {
            throw new IllegalArgumentException("Member not valid!");
        }

        var member = new Member();
        member.name = body.name();
        member.address = body.address();
        member.persist();

        return member;
    }

    public Member update(MemberBody body, long id)
    {
        Objects.requireNonNull(body);

        var member = Member.findByIdOptional(id)
                .map(Member.class::cast)
                .orElseThrow(() -> new NullPointerException("Member not found."));

        if(body.name() != null)
        {
            member.name = body.name();
        }

        if(body.address() != null)
        {
            member.address = body.address();
        }

        return member;
    }

    public List<Member> listAll()
    {
        return memberRepository.listAll();
    }

    public

}
