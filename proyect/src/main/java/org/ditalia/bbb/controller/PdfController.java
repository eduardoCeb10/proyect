package org.ditalia.bbb.controller;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.ditalia.bbb.entity.Reservacion;
import org.ditalia.bbb.service.IntServiceReservacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/pdf")
public class PdfController {
	@Autowired
	private IntServiceReservacion serviceReservacion;

	@GetMapping(value="/generar-pdf")
	public ModelAndView generarPdf(HttpServletResponse response, Map<String, Object> model,@RequestParam("id") int idReservacion) throws IOException, DocumentException{
		
		Reservacion res = serviceReservacion.obtenerPdf(idReservacion);
		Document document = new Document();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, baos);
		
		BaseColor color = BaseColor.DARK_GRAY;
		Font t = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLDITALIC, color);
		Paragraph titulo = new Paragraph("                                               D ' ITALIA\n\n", t);
		
		Font fontI = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
		Font fontD = new Font(Font.FontFamily.HELVETICA, 14, Font.UNDERLINE);
		Font fontIz = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
		Font fontDe = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL);

		Paragraph p1 = new Paragraph();
		p1.add(new Phrase("Reservación de: ", fontI));
		p1.add(new Phrase(""+res.getUsername(), fontD));
		p1.add(new Phrase("\n\n"));
		p1.setAlignment(Element.ALIGN_CENTER);
		
		Paragraph p2= new Paragraph();
		p2.add(new Phrase("                             Id de la Reservación: ",fontIz));
		p2.add(new Phrase(""+res.getId(),fontDe));
		
		Paragraph p3 = new Paragraph();
		p3.add(new Phrase("                             Nombre del cliente: ",fontIz));
		p3.add(new Phrase(""+res.getNombre()+" "+res.getApellidoPaterno()+" "+res.getApellidoMaterno(),fontDe));
		Paragraph p4 = new Paragraph();
		p4.add(new Phrase("                             Categoria: ",fontIz));
		p4.add(new Phrase(""+res.getCategoria().getNombre(),fontDe));
		Paragraph p5 = new Paragraph();
		p5.add(new Phrase("                             Modelo del vestido: ",fontIz));
		p5.add(new Phrase(""+res.getVestido().getModelo()+", color: "+res.getVestido().getColor()+", talla: "+res.getVestido().getTalla(),fontDe));
		Paragraph p6 = new Paragraph();
		p6.add(new Phrase("                             Accesorio: ",fontIz));
		p6.add(new Phrase(""+res.getAccesorio().getNombre()+", color: "+res.getAccesorio().getColor(),fontDe));
		Paragraph p7 = new Paragraph();
		p7.add(new Phrase("                             Fecha: ",fontIz));
		p7.add(new Phrase(""+res.getFecha(),fontDe));
		Paragraph p8 = new Paragraph();
		p8.add(new Phrase("                             Hora: ",fontIz));
		p8.add(new Phrase(""+res.getHora(),fontDe));
		
		document.open();
		document.add(new Paragraph("                    =========================================================\n\n\n"));
		document.add(new Paragraph(titulo));
		document.add(new Paragraph(p1));
		document.add(new Paragraph(p2));
		document.add(new Paragraph(p3));
		document.add(new Paragraph(p4));
		document.add(new Paragraph(p5));
		document.add(new Paragraph(p6));
		document.add(new Paragraph(p7));
		document.add(new Paragraph("\n\n                    ========================================================="));
		document.close();
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; fieldname=ditalia_reservarcion.pdf");
		response.setContentLength(baos.size());
		ServletOutputStream outputStream = response.getOutputStream();
		baos.writeTo(outputStream);
		outputStream.flush();
		return null;
	}
	
	
}
