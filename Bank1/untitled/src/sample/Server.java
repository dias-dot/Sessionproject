package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import sample.classes.DatabaseHandler;

import sample.classes.User;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  {




    public static void main(String[] args) {


        try{

            ServerSocket server = new ServerSocket(1111);
            System.out.println("Waiting for a client...");
            Socket socket = server.accept();
            DatabaseHandler handler = new DatabaseHandler();
            handler.getDbConnection();

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());


            User user = null;
            if((user = (User)in.readObject()) != null){
                handler.signUpUser(user);
                System.out.println("Client Connected");
            }
            in.close();
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
