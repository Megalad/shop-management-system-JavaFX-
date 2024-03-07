package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.*;

public class CreditSystem{
	
	private Label lcustomerName,ldate,lamount,lnote,lstatus,ledit,ldelete,lcreditTable,lphone;
	private TextField tcustomerName,tamount,tphone;
	private DatePicker ddate;
	private TextArea tanote;
	private ComboBox cstatus;
	
	private TableView creditTv;
	private TableColumn idCol,nameCol,amountCol,noteCol,statusCol,actionCol;
	
	private BorderPane mainPane;
	private GridPane gpPane,tablePane,gp1,gp2,gp3,gp4,gp5;
	private HBox hcustomerName,hdate,hamount,hnote,hstatus,hbutton,hphone;
	private VBox vb;
	private Button btnAdd,btnClear;
	private ArrayList<Credit> cal;
	
	private MenuItem editMI,deleteMI;
	private ContextMenu rightClick;
	
	public void createNodes()
	{
		FileInputStream fis;
		
		lcustomerName=new Label("Customer Name : ");
		ldate=new Label("Date : ");
		lamount=new Label("Amount : ");
		lnote=new Label("Note : ");
		lstatus=new Label("Status : ");
		lphone=new Label("Phone : ");
		
		lcreditTable=new Label("Credit Table");
		
		tcustomerName=new TextField();
		tamount=new TextField();
		tphone=new TextField();
		
		
		ddate=new DatePicker();
		
		tanote=new TextArea();
		tanote.setPrefWidth(300);
		tanote.setPrefHeight(100);
		
		
		ArrayList<String> al=new ArrayList<String>();
		al.add("--Select--");
		al.add("Not Paid");
		
		cstatus=new ComboBox<String>(FXCollections.observableArrayList(al));
		cstatus.getSelectionModel().select(0);
		
		
		btnAdd=new Button("Add");
		btnAdd.setOnAction(e->{
			System.out.print("Clicked!");
			setData();
			setNull();
			refresh();
			
		});
		btnAdd.setCursor(Cursor.HAND);
		btnClear=new Button("Clear");
		btnClear.setCursor(Cursor.HAND);
		
		
		try {
			fis=new FileInputStream("img/icon/pen.png");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			ledit=new Label("",imgView);
			
		}catch (FileNotFoundException e) {
			ledit=new Label("Edit");
		}
		try {
			fis=new FileInputStream("img/icon/delete.png");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			ldelete=new Label("",imgView);
			
		}catch (FileNotFoundException e) {
			ldelete=new Label("Delete");
		}
		
		
		
//		editMI=new MenuItem("Edit");
//		editMI.setOnAction(e->{
//			try {
//				editStage(new EditForInputProduct().getMainPane());
//				Item selectedItem=(Item) creditTv.getSelectionModel().getSelectedItem();
//
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		});
		
		
		deleteMI=new MenuItem("Delete");
		deleteMI.setOnAction(e->{
			Credit selectedItem=(Credit) creditTv.getSelectionModel().getSelectedItem();
			DBHandler.setEndDate(selectedItem.getCreditID());
			refresh();
			
		});
		
		rightClick=new ContextMenu();
		rightClick.getItems().addAll(deleteMI);
	}
	public void defineLayout()
	{
		hcustomerName=new HBox(lcustomerName,tcustomerName);
		hphone=new HBox(lphone,tphone);
		hdate=new HBox(ldate,ddate);
		hamount=new HBox(lamount,tamount);
		hnote=new HBox(lnote,tanote);
		hstatus=new HBox(lstatus,cstatus);
		hbutton=new HBox(btnClear,btnAdd);
		hbutton.setMargin(btnClear,new Insets(20));
		hbutton.setMargin(btnAdd,new Insets(20));
		
		
		gp1=new GridPane();
		gp1.add(lcustomerName, 0, 0);
		gp1.add(lamount, 0, 1);
		gp1.setVgap(30);
		
		gp2=new GridPane();
		gp2.add(tcustomerName, 0, 0);
		gp2.add(tamount, 0, 1);
		gp2.setVgap(20);
		
		gp3=new GridPane();
		gp3.add(lphone, 0, 0);
		gp3.add(lnote,0,1);
		gp3.setVgap(30);
		
		gp4=new GridPane();
		gp4.add(tphone, 0, 0);
		gp4.add(tanote, 0, 1);
		gp4.setVgap(20);
		
		gp5=new GridPane();
		gp5.add(ldate, 0, 0);
		gp5.add(ddate, 1, 0);
		gp5.add(btnAdd, 2,1 );
		gp5.setVgap(30);

		gpPane=new GridPane();
		gpPane.add(gp1, 0, 0);
		gpPane.add(gp2, 1, 0);
		gpPane.add(gp3, 2, 0);
		gpPane.add(gp4, 3, 0);
		gpPane.add(gp5, 4, 0);

		tablePane=new GridPane();
		tablePane.add(lcreditTable, 0, 0);
		tablePane.add(creditTv, 0, 1);
		tablePane.setAlignment(Pos.CENTER);
		
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setVgap(50);
		gpPane.setHgap(30);
		
		
		
		mainPane=new BorderPane();
		mainPane.setTop(gpPane);
		mainPane.setCenter(tablePane);
		
		
		
		
	}
	public void createCreditTable()
	{
		creditTv=new TableView<Credit>();
		
		TableColumn<Item,Integer> idCol=new TableColumn<Item,Integer>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("creditID")); 
		
		TableColumn<Item,String> customerNameCol=new TableColumn<Item,String>("Customer Name");
		customerNameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("customerName"));
		
		TableColumn<Item,String> phoneCol=new TableColumn<Item,String>("Phone");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Item,String>("phone")); 
		
		TableColumn<Item,Integer> amountCol=new TableColumn<Item,Integer>("Amount");
		amountCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("amount"));
		
		TableColumn<Item,String> noteCol=new TableColumn<Item,String>("Note");
		noteCol.setCellValueFactory(new PropertyValueFactory<Item,String>("note")); 
		
		TableColumn<Item,LocalDate> startDateCol=new TableColumn<Item,LocalDate>("Start Date");
		startDateCol.setCellValueFactory(new PropertyValueFactory<Item,LocalDate>("startDate"));
		
		TableColumn<Item,LocalDate> endDateCol=new TableColumn<Item,LocalDate>("End Date");
		endDateCol.setCellValueFactory(new PropertyValueFactory<Item,LocalDate>("endDate"));
		
		
		creditTv.getColumns().add(idCol);
		creditTv.getColumns().add(customerNameCol);
		creditTv.getColumns().add(phoneCol);
		creditTv.getColumns().add(amountCol);
		creditTv.getColumns().add(noteCol);
		creditTv.getColumns().add(startDateCol);
		creditTv.getColumns().add(endDateCol);
		
		creditTv.setPrefWidth(1200);
		creditTv.setPrefHeight(550);
		
		creditTv.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.SECONDARY)
			{
				rightClick.show(creditTv, e.getScreenX(), e.getScreenY());
			}
			
		});
		
