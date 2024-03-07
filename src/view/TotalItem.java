package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import java.util.*;

public class TotalItem{
	
	private ArrayList<Item> al;
	private ArrayList<Subcategory> subCategories;
	private ArrayList<Category> categories;
	private FilteredList<Item> fl;
	
	private Label lcategory,lsubCategory,lsearch,ltotalProduct,limage,lproductTable;
	private ComboBox ccategory,csubCategory;
	private TextField tsearch;
	private Category selectedCategory;
	private String categoryName;
	
	private MenuItem deleteMI;
	private ContextMenu rightClick;
	
	private TableView itemTv;
//	private TableColumn idCol,nameCol,categoryCol,subCategoryCol,purchasePriceCol,sellPriceCol,quantityCol,colorCol,sizeCol,dateCol,actionCol;
	
	private HBox categoryHBox,subCategoryHBox,searchHBox;
	private HBox imageHBox;
	
	private GridPane topPane,centerPane;
	private BorderPane mainPane;
	
	private TableViewSelectionModel<Item> selectionModel;
	
	private Button btnSearch;
	
	

	public void createNodes()
	{
		FileInputStream fis;
		
		lcategory=new Label("Category : ");
		lsubCategory=new Label("Sub-Category : ");
		
		lproductTable=new Label("Product Table");
		
		lsearch=new Label("Search : ");
		
		try {
			fis=new FileInputStream("");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			limage=new Label("",imgView);
			
			
			limage.setCursor(Cursor.HAND);
		}catch (FileNotFoundException e) {
			limage=new Label("--image--");
		}
		limage.setPrefWidth(200);
		limage.setPrefHeight(200);
		limage.setAlignment(Pos.TOP_CENTER);
		
		tsearch=new TextField();
		tsearch.setOnKeyTyped(e->{
			try {
				filtering();
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
		});
		
		
		csubCategory=new ComboBox();
		
		categories=DBHandler3.getAllCategoryName();
		ccategory=new ComboBox<Category>(FXCollections.observableArrayList(categories));
//		ccategory.getSelectionModel().select(0);
//		selectedCategory=(Category) ccategory.getValue();
//		categoryName = selectedCategory.getCategoryName();
		ccategory.setPromptText("-select-");
		
		
		subCategories = DBHandler3.getSubcategoryNameForComboBox(categoryName);
	    csubCategory.setItems(FXCollections.observableArrayList(subCategories));
	    csubCategory.setPromptText("-select-");
		
		ccategory.setOnAction(e -> {
			selectedCategory = (Category) ccategory.getValue();
	        categoryName = selectedCategory.getCategoryName();
	  
	        subCategories = DBHandler3.getSubcategoryNameForComboBox(categoryName);
	        csubCategory.setItems(FXCollections.observableArrayList(subCategories));
           
	        filteringDate();
	        
	        
        });
		
		
	
		btnSearch=new Button("Search");
		btnSearch.setCursor(Cursor.HAND);
		
		deleteMI=new MenuItem("Delete");
		deleteMI.setOnAction(e->{
			try {
				if(Checker.showConfirmation("Are you sure you want to delete the selected item?"))
				{
					Item selectedItem=(Item) itemTv.getSelectionModel().getSelectedItem();
					DBHandler3.deleteFromItemTable(selectedItem.getItemID());
					refresh();
				}
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
			
		});
		
		
		
		rightClick=new ContextMenu();
		rightClick.getItems().addAll(deleteMI);
		
	}
	public void defineLayout()
	{
		
		categoryHBox=new HBox(lcategory,ccategory);
		subCategoryHBox=new HBox(lsubCategory,csubCategory);
		searchHBox=new HBox(lsearch,tsearch);
		
		imageHBox=new HBox(limage);
		imageHBox.setMargin(limage, new Insets(3000));

		topPane=new GridPane();
		topPane.add(categoryHBox, 0, 0);
		topPane.add(subCategoryHBox, 1, 0);
		topPane.add(searchHBox, 2, 0);

		topPane.setHgap(30);
		topPane.setVgap(20);
		topPane.setAlignment(Pos.CENTER);
		
		centerPane=new GridPane();
		centerPane.add(lproductTable, 0, 0);

		centerPane.add(itemTv, 0, 1);
		
		centerPane.setMargin(itemTv, new Insets(10));
		centerPane.setMargin(limage, new Insets(10));
		
		mainPane=new BorderPane();
		mainPane.setTop(topPane);
		mainPane.setCenter(itemTv);

		
		
	}
	public void filtering()
	{  
		fl=new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<Item>(){

			@Override
			public boolean test(Item i) {
				String value=tsearch.getText();
				
				if(value.length()==0)
				{
					return true;
				}
				try {
					String name=value;
					
					return i.getItemName().toLowerCase().contains(name.toLowerCase());
					
				}
				catch(Exception e)
				{
					System.out.print("Catch");
					return value.contains(i.getItemName());
				}
				
				
				
			}	
		});
		itemTv.getItems().clear();
		itemTv.getItems().addAll(fl);
	}
	
	public void createProductTable()
	{
		itemTv=new TableView<Item>();
		itemTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
		
		TableColumn<Item,Integer> idCol=new TableColumn<Item,Integer>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("itemID")); 
		
		TableColumn<Item,String> nameCol=new TableColumn<Item,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("itemName"));
		
