package com.ray.ecommerce.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "videos_transiction")
@Data
@Setter
@Getter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "playlist_tid")
    private Long playlistId;

    @Column(name = "total_price")
    private double totalPrice;


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User userPay;totalPrice
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "playlist_tid")
//    private PlaylistCat playlistCat;
    @Transient
    private String urlLink;

    public Course() {}

    public Course(Long id, String orderTrackingNumber, Long userId, Long playlistId, double totalPrice) {
        this.id = id;
        this.orderTrackingNumber = orderTrackingNumber;
        this.userId = userId;
        this.playlistId = playlistId;
        this.totalPrice = totalPrice;
    }

}
