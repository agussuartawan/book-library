package com.dimata.service.general.dto.mapper;

import com.dimata.service.general.dto.MemberData;
import com.dimata.service.general.model.entitiy.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    Member toEntity(MemberData dto);

    MemberData toDTO(Member member);
}

