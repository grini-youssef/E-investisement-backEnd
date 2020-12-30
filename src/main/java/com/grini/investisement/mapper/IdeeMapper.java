package com.grini.investisement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.grini.investisement.dto.IdeeRequest;
import com.grini.investisement.dto.IdeeResponse;
import com.grini.investisement.entity.Commune;
import com.grini.investisement.entity.Idee;
import com.grini.investisement.entity.User;

@Mapper(componentModel = "spring")
public interface IdeeMapper {
	
	@Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
	Idee map(IdeeRequest ideeRequest,Commune commune ,User user);
	
    @Mapping(target = "commune", source = "commune.nom")
    @Mapping(target = "userName", source = "user.username")
	IdeeResponse mapToDto(Idee idee);

}
