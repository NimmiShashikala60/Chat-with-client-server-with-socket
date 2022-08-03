import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.*;
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

    String massage = "";


    public void initialize(){
         new Thread(() -> {
             try {
                 serverSocket = new ServerSocket(PORT);
                 System.out.println("server Started..!");
                 accept = serverSocket.accept();
                 System.out.println("\nclient Connected..!");

                 dataInputStream = new DataInputStream(accept.getInputStream());
                 dataOutputStream = new DataOutputStream(accept.getOutputStream());
                // bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                 while (!massage.equals("exit")){
                     massage = dataInputStream.readUTF();
                     textLine.appendText("\nserver :" +massage);
                 }

             } catch (IOException e) {
                 e.printStackTrace();
             }
         }).start();
    }


    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {

        String massageText = txtMasage.getText();
        dataOutputStream.writeUTF(massageText);
        dataOutputStream.flush();
    }

}


