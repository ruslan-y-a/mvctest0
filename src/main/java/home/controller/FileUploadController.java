package home.controller;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NoSuchObjectException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.gson.JsonArray;
import home.files.Xmlpars;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.env.Environment;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import home.entities.Users;
import home.repositories.UsersRepo;
import home.service.UsersService;

import javax.servlet.http.HttpServletRequest;

@Controller
@PropertySource("classpath:application.properties")
public class FileUploadController {
	@Autowired UsersService usersRepo;
	@Autowired ObjectMapper objectMapper;
	@Autowired private Environment env;
	@Value("${filepath}") private String userfilepath;

	@RequestMapping(value = { "/download/list" }, method = RequestMethod.GET)
    public ModelAndView rolesorder() {

			   List<Users> users=null;
			    try {
			    	users = usersRepo.findAll();
			    } catch (Exception e) {e.printStackTrace();}

			    ModelAndView model = new ModelAndView();
			    model.addObject("users", users);
			    return model;
 }
    @ResponseBody
	@RequestMapping(value = { "/download/list/{id}" }, method = RequestMethod.GET)
	public Users rfinduser(@PathVariable Long id) throws NoSuchObjectException {
		Users user = usersRepo.findById(id);
		if (user==null) {throw new NoSuchObjectException(" NoSuchObjectException ");}

		return user;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String rolesorder(Model model) {
		    return "upload";
}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model, HttpServletRequest request) {
		model.addAttribute("base",request);
		return "about";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file,Model model) {
	 
	        if (!file.isEmpty()) {
	 
	            try {

					byte[] fileBytes = file.getBytes();
					String rootPath = System.getProperty("catalina.home");
					System.out.println("Server rootPath: " + rootPath);
					System.out.println("File original name: " + file.getOriginalFilename());
					System.out.println("File content type: " + file.getContentType());

					//	ClassPathResource pdfFile = new ClassPathResource("/files/" );
					userfilepath = env.getProperty("filepath");
					final String sfile = file.getOriginalFilename();
					System.out.println("File is saving under: " + userfilepath + sfile);
					File newFile = new File(userfilepath + sfile); //new File(rootPath + File.separator + file.getOriginalFilename());

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
					stream.write(fileBytes);
					stream.close();

					System.out.println("File is saved under: " + newFile.getPath());// rootPath + File.separator + file.getOriginalFilename());
					List<Users> list = Xmlpars.parse(userfilepath + sfile);
					list.forEach(x -> {
						System.out.println(x + " is reading");
						try{usersRepo.save(x);System.out.println(x + "  saved");} catch (Exception e) { System.out.println(x + " was not saved");}
					});
					/*
					List<Users> list = parseList(newFile);
					list.forEach(x -> usersRepo.save(x));
					System.out.println("Userd saved: " + list.size());  /*
					//////////////////////////////////////////////////////////////
	               /* ClassPathResource fpath = new ClassPathResource("files/" + file.getOriginalFilename());
	                File newFile2 = fpath.getFile();
	                BufferedOutputStream stream2 = new BufferedOutputStream(new FileOutputStream(newFile2));
	                stream2.write(fileBytes);
	                stream2.close();
	       
	                System.out.println("File is saved under: " + "files/" + file.getOriginalFilename());
	                return "File is saved under: " + rootPath + File.separator + file.getOriginalFilename() + "\n" + 
	                "File is saved under: " + "files/" + file.getOriginalFilename();
	                */
				   return "File is saved under: " + newFile.getPath();
	            } catch (Exception e) {
	                e.printStackTrace();
	                return "File upload is failed: " + e.getMessage();
	            }
	        } else {
	            return "File upload is failed: File is empty";
	        }
	}
	
	  private List<Users> parseList(File file) throws IOException {

		 List<Users> mSubjects = new LinkedList<>();
		  mSubjects = objectMapper.readValue(file, new TypeReference<List<Users>>() {});

		    return mSubjects;
	  }
/*
	public void parse(String file) throws JsonProcessingException, MalformedURLException {
		String json = parseUrl(new URL(file));
		JsonFactory factory = new JsonFactory();

		ObjectMapper mapper = new ObjectMapper(factory);
		JsonNode rootNode = mapper.readTree(json);

		Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
		while (fieldsIterator.hasNext()) {

			Map.Entry<String,JsonNode> field = fieldsIterator.next();
			System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
		}
	}
	public void parse2(String json) throws JsonProcessingException, ParseException {
	  	JSONObject weatherJsonObject = (JSONObject) JSONValue.parseWithException(json);

		}
*/
	public static String parseUrl(URL url) {
		if (url == null) { return ""; }
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				stringBuilder.append(inputLine);
				System.out.println(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

}
