package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

public class TotalItemEditMode extends Application {
	
	private Label litemID,litemIDValue,litemNameTop,litemNameEdit,lcategory,lsubcategory,lquantity,lpurchasePrice,lsellPrice,lpriceDetail,litemNameSetting,lcategorySetting,lsubcategorySetting;
	private TextField titemNameTop,titemNameEdit,tpurchasePrice,tsellPrice,tcategoryName,tsubcategoryName;
	private Spinner<Integer> squantity;
	private ComboBox<Category> ccategory;
	private ComboBox<Subcategory> csubcategory;
	private ArrayList<Item> al;
	private Button doneBtn,updateBtn;
	
	private TableViewSelectionModel<Item> selectionModel;
	private Item itemSelected;
	
	private TableView<Item> itemListTv;
	private GridPane itemNamePane,itemIDPane,categoryPane,subcategoryPane,quantityPane,purchasePricePane,sellPricePane,topPane,centerPane,editPane,labelPane,textFieldPane,buttonPane;
	private BorderPane mainPane,mainPaneEdit;
	private Stage editStage;
	
	private int priceDetailID;
	
	public void createNode(Item i)
	{
		FileInputStream fis;
		
		lpriceDetail=new Label("Price Detail");
		
		litemID=new Label("Item ID : ");
		litemID.setPadding(new Insets(6,0,0,0));
		litemIDValue=new Label();
		litemIDValue.setPadding(new Insets(6,0,0,0));
		litemNameTop=new Label("Item Name : ");
		litemNameEdit=new Label("Item Name : ");
		lcategory=new Label("Category : ");
		lsubcategory=new Label("Subcategory : ");
		
		
		try {
			fis=new FileInputStream("img/icon/setting.png");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			litemNameSetting=new Label("",imgView);
			
			
			litemNameSetting.setCursor(Cursor.HAND);
		}catch (FileNotFoundException e) {
			litemNameSetting=new Label("Plus");
		}
		litemNameSetting.setOnMouseClicked(e->{
			try {
				editStage(new UpdateNewName().getMainPane(i));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		try {
			fis=new FileInputStream("img/icon/setting.png");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			lsubcategorySetting=new Label("",imgView);
			
			
			lsubcategorySetting.setCursor(Cursor.HAND);
		}catch (FileNotFoundException e) {
			lsubcategorySetting=new Label("Plus");
		}
		lsubcategorySetting.setOnMouseClicked(e->{
			try {
				editStage(new UpdateNewSubcategory().getMainPane(i));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		
		lquantity=new Label("Quantity : ");
		lpurchasePrice=new Label("Purchase Price : ");
		lsellPrice=new Label("SellPrice : ");
		
		titemNameTop=new TextField();
		titemNameTop.setEditable(false);
		
		titemNameEdit=new TextField();
		titemNameEdit.setEditable(false);
		
		tpurchasePrice=new TextField();
		tsellPrice=new TextField();
		
		tcategoryName=new TextField();
		tcategoryName.setEditable(false);
		
		tsubcategoryName=new TextField();
		tsubcategoryName.setEditable(false);
		
		squantity=new Spinner(1,1000,1);
		squantity.setValueFactory(new IntegerSpinnerValueFactory(0, 100, 0));
		squantity.setEditable(true);
		
		ccategory=new ComboBox();
		ccategory.setEditable(false);
		
		csubcategory=new ComboBox();
		csubcategory.setEditable(false);
		
		doneBtn=new Button("Done");
		doneBtn.setOnAction(e->{
			mainPane.setTop(null);
			mainPane.setRight(null);
			mainPane.setCenter(new TotalItem().getMainPane());
			MainPage.lpath.setText("Main Page  -> Total Item");
			
		});
		
		updateBtn=new Button("Update");
		updateBtn.setOnAction(e->{
			if(titemNameEdit.getText()!=null)
			{
				try {
					updateNewInfo();
					refresh(i);
				}catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
			}
			else {
				alertMessage("Input New Data");
			}
			
		});
		
	}
	public void defineLayout()
	{
		
		
		
		itemIDPane=new GridPane();
		itemIDPane.add(litemID, 0, 0);
		itemIDPane.add(litemIDValue, 1, 0);
		
		itemNamePane=new GridPane();
		itemNamePane.add(litemNameTop, 0, 0);
		itemNamePane.add(titemNameTop, 1, 0);
		itemNamePane.add(litemNameSetting, 2, 0);
		itemNamePane.setHgap(5);
		
		categoryPane=new GridPane();
		categoryPane.add(lcategory, 0, 0);
		categoryPane.add(tcategoryName, 1, 0);
//		categoryPane.add(lcategorySetting, 2, 0);
		categoryPane.setHgap(5);
		
		subcategoryPane=new GridPane();
		subcategoryPane.add(lsubcategory, 0, 0);
		subcategoryPane.add(tsubcategoryName, 1, 0);
		subcategoryPane.add(lsubcategorySetting, 2, 0);
		subcategoryPane.setHgap(5);
		
		quantityPane=new GridPane();
		quantityPane.add(lquantity, 0, 0);
		quantityPane.add(squantity, 1, 0);

		purchasePricePane=new GridPane();
		purchasePricePane.add(lpurchasePrice, 0, 0);
		purchasePricePane.add(tpurchasePrice, 1, 0);
		
		sellPricePane=new GridPane();
		sellPricePane.add(lsellPrice, 0, 0);
		sellPricePane.add(tsellPrice, 1, 0);
		
		buttonPane=new GridPane();
		buttonPane.add(doneBtn, 0, 0);
		buttonPane.add(updateBtn, 1, 0);
		buttonPane.setHgap(20);
		buttonPane.setAlignment(Pos.CENTER);
		
		topPane=new GridPane();
		topPane.add(itemIDPane, 0, 0);
		topPane.add(itemNamePane, 1, 0);
		topPane.add(categoryPane, 2, 0);
		topPane.add(subcategoryPane, 3, 0);
		topPane.setPadding(new Insets(20,10,30,10));
		topPane.setAlignment(Pos.CENTER);
		topPane.setHgap(40);
		
		centerPane=new GridPane();
		centerPane.add(lpriceDetail, 0, 0);
		centerPane.add(itemListTv, 0, 1);
		centerPane.setVgap(20);
		centerPane.setAlignment(Pos.TOP_LEFT);
		centerPane.setPadding(new Insets(30,0,0,30));
		
		labelPane=new GridPane();
		labelPane.add(litemNameEdit, 0, 0);
		labelPane.add(lquantity, 0, 1);
		labelPane.add(lpurchasePrice, 0, 2);
		labelPane.add(lsellPrice, 0, 3);
		labelPane.setVgap(35);
		
		textFieldPane=new GridPane();
		textFieldPane.add(titemNameEdit, 0, 0);
		textFieldPane.add(squantity, 0, 1);
		textFieldPane.add(tpurchasePrice, 0, 2);
		textFieldPane.add(tsellPrice, 0, 3);
		textFieldPane.setVgap(20);
		
		
		editPane=new GridPane();
		editPane.add(labelPane, 0, 0);
		editPane.add(textFieldPane, 1, 0);
		editPane.add(buttonPane, 1, 1);
		editPane.setVgap(20);
		editPane.setPadding(new Insets(80,60,0,0));
		
		mainPane=new BorderPane();
		mainPane.setTop(topPane);
		mainPane.setCenter(centerPane);
		mainPane.setRight(editPane);
		
		
	}
	public void createTable()
	{
		itemListTv=new TableView<Item>();
		itemListTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
//		itemListTv.setEditable(true);
		
		TableColumn<Item,Integer> itemIDCol=new TableColumn<Item,Integer>("Item ID");
		itemIDCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("itemID"));
		
		TableColumn<Item,String> itemNameCol=new TableColumn<Item,String>("Name");
		itemNameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("itemName"));
		
		TableColumn<Item,Integer> quantityCol=new TableColumn<Item,Integer>("Quantity");
		quantityCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("quantity"));
		
		TableColumn<Item,Integer> purchasePriceCol=new TableColumn<Item,Integer>("Purchase Price");
		purchasePriceCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("pprice"));
		
		TableColumn<Item,Integer> sellPriceCol=new TableColumn<Item,Integer>("Sell Price");
		sellPriceCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("sprice"));
		
		TableColumn<Item,LocalDate> startDateCol=new TableColumn<Item,LocalDate>("Start Date");
		startDateCol.setCellValueFactory(new PropertyValueFactory<Item,LocalDate>("startDate"));
		
		TableColumn<Item,LocalDate> endDateCol=new TableColumn<Item,LocalDate>("End Date");
		endDateCol.setCellValueFactory(new PropertyValueFactory<Item,LocalDate>("endDate"));
		
		
		itemListTv.getColumns().add(itemIDCol);
		itemListTv.getColumns().add(itemNameCol);
		itemListTv.getColumns().add(quantityCol);
		itemListTv.getColumns().add(purchasePriceCol);
		itemListTv.getColumns().add(sellPriceCol);
		itemListTv.getColumns().add(startDateCol);
		itemListTv.getColumns().add(endDateCol);
		
		itemListTv.setPrefWidth(750);
		itemListTv.setPrefHeight(550);
		
		selectionModel=itemListTv.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		itemListTv.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY) 
			{
				if(e.getClickCount()==2)
				{
					setUpdateInfo();
				}
			}
		});

		
	}
	public void fillFields(Item it)
	{
		litemIDValue.setText(""+it.getItemID());
		titemNameTop.setText(""+it.getItemName());
		tcategoryName.setText(it.getCategoryName());
		tsubcategoryName.setText(it.getSubcategoryName());
		
		setTableData(it);
	}
	public void setUpdateInfo()
	{
		 itemSelected=selectionModel.getSelectedItem();
		
		titemNameEdit.setText(itemSelected.getItemName());
		squantity.setValueFactory(new IntegerSpinnerValueFactory(0,100,itemSelected.getQuantity()));
		tpurchasePrice.setText(""+itemSelected.getPprice());
		tsellPrice.setText(""+itemSelected.getSprice());
		priceDetailID=DBHandler3.getPriceDetail(Integer.parseInt(tpurchasePrice.getText()), Integer.parseInt(tsellPrice.getText()), titemNameEdit.getText());

	}
	public void updateNewInfo()
	{
		System.out.print("Price Detail ID : "+priceDetailID);
		
		System.out.println();
		
		System.out.println("PD ID : "+priceDetailID);
		
		int oldQuantity=DBHandler3.getQuantityFromPricedetailToUpdate(priceDetailID);
		System.out.println("Old Quantity : "+oldQuantity);
		System.out.print("New Quantity : "+squantity.getValue());
		int oldPurchasePriceID=DBHandler3.getPurchasePriceIDFromPricedetailToUpdate(priceDetailID);
		System.out.println("oldPurchasePriceID : "+oldPurchasePriceID);
		System.out.print("NewPPriceID : ");
		int oldSellPriceID=DBHandler3.getSellPriceIDFromPricedetailToUpdate(priceDetailID);
		System.out.println("oldSellPriceID : "+oldSellPriceID);
		int purchasePriceIDEdit=DBHandler3.getPurchaseIDNew(Integer.parseInt(tpurchasePrice.getText()));
		System.out.println("purchasePriceIDEdit : "+purchasePriceIDEdit);
		int sellPriceIDEdit=DBHandler3.getSellIDNew(Integer.parseInt(tsellPrice.getText()));
		System.out.println("SellPriceIDEdit : "+sellPriceIDEdit);
		
		
		if(squantity.getValue()!=oldQuantity && purchasePriceIDEdit==oldPurchasePriceID && sellPriceIDEdit==oldSellPriceID)
		{
			try {
				
				DBHandler3.updateQuantityFromPriceDetail(squantity.getValue(), priceDetailID);
				alertMessage("Update Quantity!");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(squantity.getValue()==oldQuantity && purchasePriceIDEdit!=oldPurchasePriceID && sellPriceIDEdit==oldSellPriceID)
		{
			try {
				
				DBHandler3.updatePurchasePriceFromPriceDetail(Integer.parseInt(tpurchasePrice.getText()), priceDetailID);
				alertMessage("Update PurchasePrice!");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(squantity.getValue()==oldQuantity && purchasePriceIDEdit==oldPurchasePriceID && sellPriceIDEdit!=oldSellPriceID)
		{
			try {
				DBHandler3.updateSellPriceFromPriceDetail(Integer.parseInt(tsellPrice.getText()), priceDetailID);
				alertMessage("Update SellPrice!");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(squantity.getValue()!=oldQuantity && purchasePriceIDEdit!=oldPurchasePriceID && sellPriceIDEdit==oldSellPriceID)
		{
			try {
				
				DBHandler3.updateQuantityFromPriceDetail(squantity.getValue(), priceDetailID);
				DBHandler3.updatePurchasePriceFromPriceDetail(Integer.parseInt(tpurchasePrice.getText()), priceDetailID);
				alertMessage("Update Quantity And PurchasePrice!");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(squantity.getValue()!=oldQuantity && purchasePriceIDEdit==oldPurchasePriceID && sellPriceIDEdit!=oldSellPriceID)
		{
			try {
				
				DBHandler3.updateQuantityFromPriceDetail(squantity.getValue(), priceDetailID);
				DBHandler3.updateSellPriceFromPriceDetail(Integer.parseInt(tsellPrice.getText()), priceDetailID);
				alertMessage("Update Quantity And SellPrice!");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(squantity.getValue()==oldQuantity && purchasePriceIDEdit!=oldPurchasePriceID && sellPriceIDEdit!=oldSellPriceID)
		{
			try {
				DBHandler3.updateSellPriceFromPriceDetail(Integer.parseInt(tsellPrice.getText()), priceDetailID);
				DBHandler3.updatePurchasePriceFromPriceDetail(Integer.parseInt(tpurchasePrice.getText()), priceDetailID);
				alertMessage("Update SellPrice And PurchasePrice!");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(squantity.getValue()!=oldQuantity && purchasePriceIDEdit!=oldPurchasePriceID  && sellPriceIDEdit!=oldSellPriceID)
		{
			try {
				DBHandler3.updateQuantityFromPriceDetail(squantity.getValue(), priceDetailID);
				DBHandler3.updatePurchasePriceFromPriceDetail(Integer.parseInt(tpurchasePrice.getText()), priceDetailID);
				DBHandler3.updateSellPriceFromPriceDetail(Integer.parseInt(tsellPrice.getText()), priceDetailID);
				alertMessage("Update Quantity,PurchasePrice And SellPrice!");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else {
			System.out.print("Check Againg");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		if(squantity.getValue()!=oldQuantity)
//		{
//			try {
//				DBHandler3.updateQuantityFromPriceDetail(squantity.getValue(), priceDetailID);
//			}catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//		if(purchasePriceIDEdit!=oldPurchasePriceID)
//		{
//			try {
//				DBHandler3.updatePurchasePriceFromPriceDetail(Integer.parseInt(tpurchasePrice.getText()), priceDetailID);
//			}catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//		if(sellPriceIDEdit!=oldSellPriceID)
//		{
//			try {
//				DBHandler3.updateSellPriceFromPriceDetail(Integer.parseInt(tsellPrice.getText()), priceDetailID);
//			}catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
		
	
	
	}
	public void refresh(Item i)
	{
		itemListTv.getItems().clear();
		setTableData(i);
		
		
	}
	public void alertMessage(String message)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText(message);
        alert.showAndWait();
	}
	
	public void setTableData(Item i)
	{
		al=DBHandler3.getPriceDetail(i.getItemID());
		itemListTv.getItems().addAll(al);
	}
	public void editStage(BorderPane bp) throws Exception{
		
		mainPaneEdit=new BorderPane();
		mainPaneEdit.setCenter(bp);
		
		Scene sc=new Scene(mainPaneEdit);
		
		editStage=new Stage();
		editStage.setWidth(800);
		editStage.setHeight(500);
		editStage.setScene(sc);
		editStage.initModality(Modality.APPLICATION_MODAL);
		editStage.showAndWait();
		
	}
	public BorderPane getMainPane(Item i)
	{
		createNode(i);
		createTable();
		fillFields(i);
		
		defineLayout();
		setStyle();
		
		
		
		return mainPane;
	}
	
	public void setStyle()
	{
		topPane.setId("topPaneTotalItemEditMode");
		editPane.setId("editPaneTotalItemEditMode");
		lpriceDetail.setId("pricedetailTotalItemEditMode");
		itemListTv.setId("itemListTvTotalItemEditMode");
	}
	
	@Override
	public void start(Stage st) throws Exception {
		// TODO Auto-generated method stub
//		createNode(i);
//		createTable();
//		
//		
//		defineLayout();
		
		
		
		
		Scene sc=new Scene(mainPane,1000,1000);
		st.setScene(sc);
		st.setTitle("List of orders");
		st.show();
	}
	public static void main(String args[])
	{
		Application.launch(args);
	}

}
