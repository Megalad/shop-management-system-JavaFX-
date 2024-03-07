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
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.scene.control.TableView.TableViewSelectionModel;
import model.*;

public class InputProduct{
	
	
	
	private Label lname,loriginalPrice,lsellPrice,lquantity,lcategory,lsubCategory,lcategoryPlus,lsubcategoryPlus,lrecent;
	private TextField tname,toriginalPrice,tsellPrice,tcolor,tsize;

	private Button btnAdd,btnClear;
	private DatePicker sellPriceStartDate;
	private ComboBox ccategory,csubCategory;	
	private FileChooser fc;
	private String itemName;
	private TableView tv;
	private HBox buttonHBox,categoryHBox,subcategoryHBox,menuHBox;
	private VBox labelVBox1,labelVBox2,labelVBox3,labelVBox4,textFieldVBox1,textFieldVBox2,textFieldVBox3,textFieldVBox4;
	private GridPane inputPane;
	private BorderPane addNewItemPane,mainPane;
	private Spinner<Integer> squantity;
	
	private InputSubcategory inputSubcategory;
	private ArrayList<Item> al;
	private ArrayList<RecentAdded> recentArrayList;
	private ArrayList<Subcategory> subcategories;
	private ArrayList<Category> categories;
	
	private Label lcategroyAdd;
	private TextField tcategoryAdd;
	private Button categoryAddBtn;
	
	private MenuItem editMI,deleteMI,refreshMI;
	
	private Label lcategoryAdd,lsubcategoryAdd;
	
	private ComboBox ccategoryAdd;
	private TextField tsubcategoryAdd;
	private HBox categoryHBoxAdd,subcategoryHBoxAdd;
	private Button addBtnAdd;
	private GridPane centerPaneAdd,gp1,gp2,gp3,gp4,gp5,gp6,gp7,gp8,gp9,tablePane;
	private BorderPane mainPaneAdd,mainPaneEdit;
	
	
	private Stage addStage,editStage;
	private String categoryName;
	private Category selectedCategory;
	private ContextMenu rightClick;
	private MenuBar inputMenuBar;
	private Menu inputMenu;
	private MenuItem addMenuItem,updateMenuItem;
	private Label laddNewItem,lupdateItem,lpath;
	
