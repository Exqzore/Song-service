package com.exqzore.songservice;

import com.exqzore.songservice.config.SongServiceConfig;
import com.exqzore.songservice.model.dto.SongDto;
import com.exqzore.songservice.rest.SongController;
import com.exqzore.songservice.service.SongService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SongServiceConfig.class)
@Tag("contract-test")
public class SongContractTest {
  @Autowired
  private SongController songController;

  @MockBean
  private SongService songService;

  @BeforeEach
  public void setUp() {
    RestAssuredMockMvc.standaloneSetup(songController);
    when(songService.getById(anyLong()))
        .thenReturn(new SongDto("name", "artist", "album", "1", 1L, 2022));
  }
}