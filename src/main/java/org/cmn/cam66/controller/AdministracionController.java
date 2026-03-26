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
import org.cmn.cam66.Bitacoraimpresion;
import org.cmn.cam66.entity.Alumno;
import org.cmn.cam66.entity.MaterialDidactico;
import org.cmn.cam66.entity.Personal;
import org.cmn.cam66.entity.Resguardos;
import org.cmn.cam66.repository.MaterialRepository;
import org.cmn.cam66.service.AdministracionService;
import org.cmn.cam66.service.ImpresionService;
import org.cmn.cam66.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;

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

    @Autowired
    ImpresionService impresionService;


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



    @RequestMapping(
            value = "/Administracion/getResguardos",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public @ResponseBody
    List<Resguardos> getResguardos(

            HttpServletRequest request,
            HttpServletResponse response

    ) throws IOException {

        List<Resguardos> art = administracionService.getResguardos();


        return art;

    }

    @RequestMapping("Administracion/saveResguardo")
    @ResponseBody
    public Resguardos saveResguardo(@AuthenticationPrincipal UserDetails user,
                                    @RequestParam(name = "idpersonal") Long idPersonal,
                                    @RequestParam(name = "articulos") String articulos,
                                    @RequestParam(name = "obs") String obs){


        Optional<Personal> autoriza = personalService.findByUsuario(user.getUsername());
        Personal resguarda = personalService.findById(idPersonal);

        Resguardos resguardo = new Resguardos();

        resguardo.setAutoriza(autoriza.get());
        resguardo.setObsAutoriza(obs);
        resguardo.setResguarda(resguarda);

        return null;
    }


    @RequestMapping("/Administracion/impresiones")
    public String impresiones(Model model) {


        List<Personal> personal = personalService.findAll();

        model.addAttribute("personal", personal);


        return "pages/cam66/administracion/impresiones";


    }

    //getImpresiones

    @GetMapping("/Administracion/getImpresiones")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getImpresiones(Pageable pageable,
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

        Page<Bitacoraimpresion> impresiones;
        if (search.isBlank() || search.isEmpty()) {
            impresiones = administracionService.getImpresiones(sortedPageable);
        } else {
            // TODO: Hacer el metodo findAll con el search
            impresiones = administracionService.getImpresiones(sortedPageable);
            // usuarios = usuarioService.findAllSearch(search, sortedPageable);
        }
        BigDecimal total = BigDecimal.ZERO;

        for (Bitacoraimpresion b : impresiones) {
            total = total.add(b.getCobrar());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("draw", params.get("draw"));
        response.put("data", impresiones.getContent()); // Datos actuales
        response.put("recordsTotal", impresiones.getTotalElements()); // Total de registros
        response.put("recordsFiltered", impresiones.getTotalElements()); // Total de registros filtrados
        response.put("total", total); // Total de registros filtrados
        return ResponseEntity.ok(response);
    }


//    saveBitacoraImpresion



    @RequestMapping("Administracion/saveBitacoraImpresion")
    @ResponseBody
    public Bitacoraimpresion saveBitacoraImpresion(@AuthenticationPrincipal UserDetails user,
                                    @RequestParam(name = "idpersonal") Long idPersonal,
                                    @RequestParam(name = "cantidad") int cantidad,
                                    @RequestParam(name = "costo") Double costo){


        Personal solicita = personalService.findById(idPersonal);
        Optional<Personal> creador = personalService.findByUsuario(user.getUsername());
        Date fecha = new Date(System.currentTimeMillis());
        Bitacoraimpresion bitacora = new Bitacoraimpresion();
        bitacora.setCantidad(cantidad);
        bitacora.setCobrar(BigDecimal.valueOf(costo*cantidad));
        bitacora.setCobrado(false);
        bitacora.setFecha(fecha);
        bitacora.setIdpersonal(solicita);
        bitacora.setIdcreador(creador.get());

        Bitacoraimpresion rest = impresionService.save(bitacora);



        return rest;
    }

}
