package Sever;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever {
    public static void main(String[] args) {
        try {
            // 서버 소켓 생성
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("서버 대기 중...");

            // 클라이언트의 연결 대기
            Socket clientSocket = serverSocket.accept();
            System.out.println("클라이언트 연결 완료");

            // 소켓으로부터 입력 스트림 얻기
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // 소켓으로부터 출력 스트림 얻기
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());

            // 클라이언트로부터의 메시지 읽기
            String message = reader.readLine();
            System.out.println("클라이언트로부터의 메시지: " + message);

            // 클라이언트에게 응답 보내기
            writer.println("Hello, Client!");
            writer.flush();

            // 소켓 종료
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}