package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

public class OrderList extends Application {
	
	private Stage stage;
	private BorderPane mainPane,mainPaneNew;
	private TableView orderTv;
	private GridPane topPane,centerPane,searchPane,btnPane;
	private Label lsearch;
	private TextField tsearch;
	private DatePicker dsearch;

	private Button btnCreate;
	private FilteredList<Order> fl;
	private ArrayList<Order> al;
	private TableViewSelectionModel<Order> selectionModel;
	private TableColumn<Order,Integer> orderIDCol,amount;
	private MenuItem editMI,deleteMI,refreshMI;
	private ContextMenu rightClick;
	
	private Stage createOrderStage;
	
	public OrderList() {
	    mainPane = new BorderPane(); // Initialize mainPane
	    // Other initialization code goes here
	}
	public void createNode()
	{
		
		
		tsearch=new TextField();
		tsearch.setPromptText("Search.......");
		tsearch.setOnKeyTyped(e->{
//			if(e.getCode()==KeyCode.ENTER)
//			{
				filtering();
//			}
		});
		
		dsearch=new DatePicker();
		dsearch.setPromptText("mm/dd/yy");
		dsearch.setEditable(true);
		dsearch.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER)
			{
				filteringDate();
			}
		});
		btnCreate=new Button("+Create New Order");
		btnCreate.setCursor(Cursor.HAND);
		btnCreate.setOnAction(e->{
			try {
//				mainPane.setCenter(new CreateOrder().getMainPane());
//				mainPane.setTop(null);
				new CreateOrder(this).start(new Stage());
				
				MainPage.lpath.setText("Main Page  -> Order  ->  Create New Order ");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		editMI=new MenuItem("Edit");
		deleteMI=new MenuItem("Delete");
		deleteMI.setOnAction(e->{
			try {
				if(Checker.showConfirmation("Are you sure you want to delete the selected item?"))
				{
					Order selectedItem=(Order) orderTv.getSelectionModel().getSelectedItem();
					DBHandler3.deleteOrdersFromOrderdetail(selectedItem.getOrderID());
					refresh();
//					Checker.alertMessage("Delete Orders", "Order deleted successfully!");
				}
				
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
			
			
		});
		refreshMI=new MenuItem("Refresh");
		
		
		rightClick=new ContextMenu();
		rightClick.getItems().addAll(refreshMI,editMI,deleteMI);
		
		
	}
	public void defineLayout()
	{
		btnPane=new GridPane();
		btnPane.add(btnCreate, 0, 0);
		
		
		
		searchPane=new GridPane();
		searchPane.add(tsearch, 0, 0);
		searchPane.add(dsearch, 1, 0);
		searchPane.setHgap(30);
		
		
		topPane=new GridPane();
		topPane.add(searchPane, 0, 0);
		topPane.add(btnPane, 1, 0);
		topPane.setAlignment(Pos.CENTER);
		topPane.setPadding(new Insets(20));
		topPane.setHgap(550);
		
		
		centerPane=new GridPane();
		centerPane.add(orderTv, 0, 0);
//		centerPane.setPadding(new Insets(10));
		centerPane.setAlignment(Pos.CENTER);
		
	
		mainPane=new BorderPane();
		mainPane.setTop(topPane);
		mainPane.setCenter(centerPane);
		
	}
	public void createTable()
	{
		orderTv=new TableView<Order>();
		orderTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
		
		orderIDCol=new TableColumn<Order,Integer>("Order ID");
		orderIDCol.setCellValueFactory(new PropertyValueFactory<Order,Integer>("orderID"));
		
		TableColumn<Order,String> customerName=new TableColumn<Order,String>("Customer Name");
		customerName.setCellValueFactory(new PropertyValueFactory<Order,String>("customerName"));
		
		TableColumn<Order,String> customerPh=new TableColumn<Order,String>("Phone Number");
		customerPh.setCellValueFactory(new PropertyValueFactory<Order,String>("phoneNumber"));
		
		TableColumn<Order,LocalDate> orderDate=new TableColumn<Order,LocalDate>("Order Date");
		orderDate.setCellValueFactory(new PropertyValueFactory<Order,LocalDate>("orderDate"));
		
		amount=new TableColumn<Order,Integer>("Amount");
		amount.setCellValueFactory(new PropertyValueFactory<Order,Integer>("amount"));
		
		TableColumn<Order,String> remark=new TableColumn<Order,String>("Remark");
		remark.setCellValueFactory(new PropertyValueFactory<Order,String>("remark"));
		
		orderTv.getColumns().add(orderIDCol);
		orderTv.getColumns().add(customerName);
		orderTv.getColumns().add(customerPh);
		orderTv.getColumns().add(orderDate);
		orderTv.getColumns().add(amount);
		orderTv.getColumns().add(remark);
		
		selectionModel=orderTv.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		orderTv.setPrefWidth(1300);
		orderTv.setPrefHeight(900);
		
		
		orderTv.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.SECONDARY)
			{
				rightClick.show(orderTv, e.getScreenX(), e.getScreenY());
			}
			else if(e.getButton()==MouseButton.PRIMARY) 
			{
				if(e.getClickCount()==2)
				{
					mainPane.setTop(null);
					mainPane.setCenter(new OrderEditMode().getMainPane(setEditInfo()));
					
					MainPage.lpath.setText("Main Page  -> Order  ->  Edit Mode ");
				}
			}
		});
		
		
		setData();
		
	}
	public void setData()
	{
		al=DBHandler3.getAllOrder();
		fl=new FilteredList<model.Order>(FXCollections.observableArrayList(al));
		orderTv.getItems().addAll(al);
		
	}
	public void filtering()
	{  
		fl=new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<Order>(){

			@Override
			public boolean test(Order o) {
				String value=tsearch.getText();
				LocalDate date=dsearch.getValue();
				if(value.length()==0)
				{
					System.out.print("Null");
					return true;
				}
				try {
					String name=value;
					
					return o.getCustomerName().toLowerCase().contains(name.toLowerCase()) || o.getPhoneNumber().toLowerCase().contains(name.toLowerCase());
					
				}
				catch(Exception e)
				{
					System.out.print("Catch");
					return value.contains(o.getCustomerName());
				}
				
				
				
			}	
		});
		orderTv.getItems().clear();
		orderTv.getItems().addAll(fl);
	}
	public void filteringDate()
	{  
		fl=new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<Order>(){

			@Override
			public boolean test(Order o) {
				
				LocalDate value=dsearch.getValue();
				
				if(value==null)
				{
					return true;
				}
				try {
					LocalDate date=value;
					
					return date.equals(o.getOrderDate().toLocalDate());
					
				}
				catch(Exception e)
				{
					return value.equals(o.getOrderDate().toLocalDate());
				}
				
				
				
			}	
		});
		orderTv.getItems().clear();
		orderTv.getItems().addAll(fl);
	}
	public void newStage(BorderPane bp) throws Exception {
		

		mainPaneNew=new BorderPane();
		mainPaneNew.setCenter(bp);
		
		Scene sc=new Scene(mainPaneNew);
		URL url = this.getClass ().getResource ("mystyle.css"); 
		
		
		createOrderStage=new Stage();
		
		createOrderStage.setWidth(400);
		createOrderStage.setHeight(250);
		createOrderStage.setResizable(false);
		createOrderStage.setScene(sc);
		createOrderStage.initModality(Modality.APPLICATION_MODAL);
//		addStage.initStyle(StageStyle.UNDECORATED);
		createOrderStage.showAndWait();
		 

	}
	public void refresh()
	{
		orderTv.getItems().clear();
		setData();
	}
	public void setStyle()
	{
		btnCreate.setId("createBtnOrderList");
		centerPane.setId("centerPaneOrderList");
		
		tsearch.getStyleClass().add("searchOrderList");
		dsearch.getStyleClass().add("searchOrderList");
		
		
		orderTv.setId("orderTableOrderList");
		
		orderIDCol.setId("idCol");
		amount.setId("amountCol");
		
		topPane.setId("topPaneOrderList");
	}
	public Order setEditInfo()
	{
		
		Order o=selectionModel.getSelectedItem();
//		System.out.print(it.getItemName());
	
		return new Order(o.getOrderID(),o.getCustomerName(),o.getPhoneNumber(),o.getOrderDate(),o.getAmount(),o.getRemark());
		
	}
	
	public BorderPane getMainPane()
	{
		createNode();
		createTable();
		defineLayout();
		setStyle();
		
		return mainPane;
	}
	public void setMainPane(BorderPane pane)
	{
		mainPane.setCenter(pane);
		mainPane.setTop(null);
	}
	@Override
	public void start(Stage st) throws Exception {
		
		createNode();
		createTable();
		defineLayout();
		
		
		
		
		Scene sc=new Scene(mainPane,300,300);
		st.setScene(sc);
		st.setTitle("List of orders");
		st.show();
		
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}

	

}
