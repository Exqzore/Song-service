package com.exqzore.songservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SongDto {
  private String name;
  private String artist;
  private String album;
  private String length;
  private Long resourceId;
  private Integer year;
}
