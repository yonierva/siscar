package com.siscar.siscar_backend.repository;

import com.siscar.siscar_backend.model.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Integer> {
    List<Sticker> findByIdSolicitud(Integer idSolicitud);
}