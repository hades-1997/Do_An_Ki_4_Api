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
    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "cat_status")
//    public StatusPlaylist statusPlaylist;
    @Column(name = "is_status")
    private Boolean isStatus;

    @Column(name = "star")
    private int star;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "cat_status")
//    public StatusPlaylist statusPlaylist;

    // dùng transient mình dùng thôi không lưu lên database
//    @Transient
//    public String StatusId;

    public Rate() {}

    public Rate(Long id, int userId, int playListId, Boolean isStatus, int star) {
        this.id = id;
        this.userId = userId;
        this.playListId = playListId;
        this.isStatus = isStatus;
        this.star = star;
    }
}
