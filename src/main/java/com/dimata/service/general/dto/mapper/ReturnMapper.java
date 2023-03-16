package com.dimata.service.general.dto.mapper;

import com.dimata.service.general.dto.ReturnData;
import com.dimata.service.general.model.entitiy.Borrow;
import com.dimata.service.general.model.entitiy.Return;
import com.dimata.service.general.repository.BorrowRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BorrowMapper.class})
public interface ReturnMapper {

    ReturnMapper INSTANCE = Mappers.getMapper(ReturnMapper.class);

    @Mappings({
            @Mapping(source = "dto.borrowId", target = "borrow", qualifiedByName = "borrowIdToBorrow")
    })
    Return toEntity(ReturnData dto);

    @Mapping(source = "borrowId", target = "id")
    @Named("borrowIdToBorrow")
    default Borrow borrowIdToBorrow(Long borrowId) {
        return new BorrowRepository().findById(borrowId);
    }
}
