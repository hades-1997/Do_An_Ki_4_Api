package com.ray.ecommerce.entity;

import com.ray.ecommerce.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "videos_transiction")
@Data
@Setter
@Getter
public class Transiction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userPay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_tid")
    private PlaylistCat playlistCat;

    @Transient
    private String urlLink;
    public Transiction() {}

    public Transiction(Long id, String orderTrackingNumber, User userPay, PlaylistCat playlistCat) {
        this.id = id;
        this.orderTrackingNumber = orderTrackingNumber;
        this.userPay = userPay;
        this.playlistCat = playlistCat;
    }



}
