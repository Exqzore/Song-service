package com.exqzore.songservice;

import com.exqzore.songservice.config.SongServiceConfig;
import com.exqzore.songservice.exception.ResourceNotFoundException;
import com.exqzore.songservice.model.DeletedEntitiesInfo;
import com.exqzore.songservice.model.EntityId;
import com.exqzore.songservice.model.domain.Song;
import com.exqzore.songservice.model.dto.SongDto;
import com.exqzore.songservice.repository.SongDao;
import com.exqzore.songservice.service.SongService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SongServiceConfig.class)
@ActiveProfiles("test")
@Tag("unit-test")
class SongServiceTest {

  private SongService songService;

  @MockBean
  private SongDao songDao;

  @Autowired
  private ModelMapper modelMapper;

  private SongDto songDto;
  private Song song;

  @BeforeEach
  void setUp() {
    songService = new SongService(songDao, modelMapper);

    songDto = new SongDto().setName("name1");
    song = new Song().setName("name1").setId(1L);
  }

  @Test
  void findById_useFillSong_returnsSongDto() {
    when(songDao.findById(anyLong())).thenReturn(Optional.of(song));

    final SongDto expected = songDto;
    final SongDto actual = songService.getById(1L);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void findById_useEmpty_throwsResourceNotFoundException() {
    when(songDao.findById(anyLong())).thenReturn(Optional.empty());

    final Class<ResourceNotFoundException> expectedType = ResourceNotFoundException.class;
    final Executable executable = () -> songService.getById(1L);
    Assertions.assertThrows(expectedType, executable);
  }

  @Test
  void save_useFillSong_returnsFillSongDto() {
    when(songDao.save(any(Song.class))).thenReturn(song);

    final EntityId expected = new EntityId(1L);
    final EntityId actual = songService.create(songDto);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void delete_useExistingSong_returnsListOfDeletedEntities() {
    when(songDao.deleteAllByIdIn(List.of(1L, 2L))).thenReturn(List.of(song));

    final DeletedEntitiesInfo expected = new DeletedEntitiesInfo();
    expected.setIds(List.of(1L));
    final DeletedEntitiesInfo actual = songService.deleteSongs("1,2");
    Assertions.assertEquals(expected, actual);
  }
}