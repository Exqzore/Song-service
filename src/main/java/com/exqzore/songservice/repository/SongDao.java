package com.exqzore.songservice.repository;

import com.exqzore.songservice.model.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongDao extends JpaRepository<Song, Long> {
    List<Song> deleteAllByIdIn(List<Long> idValues);
}
