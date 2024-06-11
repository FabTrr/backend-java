package com.example.veterinaria.controller;
import com.example.veterinaria.entity.Mascota;
import com.example.veterinaria.entity.Veterinaria;
import com.example.veterinaria.service.VeterinariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/veterinarias")
public class VeterinariaController {

    @Autowired
    private VeterinariaService veterinariaService;

    @PostMapping
    public ResponseEntity<Veterinaria> crearVeterinaria(@RequestBody Veterinaria veterinaria) {
        try {
            return ResponseEntity.ok(veterinariaService.crearVeterinaria(veterinaria));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No es posible guardar veterinaria", e);
        }
    }

    @PostMapping("/{veterinariaId}/mascotas")
    public ResponseEntity<Mascota> agregarMascotaAVeterinaria(@PathVariable Long veterinariaId, @RequestBody Mascota mascota) {
        try {
            return ResponseEntity.ok(veterinariaService.agregarMascotaAVeterinaria(veterinariaId, mascota));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No es posible guardar mascota", e);
        }
    }

    @GetMapping("/{veterinariaId}/mascotas")
    public ResponseEntity<List<Mascota>> consultarMascotasPorVeterinaria(@PathVariable Long veterinariaId) {
        try {
            List<Mascota> mascotas = veterinariaService.consultarMascotasPorVeterinaria(veterinariaId);
            return ResponseEntity.ok(mascotas);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Imposible obtener (GET)", e);
        }
    }

    @GetMapping("/mascotas/perros")
    public ResponseEntity<List<Mascota>> consultarMascotasQueSonPerros() {
        try {
            List<Mascota> perros = veterinariaService.consultarMascotasQueSonPerros();
            return ResponseEntity.ok(perros);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Imposible obtener (GET)", e);
        }
    }
}
