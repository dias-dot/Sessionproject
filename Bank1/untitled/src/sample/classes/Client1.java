package sample.classes;

import javafx.beans.binding.ObjectBinding;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client1 {

    public static void Sendmessage(TextField typing){
        try{
            Socket socket = new Socket("127.0.0.1", 1111);
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            if((typing=(TextField)inputStream.readObject())!=null){
                toServer.writeObject(typing);

            }
            socket.close();
            toServer.close();

        }catch (Exception e){
            e.printStackTrace();
        }}
}



