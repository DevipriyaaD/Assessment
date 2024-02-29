package test_package;

import base_package.Testbase;
import page_package.GetRequestExample;
import page_package.PostRequestExample;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Properties;


public class TC_02_PostRequest_Positive extends Testbase {

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
       // String urlParameters = "{\"email\":\""+testData.getProperty("email")+"\"}";
        String urlParameters = "{\"email\":\""+testData.getProperty("email")+"\",\"password\":\""+testData.getProperty("password")+"\"}";
        endpoint=endpoint+"register";
        PostRequestExample pr=new PostRequestExample();
        System.out.println(endpoint);
        String res = pr.getConnection(endpoint,urlParameters);
        PostRequestExample p = new PostRequestExample();
        int actualStatus=p.getRescode();
        assertValues(testData.getProperty("expectedResponseCode"), String.valueOf(actualStatus));
        System.out.println("Sucessfull Register");
    }

}