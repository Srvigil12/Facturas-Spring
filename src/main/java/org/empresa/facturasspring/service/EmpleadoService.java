package org.empresa.facturasspring.service;

import org.empresa.facturasspring.model.Empleado;
import org.empresa.facturasspring.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private NominaService nominaService;

    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAllByOrderByNombreAsc();
    }

    public Optional<Empleado> obtenerPorDni(String dni) {
        return empleadoRepository.findById(dni);
    }

    public Empleado obtenerEmpleadoPorDni(String dni) {
        return empleadoRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con DNI: " + dni));
    }

    public List<Empleado> buscarEmpleados(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return obtenerTodos();
        }
        return empleadoRepository.buscarPorCriterio(criterio.trim());
    }

    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public void eliminarEmpleado(String dni) {
        empleadoRepository.deleteById(dni);
    }

    public String calcularSueldoFormateado(Empleado empleado) {
        return nominaService.calcularSueldoFormateado(empleado);
    }

    public String calcularSueldoFormateado(String dni) {
        Empleado empleado = obtenerEmpleadoPorDni(dni);
        return nominaService.calcularSueldoFormateado(empleado);
    }

    public boolean existeEmpleado(String dni) {
        return empleadoRepository.existsById(dni);
    }
}