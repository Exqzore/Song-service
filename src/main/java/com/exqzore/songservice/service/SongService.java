package com.exqzore.songservice.service;

import com.exqzore.songservice.exception.InternalServerException;
import com.exqzore.songservice.exception.InvalidBodyOrValidationException;
import com.exqzore.songservice.exception.ResourceNotFoundException;
import com.exqzore.songservice.model.DeletedEntitiesInfo;
import com.exqzore.songservice.model.EntityId;
import com.exqzore.songservice.model.domain.Song;
import com.exqzore.songservice.model.dto.SongDto;
import com.exqzore.songservice.repository.SongDao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SongService {
  private static final String INVALID_BODY_VALIDATION_ERROR = "Validation error or request body is an invalid MP3";
  private static final String INTERNAL_SERVER_ERROR = "Internal server error occurred";
  private static final String NOT_FOUND_ERROR = "Resource doesn't exist with given id";
  private final SongDao songDao;

  private final ModelMapper modelMapper;

  public EntityId create(SongDto song) {
    return new EntityId(songDao.save(new Song().setAlbum(song.getAlbum()).setArtist(song.getArtist())
        .setLength(song.getLength()).setName(song.getName()).setYear(song.getYear())
        .setResourceId(song.getResourceId())).getId());
  }

  public SongDto getById(Long id) {
    return modelMapper.map(songDao.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_ERROR)), SongDto.class);
  }

  @Transactional
  public DeletedEntitiesInfo deleteSongs(String idsString) {
    if (idsString == null || idsString.length() > 200) {
      throw new InvalidBodyOrValidationException(INVALID_BODY_VALIDATION_ERROR);
    }
    try {
      List<Long> idValues = getIdValuesFromStrings(idsString.split(","));
      List<Song> deletedSongs = songDao.deleteAllByIdIn(idValues);
      return new DeletedEntitiesInfo(deletedSongs.stream().map(Song::getId).collect(Collectors.toList()));

    } catch (Exception exception) {
      throw new InternalServerException(INTERNAL_SERVER_ERROR);
    }
  }

  private List<Long> getIdValuesFromStrings(String[] ids) {
    return Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList());
  }
}
