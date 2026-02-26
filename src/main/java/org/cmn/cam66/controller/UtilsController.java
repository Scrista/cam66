package org.cmn.cam66.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.cmn.cam66.entity.MaterialDidactico;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Santiago Cristales
 * @date 19/02/2026
 * @project cam66
 */

@Controller
public class UtilsController {

    @RequestMapping("/utils/mergePdf")
    @ResponseBody
    public String mergePdf() throws IOException {


        String pdf1 = "C:\\Users\\nemo_\\OneDrive\\Documentos\\p1 la esc.pdf";
        String pdf2 = "C:\\Users\\nemo_\\OneDrive\\Documentos\\expedienteLa escuela es nuestra p2.pdf";
        String output = "C:\\Users\\nemo_\\OneDrive\\Documentos\\expedienteLaEscuelaEsNuestra.pdf";

        PDFMergerUtility merger = new PDFMergerUtility();

        merger.addSource(pdf1);
        merger.addSource(pdf2);

        merger.setDestinationFileName(output);

        merger.mergeDocuments(null);

        System.out.println("PDFs unidos correctamente.");


        return "ok";
    }


    @SneakyThrows
    @RequestMapping(
            value = "/Administracion/foliosInventario",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public @ResponseBody
    List<MaterialDidactico> foliosInventario(

            HttpServletRequest request,
            HttpServletResponse response

    ) {

        int width = 800;
        int height = 150;
        int constInicio=1;
        int inicio = constInicio;
        int limite = 180;
        int hoja = 1;

        List<String> hojas = new ArrayList<>();
        for (int i = 1; i <= limite; i++) {
            String serie = String.format("%06d", inicio++);

            String codigo = "CAM66-2526-"+serie;

            Code128Writer barcodeWriter = new Code128Writer();
            BitMatrix bitMatrix = barcodeWriter.encode(codigo, BarcodeFormat.CODE_128, width, height);
            String rutaCode = "C:\\Users\\nemo_\\OneDrive\\Imágenes\\codigo\\"+codigo+".png";

            Path path = new File(rutaCode).toPath();
            try {
                MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }



        HashMap<String, Object> datos = new HashMap<>();
        int reduce = 17;
        int constante = 18;

        inicio = constInicio;
        for (int i = 1; i <= limite; i++) {
            int itera = constante-reduce;
            reduce--;

            String serie = String.format("%06d", inicio++);

            String codigo = "CAM66-2526-"+serie;
            String rutaCode = "C:\\Users\\nemo_\\OneDrive\\Imágenes\\codigo\\"+codigo+".png";
            List s = new ArrayList();

            datos.put("folio"+itera, codigo);
            datos.put("codigo"+itera, rutaCode);
            s.add(datos);

            if (i % 18 == 0) {
                reduce = 17;

                JRBeanCollectionDataSource dataSource =
                        new JRBeanCollectionDataSource(s);

                File jasper = new File("C:\\Users\\nemo_\\JaspersoftWorkspace\\MyReports\\folios.jasper");

                JasperReport jasperReport = null;
                try {
                    jasperReport = (JasperReport) JRLoader.loadObject(jasper);

                    JasperPrint jasperPrint =
                            JasperFillManager.fillReport(jasperReport, datos, new JREmptyDataSource(1));

                    JRPdfExporter exporter = new JRPdfExporter();

                    String archivo = "C:\\Users\\nemo_\\OneDrive\\Imágenes\\codigo\\archivo"+hoja+".pdf";
                    hojas.add(archivo);

                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(
                            new SimpleOutputStreamExporterOutput(
                                    archivo
                            )
                    );

                    exporter.exportReport();

                    hoja++;
                    datos.clear();
                } catch (JRException e) {
                    throw new RuntimeException(e);
                }


            }

        }



        PDFMergerUtility merger = new PDFMergerUtility();

       for (String h:hojas){
           merger.addSource(h);
       }


       String nombrefinal= "foliosDel"+constInicio+"al"+(constInicio+limite);

        String archivo = "C:\\Users\\nemo_\\OneDrive\\Imágenes\\codigo\\"+nombrefinal+".pdf";

        merger.setDestinationFileName(archivo);

        merger.mergeDocuments(null);

        System.out.println("PDFs unidos correctamente.");

        try {
            merger.mergeDocuments(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Código generado correctamente.");





        return null;

    }
}
