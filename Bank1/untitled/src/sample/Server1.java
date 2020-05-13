package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.classes.DatabaseHandler;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class Server1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample/fxml/admin.fxml"));
        primaryStage.setTitle("Mobile bank");
        primaryStage.setScene(new Scene(root, 334, 589));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);


        try{

            ServerSocket server = new ServerSocket(1111);
            System.out.println("Waiting for a client...");
            Socket socket = server.accept();
            DatabaseHandler handler = new DatabaseHandler();
            handler.getDbConnection();
            ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            String message= "";
            while((message=(String)in.readObject())!=null){
                if(message.equals("HELLO")){
                    toClient.writeObject("HELLO");
                }
                else if(message.equals("Bye")){
                    toClient.writeObject("Good Bye");
                }

            }
            in.close();
            socket.close();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }}