package home.excelpdf;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;//  document.AbstractExcelView;

import home.entities.Users;

@Component
public class ExcelDocument extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
	      //New Excel sheet
        Sheet excelSheet = workbook.createSheet("Simple excel example");
        //Excel file name change
        response.setHeader("Content-Disposition", "attachment; filename=excelDocument.xls");
 
 
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
        setExcelHeader(excelSheet, styleHeader);
 
        //Get data from model
        List<Users> cats = (List<Users>) model.get("modelObject");
        int rowCount = 1;
        for (Users cat : cats) {
            Row row = excelSheet.createRow(rowCount++);
            row.createCell(0).setCellValue(cat.getId());
            row.createCell(1).setCellValue(cat.getName());
            row.createCell(2).setCellValue(cat.getPhone());
        }
 
    }
    public void setExcelHeader(Sheet excelSheet, CellStyle styleHeader) {
        //set Excel Header names
        Row header = excelSheet.createRow(0);
        header.createCell(0).setCellValue("id");
        header.getCell(0).setCellStyle(styleHeader);
        header.createCell(1).setCellValue("User");
        header.getCell(1).setCellStyle(styleHeader);
        header.createCell(2).setCellValue("Phone");
        header.getCell(2).setCellStyle(styleHeader);

	}

}
