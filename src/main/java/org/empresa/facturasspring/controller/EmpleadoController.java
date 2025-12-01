package org.empresa.facturasspring.controller;

import org.empresa.facturasspring.model.Empleado;
import org.empresa.facturasspring.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoService.obtenerTodos();
        model.addAttribute("empleados", empleados);
        model.addAttribute("titulo", "Lista Completa de Empleados");
        return "lista-empleados";
    }

    @GetMapping("/buscar")
    public String mostrarBusqueda(Model model) {
        model.addAttribute("criterio", "");
        return "buscar-empleados";
    }

    @PostMapping("/buscar")
    public String buscarEmpleados(@RequestParam String criterio, Model model) {
        List<Empleado> empleados = empleadoService.buscarEmpleados(criterio);
        model.addAttribute("empleados", empleados);
        model.addAttribute("criterio", criterio);
        model.addAttribute("titulo", "Resultados de Búsqueda");
        model.addAttribute("mensaje", "Se encontraron " + empleados.size() + " empleados");
        return "resultado-busqueda";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "form-empleado";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@Valid @ModelAttribute Empleado empleado,
                                  BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("error", "Por favor, corrige los errores del formulario");
            return "form-empleado";
        }

        try {
            empleadoService.guardarEmpleado(empleado);
            model.addAttribute("mensaje", "Empleado guardado correctamente");
            return "redirect:/empleados?exito=true";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar empleado: " + e.getMessage());
            return "form-empleado";
        }
    }

    @GetMapping("/editar/{dni}")
    public String mostrarFormularioEditar(@PathVariable String dni, Model model) {
        try {
            Empleado empleado = empleadoService.obtenerEmpleadoPorDni(dni);
            model.addAttribute("empleado", empleado);
            return "form-empleado";
        } catch (Exception e) {
            model.addAttribute("error", "Empleado no encontrado: " + e.getMessage());
            return "redirect:/empleados";
        }
    }

    @GetMapping("/eliminar/{dni}")
    public String eliminarEmpleado(@PathVariable String dni, Model model) {
        try {
            empleadoService.eliminarEmpleado(dni);
            model.addAttribute("mensaje", "Empleado eliminado correctamente");
        } catch (Exception e) {
            model.addAttribute("error", "Error al eliminar empleado: " + e.getMessage());
        }
        return "redirect:/empleados";
    }
}