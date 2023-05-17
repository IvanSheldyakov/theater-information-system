package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.ProducerCreateDTO;
import com.db.theaterinformationsystem.dto.ProducerDTO;
import com.db.theaterinformationsystem.model.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface ProducerMapper {

    ProducerDTO map(Producer producer);

    Producer map(ProducerDTO dto);

    @Mapping(target = "sex", ignore = true)
    @Mapping(target = "age", ignore = true)
    ProducerCreateDTO mapProducer(Producer producer);

    Producer map(ProducerCreateDTO producerCreateDTO);
}
