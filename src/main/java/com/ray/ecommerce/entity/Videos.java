package com.ray.ecommerce.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "videos_rows")
@Data
@Setter
@Getter
public class Videos {
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
    public VideoCategories categories;
    // dùng transient mình dùng thôi không lưu lên database
    @Transient
    public String CategoryId;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "videos_playlist",
//            joinColumns = { @JoinColumn(name = "video_id") },
//            inverseJoinColumns = {@JoinColumn(name = "playlist_id") })
//    private Set<PlaylistCat> listCatsVideo; // delete, insert, update, delete


    public Videos() {
    }

    public Videos(Long id, String author, String artist, int sourceid, Date add_time, int status, int archive, String title, String alias, String hometext, String vid_path, String vid_type, String vid_duration, String homeimgfile, String homeimgalt, String allowed_comm, int allowed_rating, int hitstotal, int total_rating, int click_rating, VideoCategories categories) {
        this.id = id;
        this.author = author;
        this.artist = artist;
        this.sourceid = sourceid;
        this.add_time = add_time;
        this.status = status;
        this.archive = archive;
        this.title = title;
        this.alias = alias;
        this.hometext = hometext;
        this.vid_path = vid_path;
        this.vid_type = vid_type;
        this.vid_duration = vid_duration;
        this.homeimgfile = homeimgfile;
        this.homeimgalt = homeimgalt;
        this.allowed_comm = allowed_comm;
        this.allowed_rating = allowed_rating;
        this.hitstotal = hitstotal;
        this.total_rating = total_rating;
        this.click_rating = click_rating;
        this.categories = categories;
    }
}
