package test_package;
import base_package.Testbase;
import page_package.GetRequestExample;
import java.io.FileNotFoundException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;



public class TC_01_Pagination extends Testbase {

    public static void main(String args[]) throws IOException
    {
        HttpURLConnection Conn;
        Properties url = new Properties();
        Properties testData = new Properties();
        FileReader urlReader = new FileReader("Test_Data/endpoint.properties");
        FileReader testDataReader = new FileReader("Test_Data/TC_01_PaginationTestData.properties");
        url.load(urlReader);
        testData.load(testDataReader);
        String endpoint = url.getProperty("Test_endpoint");
        //Test Inputs
        String Page1endpoint=endpoint+"users?delay="+testData.getProperty("delay")+"&page="+testData.getProperty("page1")+"&per_page="+testData.getProperty("per_page")+"";
        String Page2endpoint=endpoint+"users?delay="+testData.getProperty("delay")+"&page="+testData.getProperty("page2")+"&per_page="+testData.getProperty("per_page")+"";
        GetRequestExample gr=new GetRequestExample();
        String res = gr.getConnection(Page1endpoint);
        System.out.println(res);
        String res1 = gr.getConnection(Page2endpoint);
        System.out.println(res1);
        int count = getCountfromRes(res,"id");
        int count1= getCountfromRes(res1,"id");
        assertValues(testData.getProperty("page1_expected"),String.valueOf(count));
        assertValues(testData.getProperty("page2_expected"),String.valueOf(count1));

    }
}