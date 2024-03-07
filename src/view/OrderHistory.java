package view;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;


public class OrderHistory extends Application{

	private Label lorder,lhistory,ltitle;
	private TableView tv;
	private TableColumn orderNoCol,customerNameCol,dateCol;
	private GridPane tablePane;
	private BorderPane mainPane;
	
	public void createNodes()
	{
		lorder=new Label("Order");
		
		
		lhistory=new Label("History");
		ltitle=new Label("Order History");
		ltitle.setAlignment(Pos.CENTER);
		
		
	}
	public void defineLayout()
	{
		tablePane=new GridPane();
		tablePane.add(ltitle, 0, 0);
		tablePane.add(tv, 0, 1);
		tablePane.setAlignment(Pos.CENTER);
		tablePane.setPrefWidth(700);
		tablePane.setPrefHeight(700);
		tablePane.setVgap(20);
		
		mainPane=new BorderPane();

		mainPane.setCenter(tablePane);
		
		
	}
	public void createTable()
	{
		tv=new TableView();
		tv.setPrefWidth(600);
		tv.setPrefHeight(600);
		tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
		orderNoCol=new TableColumn("Order No");
		customerNameCol=new TableColumn("Customer Name");
		dateCol=new TableColumn("Date");
		
		
		tv.getColumns().add(orderNoCol);
		tv.getColumns().add(customerNameCol);
		tv.getColumns().add(dateCol);
		
		
		
		
		
		
		
		
		
//		orderNoCol.setHgrow
//	    customerNameCol.setHgrow(Priority.ALWAYS);
//	    dateCol.setHgrow(Priority.ALWAYS);	
	    }
	public void setStyle()
	{
		ltitle.setId("titleOrderHistory");
	}
	

	@Override
	public void start(Stage st) throws Exception {
		
		createNodes();
		createTable();
		defineLayout();
		setStyle();
		
		Scene sc=new Scene(mainPane,700,700);
		URL url = this.getClass ().getResource ("mystyle.css"); 
		sc.getStylesheets().add(url.toExternalForm());
		
		st.setScene(sc);
		st.setTitle("Order History");
		st.show();
		
	}
	public static void main(String[] args) {
		Application.launch(args);

	}

}
