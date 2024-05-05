package seng201.team0.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class ShopInventoryWindow extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/ShopInventory.fxml"));
        Parent root = baseLoader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Shop and Inventory");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void launchWrapper(String[] args) {
        launch(args);
    }
}
