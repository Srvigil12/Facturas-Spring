package org.empresa.facturasspring.repository;

import org.empresa.facturasspring.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

    @Query("SELECT e FROM Empleado e WHERE " +
            "e.nombre LIKE %:criterio% OR " +
            "e.dni LIKE %:criterio% OR " +
            "e.sexo LIKE %:criterio% OR " +
            "CAST(e.categoria AS string) LIKE %:criterio% OR " +
            "CAST(e.anyos AS string) LIKE %:criterio%")
    List<Empleado> buscarPorCriterio(@Param("criterio") String criterio);

    List<Empleado> findByCategoria(Integer categoria);

    List<Empleado> findAllByOrderByNombreAsc();
}