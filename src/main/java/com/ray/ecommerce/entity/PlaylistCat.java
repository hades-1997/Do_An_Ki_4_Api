package com.ray.ecommerce.entity;

import com.ray.ecommerce.domain.Authority;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Table(name = "videos_playlist_cat")
@Entity
@Data
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "playlistCat")
    private List<VideosTransiction> videosTransictions;

    public PlaylistCat() {}

    public PlaylistCat(Long id, int status, int private_mode, int numbers, String title, String alias, String image, String description, int weight, String keywords, int hitstotal, int favorite, Date add_time) {
        this.id = id;
        this.status = status;
        this.private_mode = private_mode;
        this.numbers = numbers;
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


//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "videos_playlist",
//            joinColumns = @JoinColumn(name = "video_id"),
//            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
//    private Set<Videos> videos;
}
