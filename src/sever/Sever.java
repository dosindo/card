package sever;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Sever{
    public static void main(String[] args)
    {
        try{
            Socket socket = new Socket("192.168.159.235",   1234);
            System.out.println("소켓 서버에 접속 완료");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}