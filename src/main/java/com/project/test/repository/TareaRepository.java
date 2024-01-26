package com.project.test.repository;

import com.project.test.entity.Tarea;
import com.project.test.enumarator.EstadoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    @Query("SELECT t FROM Tarea t WHERE t.fechaInicio BETWEEN :fechaInicio AND :fechaFin OR t.fechaFin BETWEEN :fechaInicio AND :fechaFin")
    List<Tarea> listarTareasPorFecha(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    @Query("SELECT t FROM Tarea t WHERE t.estado=:estado AND (t.fechaInicio BETWEEN :fechaInicio AND :fechaFin OR t.fechaFin BETWEEN :fechaInicio AND :fechaFin)")
    List<Tarea> listarTareasPorFechaYEstado(@Param("estado") EstadoEnum estado, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    @Query(value = "SELECT COALESCE(COALESCE(SUM(o.total), 0)/ GREATEST(COUNT(o.fecha), 1), 0) FROM (SELECT t.fecha_fin fecha, SUM(t.valor) total FROM Tarea t WHERE t.estado='PAGADO' GROUP BY t.fecha_fin) o", nativeQuery = true)
    BigDecimal calcularPromedioDiario();
}
