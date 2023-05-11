package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.TourDTO;
import com.db.theaterinformationsystem.dto.TourPlayDTO;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
import com.db.theaterinformationsystem.mappers.TourMapper;
import com.db.theaterinformationsystem.model.Play;
import com.db.theaterinformationsystem.model.Tour;
import com.db.theaterinformationsystem.repository.PlayRepository;
import com.db.theaterinformationsystem.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourMapper tourMapper;
    private final TourRepository tourRepository;
    private final PlayRepository playRepository;

    @Transactional
    public Long save(TourDTO dto) {
        Tour tour = tourMapper.map(dto);
        Tour newTour = tourRepository.save(tour);
        return newTour.getId();
    }

    public TourDTO find(Long id) {
        return tourMapper.map(tourRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND));
    }

    public List<TourDTO> findAll() {
        return tourRepository.findAll().stream().map(tourMapper::map).collect(Collectors.toList());
    }

    @Transactional
    public void addPlay(TourPlayDTO dto) {
        Play play = playRepository.findById(dto.getPlayId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        Tour tour = tourRepository.findById(dto.getTourId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        tour.setPlay(play);
    }
}