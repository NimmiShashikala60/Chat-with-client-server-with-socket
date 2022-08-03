import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerForm {
    public Label txtserver;
    public TextArea textLine;
    public TextField txtMasage;

    final int PORT = 5000;

    ServerSocket serverSocket;
    Socket accept;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public void initialize(){
         new Thread(() -> {
             try {
                 serverSocket = new ServerSocket(PORT);
                 System.out.println("server Started..!");
                 accept = serverSocket.accept();
                 System.out.println("client Connected..!");
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }).start();
    }

    public void btnsendonaction(ActionEvent actionEvent) {
    }
}
