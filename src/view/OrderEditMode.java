package view;

import java.net.URL;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

public class OrderEditMode{
	
	private Label lorderNo,lorderNumber,lcustomerName,lphone,ltotalAmount,ldate,ldateValue,litemName,lquantity,ldiscountAmount;
	private TextField tcustomerName,tphone,ttotalAmount,titemName,tdiscountAmount;
	private TableView<Item> itTv;
	private Button updateBtn,cancelBtn;
	private Spinner<Integer> squantity; 
	
	private ArrayList<Item> al,quantyAl;
	private TableViewSelectionModel<Item> selectionModel;
	
	private GridPane centerPane,topPane,orderNumberPane,namePane,phonePane,totalAmountPane,buttonPane,datePane,editPane;
	private BorderPane mainPane,dataPane;
	
	private int orderDetailID;
	
	public void createNode(Order o)
	{
		lorderNo=new Label("Order No : ");
		lorderNumber=new Label("1");
		lcustomerName=new Label("Name : ");
		lphone=new Label("Phone : ");
		ltotalAmount=new Label("Total Amount : ");
		ldate=new Label("Date : ");
		ldateValue=new Label();
		
		litemName=new Label("Item Name : ");
		lquantity=new Label("Quantity : ");
		ldiscountAmount=new Label("Discount Amount : ");
		
		squantity=new Spinner(1,1000,1);
		squantity.setValueFactory(new IntegerSpinnerValueFactory(0, 100, 0));
		squantity.setEditable(true);
		
		titemName=new TextField();
		titemName.setEditable(false);
		
		tdiscountAmount=new TextField();
		
		
		tcustomerName=new TextField();
		tcustomerName.setEditable(false);
		
		tphone=new TextField();
		tphone.setEditable(false);
		
		ttotalAmount=new TextField();
		ttotalAmount.setEditable(false);
		
		updateBtn=new Button("Update");
		updateBtn.setPrefWidth(100);
		updateBtn.setCursor(Cursor.HAND);
		updateBtn.setOnAction(e->{
			if(titemName.getText()!=null)
			{
				try {
					updateNewInfo();
					refresh(o);
				}catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
				
			}
			else {
				alertMessage("Put Data");
			}
		});
	
	
		cancelBtn=new Button("Cancel");
		cancelBtn.setPrefWidth(100);
		cancelBtn.setCursor(Cursor.HAND);
		cancelBtn.setOnAction(e->{
			mainPane.setTop(null);
			mainPane.setRight(null);
			mainPane.setCenter(new OrderList().getMainPane());
			MainPage.lpath.setText("Main Page  -> Order");
		});
		
	}
	public void defineLayout()
	{
		orderNumberPane=new GridPane();
		orderNumberPane.add(lorderNo, 0, 0);
		orderNumberPane.add(lorderNumber, 1, 0);
		orderNumberPane.setPadding(new Insets(5,0,0,0));
		
		
		namePane=new GridPane();
		namePane.add(lcustomerName, 0, 0);
		namePane.add(tcustomerName, 1, 0);
		namePane.setHgap(20);
		
		phonePane=new GridPane();
		phonePane.add(lphone, 0, 0);
		phonePane.add(tphone, 1, 0);
		phonePane.setHgap(20);

		
		totalAmountPane=new GridPane();
		totalAmountPane.add(ltotalAmount, 0, 0);
		totalAmountPane.add(ttotalAmount, 1, 0);
		totalAmountPane.setHgap(20);

		buttonPane=new GridPane();
		buttonPane.add(cancelBtn, 0, 0);
		buttonPane.add(updateBtn, 1, 0);
		buttonPane.setHgap(20);
		buttonPane.setAlignment(Pos.CENTER_RIGHT);
		
		datePane=new GridPane();
		datePane.add(ldate, 0, 0);
		datePane.add(ldateValue, 1, 0);
		datePane.setAlignment(Pos.TOP_RIGHT);
		datePane.setPadding(new Insets(10,50,0,0));
		
		topPane=new GridPane();
		topPane.add(orderNumberPane, 0, 0);
		topPane.add(namePane, 1, 0);
		topPane.add(phonePane, 2, 0);
		topPane.add(totalAmountPane, 3, 0);
		topPane.add(buttonPane, 3, 1);
		topPane.setVgap(20);
		
		topPane.setAlignment(Pos.CENTER);
		topPane.setPadding(new Insets(20,0,20,0));
		topPane.setHgap(40);
		
		
		dataPane=new BorderPane();
		dataPane.setTop(datePane);
		dataPane.setCenter(topPane);
		

		centerPane=new GridPane();	
		centerPane.add(itTv, 0, 0);
//		centerPane.add(buttonPane, 0,1);
		centerPane.setAlignment(Pos.TOP_CENTER);
		centerPane.setPadding(new Insets(20,0,0,0));
		
		editPane=new GridPane();
		editPane.add(litemName, 0, 0);
		editPane.add(titemName, 1, 0);
		editPane.add(lquantity, 0, 1);
		editPane.add(squantity, 1, 1);
		editPane.add(ldiscountAmount, 0, 2);
		editPane.add(tdiscountAmount, 1, 2);
		editPane.add(buttonPane, 1, 3);
		editPane.setPadding(new Insets(50,40,0,40));
		editPane.setVgap(50);
		
		mainPane=new BorderPane();
		mainPane.setTop(dataPane);
		mainPane.setCenter(centerPane);
		mainPane.setRight(editPane);
		
	}
	public void createTable(Order o)
	{
		itTv=new TableView<Item>();
		itTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 		
		
		TableColumn<Item,String> vnameCol=new TableColumn<Item,String>("Name");
		vnameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("itemName"));
		
