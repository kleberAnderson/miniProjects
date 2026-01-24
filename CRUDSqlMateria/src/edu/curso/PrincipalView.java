package edu.curso;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class PrincipalView extends Application {

    private Tela contatoView = new ContatoView();

    @Override
    public void start(Stage stage) {

        Pane telaContatoView = contatoView.render();

        BorderPane panPrincipal = new BorderPane();

        Scene scn = new Scene(panPrincipal, 600, 400);

        //MenuBar menuBar = new MenuBar();

        //Menu menuFile = new Menu("File");
        //Menu menuEdit = new Menu("Edit");
        //Menu menuTela = new Menu("Tela");
        //Menu menuHelp = new Menu("Help");

        //menuBar.getMenus().addAll( menuFile, menuEdit, menuTela, menuHelp);

        //MenuItem itemContato = new MenuItem("Contato");
        //menuTela.getItems().addAll(itemContato);

        //itemContato.setOnAction( e -> panPrincipal.setCenter( telaContatoView ));

        //panPrincipal.setTop( menuBar );

        stage.setScene(scn);
        stage.setTitle("Sistema de Contatos");
        stage.show();
        panPrincipal.setCenter(telaContatoView);

    }

    public static void main(String[] args) {
        Application.launch(PrincipalView.class, args);
    }
}
