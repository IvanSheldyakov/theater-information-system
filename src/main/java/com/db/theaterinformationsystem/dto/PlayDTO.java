package com.db.theaterinformationsystem.dto;

import lombok.Data;

@Data
public class PlayDTO {

    private Long id;
    private GenreDTO genre;
    private AudienceDTO audience;
    private AuthorDTO author;
    private ProducerDTO directorProducer;
    private ProducerDTO artProducer;
    private ProducerDTO conductorProducer;
    private String premiere;
    private String name;
    private Integer createCentury;
    private Integer places;
}
