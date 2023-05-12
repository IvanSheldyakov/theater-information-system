package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.PlayActorDTO;
import com.db.theaterinformationsystem.dto.PlayDTO;
import com.db.theaterinformationsystem.dto.PlayMusicianDTO;
import com.db.theaterinformationsystem.dto.PlayRoleDTO;
import com.db.theaterinformationsystem.exception.ConflictException;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
import com.db.theaterinformationsystem.mappers.PlayMapper;
import com.db.theaterinformationsystem.model.*;
import com.db.theaterinformationsystem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayService {

    private final PlayMapper playMapper;
    private final PlayRepository playRepository;
    private final ActorRepository actorRepository;
    private final PlayActorRepository playActorRepository;
    private final RoleRepository roleRepository;
    private final PlayRoleRepository playRoleRepository;
    private final MusicianRepository musicianRepository;
    private final PlayMusicianRepository playMusicianRepository;

    @Transactional
    public Long save(PlayDTO dto) {
        Play play = playMapper.map(dto);
        Play newPlay = playRepository.save(play);
        return newPlay.getId();
    }


    public PlayDTO find(Long id) {
        return playMapper.map(playRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND));
    }

    public List<PlayDTO> findAll() {
        return playRepository.findAll().stream().map(playMapper::map).collect(Collectors.toList());
    }

    @Transactional
    public void addActorOnRole(PlayActorDTO dto) {
        Actor actor = actorRepository.findById(dto.getActorId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        Play play = playRepository.findById(dto.getPlayId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        Role role = roleRepository.findById(dto.getRoleId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        playRoleRepository.findByPlayAndRole(play, role).orElseThrow(() -> new ConflictException("Уже существует"));
        PlayActor playActor = new PlayActor();
        playActor.setActor(actor);
        playActor.setPlay(play);
        if (dto.getBackup()) {
            role.setBackup(actor);
        } else {
            role.setActor(actor);
        }
        playActorRepository.save(playActor);
    }

    @Transactional
    public void addRole(PlayRoleDTO dto) {
        Role role = roleRepository.findById(dto.getRole()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        Play play = playRepository.findById(dto.getPlay()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        playRoleRepository.findByPlayAndRole(play, role).orElseThrow(() -> new ConflictException("Уже существует"));
        PlayRole playRole = new PlayRole();
        playRole.setPlay(play);
        playRole.setRole(role);
        playRoleRepository.save(playRole);
    }

    @Transactional
    public void addMusician(PlayMusicianDTO dto) {
        Musician musician = musicianRepository.findById(dto.getMusicianId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        Play play = playRepository.findById(dto.getPlayId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        playMusicianRepository.findByPlayAndMusician(play, musician).orElseThrow(() -> new ConflictException("Уже существует"));
        PlayMusician playMusician = new PlayMusician();
        playMusician.setMusician(musician);
        playMusician.setPlay(play);
        playMusicianRepository.save(playMusician);
    }

    public List<String> findProducersByPlayId(Long playId) {
        Play play = playRepository.findById(playId).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        return List.of(play.getArtProducer().getEmployee().getFullName().toString(),
                play.getConductorProducer().getEmployee().getFullName().toString(),
                play.getDirectorProducer().getEmployee().getFullName().toString());
    }

    public List<String> findBackupsByPlayId(Long playId) {
        Play play = playRepository.findById(playId).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        List<PlayRole> playRoles = playRoleRepository.findAllByPlay(play);
        return playRoles.stream()
                .map(PlayRole::getRole)
                .map(Role::getBackup)
                .filter(Objects::nonNull)
                .map(Actor::toString)
                .collect(Collectors.toList());
    }

    public List<String> findActorsByPlayId(Long playId) {
        Play play = playRepository.findById(playId).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        List<PlayActor> playActors = playActorRepository.findAllByPlay(play);
        return playActors.stream()
                .map(PlayActor::getActor)
                .filter(Objects::nonNull)
                .map(Actor::toString)
                .collect(Collectors.toList());
    }

    public Set<String> findAuthorsWithPastPremieres() {
        return playRepository.findAll().stream()
                .filter(play -> play.getPremiere().isBefore(LocalDate.now()))
                .map(Play::getAuthor).map(Author::toString).collect(Collectors.toSet());
    }

    public Set<String> findPlaysByAuthor(Long authorId) {
        return playRepository.findAll().stream().filter(play ->
                Objects.equals(play.getAuthor().getId(), authorId)).map(Play::toString).collect(Collectors.toSet());
    }
}
