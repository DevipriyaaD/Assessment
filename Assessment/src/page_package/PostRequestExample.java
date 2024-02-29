package page_package;

import base_package.Testbase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class PostRequestExample extends Testbase {
    public int getRescode() {
        return rescode;
    }

    public void setRescode(int rescode) {
        this.rescode = rescode;
    }

    static int rescode=0;
    public static String getConnection(String requestURL, String BodyData ) throws IOException {
        String responseMessage = null;
        try {

            byte[] postData = BodyData.getBytes( StandardCharsets.UTF_8 );
            int postDataLength = postData.length;
            URL url = new URL( requestURL );
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/json");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                wr.write( postData );
                int responseCode = conn.getResponseCode();
                rescode=responseCode;
                System.out.println("Response Code: " + responseCode);
                if (responseCode >= 400) {
                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    StringBuilder errorResponse = new StringBuilder();
                    String line;
                    while ((line = errorReader.readLine()) != null) {
                        errorResponse.append(line);
                    }
                    errorReader.close();
                    System.out.println("Error Response: " + errorResponse.toString());
                    responseMessage=errorResponse.toString();
                }else
                {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    System.out.println(response.toString());
                    responseMessage=response.toString();
                }}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessage;
    }
}
