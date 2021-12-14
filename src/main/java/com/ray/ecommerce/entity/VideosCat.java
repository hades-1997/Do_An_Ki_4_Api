package com.ray.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "videos_cat")
public class VideosCat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
