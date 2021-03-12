package home.excelpdf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import home.entities.Users;
import home.repositories.UsersRepo;


public class ExcelPDFController {

/*	@Autowired UsersRepo usersRepo;
	
//	@RequestMapping(value = "/excel", method= RequestMethod.GET)
    public ModelAndView excel() {
		System.out.println("ExcelPDFController excel is called");
		List<Users> cats = usersRepo.findAll();
		//excelDocument - look excel-pdf-config.xml
		return new ModelAndView("excelDocument", "modelObject", cats);
 
    }
	
	@RequestMapping(value = "/pdf", method= RequestMethod.GET)
    public ModelAndView pdf() {
		
		System.out.println("ExcelPDFController pdf is called");
		List<Users> cats = usersRepo.findAll();
		//pdfDocument - look excel-pdf-config.xml
		return new ModelAndView("pdfDocument", "modelObject", cats);
 
    }
*/
}
