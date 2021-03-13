package home.controller;

import java.io.*;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import home.excelpdf.CsvViewImpl;
import home.excelpdf.ExcelDocument;
import home.excelpdf.PDFDocument;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import home.entities.Users;
import home.repositories.UsersRepo;
import home.service.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/download")
@PropertySource("classpath:application.properties")
public class DownloadFileRestController {
	@Autowired UsersService usersRepo;
	@Autowired private Environment env;
	@Value("${filepath}") private String userfilepath;
//	@Autowired PdfWriter pdfWriter;

	@RequestMapping(value = "/pdf", method = RequestMethod.GET, produces = "application/pdf")
 public ResponseEntity<InputStreamResource> downloadPdf(HttpServletRequest request,
						 HttpServletResponse httpresponse) throws Exception {
  
	
  HttpHeaders headers = new HttpHeaders();
  headers.setContentType(MediaType.valueOf("application/pdf"));
  headers.add("Access-Control-Allow-Origin", "*");
  headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
  headers.add("Access-Control-Allow-Headers", "Content-Type");
  headers.add("Content-Disposition", "filename=" );
  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
  headers.add("Pragma", "no-cache");
  headers.add("Expires", "0");

  if (userfilepath.isBlank()) {userfilepath = env.getProperty("filepath");}
  final String fname=userfilepath+"phones-list.pdf";
  FileOutputStream fos = new FileOutputStream(fname);
  //ByteArrayOutputStream baos = new ByteArrayOutputStream();
  Document document =  new Document();
  System.out.println("=====================================1");
		PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
		System.out.println("=====================================2");
		document.open();
		System.out.println("=====================================3");
		 getPdf(document);
		System.out.println("=====================================4");
		 document.close();
		System.out.println("=====================================5");
  //pDFDocument.buildPdfDocumentMy(pDFDocument.getAttributesMap(), document, PdfWriter, request, httpresponse );
  byte[] pdf = Files.readAllBytes((new File(fname)).toPath());
  InputStream inputStream = new ByteArrayInputStream(pdf);
  InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
  headers.setContentLength(pdf.length);
	headers.put("Content-Disposition", Collections.singletonList("attachment; filename=phones.pdf"));
 // baos.flush();
  ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(inputStreamResource, headers, HttpStatus.OK);
 // baos.close();
		return response;
 }
	
	@RequestMapping(value = "/xls", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	 public ResponseEntity<InputStreamResource> downloadXls() throws IOException, DocumentException {
	  
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	  Workbook workbook  = getXls();
	//	workbook.
	  workbook.write(baos);
		workbook.close();
	  HttpHeaders headers = new HttpHeaders();
	  headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
	  headers.add("Access-Control-Allow-Origin", "*");
	  headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
	  headers.add("Access-Control-Allow-Headers", "Content-Type");
	  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	  headers.add("Pragma", "no-cache");
	  headers.add("Expires", "0");

		byte[] pdf = baos.toByteArray();
		InputStream inputStream = new ByteArrayInputStream(pdf);
		InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
		headers.setContentLength(pdf.length);
		headers.put("Content-Disposition", Collections.singletonList("attachment; filename=phones.xlsx"));
		baos.flush();
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(inputStreamResource, headers, HttpStatus.OK);
		baos.close();
		return response;
	 }
	
	@RequestMapping(value = "/file/{fileName:.+}", method = RequestMethod.GET)
	 public ResponseEntity<InputStreamResource> downloadfile(@PathVariable("fileName") String fileName) throws IOException {
	  System.out.println("Calling Download:- " + fileName);
		final String userfilepath = env.getProperty("filepath");

	  ClassPathResource pdfFile = new ClassPathResource(userfilepath + fileName);
	  HttpHeaders headers = new HttpHeaders();
	  headers.setContentType(MediaType.APPLICATION_JSON);
	  headers.add("Access-Control-Allow-Origin", "*");
	  headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
	  headers.add("Access-Control-Allow-Headers", "Content-Type");
	  headers.add("Content-Disposition", "filename=" + fileName);
	  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	  headers.add("Pragma", "no-cache");
	  headers.add("Expires", "0");
	 
	  headers.setContentLength(pdfFile.contentLength());
	  ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
	    new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);
	  return response;
	 
	 }
	////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////	
	
	@RequestMapping(value = "/excel", method= RequestMethod.GET)
    public ModelAndView excel() {
		System.out.println("ExcelPDFController excel is called");
		List<Users> cats = usersRepo.findAll();
		ModelAndView mv = new ModelAndView();
		mv.setView(new ExcelDocument());
		mv.getModel().put("modelObject",cats);
		return mv;
//		return new ModelAndView("pdfDocument", "modelObject", cats);
	//	return new ModelAndView("excelDocument", "modelObject", cats);
 
    }
	
	@RequestMapping(value = "/pdfdoc", method= RequestMethod.GET)
    public ModelAndView pdf() {
		
		System.out.println("ExcelPDFController pdf is called");
		List<Users> cats = usersRepo.findAll();
		//pdfDocument - look excel-pdf-config.xml
		ModelAndView mv = new ModelAndView();
		mv.setView(new PDFDocument());
		mv.getModel().put("modelObject",cats);
		return mv;
//		return new ModelAndView("pdfDocument", "modelObject", cats);
 
    }

	@RequestMapping(value = "/csv", method= RequestMethod.GET)
	public ModelAndView csv() {

		System.out.println("CSVController pdf is called");
		List<Users> cats = usersRepo.findAll();
		//pdfDocument - look excel-pdf-config.xml
		ModelAndView mv = new ModelAndView();
		mv.setView(new CsvViewImpl());
		mv.getModel().put("csvData",cats);
		mv.getModel().put("csvHeader", new String[]{"id", "name", "phone"});
		return mv;
//		return new ModelAndView("pdfDocument", "modelObject", cats);

	}
 /////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
	private void getPdf(Document document) throws DocumentException {

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

	        List<Users> cats = usersRepo.findAll();;
	        for (Users cat : cats) {
	            table.addCell(cat.getId().toString());
	            table.addCell(cat.getName());
	            table.addCell(cat.getPhone());
	        }

	        document.add(table);

	//	document.close();
		//return document;
	}
	private Workbook getXls() throws DocumentException, IOException {
		 Workbook workbook = new XSSFWorkbook(); 
		Sheet excelSheet = workbook.createSheet("Simple excel example");
	        //Excel file name change
	      
	        Font font = workbook.createFont();
	        font.setFontName("Arial");
	        font.setBold(true); // setBoldweight(Font.);
	        font.setColor(Font.COLOR_RED); //setColor(Color. );
	        
	        //Create Style for header
	        CellStyle styleHeader = workbook.createCellStyle();
	        styleHeader.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	        styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND );
	        styleHeader.setFont(font);
	 
	        //Set excel header
	        Row header = excelSheet.createRow(0);
	        header.createCell(0).setCellValue("id");
	        header.getCell(0).setCellStyle(styleHeader);
	        header.createCell(1).setCellValue("User");
	        header.getCell(1).setCellStyle(styleHeader);
	        header.createCell(2).setCellValue("Phone");
	        header.getCell(2).setCellStyle(styleHeader);
	 
	        //Get data from model
	        List<Users> cats =  usersRepo.findAll();
	        int rowCount = 1;
	        for (Users cat : cats) {
	            Row row = excelSheet.createRow(rowCount++);
	            row.createCell(0).setCellValue(cat.getId());
	            row.createCell(1).setCellValue(cat.getName());
	            row.createCell(2).setCellValue(cat.getPhone());
	        }
	    //    workbook.close();
		return workbook;
	}
	//////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
}
