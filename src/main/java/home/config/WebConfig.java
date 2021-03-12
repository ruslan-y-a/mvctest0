package home.config;
/*
import home.excelpdf.ExcelDocument;
import home.excelpdf.PDFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;
*/
import java.util.ArrayList;
import java.util.List;

//@Configuration
//@ImportResource({ "/WEB-INF/dispatcher.xml"})
public class WebConfig  { //implements WebMvcConfigurer
    /*
   // @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.TEXT_HTML);
    }

   // @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        jsonView.setPrettyPrint(true);
        MappingJackson2XmlView xmlView = new MappingJackson2XmlView();
        xmlView.setPrettyPrint(true);
        registry.enableContentNegotiation(jsonView, xmlView,
                new ExcelDocument(), new PDFDocument());
        registry.freeMarker();
    }

*/
}