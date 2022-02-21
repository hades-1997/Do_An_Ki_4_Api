package com.ray.ecommerce.entity;

import com.ray.ecommerce.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "videos_transiction")
@Data
@Setter
@Getter

public class VideosTransiction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_tracking_number")
    private String order_tracking_number;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "title")
    private String title;

    @Column(name = "unit_price")
    private BigDecimal unit_price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playlist_id")
    public PlaylistCat playlistCat;


    public VideosTransiction() {
    }

    public VideosTransiction(Long id, String order_tracking_number, String image_url, String title, BigDecimal unit_price, User user, PlaylistCat playlistCat) {
        this.id = id;
        this.order_tracking_number = order_tracking_number;
        this.image_url = image_url;
        this.title = title;
        this.unit_price = unit_price;
        this.user = user;
        this.playlistCat = playlistCat;
    }
}
