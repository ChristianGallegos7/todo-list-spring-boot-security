package com.todolist.todos.controllers;

import com.todolist.todos.entities.Tarea;
import com.todolist.todos.services.TareaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> obtenerTodasLasTareas() {
        return ResponseEntity.ok().body(tareaService.obtenerTodasLasTareas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerPorId(@PathVariable Long id) {
        Optional<Tarea> tarea = tareaService.obtenerTareaPorId(id);

        if (tarea.isPresent()) {
            return ResponseEntity.ok().body(tareaService.obtenerTareaPorId(id).get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaService.crearTarea(tarea));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        Optional<Tarea> tareaOptional = tareaService.obtenerTareaPorId(id);

        if (tareaOptional.isPresent()) {
            Tarea tareaDb = tareaOptional.get();
            tareaDb.setNombre(tarea.getNombre());
            tareaDb.setEstado(tarea.isEstado());
            return ResponseEntity.ok().body(tareaService.actualizarTarea(tareaDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaTarea(@PathVariable Long id) {
        Optional<Tarea> tarea = tareaService.obtenerTareaPorId(id);

        if (tarea.isPresent()) {
            tareaService.borrarTarea(id);
            return ResponseEntity.noContent().build(); // Respuesta 204 No Content si se eliminó correctamente
        }

        return ResponseEntity.notFound().build(); // Respuesta 404 Not Found si la tarea no existe
    }


}
