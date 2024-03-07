package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

public class ItemList extends Application {
	
	private TableView itemTv;
	
	private ArrayList<Item> al;
	private FilteredList<Item> fl;
	
	private GridPane centerPane;
	private BorderPane mainPane;
	
	private TableViewSelectionModel<Item> selectionModel;
	
	
	public void defineLayout()
	{
		
		centerPane=new GridPane();
		centerPane.add(itemTv, 0, 0);
		centerPane.setAlignment(Pos.CENTER);
		
		mainPane=new BorderPane();
		mainPane.setCenter(itemTv);
	}
	public void createTable(String itemName)
	{
		itemTv=new TableView<Item>();
		itemTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
		
		TableColumn<Item,Integer> itemIDCol=new TableColumn<Item,Integer>("Item ID");
		itemIDCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("itemID"));
		
		TableColumn<Item,String> itemNameCol=new TableColumn<Item,String>("Item Name");
		itemNameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("itemName"));
		
		TableColumn<Item,String> categoryCol=new TableColumn<Item,String>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<Item,String>("categoryName"));
		
		TableColumn<Item,String> subcategoryCol=new TableColumn<Item,String>("Subcategory Name");
		subcategoryCol.setCellValueFactory(new PropertyValueFactory<Item,String>("subcategoryName"));
		
		itemTv.getColumns().add(itemIDCol);
		itemTv.getColumns().add(itemNameCol);
		itemTv.getColumns().add(categoryCol);
		itemTv.getColumns().add(subcategoryCol);
		
		itemTv.setPrefHeight(750);
		itemTv.setPrefWidth(650);
		
		selectionModel=itemTv.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		itemTv.setOnMouseClicked(e->{
			
			if(e.getButton()==MouseButton.PRIMARY)
			{
				if(e.getClickCount()==2)
				{
					Item it=selectionModel.getSelectedItem();
					OrderDetail.setProductName(it.getItemName());
					closeStage();
				}
				
			}
			
		});
		itemTv.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER)
			{
				Item it=selectionModel.getSelectedItem();
				OrderDetail.setProductName(it.getItemName());
				closeStage();
			}
			else if(e.getCode()==KeyCode.BACK_SPACE)
			{
				closeStage();
			}
		});
		setDataForTable();
		filteringDate(itemName);
		
	}
	public void filteringDate(String var)
	{  
		fl=new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<Item>(){

			@Override
			public boolean test(Item i) {
				String value=var;
				
				if(value.length()==0)
				{
					return true;
				}
				try {
					String name=value;
					
					return i.getItemName().toLowerCase().contains(name.toLowerCase()) || i.getSubcategoryName().toLowerCase().contains(name.toLowerCase());
					
				}
				catch(Exception e)
				{
					return value.contains(i.getItemName());
				}
				
				
				
			}	
		});
		itemTv.getItems().clear();
		itemTv.getItems().addAll(fl);
	}
	public void closeStage() {
	    Stage stage = (Stage) itemTv.getScene().getWindow(); 
	    stage.close();
	}
	public void setDataForTable()
	{
		al=DBHandler3.getItemList();
		itemTv.getItems().addAll(al);
	}
	public BorderPane getMainPane(String var)
	{
		createTable(var);
		defineLayout();
		
		return mainPane;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
//		createNodes();
//		createTable(var);
//		defineLayout();
		
//		setStyle();
		
		Scene sc=new Scene(mainPane,700,900);
		URL url = this.getClass ().getResource ("mystyle.css"); 
		sc.getStylesheets().add(url.toExternalForm());
		
		stage.setScene(sc);
		stage.setTitle("Item List");
		
//		stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);

	}

}