		TableColumn<Item,Integer> vquantityCol=new TableColumn<Item,Integer>("Quantity");
		vquantityCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("quantity"));
		
		vquantityCol.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList()));
		
		vquantityCol.setOnEditCommit(
				t -> 
				t.getTableView().getItems() .get(t.getTablePosition(). getRow()) .setQuantity(t.getNewValue())
				
				
				);

		
		
		TableColumn<Item,Integer> vdiscountCol=new TableColumn<Item,Integer>("Discount");
		vdiscountCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("discountAmount"));
		
		TableColumn<Item,Integer> vsellPriceCol=new TableColumn<Item,Integer>("Sell Price");
		vsellPriceCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("sellPrice"));
		
		TableColumn<Item,Integer> vtotalPriceCol=new TableColumn<Item,Integer>("Total");
		vtotalPriceCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("totalPrice"));
		
		
		
		itTv.getColumns().add(vnameCol);
		itTv.getColumns().add(vquantityCol);
		itTv.getColumns().add(vdiscountCol);
		itTv.getColumns().add(vsellPriceCol);
		itTv.getColumns().add(vtotalPriceCol);
		
		
		
		itTv.setPrefWidth(750);
		itTv.setPrefHeight(550);
		
		itTv.setEditable(true);
		selectionModel=itTv.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		itTv.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				if(e.getClickCount()==2)
				{
					setUpdateInfo();
					
				}
