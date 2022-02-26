package com.ray.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status_playlist")
@Getter
@Setter
public class StatusPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_status")
    private String name_status;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "statusPlaylist")
    @JsonIgnore
    private List<Rate> rates;

    public StatusPlaylist() {}


}
