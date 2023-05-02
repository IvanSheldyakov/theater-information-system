package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.ProducerDTO;
import com.db.theaterinformationsystem.model.Producer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface ProducerMapper {

    ProducerDTO map(Producer producer);

    Producer map(ProducerDTO dto);
}
