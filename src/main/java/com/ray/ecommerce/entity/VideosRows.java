package com.ray.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "videos_rows")
@Getter
@Setter
public class VideosRows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "artist")
    private String artist;

    @Column(name = "sourceid")
    private int sourceid;

    @Column(name = "add_time")
    @CreationTimestamp
    private Date add_time;

    @Column(name = "status")
    private int status;

    @Column(name = "archive")
    private int archive;

    @Column(name = "title")
    private String title;

    @Column(name = "alias")
    private String alias;

    @Column(name = "hometext")
    private String hometext;

    @Column(name = "vid_path")
    private String vid_path;

    @Column(name = "vid_type")
    private String vid_type;

    @Column(name = "vid_duration")
    private String vid_duration;

    @Column(name = "homeimgfile")
    private String homeimgfile;

    @Column(name = "homeimgalt")
    private String homeimgalt;

    @Column(name = "allowed_comm")
    private String allowed_comm;

    @Column(name = "allowed_rating")
    private int allowed_rating;

    @Column(name = "hitstotal")
    private int hitstotal;

    @Column(name = "total_rating")
    private int total_rating;

    @Column(name = "click_rating")
    private int click_rating;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "catid")
    public VideosCat videosCat;
}
