package base_package;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Testbase {

    public static HttpURLConnection getConnectionMethod(String requestURL) throws IOException {
        URL url = new URL( requestURL );
        HttpURLConnection Conn= (HttpURLConnection) url.openConnection();
        Conn.setRequestMethod("GET");
        Conn.setRequestProperty("Content-Type", "application/json");
        return Conn;
    }


     public static void getInputFromReader(HttpURLConnection Connection) throws IOException {
         BufferedReader in = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
         String inputLine;
         StringBuilder response = new StringBuilder();

    }
    public static Boolean assertValues(String Expected , String ActualValue)
    {
        boolean flag = false;
        System.out.println("Expected----->"+Expected);
        System.out.println("ActualValue----->"+ActualValue);
        if (Expected.equals(ActualValue))
        {
            flag = true;
            System.out.println( "As Expected , " +Expected + "To Matches with" + ActualValue);
        }else
        {
            System.out.println("Not as Expected");
        }
        return  flag;
    }

    public static int getCountfromRes(String para , String searchWord)
    {
        int count = para.split(searchWord, -1).length - 1;
        System.out.println("per_page has " + searchWord+ " " + count + " times .");
        return count;
    }
    public static Boolean assertValuesTime(String Expected , String ActualValue)
    {
        boolean flag = false;
        System.out.println("ExpectedTime----->"+Expected);
        System.out.println("ActualValueTime----->"+ActualValue);
        if (Expected.equals(ActualValue))
        {
            flag = true;
            System.out.println( "As Expected , " +Expected + " To Matches with" + ActualValue);
        }else
        {
            System.out.println("Not as Expected");
        }
        return  flag;
    }


}
