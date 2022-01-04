package com.ray.ecommerce.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "videos_playlist")
@Data
public class VideosPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
