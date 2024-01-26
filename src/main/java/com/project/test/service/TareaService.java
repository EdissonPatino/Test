package com.project.test.service;

import com.project.test.entity.Tarea;
import com.project.test.enumarator.EstadoEnum;
import com.project.test.enumarator.TipoPromedioEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

public interface TareaService {

    Tarea crearTarea(Tarea tarea) throws IllegalArgumentException;
    List<Tarea> listarTareasMes() throws Exception;
    void setTareaPagada(Long id) throws NoSuchElementException;
    List<Tarea> listarTareasMesPorEstado(EstadoEnum estado);
    BigDecimal calcularPromedio(TipoPromedioEnum tipoPromedio);
}
