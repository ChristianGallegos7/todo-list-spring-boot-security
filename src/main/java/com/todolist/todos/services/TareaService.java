package com.todolist.todos.services;

import com.todolist.todos.entities.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaService {
    //Listar todas
    List<Tarea> obtenerTodasLasTareas();
    //Obtener tarea por id
    Optional<Tarea> obtenerTareaPorId(Long id);
    //Crear tarea
    Tarea crearTarea(Tarea tarea);
    //Actualizar tarea
    Tarea actualizarTarea(Tarea tarea);
    //Borrar tarea
    void borrarTarea(Long id);
}
