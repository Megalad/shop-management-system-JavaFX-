package view;

import java.net.URL;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Category;
import model.DBHandler;

public class InputSubcategory extends Application{

	private Label lcategoryAdd,lsubcategoryAdd;
	private ComboBox ccategoryAdd;
	private TextField tsubcategoryAdd;
	
	private HBox categoryHBoxAdd,subcategoryHBoxAdd;
	
	private Button addBtnAdd;
	private GridPane centerPaneAdd;
	
	private BorderPane mainPaneAdd;
	
	private Stage subcategoryStage;

 
	public void createNodes()
	{
		lcategoryAdd=new Label("Category : ");
		lsubcategoryAdd=new Label("Subcategroy : ");
		
		tsubcategoryAdd=new TextField();
		
		ccategoryAdd=new ComboBox();
		ArrayList<Category> categories=DBHandler.getAllCategoryName();
		
		ccategoryAdd=new ComboBox<Category>(FXCollections.observableArrayList(categories));
		ccategoryAdd.getSelectionModel().select(0);
		
		
		
		addBtnAdd=new Button("Add");
	}
	public void defineLayout()
	{
		
		categoryHBoxAdd=new HBox(lcategoryAdd,ccategoryAdd);
		subcategoryHBoxAdd=new HBox(lsubcategoryAdd,tsubcategoryAdd);
		
		
		centerPaneAdd=new GridPane();
		centerPaneAdd.add(categoryHBoxAdd, 0, 0);
		centerPaneAdd.add(subcategoryHBoxAdd, 0, 1);
		centerPaneAdd.add(addBtnAdd, 1, 2);
		centerPaneAdd.setVgap(20);
		centerPaneAdd.setAlignment(Pos.CENTER);
		
		mainPaneAdd=new BorderPane();
		mainPaneAdd.setCenter(centerPaneAdd);
		
		
	}
	public void setStyle()
	{
		mainPaneAdd.setId("mainpaneadd");
		
		addBtnAdd.setId("addbtnadd");		
	}
	public BorderPane getMainPane()
	{
		
		createNodes();
		defineLayout();
		setStyle();
		
		
		return mainPaneAdd;
	}
	
	
	

	@Override
	public void start(Stage stage) throws Exception{
		createNodes();
		defineLayout();
		setStyle();
		
		
		Scene sc=new Scene(mainPaneAdd,500,200);
		URL url = this.getClass ().getResource ("addnewsubcategorystyle.css"); 
		sc.getStylesheets().add(url.toExternalForm());
		
		stage.setScene(sc);
		stage.setTitle("Adding New Subcategory");
		
//		stage.initModality(Modality.APPLICATION_MODAL);
		
//		stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
		
		
	}
	public static void main(String[] args) {
		Application.launch(args);

	}

}