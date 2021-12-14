package com.ray.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "videos_cat")
@Getter
@Setter
public class VideosCat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "alias")
    private String alias;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "weight")
    private String weight;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "add_time")
    @CreationTimestamp
    private Date add_time;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "videosCat", cascade = {CascadeType.ALL})
    private List<VideosRows> videosRows;
}
