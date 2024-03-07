package view;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Orders extends Application {
	
	private static BorderPane mainPane;
	
	public static void setMainPane()
	{
		mainPane=new BorderPane();
		
		mainPane.setCenter(new OrderList().getMainPane());
		
	}
	public static  BorderPane getMainPane()
	{
		setMainPane();
		return mainPane;
	}
	
	
	public void start(Stage st) throws Exception {
		
		Scene sc=new Scene(mainPane,1000,1000);
		URL url = this.getClass ().getResource ("mystyle.css"); 
		sc.getStylesheets().add(url.toExternalForm());
		
		st.setScene(sc);
		st.setTitle("Order");
		st.show();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