//				rightClick.show(itemListTv, e.getScreenX(), e.getScreenY());
			}
			
		});
		setDataForTable(o.getOrderID());
	}
	public void setUpdateInfo()
	{
		Item i=selectionModel.getSelectedItem(); 
		titemName.setText(i.getItemName()); 
		squantity.setValueFactory(new IntegerSpinnerValueFactory(0,100,i.getQuantity()));
		tdiscountAmount.setText(i.getDiscountAmount());
		
		orderDetailID=DBHandler3.getOrderDetailID(squantity.getValue(),Integer.parseInt(tdiscountAmount.getText()), Integer.parseInt(lorderNumber.getText()), DBHandler3.getItemID(titemName.getText()));
	}
	public void updateNewInfo()
	{
//		ArrayList<model.OrderDetail> odAl=new ArrayList<model.OrderDetail>();
		
		
		
		System.out.println("Order ID"+lorderNumber.getText());
		System.out.println("Order Detail ID "+orderDetailID);
		
		
		int oldQuantity=DBHandler3.getQuantityToUpdate(orderDetailID);
		System.out.println("Old"+oldQuantity);
		System.out.println("New"+squantity.getValue());
		int oldDiscountAmount=DBHandler3.getDiscountAmoutToUpdate(orderDetailID);
		System.out.println("Old D"+oldDiscountAmount);
		System.out.println("New D"+tdiscountAmount.getText());
		
		
		if(oldQuantity!=squantity.getValue() && oldDiscountAmount!=Integer.parseInt(tdiscountAmount.getText()))
		{
			try {
				
				DBHandler3.updateQuantityFromOrderdetail(squantity.getValue(), orderDetailID);
				DBHandler3.updateDiscountAmountFromOrderdetail(Integer.parseInt(tdiscountAmount.getText()),orderDetailID);
				
				alertMessage("Quantity and DiscountAmount updated sucessfully!");
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
		}
		else if(oldQuantity==squantity.getValue() && oldDiscountAmount!=Integer.parseInt(tdiscountAmount.getText()))
		{
			try {
				
				
				DBHandler3.updateDiscountAmountFromOrderdetail(Integer.parseInt(tdiscountAmount.getText()),orderDetailID);
				
				alertMessage("DiscountAmount updated sucessfully!");
			}catch(Exception e)
			{
				e.printStackTrace();
			}	
		}
		else if(oldQuantity!=squantity.getValue() && oldDiscountAmount==Integer.parseInt(tdiscountAmount.getText()))
		{
			try {
				
				DBHandler3.updateQuantityFromOrderdetail(squantity.getValue(), orderDetailID);
				
				
				alertMessage("Quantity updated sucessfully!");
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else {
			alertMessage("Input New Data to update!!");
		}
		
		
	}
	public void alertMessage(String message)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText(message);
        alert.showAndWait();
	}
	public void refresh(Order o)
	{
		itTv.getItems().clear();
		setDataForTable(o.getOrderID());
		
		ttotalAmount.setEditable(true);
		ttotalAmount.setText(""+DBHandler3.getTotalAmountOrderDetail(Integer.parseInt(lorderNumber.getText())));
		ttotalAmount.setEditable(false);
		
	}
	public void setDataForTable(int orderID)
	{
		al=DBHandler3.getItemList(orderID);
		itTv.getItems().addAll(al);
		
		
	}
	public void fillFields(Order o)
	{
		lorderNumber.setText(Integer.toString(o.getOrderID()));
		tcustomerName.setText(o.getCustomerName());
		tphone.setText(o.getPhoneNumber());
		ttotalAmount.setText(o.getAmount());
		ldateValue.setText(o.getOrderDate().toString());
//		updateBtn.setOnAction(e->{
//			if(titemName.getText()!=null)
//			{
//				try {
//					updateNewInfo();
//					refresh(o);
////					ttotalAmount.setText(""+DBHandler3.getTotalAmountOrderDetail(Integer.parseInt(lorderNumber.getText())));
//					ttotalAmount.setText(""+o.getAmount());
//				}catch(Exception e1)
//				{
//					e1.printStackTrace();
//				}
//				
//				
//			}
//			else {
//				alertMessage("Put Data");
//			}
//		});
		
	}
	public void setStyle()
	{
		dataPane.setId("datapaneOrderEditMode");
		centerPane.setId("centerPaneOrderEditMode");
		updateBtn.setId("doneBtnOrderEditMode");
		cancelBtn.setId("cancelBtnOrderEditMode");
		itTv.setId("itemListTableOrderEditMode");
		editPane.setId("editPaneOrderEditMode");
	}
	public BorderPane getMainPane(Order o)
	{
		
		createNode(o);
		createTable(o);
		
		defineLayout();
		setStyle();
		fillFields(o);
		
		
		return mainPane;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}
