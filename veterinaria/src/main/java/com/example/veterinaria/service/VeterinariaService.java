package com.example.veterinaria.service;

import com.example.veterinaria.entity.Mascota;
import com.example.veterinaria.entity.Veterinaria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.veterinaria.repository.MascotaRepository;
import com.example.veterinaria.repository.VeterinariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinariaService {

    private static final Logger logger = LogManager.getLogger(VeterinariaService.class);

    @Autowired
    private VeterinariaRepository veterinariaRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    public Veterinaria crearVeterinaria(Veterinaria veterinaria) {
        logger.info("Creando nueva veterinaria: {}", veterinaria.getNombre());
        return veterinariaRepository.save(veterinaria);
    }

    public Mascota agregarMascotaAVeterinaria(Long veterinariaId, Mascota mascota) {
        logger.info("Agregando mascota {} a la veterinaria con ID: {}", mascota.getNombre(), veterinariaId);
        Veterinaria veterinaria = veterinariaRepository.findById(veterinariaId)
                .orElseThrow(() -> new IllegalArgumentException("Veterinaria no encontrada"));
        mascota.setVeterinaria(veterinaria);
        return mascotaRepository.save(mascota);
    }

    public List<Mascota> consultarMascotasPorVeterinaria(Long veterinariaId) {
        logger.info("Listando mascotas con ID: {}", veterinariaId);
        return mascotaRepository.findAllByVeterinariaId(veterinariaId);
    }

    public List<Mascota> consultarMascotasQueSonPerros() {
        logger.info("Listando mascotas que son perros");
        return mascotaRepository.findAllPerros();
    }
}
