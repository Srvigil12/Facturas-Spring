package org.empresa.facturasspring.service;

import org.empresa.facturasspring.model.Empleado;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class NominaService {

    private static final int[] SUELDO_BASE = {
            5000, 7000, 9000, 11000, 13000,
            15000, 17000, 19000, 21000, 23000
    };

    public double calcularSueldo(Empleado empleado) {
        int base = SUELDO_BASE[empleado.getCategoria() - 1];
        return base + (5000 * empleado.getAnyos());
    }

    public String calcularSueldoFormateado(Empleado empleado) {
        double sueldo = calcularSueldo(empleado);
        DecimalFormat df = new DecimalFormat("#,###.00 €");
        return df.format(sueldo);
    }
}
