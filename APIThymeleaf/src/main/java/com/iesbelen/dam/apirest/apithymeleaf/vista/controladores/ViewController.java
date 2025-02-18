package com.iesbelen.dam.apirest.apithymeleaf.vista.controladores;

import com.iesbelen.dam.apirest.apithymeleaf.servidor.modelo.dao.IDepartamentosDAO;
import com.iesbelen.dam.apirest.apithymeleaf.servidor.modelo.dao.IEmpleadosDAO;
import com.iesbelen.dam.apirest.apithymeleaf.servidor.modelo.entidades.Departamento;
import com.iesbelen.dam.apirest.apithymeleaf.servidor.modelo.entidades.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DecimalStyle;
import java.util.List;
import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    IDepartamentosDAO departamentosDAO;
    @Autowired
    IEmpleadosDAO empleadosDAO;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/verdepartamentos")
    public String mostrarDepartamentos(Model model) {
        List<Departamento> departamentos = (List<Departamento>) departamentosDAO.findAll();
        model.addAttribute("departamentos", departamentos);
        return "verdepartamentos";
    }

    @GetMapping("/altadepartamento")
    public String altaDepartamento(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "altadepartamento";
    }

    @PostMapping("/altadepartamento")
    public String crearDepartamento(@ModelAttribute Departamento departamento, Model model) {
        if (!departamentosDAO.existsById(departamento.getId()))
        {
            departamentosDAO.save(departamento);
            model.addAttribute("tipo_operacion", "ok");
            model.addAttribute("mensaje", "Departamento creado correctamente");
        }
        else {
            model.addAttribute("tipo_operacion", "error");
            model.addAttribute("mensaje", "Error al crear el departamento: clave duplicada");
        }
        return "altadepartamento";
    }

    @GetMapping("/verempleados")
    public String mostrarEmpleados(Model model, @RequestParam(name = "depno", required = false) Integer depno) {
        List<Departamento> departamentos = (List<Departamento>) departamentosDAO.findAll();
        model.addAttribute("departamentos", departamentos);
        List<Empleado> empleados;
        if (depno == null)//|| depno == 0)
            empleados = (List<Empleado>) empleadosDAO.findAll();
        else
            empleados = (List<Empleado>) empleadosDAO.findAllByDepno(departamentosDAO.findById(depno));
        model.addAttribute("empleados", empleados);
        return "verempleados";
    }

    @GetMapping("/verempleado")
    public String mostrarEmpleado(Model model, @RequestParam(name = "id", required = true) int id) {
        Optional<Empleado> empleado = empleadosDAO.findById(id);
        if (!empleado.isPresent()) {
            model.addAttribute("titulo", "Error");
            model.addAttribute("mensaje", "No se encontro el empleado con el id " + id);
            return "error";
        }
        model.addAttribute("empleado", empleado.get());
        return "verempleado";
    }
}
