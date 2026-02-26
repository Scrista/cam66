package org.cmn.cam66.controller;

import org.cmn.cam66.entity.Alumno;
import org.cmn.cam66.entity.CatCondicion;
import org.cmn.cam66.entity.CatGrupo;
import org.cmn.cam66.repository.MaterialRepository;
import org.cmn.cam66.service.AlumnoService;
import org.cmn.cam66.service.CatCondicionService;
import org.cmn.cam66.service.CatGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Santiago Cristales
 * @date 26/11/2025
 * @project cam66
 */

@Controller
public class AlumnosController {
    @Autowired
    AlumnoService alumnoService;
    @Autowired
    CatCondicionService catCondicionService;
    @Autowired
    CatGrupoService catGrupoService;



    @RequestMapping("/alumnos")
    public String alumnos(Model model) {

        List<CatCondicion> condicionList = catCondicionService.findAll();
        List<CatGrupo> grupoList = catGrupoService.findAllByOrderByIdAsc();
        model.addAttribute("condicionList", condicionList);
        model.addAttribute("grupoList", grupoList);



        return "pages/cam66/alumnos/alumnos";

    }

    @GetMapping("/alumnos/getAll")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listarUsuariosPaginado(Pageable pageable,
                                                                      @RequestParam(name = "search[value]") String search, @RequestParam Map<String, String> params) {
        List<Sort.Order> orders = new ArrayList<>();
        int orderIndex = 0;

        while (params.containsKey("order[" + orderIndex + "][column]")) {
            String columnIndex = params.get("order[" + orderIndex + "][column]");
            String dir = params.get("order[" + orderIndex + "][dir]");

            // Obtén el nombre de la propiedad asociada al índice de columna
            String columnName = params.get("columns[" + columnIndex + "][data]");
            if (columnName != null && !columnName.isEmpty()) {
                Sort.Direction direction = "desc".equalsIgnoreCase(dir) ? Sort.Direction.DESC : Sort.Direction.ASC;
                System.out.println("columnName: " + columnName);
                orders.add(new Sort.Order(direction, columnName));
            }
            orderIndex++;
        }
        System.out.println("orders" + orders);
        Sort sort = Sort.by(orders);
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<Alumno> alumnos;
        if (search.isBlank() || search.isEmpty()) {
            alumnos = alumnoService.findAll(sortedPageable);
        } else {
            // TODO: Hacer el metodo findAll con el search
            alumnos = alumnoService.findAll(sortedPageable);
            // usuarios = usuarioService.findAllSearch(search, sortedPageable);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("data", alumnos.getContent()); // Datos actuales
        response.put("recordsTotal", alumnos.getTotalElements()); // Total de registros
        response.put("recordsFiltered", alumnos.getTotalElements()); // Total de registros filtrados
        return ResponseEntity.ok(response);
    }
}
