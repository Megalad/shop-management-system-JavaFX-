package view;

import java.net.URL;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.DBHandler3;

public class CreateOrder extends Application {
	
	private GridPane labelPane,textFieldPane,centerPane,buttonPane;
	private Label lorderNo,lorderNumber,lcustomerName,lphone,lerror,lback;
	private TextField tcustomerName,tphone;
	private BorderPane mainPane; 
	private Button  btnCreate,btnCancel;
	private OrderList orderList;
	private Stage stage;
	
	 public CreateOrder(OrderList orderList) {
	        this.orderList = orderList;
	    }
	public void createNode()
	{
		
		lorderNo=new Label("Order No : ");
		lorderNumber=new Label(Integer.toString(DBHandler3.getOrderNumber()+1));
		lcustomerName=new Label("Customer Name : ");
		lphone=new Label("Phone : ");
		lerror=new Label();
		
		lback=new Label("< Back");
		lback.setCursor(Cursor.HAND);
		lback.setPadding(new Insets(10));
		lback.setOnMouseClicked(e->{
			mainPane.setCenter(new OrderList().getMainPane());
			mainPane.setTop(null);
			MainPage.lpath.setText("Main Page  -> Order ");
		});
		
		tcustomerName=new TextField();
		tphone=new TextField();
		
		btnCreate=new Button("Create");
		btnCreate.setOnAction(e -> {
		    if (tcustomerName.getText().equals("") || tphone.getText().equals("")) {
		        tcustomerName.setText("Unknown Customer");
		        tphone.setText("09XXXXXXXXX");
		    } else {
		        lerror.setText("");
		        DBHandler3.insertOrderTable(tcustomerName.getText(), tphone.getText());
		        
		        // Update the main pane of the existing OrderList instance
		        orderList.setMainPane(new OrderDetail().getMainPane());
		        
		        MainPage.lpath.setText("Main Page  -> Order  ->  Create New Order  ->  Order Detail ");
		        stage.close();
		    }
		});

		
		btnCancel=new Button("Cancel");
		btnCancel.setOnAction(e->{
//			mainPane.setCenter(new OrderList().getMainPane());
//			mainPane.setTop(null);
			stage.close();
			MainPage.lpath.setText("Main Page  -> Order ");
		});
		

	}
	public void defineLayout()
	{
		
		labelPane=new GridPane();
//		labelPane.add(lorderNo, 0, 0);
		labelPane.add(lcustomerName, 0, 0);
		labelPane.add(lphone, 0, 1);
		labelPane.setVgap(30);
		
		textFieldPane=new GridPane();
//		textFieldPane.add(lorderNumber, 0, 0);
		textFieldPane.add(tcustomerName, 0, 0);
		textFieldPane.add(tphone, 0, 1);
		textFieldPane.setVgap(20);
		
		
		buttonPane=new GridPane();
		buttonPane.add(btnCancel, 0, 0);
		buttonPane.add(btnCreate, 1, 0);
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setHgap(30);
		
		centerPane=new GridPane();
		centerPane.add(labelPane, 0, 0);
		centerPane.add(textFieldPane, 1, 0);
		
		centerPane.add(buttonPane, 1, 1);
		centerPane.add(lerror, 0, 3);
		centerPane.setHgap(30);
		centerPane.setVgap(20);
		centerPane.setPadding(new Insets(30));
		
		centerPane.setAlignment(Pos.CENTER);
		
		
		
		mainPane=new BorderPane();
//		mainPane.setTop(lback);
		mainPane.setCenter(centerPane);

	}
	public void closeStage() {
	    Stage stage = (Stage) btnCreate.getScene().getWindow(); 
	    stage.close();
	}
	public void setStyle()
	{
		lorderNo.getStyleClass().add("labelCreateOrder");
		lorderNumber.getStyleClass().add("labelCreateOrder");
		lcustomerName.getStyleClass().add("labelCreateOrder");
		lphone.getStyleClass().add("labelCreateOrder");
		
		
		lback.setId("backBtnCreateOrder");
		centerPane.setId("cneterPaneCreateOrder");
		mainPane.setId("mainPaneCreateOrder");
		
		btnCreate.setId("btnCreateCreateOrder");
		btnCancel.setId("btnCancelCreateOrder");
		
		
	}

	public BorderPane getMainPane()
	{
		createNode();
		defineLayout();
		setStyle();
		
		return mainPane;
	}
	

	@Override
	public void start(Stage st) throws Exception {
		stage=st;
		createNode();
		defineLayout();
		setStyle();
		
		Scene sc=new Scene(mainPane,400,170);
		URL url = this.getClass ().getResource ("mystyle.css"); 
		
		st.setScene(sc);
		st.setTitle("Create Order");
		st.show();
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);

	}

}
