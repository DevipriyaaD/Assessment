package page_package;

import base_package.Testbase;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetRequestExample extends Testbase {

    public static String getConnection(String URL) {
        String formattedJson = null;
        try {
            HttpURLConnection conn = GetRequestExample.getConnectionMethod(URL);
            long startTime = System.currentTimeMillis();
            conn.connect();
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;
            System.out.println("Response Time: " + responseTime + " milliseconds");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                Object json = mapper.readValue(inputLine, Object.class);
                formattedJson = mapper.writeValueAsString(json);
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedJson;
    }
}
