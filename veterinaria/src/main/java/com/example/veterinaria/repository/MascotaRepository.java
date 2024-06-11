package com.example.veterinaria.repository;

import com.example.veterinaria.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findAllByVeterinariaId(Long veterinariaId);

    @Query("SELECT m FROM Mascota m WHERE m.tipo = 'perro'")
    List<Mascota> findAllPerros();
}