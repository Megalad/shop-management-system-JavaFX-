package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Input extends Application{

	
	private Menu addMenu,subcategoryMenu;
	private MenuItem addProductMI,addSubcategoryMI;
	private MenuBar bar;
	private VBox vb;
	private BorderPane centerPane;
	
	public void createNodes()
	{
		
	}
	
	public void createMenuBar()
	{
		addMenu=new Menu("Add Data");
		addProductMI=new MenuItem("Add Product");
		
		addMenu.getItems().add(addProductMI);
		addMenu.getItems().add(new SeparatorMenuItem());
		addProductMI.setOnAction(e->{
			centerPane.setCenter(null);
			centerPane.setCenter(new InputProduct().getMainPane());
			System.out.print("Hi");
		});
		
		addSubcategoryMI=new MenuItem("Add Subcategory");
		addMenu.getItems().add(addSubcategoryMI);
		addMenu.getItems().add(new SeparatorMenuItem());
		addSubcategoryMI.setOnAction(e->{
			centerPane.setCenter(null);
			centerPane.setCenter(new InputSubcategory().getMainPane());
		});
		
		
		
		
		
	}
	public void defineLayouts()
	{
		
		bar=new MenuBar(addMenu);
		
		centerPane=new BorderPane();
		centerPane.setCenter(new InputProduct().getMainPane());
		
		vb=new VBox(bar,centerPane);
		
	}
	public void setStyle()
	{
		addMenu.setId("addmenu");
	}

	public VBox getVBox()
	{
		createNodes();
		createMenuBar();
		defineLayouts();
		setStyle();
		
		return vb;
	}
	
	@Override
	public void start(Stage st) throws Exception {
		
		
		
		Scene sc=new Scene(vb,1590,1600);
		
		st.setScene(sc);
		st.setTitle("Input");
		st.show();
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}
