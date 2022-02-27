package com.ray.ecommerce.dao;

import com.ray.ecommerce.entity.StatusPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusPlaylistRepository extends JpaRepository<StatusPlaylist, Long> {
    StatusPlaylist findStatusPlaylistById(Long id);
}
