package com.ray.ecommerce.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "playlist_rate")
@Data
@Setter
@Getter
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private int userId;
    @Column(name = "playlist_id")
    private int playListId;
    @Column(name = "star")
    private int star;

    public Rate() {}

    public Rate(int userId, int playListId, int star) {
        this.userId = userId;
        this.playListId = playListId;
        this.star = star;
    }
}
