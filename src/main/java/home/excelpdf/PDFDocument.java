package home.excelpdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; 

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;

import home.entities.Users;

@Component
public class PDFDocument extends AbstractPdfView {

	public void buildPdfDocumentMy(
			Map<String, Object> model,
			Document document,
			PdfWriter writer,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		buildPdfDocument(model, document, writer, request, response);
	}
	@Override
	 protected void buildPdfDocument(
	            Map<String, Object> model,
	            Document document,
	            PdfWriter writer,
	            HttpServletRequest request,
	            HttpServletResponse response) throws Exception {


	        PdfPTable table = new PdfPTable(3);
	        PdfPCell header1 = new PdfPCell(new Phrase("id"));
	        PdfPCell header2 = new PdfPCell(new Phrase("User"));
	        PdfPCell header3 = new PdfPCell(new Phrase("Phone"));
	        header1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        header2.setHorizontalAlignment(Element.ALIGN_LEFT);
	        header3.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table.addCell(header1);
	        table.addCell(header2);
	        table.addCell(header3);

	        //Get data from model
	        List<Users> cats = (List<Users>) model.get("modelObject");
	        for (Users cat : cats) {
	            table.addCell(cat.getId().toString());
	            table.addCell(cat.getName());
	            table.addCell(cat.getPhone());
	        }
	        document.add(table);
	    }
	
}
