package org.cmn.cam66.controller;

import org.cmn.cam66.entity.Alumno;
import org.cmn.cam66.entity.CatGrupo;
import org.cmn.cam66.entity.Tutor;
import org.cmn.cam66.repository.AlumnoRepository;
import org.cmn.cam66.service.AlumnoService;
import org.cmn.cam66.service.CatGrupoService;
import org.cmn.cam66.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tools.jackson.databind.ObjectMapper;

import java.util.*;

/**
 * @author Santiago Cristales
 * @date 22/11/2025
 * @project cam66
 */

@Controller
public class indexController {

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    TutorService tutorService;

    @Autowired
    CatGrupoService catGrupoService;
    @Autowired
    private AlumnoRepository alumnoRepository;

    @RequestMapping("/cargaPrimaria")
    public String cargaPrimaria() {


        String json = "";
        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, Object> map = mapper.readValue(
                json, HashMap.class
        );

        ArrayList<HashMap<String, Object>> hashMap = (ArrayList<HashMap<String, Object>>) map.get("lista");

        List<CatGrupo> grupos = catGrupoService.findAll();
        hashMap.forEach(fila -> {

            Optional<Tutor> tutor = tutorService.findByCurp(fila.get("curpT").toString());
            Optional<Alumno> alumno = alumnoService.findByCurp(fila.get("curp").toString());


            try {
                Alumno a = alumno.get();
                a.setIdresponsable(tutor.get());
                int i = 0;
                alumnoRepository.save(a);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });


        return "index";
    }

    @RequestMapping("/index")
    public String cargaPreescolar() {
        return "index";
    }

    @RequestMapping("/cargaSecundaria")
    public String index() {
        return "index";
    }
}
