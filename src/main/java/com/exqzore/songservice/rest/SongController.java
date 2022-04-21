package com.exqzore.songservice.rest;

import com.exqzore.songservice.model.DeletedEntitiesInfo;
import com.exqzore.songservice.model.EntityId;
import com.exqzore.songservice.model.dto.SongDto;
import com.exqzore.songservice.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
public class SongController {

  private final SongService songService;

  @Autowired
  public SongController(SongService songService) {
    this.songService = songService;
  }

  @GetMapping("/{id}")
  public SongDto getOne(@PathVariable Long id) {
    return songService.getById(id);
  }

  @PostMapping
  public ResponseEntity<EntityId> create(@RequestBody SongDto song) {
    return new ResponseEntity<>(songService.create(song), HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<DeletedEntitiesInfo> removeResources(@RequestParam("id") String ids) {
    return new ResponseEntity<>(songService.deleteSongs(ids), HttpStatus.OK);
  }
}
