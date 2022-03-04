package com.ray.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class VideoCategories {
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
    private int weight;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "add_time")
    @CreationTimestamp
    private Date add_time;


    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "categories")
    @JsonIgnore
    private List<Videos> videosList;

    public VideoCategories() {
    }

    public VideoCategories(Long id, String title, String alias, String description, String image, int weight, String keywords, Date add_time, List<Videos> videosList) {
        this.id = id;
        this.title = title;
        this.alias = alias;
        this.description = description;
        this.image = image;
        this.weight = weight;
        this.keywords = keywords;
        this.add_time = add_time;
        this.videosList = videosList;
    }
}
