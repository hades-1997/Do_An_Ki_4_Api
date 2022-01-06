package com.ray.ecommerce.dao;

import com.ray.ecommerce.entity.PlaylistCat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface VideosPlaylistCatRepository extends JpaRepository<PlaylistCat, Long> {
    Page<PlaylistCat> findAll(Pageable pageable);

    PlaylistCat findByAlias(String alias);
}
