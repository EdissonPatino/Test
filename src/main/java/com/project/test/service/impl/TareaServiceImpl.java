package com.project.test.service.impl;

import com.project.test.entity.Tarea;
import com.project.test.enumarator.EstadoEnum;
import com.project.test.enumarator.TipoPromedioEnum;
import com.project.test.repository.TareaRepository;
import com.project.test.service.TareaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.project.test.util.Utils.*;
import static org.apache.commons.lang3.StringUtils.*;

@Service
public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;

    public TareaServiceImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public Tarea crearTarea(Tarea tarea) throws IllegalArgumentException{
        validarTarea(tarea);
        return tareaRepository.save(tarea);
    }

    @Override
    public List<Tarea> listarTareasMes(){
        Date fechaInicio = Date.from(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaFin = Date.from(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Tarea> tareas = tareaRepository.listarTareasPorFecha(fechaInicio, fechaFin);
        return tareas;
    }

    @Override
    public List<Tarea> listarTareasMesPorEstado(EstadoEnum estado) {
        Date fechaInicio = Date.from(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaFin = Date.from(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return tareaRepository.listarTareasPorFechaYEstado(estado, fechaInicio, fechaFin);
    }

    @Override
    public void setTareaPagada(Long id) throws NoSuchElementException {
        Optional<Tarea> optTarea = tareaRepository.findById(id);
        if (optTarea.isPresent()){
            Tarea tarea = optTarea.get();
            tarea.setFechaFin(new Date());
            tarea.setEstado(EstadoEnum.PAGADO);
            tareaRepository.save(tarea);
        }else{
            throw new NoSuchElementException("La tarea no fue encontrada");
        }
    }

    @Override
    public BigDecimal calcularPromedio(TipoPromedioEnum tipoPromedio) {
        BigDecimal value = tareaRepository.calcularPromedioDiario();
        if (tipoPromedio.equals(TipoPromedioEnum.QUINCENA)){
            value = value.multiply(new BigDecimal("15"));
        } else if (tipoPromedio.equals(TipoPromedioEnum.SEMANA)){
            value = value.multiply(new BigDecimal("7"));
        }

        return value.setScale(2, RoundingMode.CEILING);
    }

    private void validarTarea(Tarea tarea) throws IllegalArgumentException{
        StringBuilder sbValidations = new StringBuilder();

        if (!validarSoloTexto(tarea.getNombre()))
            sbValidations.append("El nombre no es válido");

        if (!validarSoloTexto(tarea.getApellido()))
            sbValidations.append(sbValidations.isEmpty() ? "" : ",").append("El apellido no es válido");

        if (!validarSoloNumeros(tarea.getTelefono()))
            sbValidations.append(sbValidations.isEmpty() ? "" : ",").append("El telefono no es válido");

        if (!validarEmail(tarea.getEmail()))
            sbValidations.append(sbValidations.isEmpty() ? "" : ",").append("El email no es válido");

        if (!validarAlfanumerico(tarea.getCurp()))
            sbValidations.append(sbValidations.isEmpty() ? "" : ",").append("El curp no es válido");

        if (!validarAlfanumerico(tarea.getRfc()))
            sbValidations.append(sbValidations.isEmpty() ? "" : ",").append("El rfc no es válido");

        if (!validarSoloTexto(tarea.getNombreTarea()))
            sbValidations.append(sbValidations.isEmpty() ? "" : ",").append("El nombre de la tarea no es válido");

        if (!isEmpty(tarea.getDescripcion()) && !isAlphaSpace(tarea.getDescripcion()))
            sbValidations.append(sbValidations.isEmpty() ? "" : ",").append("La descripción de la tarea no es válida");

        if (tarea.getEstado() == null)
            sbValidations.append(sbValidations.isEmpty() ? "" : ",").append("El estado no es válido");

        if (!sbValidations.isEmpty())
            throw new IllegalArgumentException(sbValidations.toString());
    }
}
