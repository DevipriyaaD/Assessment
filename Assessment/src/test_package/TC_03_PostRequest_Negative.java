package test_package;

import page_package.PostRequestExample;

import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.SQLOutput;
import java.util.Properties;

import static base_package.Testbase.assertValues;


public class TC_03_PostRequest_Negative {

    public static void main(String args[]) throws IOException
    {
        HttpURLConnection Conn;
        Properties url = new Properties();
        Properties testData = new Properties();
        FileReader urlReader = new FileReader("Test_Data/endpoint.properties");
        FileReader testDataReader = new FileReader("Test_Data/TC_02_PostTestData.properties");
        url.load(urlReader);
        testData.load(testDataReader);
        String endpoint = url.getProperty("Test_endpoint");
        //Test Inputs
        String urlParameters = "{\"email\":\""+testData.getProperty("email")+"\"}";
       // String urlParameters = "{\"email\":\""+testData.getProperty("email")+"\",\"password\":\""+testData.getProperty("password")+"\"}";
        endpoint=endpoint+"register";
        PostRequestExample pr=new PostRequestExample();
        System.out.println(endpoint);
        String res = pr.getConnection(endpoint,urlParameters);
        PostRequestExample p = new PostRequestExample();
        int actualStatus=p.getRescode();
        assertValues(testData.getProperty("negativeResponsecode"), String.valueOf(actualStatus));
        System.out.println("Unsucessfull Register");
    }

}