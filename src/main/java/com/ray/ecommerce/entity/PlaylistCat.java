package com.ray.ecommerce.entity;

import com.ray.ecommerce.domain.Authority;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "videos_playlist_cat")
@Data
@Setter
@Getter
public class PlaylistCat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "status")
    private int status;
    @Column(name = "private_mode")
    private int private_mode;
    @Column(name = "numbers")
    private  int numbers;
    @Column(name = "price")
    private double price;
    @Column(name = "title")
    private String title;
    @Column(name = "alias")
    private String alias;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @Column(name = "weight")
    private int weight;
    @Column(name = "keywords")
    private String keywords;
    @Column(name = "hitstotal")
    private int hitstotal;
    @Column(name = "favorite")
    private int favorite;
    @Column(name = "add_time")
    @CreationTimestamp
    private Date add_time;

        @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinTable(name = "videos_playlist",
                joinColumns = { @JoinColumn(name = "playlist_id") },
                inverseJoinColumns = {@JoinColumn(name = "video_id") })
        private Set<Videos> videos = new HashSet<>();

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "playlistCat")
//    private Set<Transiction> tCourse;

    public PlaylistCat() {
    }

    public PlaylistCat(Long id, int status, int private_mode, int numbers, double price, String title, String alias, String image, String description, int weight, String keywords, int hitstotal, int favorite, Date add_time) {
        this.id = id;
        this.status = status;
        this.private_mode = private_mode;
        this.numbers = numbers;
        this.price = price;
        this.title = title;
        this.alias = alias;
        this.image = image;
        this.description = description;
        this.weight = weight;
        this.keywords = keywords;
        this.hitstotal = hitstotal;
        this.favorite = favorite;
        this.add_time = add_time;
    }
//    public void add(Transiction transiction) {
//        if (transiction != null) {
//            if (tCourse == null) {
//                tCourse = new HashSet<>();
//            }
//            tCourse.add(transiction);
//            transiction.setPlaylistCat(this);
//        }
//    }
}
