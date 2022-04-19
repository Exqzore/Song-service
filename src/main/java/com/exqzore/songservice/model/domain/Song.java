package com.exqzore.songservice.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "songs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Song {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "artist")
  private String artist;

  @Column(name = "album")
  private String album;

  @Column(name = "length")
  private String length;

  @Column(name = "resource_id")
  private Long resourceId;

  @Column(name = "year")
  private Integer year;
}
