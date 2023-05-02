package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.TourDTO;
import com.db.theaterinformationsystem.mappers.TourMapper;
import com.db.theaterinformationsystem.model.Tour;
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

    @Transactional
    public Long save(TourDTO dto) {
        Tour tour = tourMapper.map(dto);
        Tour newTour = tourRepository.save(tour);
        return newTour.getId();
    }

    public TourDTO find(Long id) {
        return tourMapper.map(tourRepository.findById(id).orElse(null));
    }

    public List<TourDTO> findAll() {
        return tourRepository.findAll().stream().map(tourMapper::map).collect(Collectors.toList());
    }
}