//		setTableData();
			
	}
	public void setNull()
	{
		
		tcustomerName.setText("");
		tphone.setText("");
		tanote.setText("");
		tamount.setText("");
	}
	public void refresh()
	{
		creditTv.getItems().clear();
		setTableData();
	}
	public void setData()
	{
		
		
		String name=tcustomerName.getText();
		
			
		

		String phone=tphone.getText();
		
		int amount=0;
		if (tamount.getText() != null && !tamount.getText().isEmpty()) {
	        amount = Integer.parseInt(tamount.getText());
	    }
		String note=tanote.getText();
		

		
		LocalDate date = ddate.getValue();
	    java.sql.Date sqldate = java.sql.Date.valueOf(date);
	    
	    DBHandler.insertCreditTable(name,phone,note,sqldate,amount);
	}
	
	public void setTableData()
	{
		cal=new ArrayList<Credit>();
		cal=DBHandler.getAllCredit();
		creditTv.getItems().addAll(cal);
	}
	public void setStyle()
	{
		creditTv.setId("creditTvCredit");
		gpPane.setId("gppaneCredit");
		tablePane.setId("tablepaneCredit");
		btnAdd.setId("addCredit");
		btnClear.setId("clearCredit");
		mainPane.setId("mainpaneCredit");
		lcreditTable.setId("credittableCredit");
		
		hcustomerName.getStyleClass().add("hboxCredit");
		hdate.getStyleClass().add("hboxCredit");
		hamount.getStyleClass().add("hboxCredit");
		hnote.getStyleClass().add("hboxCredit");
		hstatus.getStyleClass().add("hboxCredit");
		
		btnAdd.getStyleClass().add("buttonCredit");
		btnClear.getStyleClass().add("buttonCredit");
		
		
		
		
	}
	public BorderPane getMainPane()
	{
		createCreditTable();
		createNodes();
		defineLayout();
		
		setStyle();
		
		
		return mainPane;
	}

//	@Override
//	public void start(Stage st) throws Exception {
//		createCreditTable();
//		createNodes();
//		defineLayout();
//		
//		setStyle();
//		
//		Scene sc=new Scene(mainPane,1000,1000);
//		URL url = this.getClass ().getResource ("mystylecredit.css"); 
//		sc.getStylesheets().add(url.toExternalForm());
//		
//		st.setScene(sc);
//		st.setTitle("Credit System");
//		st.show();
//		
//	}
//	
	public static void main(String args[])
	{
		Application.launch(args);
	}

}