		TableColumn<Item,String> categoryCol=new TableColumn<Item,String>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<Item,String>("categoryName"));
		
		TableColumn<Item,String> subcategoryCol=new TableColumn<Item,String>("Subcategory");
		subcategoryCol.setCellValueFactory(new PropertyValueFactory<Item,String>("subcategoryName"));
		
		
		TableColumn<Item,Integer> purchasePriceCol=new TableColumn<Item,Integer>("Purchase Price");
		purchasePriceCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("pprice"));
		
		TableColumn<Item,Integer> sellPriceCol=new TableColumn<Item,Integer>("Sell Price");
		sellPriceCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("sprice"));
		
		TableColumn<Item,Integer> quantityCol=new TableColumn<Item,Integer>("Quantity");
		quantityCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("totalQuantity"));
		
		TableColumn<Item,Integer> profitAmountCol=new TableColumn<Item,Integer>("ProfitAmount");
		profitAmountCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("profitAmount"));
		
		itemTv.getColumns().add(idCol);
		itemTv.getColumns().add(nameCol);
		itemTv.getColumns().add(categoryCol);
		itemTv.getColumns().add(subcategoryCol);
		itemTv.getColumns().add(quantityCol);
		itemTv.getColumns().add(purchasePriceCol);
		itemTv.getColumns().add(sellPriceCol);
		itemTv.getColumns().add(profitAmountCol);
		
		selectionModel=itemTv.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		itemTv.setPrefWidth(1200);
		itemTv.setPrefHeight(700);
		
		itemTv.setOnMouseClicked(e->{
			
			if(e.getButton()==MouseButton.PRIMARY) 
			{
				if(e.getClickCount()==2)
				{
					mainPane.setTop(null);
					mainPane.setCenter(new TotalItemEditMode().getMainPane(setEditInfo()));
					
					MainPage.lpath.setText("Main Page  -> Total Item  ->  Edit Mode ");
				}
			}
			if(e.getButton()==MouseButton.SECONDARY)
			{
				rightClick.show(itemTv, e.getScreenX(), e.getScreenY());
			}
		});
		
		setData();
	}
	public void setData()
	{
		al=DBHandler3.getTotalItemList();
		itemTv.getItems().addAll(al);
		
		
	}
	public void filteringDate()
	{  
		fl=new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<Item>(){

			@Override
			public boolean test(Item i) {
//				System.out.println(ccategory.getValue().toString());
//				System.out.println(" CategoryName "+i.getCategoryName());
				String value=ccategory.getValue().toString();
	
				if(value==null)
				{
					return true;
				}
				try {
					String categoryName=value;
		
					return categoryName.equals(i.getCategoryName());
					
				}
				catch(Exception e)
				{
					return value.equals(i.getCategoryName());
				}
				
				
				
			}	
		});
		itemTv.getItems().clear();
		itemTv.getItems().addAll(fl);
	}
	public void filteringSubcategory()
	{  
		fl=new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<Item>(){

			@Override
			public boolean test(Item i) {
//				System.out.println(ccategory.getValue().toString());
//				System.out.println(" CategoryName "+i.getCategoryName());
				String value=ccategory.getValue().toString();
				String value1=csubCategory.getValue().toString();
				
				if(value==null)
				{
					return true;
				}
				try {
					String categoryName=value;
					String subcategoryName=value1;
		
					return categoryName.equals(i.getCategoryName()) && subcategoryName.equals(i.getSubcategoryName());
					
				}
				catch(Exception e)
				{
					return value.equals(i.getSubcategoryName()) && value1.equals(i.getSubcategoryName());
				}
				
				
				
			}	
		});
		itemTv.getItems().clear();
		itemTv.getItems().addAll(fl);
	}
	public void refresh()
	{
		itemTv.getItems().clear();
		setData();
	}

	public Item setEditInfo()
	{
		Item i=selectionModel.getSelectedItem();
//		System.out.print(it.getItemName());
	
		return new Item(i.getItemID(),i.getItemName(),i.getCategoryName(),i.getSubcategoryName());
		
	}
	public void setStyle()
	{
		topPane.setId("toppaneTotal");
		btnSearch.setId("btnsearchTotal");
		
		limage.setId("imageTotal");
		centerPane.setId("centerpaneTotal");
		lproductTable.setId("titleTotal");
		itemTv.setId("itemTableTotal");
	}
	public BorderPane getMainPane()
	{
		createProductTable();
		createNodes();
		defineLayout();
		setStyle();
		
		return mainPane;
	}
	

//	@Override
//	public void start(Stage st) throws Exception {
//		
//		createProductTable();
//		createNodes();
//		defineLayout();
//		setStyle();
//		
//		Scene sc=new Scene(mainPane,1000,1000);
//		URL url = this.getClass ().getResource ("mystyletotalproduct.css"); 
//		sc.getStylesheets().add(url.toExternalForm());
//		
//		st.setScene(sc);
//		st.setTitle("Total Product");
//		st.show();
//		
//	}
	
	
	
	
	
//	public static void main(String[] args) {
//		
//		Application.launch(args);
//
//	}

}