	// For Error Message
	private Label lnameError,lpurchasePriceError,lsellPriceError,lcategoryError,lsubcategoryError,lquantityError;
	private GridPane tnamePane,tpurchasePane,tsellPane,tcategoryPane,tsubcategoryPane,tquantityPane;
	
	
	private TableViewSelectionModel<Item> selectionModel;

	
	public void createNode()
	{
		
		FileInputStream fis;
		
		lname=new Label("Name : ");
		lcategory=new Label("Category : ");
		lsubCategory=new Label("Sub Cateogry : ");
		loriginalPrice=new Label("Purchase Price : ");
		lsellPrice=new Label("Sell Price : ");
		lquantity=new Label("Quantity : ");
		
//		For Error Message
		lnameError=new Label();
		lcategoryError=new Label();
		lsubcategoryError=new Label();
		lpurchasePriceError=new Label();
		lsellPriceError=new Label();
		lquantityError=new Label();

		csubCategory=new ComboBox();
		
		categories=DBHandler3.getAllCategoryName();
		ccategory=new ComboBox<Category>(FXCollections.observableArrayList(categories));
		ccategory.setPromptText("-select-");;
//		selectedCategory=(Category) ccategory.getValue();
//		categoryName = selectedCategory.getCategoryName();
		
		
		subcategories = DBHandler3.getSubcategoryNameForComboBox(categoryName);
	    csubCategory.setItems(FXCollections.observableArrayList(subcategories));
	    csubCategory.setPromptText("-select-");
		
		ccategory.setOnAction(e -> {
			selectedCategory = (Category) ccategory.getValue();
	        categoryName = selectedCategory.getCategoryName();
	  
	        subcategories = DBHandler3.getSubcategoryNameForComboBox(categoryName);
	        csubCategory.setItems(FXCollections.observableArrayList(subcategories));
            csubCategory.getSelectionModel().select(0);
        });
		
		
		try {
			fis=new FileInputStream("img/icon/setting.png");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			lcategoryPlus=new Label("",imgView);
			
			
			lcategoryPlus.setCursor(Cursor.HAND);
		}catch (FileNotFoundException e) {
			lcategoryPlus=new Label("Plus");
		}
		lcategoryPlus.setOnMouseClicked(e->{
			try {
				newStage(new AddNewCategory().getMainPane());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		try {
			fis=new FileInputStream("img/icon/setting.png");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			lsubcategoryPlus=new Label("",imgView);
			
			
			lsubcategoryPlus.setCursor(Cursor.HAND);
		}catch (FileNotFoundException e) {
			lsubcategoryPlus=new Label("Plus");
		}
		lsubcategoryPlus.setOnMouseClicked(e->{
			try {
				newStage(new AddNewSubcategory().getMainPane());
		
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});	
		
		lrecent=new Label("Recent");
		
		btnAdd=new Button("Add");
		btnAdd.setOnAction(e->{
			
			try {
				if(checkNull())
				{
					
					setData();
					setNull();
					refresh();
					mainPane.setCenter(addNewItemPane);
					Checker.alertMessage("Add New Item", "New item added successfully!");
				}
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
		});
		btnAdd.setCursor(Cursor.HAND);
		btnClear=new Button("Clear");
		btnClear.setOnAction(e->{
			setNull();
		});
		btnClear.setCursor(Cursor.HAND);
	
		tname=new TextField();
//		tname.setOnAction(e->{
//			try {
//				autoSelectCategoryAndSubcategory();
//			}catch(Exception e1)
//			{
//				e1.printStackTrace();
//			}
//			
//		});

		OrderDetail.autoCompleteSearch(tname);
		
		tname.setMaxSize(600, 300);
		
		toriginalPrice=new TextField();
		tsellPrice=new TextField();
		tcolor=new TextField();
		tsize=new TextField();
		
		
		
		
		squantity=new Spinner(1,10000,1);
		squantity.setValueFactory(new IntegerSpinnerValueFactory(0, 10000, 0));
		squantity.setEditable(true);

		sellPriceStartDate=new DatePicker();
		
		
		
		
		
		editMI=new MenuItem("Edit");
		editMI.setOnAction(e->{
			try {
				mainPane.setCenter(new EditForInputProduct().getMainPane(setEditInfo()));
				
				MainPage.lpath.setText("Main Page  -> Input Product  ->  Edit Mode ");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		deleteMI=new MenuItem("Delete");
		deleteMI.setOnAction(e->{
			try {
				if(Checker.showConfirmation("Are you sure you want to delete the selected item?"))
				{
					RecentAdded selectedItem=(RecentAdded) tv.getSelectionModel().getSelectedItem();
					DBHandler3.deleteItemFromRecent(selectedItem.getPricedetailID());
					refresh();
//					Checker.alertMessage("Delete Recent Item", "Recent Item deleted successfully!");
				}
				
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
			
		});
		
		
		refreshMI=new MenuItem("Refresh");
		refreshMI.setOnAction(e->{
			tv.getItems().clear();
			setTableData();
			
		});
		
		rightClick=new ContextMenu();
		rightClick.getItems().addAll(refreshMI,deleteMI);
	}
	public void defineLayout()
	{

		laddNewItem=new Label("Add New Item");
		lupdateItem=new Label("Update Item");
		
		menuHBox=new HBox(laddNewItem,lupdateItem);
		menuHBox.setPadding(new Insets(9));
		
		addMenuItem=new MenuItem("Add New Item");
		updateMenuItem=new MenuItem("Update Item");
		
		
		inputMenu=new Menu();
		inputMenu.getItems().add(addMenuItem);
		inputMenu.getItems().add(updateMenuItem);
		
		
		
		inputMenuBar=new MenuBar();
		
		
		buttonHBox=new HBox(btnClear,btnAdd);
		
		buttonHBox.setMargin(btnAdd, new Insets(10));
		buttonHBox.setMargin(btnClear, new Insets(10));
		
		categoryHBox=new HBox(ccategory,lcategoryPlus);
		categoryHBox.setMargin(lcategoryPlus, new javafx.geometry.Insets(2, 0, 0, 10));
		
		subcategoryHBox=new HBox(csubCategory,lsubcategoryPlus);
		categoryHBox.setMargin(lsubcategoryPlus, new javafx.geometry.Insets(2, 0, 0, 10));
		
		tnamePane=new GridPane();
		tnamePane.add(lnameError, 0, 0);
		tnamePane.add(tname, 0, 1);
		
		tcategoryPane=new GridPane();
		tcategoryPane.add(lcategoryError, 0, 0);
		tcategoryPane.add(ccategory, 0, 1);
		tcategoryPane.add(lcategoryPlus, 1, 1);
		tcategoryPane.setHgap(10);
		
		tsubcategoryPane=new GridPane();
		tsubcategoryPane.add(lsubcategoryError, 0, 0);
		tsubcategoryPane.add(csubCategory, 0, 1);
		tsubcategoryPane.add(lsubcategoryPlus, 1, 1);
		tsubcategoryPane.setHgap(10);
		
		tsellPane=new GridPane();
		tsellPane.add(lsellPriceError, 0, 0);
		tsellPane.add(tsellPrice, 0, 1);
		
		tquantityPane=new GridPane();
		tquantityPane.add(lquantityError, 0, 0);
		tquantityPane.add(squantity, 0, 1);


		
		tpurchasePane=new GridPane();
		tpurchasePane.add(lpurchasePriceError, 0, 0);
		tpurchasePane.add(toriginalPrice, 0, 1);
		
		
		
		gp1=new GridPane();
		gp1.add(lname, 0, 0);
		gp1.add(loriginalPrice, 0, 1);
		gp1.setVgap(50);
		gp1.setPadding(new Insets(20,0,0,0));
		
		gp2=new GridPane();
		gp2.add(tnamePane, 0, 0);
		gp2.add(tpurchasePane, 0, 1);
		gp2.setVgap(20);
		
		gp3=new GridPane();
		gp3.add(lcategory, 0, 0);
		gp3.add(lsellPrice, 0, 1);
		gp3.setVgap(50);
		gp3.setPadding(new Insets(20,0,0,0));
		
		gp4=new GridPane();
		gp4.add(tcategoryPane, 0, 0);
		gp4.add(tsellPane, 0, 1);
		gp4.setVgap(20);
		
		gp5=new GridPane();
		gp5.add(lsubCategory, 0, 0);
		gp5.add(lquantity, 0, 1);
		gp5.setVgap(50);
		gp5.setPadding(new Insets(20,0,0,0));
		
		gp6=new GridPane();
		gp6.add(tsubcategoryPane, 0, 0);
		gp6.add(tquantityPane, 0, 1);
		gp6.add(buttonHBox, 0, 2);
		gp6.setVgap(20);
		
		inputPane=new GridPane();
		
		inputPane.add(gp1, 0, 0);
		inputPane.add(gp2, 1, 0);
		inputPane.add(gp3, 2, 0);
		inputPane.add(gp4, 3, 0);
		inputPane.add(gp5, 4, 0);
		inputPane.add(gp6, 5, 0);
//		inputPane.add(gp7, 6, 0);
//		inputPane.add(gp8, 7, 0);
		
		inputPane.setVgap(50);
		inputPane.setHgap(30);
		inputPane.setAlignment(Pos.CENTER);
		
		tablePane=new GridPane();
		tablePane.add(lrecent, 0, 0);
		tablePane.add(tv, 0, 1);
		tablePane.setAlignment(Pos.CENTER);
		tablePane.setVgap(10);
		
		addNewItemPane=new BorderPane();
		addNewItemPane.setTop(inputPane);
		addNewItemPane.setCenter(tv);
		
		mainPane=new BorderPane();
//		mainPane.setTop(lpath);
		mainPane.setCenter(addNewItemPane);
		
	}
	public void setNull()
	{
		tname.setText("");
		squantity.getValueFactory().setValue(0);
		toriginalPrice.setText("");
		tsellPrice.setText("");
		tsize.setText("");
		tcolor.setText("");
		
	}
	public void setData()
	{
		 	String itemName = tname.getText();
		    Category selectedCategory = (Category) ccategory.getValue();
		    String category = selectedCategory.getCategoryName();

		    Subcategory selectedSubcategory = (Subcategory) csubCategory.getValue();
		    String subcategory = selectedSubcategory.getSubcategoryName();

		    String color = tcolor.getText();
		    String size = tsize.getText();

		    int originalPrice = 0;
		    if (toriginalPrice.getText() != null && !toriginalPrice.getText().isEmpty()) {
		        originalPrice = Integer.parseInt(toriginalPrice.getText());
		    }

		    int sellPrice = 0;
		    if (tsellPrice.getText() != null && !tsellPrice.getText().isEmpty()) {
		        sellPrice = Integer.parseInt(tsellPrice.getText());
		    }

		    int quantity = 0;
		    try{
		    	if (squantity.getValue() != null) {
			        quantity = squantity.getValue();
			    }
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
	
			try {
				DBHandler3.updateEndDateNullBeforeNewPrice(tname.getText());
			    DBHandler3.insertItemNew(itemName, category, subcategory,quantity, originalPrice, sellPrice);

			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
	       
	}
	public boolean checkNull()
	{
		if(!tname.getText().isEmpty() && ccategory.getValue()!=null && csubCategory.getValue()!=null && !toriginalPrice.getText().isEmpty() && !tsellPrice.getText().isEmpty() && squantity.getValue()!=0 && Checker.isValidPrice(tsellPrice.getText()) && Checker.isValidPrice(toriginalPrice.getText()))
		{
			lnameError.setText(null);
			lpurchasePriceError.setText(null);
			lsellPriceError.setText(null);
			lcategoryError.setText(null);
			lsubcategoryError.setText(null);
			lquantityError.setText(null);
		
			return true;
		}
		
		// Check Null For Item Name
		if(tname.getText().isEmpty())
		{
			lnameError.setText("Fill item Name!");
		}
		else {
			lnameError.setText(null);
		}
		
		//Check Null for purchase price
		if(toriginalPrice.getText().isEmpty())
		{
			lpurchasePriceError.setText("Fill purchase price!");
		}
		else {
			if(!Checker.isValidPrice(toriginalPrice.getText())){
				lpurchasePriceError.setText("Price must be number!");
			}
			else {
				lpurchasePriceError.setText(null);
			}
			
		}
		
		//check null for sell price
		if(tsellPrice.getText().isEmpty())
		{
			lsellPriceError.setText("Fill sell price!");
		}
		else {
			if(!Checker.isValidPrice(tsellPrice.getText())){
				lsellPriceError.setText("Price must be number!");
			}
			else {
				lsellPriceError.setText(null);
			}
		}
		
		//check null for category
		if(ccategory.getValue()==null)
		{
			lcategoryError.setText("Select category!");
		}
		else {
			lcategoryError.setText(null);
		}
		
		
		//check null for subcategory
		if(csubCategory.getValue()==null)
		{
			lsubcategoryError.setText("Select subcategory!");
		}
		else {
			lsubcategoryError.setText(null);
		}
		
		if(squantity.getValue()==0)
		{
			lquantityError.setText("Fill quanity!");
		}
		else {
			lquantityError.setText(null);
		}
		
		
		return false;
	}
	public void checkData()
	{
		if(tname.getText().isEmpty())
		{
			lnameError.setText("Fill item Name!");
		}
		else {
			lnameError.setText(null);
		}
		
		
		if(toriginalPrice.getText().isEmpty())
		{
			lpurchasePriceError.setText("Fill purchase price!");
		}
		else {
			if(!Checker.isValidPrice(toriginalPrice.getText())){
				lpurchasePriceError.setText("Price must be number!");
			}
			else {
				lpurchasePriceError.setText(null);
			}
			
		}
		
		
		
		if(tsellPrice.getText().isEmpty())
		{
			lsellPriceError.setText("Fill sell price!");
		}
		else {
			if(!Checker.isValidPrice(tsellPrice.getText())){
				lsellPriceError.setText("Price must be number!");
			}
			else {
				lsellPriceError.setText(null);
			}
		}
		
		
		if(ccategory.getValue()==null)
		{
			lcategoryError.setText("Select category!");
		}
		else {
			lcategoryError.setText(null);
		}
		
		
		if(csubCategory.getValue()==null)
		{
			lsubcategoryError.setText("Select subcategory!");
		}
		else {
			lsubcategoryError.setText(null);
		}
		
		
		if(squantity.getValue()==0)
		{
			lquantityError.setText("Fill quanity!");
		}
		else {
			lquantityError.setText(null);
		}
		
//		if(!Checker.isValidPrice(toriginalPrice.getText()))
//		{
//			lpurchasePriceError.setText("Price must be numbers!");
//		}
//		else {
//			lpurchasePriceError.setText(null);
//		}
//		
//		if(!Checker.isValidPrice(tsellPrice.getText()))
//		{
//			
//			lsellPriceError.setText("Price must be numbers!");
//		}
//		else {
//			lsellPriceError.setText(null);
//		}
				
	}
	public void createTable()
	{
		tv=new TableView<Item>();
		tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
				
		TableColumn<RecentAdded,Integer> idCol=new TableColumn<RecentAdded,Integer>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<RecentAdded,Integer>("pricedetailID")); 
		
		TableColumn<RecentAdded,String> nameCol=new TableColumn<RecentAdded,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<RecentAdded,String>("itemName"));
		
		TableColumn<RecentAdded,String> categoryCol=new TableColumn<RecentAdded,String>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<RecentAdded,String>("categoryName"));
		
		TableColumn<RecentAdded,String> subcategoryCol=new TableColumn<RecentAdded,String>("Subcategory");
		subcategoryCol.setCellValueFactory(new PropertyValueFactory<RecentAdded,String>("subcategoryName"));
		
		TableColumn<RecentAdded,Integer> quantityCol=new TableColumn<RecentAdded,Integer>("Quantity");
		quantityCol.setCellValueFactory(new PropertyValueFactory<RecentAdded,Integer>("quantity"));
		
		TableColumn<RecentAdded,Integer> purchasePriceCol=new TableColumn<RecentAdded,Integer>("Purchase Price");
		purchasePriceCol.setCellValueFactory(new PropertyValueFactory<RecentAdded,Integer>("purchasePrice"));
		
		
		TableColumn<RecentAdded,Integer> sellPriceCol=new TableColumn<RecentAdded,Integer>("Sell Price");
		sellPriceCol.setCellValueFactory(new PropertyValueFactory<RecentAdded,Integer>("sellPrice"));
		
		TableColumn<RecentAdded,LocalDate> dateCol=new TableColumn<RecentAdded,LocalDate>("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<RecentAdded,LocalDate>("date"));

		tv.getColumns().add(idCol);
		tv.getColumns().add(nameCol);
		tv.getColumns().add(categoryCol);
		tv.getColumns().add(subcategoryCol);
		tv.getColumns().add(quantityCol);
		
		tv.getColumns().add(purchasePriceCol);
		tv.getColumns().add(sellPriceCol);
		tv.getColumns().add(dateCol);
		
		selectionModel=tv.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		tv.setPrefWidth(1300);
		tv.setPrefHeight(520);
		tv.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				if(e.getClickCount()==2)
				{
					try {
						
						mainPane.setCenter(new EditForInputProduct().getMainPane(setEditInfo()));
//						 new EditForInputProduct().fillFields();
						
						MainPage.lpath.setText("Main Page  -> Input Product  ->  Edit Mode ");						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
//				rightClick.show(tv, e.getScreenX(), e.getScreenY());
//				System.out.print("Secondary");
				
			}
			if(e.getButton()==MouseButton.SECONDARY)
			{
				rightClick.show(tv, e.getScreenX(), e.getScreenY());
			}
			
		});
		
		setTableData();
	}
	public void refresh()
	{
		tv.getItems().clear();
		setTableData();
	}
	
	
	
	// set automatic to select category and subcategory
	public void autoSelectCategoryAndSubcategory() {
		String categoryName=DBHandler3.getCategoryNameForAutoFill(tname.getText());
		String subcategoryName=DBHandler3.getSubategoryNameForAutoFill(tname.getText());
		
		if(tname.getText()!=null && categoryName!=null && subcategoryName!=null)
		{
			ccategory.setValue(categoryName);
			csubCategory.setValue(subcategoryName);
		}
	}

	
	
	public void setTableData()
	{
		recentArrayList=DBHandler3.getRecentAddedItem();
		tv.getItems().addAll(recentArrayList);
			
	}
	public Item setEditInfo()
	{
		
		Item im=selectionModel.getSelectedItem();
//		System.out.print(it.getItemName());
	
		return new Item(im.getItemID(),im.getItemName(),im.getQuantity(),im.getCategoryName(),im.getSubcategoryName(),im.getPurchasePrice(),im.getPurchaseStartDate(),im.getSellPrice(),im.getSellStartDate());
		
	}
	public void setStyle()
	{
		


		tname.getStyleClass().add("textfieldinput");
		toriginalPrice.getStyleClass().add("textfieldinput");
		tsellPrice.getStyleClass().add("textfieldinput");
		tsize.getStyleClass().add("textfieldinput");
		tcolor.getStyleClass().add("textfieldinput");
		

		sellPriceStartDate.getStyleClass().add("textfieldinput");
		squantity.getStyleClass().add("textfieldinput");
		
		
		inputPane.setId("inputpaneInput");

		
		btnAdd.setId("btnaddInput");
		btnClear.setId("btnclearInput");
		
		btnAdd.getStyleClass().add("buttonInput");
		btnClear.getStyleClass().add("buttonInput");
		
		
//		mainPaneAdd.setId("mainpaneadd");
//		addBtnAdd.setId("addbtnadd");
		
		
//		btnImageChoose.setId("btnfileInput");
		
		tv.setId("tableInput");
		tablePane.setId("tablepaneInput");
		
		lrecent.setId("recentInput");
		
		
		menuHBox.getStyleClass().add("menuhboxInput");
		
		
		
		lnameError.getStyleClass().add("errorInput");
		lpurchasePriceError.getStyleClass().add("errorInput");
		lsellPriceError.getStyleClass().add("errorInput");
		lcategoryError.getStyleClass().add("errorInput");
		lsubcategoryError.getStyleClass().add("errorInput");
		lquantityError.getStyleClass().add("errorInput");
	}
	public BorderPane getMainPane()
	{

		createTable();
		createNode();
		defineLayout();
		setStyle();
		
		return mainPane;
		
	}
	public void setInputPane(BorderPane mainPane) {
		this.addNewItemPane = mainPane;
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

	public void newStage(BorderPane bp) throws Exception {
			

		mainPaneAdd=new BorderPane();
		mainPaneAdd.setCenter(bp);
		
		Scene sc=new Scene(mainPaneAdd);
		
		
		addStage=new Stage();
		
		addStage.setWidth(300);
		addStage.setHeight(300);
		addStage.setResizable(false);
		addStage.setScene(sc);
		addStage.initModality(Modality.APPLICATION_MODAL);
//		addStage.initStyle(StageStyle.UNDECORATED);
		addStage.showAndWait();
		 

	}
	public void refreshCategoryComboBox() {
	    categories = DBHandler3.getAllCategoryName();
	    ccategory.setItems(FXCollections.observableArrayList(categories));
	    ccategory.setPromptText("-select-");
	}


}
