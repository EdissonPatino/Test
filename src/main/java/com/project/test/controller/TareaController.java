package com.project.test.controller;

import com.project.test.entity.Tarea;
import com.project.test.enumarator.EstadoEnum;
import com.project.test.enumarator.TipoPromedioEnum;
import com.project.test.service.TareaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearTarea(@RequestBody Tarea tarea){
        try{
            Tarea persistedTarea = tareaService.crearTarea(tarea);
            return ResponseEntity.ok(persistedTarea);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/listartareasmes")
    public ResponseEntity<?> listarTareasMes(){
        try{
            return ResponseEntity.ok(tareaService.listarTareasMes());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/listartareasmes/{estado}")
    public ResponseEntity<?> listarTareasMesPorEstado(@PathVariable EstadoEnum estado){
        try{
            return ResponseEntity.ok(tareaService.listarTareasMesPorEstado(estado));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @PutMapping("/cambiarpagada/{id}")
    public ResponseEntity<?> setTareaPagada(@PathVariable Long id){
        try{
            tareaService.setTareaPagada(id);
            return ResponseEntity.ok("OK");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/promedio/{tipo}")
    public ResponseEntity<?> calcularPromedio(@PathVariable TipoPromedioEnum tipo){
        try{
            return ResponseEntity.ok(tareaService.calcularPromedio(tipo));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }
}
