package org.empresa.facturasspring.controller;

import org.empresa.facturasspring.model.Empleado;
import org.empresa.facturasspring.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/salarios")
public class SalarioController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String mostrarBusquedaSalario() {
        return "buscar-salario";
    }

    @PostMapping
    public String calcularSalario(@RequestParam String dni, Model model) {
        try {
            Empleado empleado = empleadoService.obtenerEmpleadoPorDni(dni);
            String sueldo = empleadoService.calcularSueldoFormateado(empleado);

            model.addAttribute("empleado", empleado);
            model.addAttribute("sueldo", sueldo);
            model.addAttribute("mensaje", "Salario calculado correctamente");

            return "resultado-salario";

        } catch (Exception e) {
            model.addAttribute("error", "No se encontró empleado con DNI: " + dni);
            return "buscar-salario";
        }
    }

    @GetMapping("/{dni}")
    public String calcularSalarioDesdeUrl(@PathVariable String dni, Model model) {
        try {
            Empleado empleado = empleadoService.obtenerEmpleadoPorDni(dni);
            String sueldo = empleadoService.calcularSueldoFormateado(empleado);

            model.addAttribute("empleado", empleado);
            model.addAttribute("sueldo", sueldo);
            model.addAttribute("mensaje", "Salario calculado correctamente");

            return "resultado-salario";

        } catch (Exception e) {
            model.addAttribute("error", "No se encontró empleado con DNI: " + dni);
            return "buscar-salario";
        }
    }
}