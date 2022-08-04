import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientForm {
    public TextArea textline;
    public TextField textmasage;
    public Label txtclient;

    final int PORT = 5001;

    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String massage = "";

    public void initialize(){
        new Thread(() -> {
            try {
             socket = new Socket("localhost",PORT);

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                // bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                while (!massage.equals("exit")) {
                    massage = dataInputStream.readUTF();
                    textline.appendText("\nclient :"+ massage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }



    public void btnsendonaction(ActionEvent actionEvent) throws IOException {
         String massageText = textmasage.getText().trim();
         dataOutputStream.writeUTF(massageText);
         dataOutputStream.flush();
    }
}
