package Sever;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Communic{
    public static void main(String[] args) {
        try {
            // 서버에 접속
            Socket socket = new Socket("192.168.158.49", 1234);
            System.out.println("소켓 서버에 접속 완료");

            // 소켓으로부터 입력 스트림 얻기
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 소켓으로부터 출력 스트림 얻기
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            // 서버에 메시지 보내기
            writer.println("Hello, Server!");
            writer.flush();

            // 서버로부터의 응답 읽기
            String response = reader.readLine();
            System.out.println("서버로부터의 응답: " + response);

            // 소켓 종료
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}