package org.cmn.cam66.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.cmn.cam66.entity.Alumno;
import org.cmn.cam66.entity.MaterialDidactico;
import org.cmn.cam66.entity.Personal;
import org.cmn.cam66.repository.MaterialRepository;
import org.cmn.cam66.service.AdministracionService;
import org.cmn.cam66.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Santiago Cristales
 * @date 19/02/2026
 * @project cam66
 */

@Controller
public class AdministracionController {

    @Autowired
    PersonalService personalService;

    @Autowired
    AdministracionService administracionService;


    @RequestMapping("/Administracion/resguardos")
    public String resguardos(Model model) {


        List<Personal> personal = personalService.findAll();

        model.addAttribute("personal", personal);


        return "pages/cam66/administracion/resguardos";


    }

    @GetMapping("/Administracion/getMaterial")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMaterial(Pageable pageable,
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

        Page<MaterialDidactico> material;
        if (search.isBlank() || search.isEmpty()) {
            material = administracionService.getMaterial(sortedPageable);
        } else {
            // TODO: Hacer el metodo findAll con el search
            material = administracionService.getMaterial(sortedPageable);
            // usuarios = usuarioService.findAllSearch(search, sortedPageable);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("draw", params.get("draw"));
        response.put("data", material.getContent()); // Datos actuales
        response.put("recordsTotal", material.getTotalElements()); // Total de registros
        response.put("recordsFiltered", material.getTotalElements()); // Total de registros filtrados
        return ResponseEntity.ok(response);
    }


    @RequestMapping(
            value = "/Administracion/getAllArticulo",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public @ResponseBody
    List<MaterialDidactico> getAllArticulo(

            HttpServletRequest request,
            HttpServletResponse response

    ) throws IOException {

        List<MaterialDidactico> art = administracionService.getAllArticulo();


        return art;

    }


}
