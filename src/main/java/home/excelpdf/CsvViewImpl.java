package home.excelpdf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.supercsv.io.ICsvBeanWriter;

import home.entities.Users;

import javax.servlet.http.HttpServletResponse;

public class CsvViewImpl extends AbstractCsvView {
	 
    @Override
    protected void buildCsvDocument(ICsvBeanWriter csvWriter,
            Map<String, Object> model, HttpServletResponse response) throws IOException {

        response.setHeader("Content-Disposition", "attachment; filename=\"users-list.csv\"");
        List<Users> listUsers = (List<Users>) model.get("csvData");
        String[] header = (String[]) model.get("csvHeader");
        csvWriter.writeHeader(header);
        for (Users users : listUsers) {
            csvWriter.write(users, header);
        }
    }
}