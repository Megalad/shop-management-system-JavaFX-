package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.InputProduct;
import model.DBHandler3;
import model.Staff;
public class MainPage extends Application{
	
	private Label lwelcome,lcompanyName,luserName,luserLogo,llogo,lhome,linputItem,lorder,lcreditSystem,ltotalProduct,lstaff,llogout,lreport,lregister;
	private GridPane menuPane,profilePane,pathPane;
	private FileInputStream fis;
	private ImageView imgView;
	private Label lastClickedLabel;
	
	private BorderPane mainPane,headerPane,centerPane;
	
	private HBox profileHBox;
	private Pane welcomePane;
	private String setColorMouseEntered,setColorMouseExited;
	
	public static Label lpath;
	
	
	private Stage st;
	
	
	public MainPage()
	{
		Staff s=DBHandler3.getStaffgetStaffInformationTempo();
		st=new Stage();
		createNode(s);
		defineLayout(s);
	
		setStyle();
		
		Scene sc=new  Scene(mainPane,2560,1600);
		URL url = this.getClass ().getResource ("mystyle.css"); 
		sc.getStylesheets().add(url.toExternalForm());
		setStyle();
		
		st.setMaxWidth(2560);
		st.setMinWidth(1000);
		
		st.setScene(sc);
		st.setTitle("Shop Management System");
		st.show();
		 
	}
	public void checkRole(Staff staff)
	{
		if(staff.getRoleName().equals("Admin"))
		{
			createHome();
			createInputProduct();
			createOrder();
			createTotalItem();
			createReport();
			createRegister();
			createLogout();
			centerPane.setCenter(new Home().getMainPane());
			
		}
		else {
			createInputProduct();
			createOrder();
			createTotalItem();
			createLogout();
			centerPane.setCenter(new InputProduct().getMainPane());
			lpath=new Label("Main Page  ->  Input Product");
			lwelcome.setText("Input Product");
		}
		
	}
	public void createHome()
	{
		
		try {
			fis=new FileInputStream("img/menu/business.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			setImgViewSize(imgView);
			lhome=new Label("Home",imgView);
			lhome.setPrefSize(195, 0);
			lhome.setPrefHeight(20);
			lhome.setCursor(Cursor.HAND);
		}catch (FileNotFoundException e) {
			lhome=new Label("Home");
		}
		
		menuPane.add(lhome, 0, 2);
		lhome.getStyleClass().add("menuMain");
		GridPane.setMargin(lhome, new Insets(15,0,0,0));
		
		lhome.setOnMouseEntered(e->{
			lhome.setStyle(setColorMouseEntered);
		});
		lhome.setOnMouseExited(e->{
			if (lastClickedLabel != lhome) {
				lhome.setStyle(setColorMouseExited);
            }
		});
		lhome.setOnMouseClicked(e->{
			try {
				setClickedColor(lhome);
				lastClickedLabel=lhome;
				centerPane.setCenter(null);
				centerPane.setCenter(new Home().getMainPane());
				lwelcome.setText("Dashboard");
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		});
	}
	public void createInputProduct()
	{
		
		try {
			fis=new FileInputStream("img/menu/input.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			setImgViewSize(imgView);
			linputItem=new Label("Input Product",imgView);
			linputItem.setCursor(Cursor.HAND);
			linputItem.setPrefSize(195, 0);
			linputItem.setPrefHeight(20);
			
		}catch (FileNotFoundException e) {
			linputItem=new Label("Input Product");
		}
		
		menuPane.add(linputItem, 0,3);
		GridPane.setMargin(linputItem, new Insets(15,0,0,0));
		linputItem.getStyleClass().add("menuMain");
		linputItem.setOnMouseEntered(e->{
			linputItem.setStyle(setColorMouseEntered);
		});
		linputItem.setOnMouseExited(e->{
			if (lastClickedLabel != linputItem) {
				linputItem.setStyle(setColorMouseExited);
            }
		});
		linputItem.setOnMouseClicked(e->{
			try {
				setClickedColor(linputItem);
				lastClickedLabel=linputItem;
				centerPane.setCenter(null);
				centerPane.setCenter(new InputProduct().getMainPane());
				lwelcome.setText("Input Product");
				lpath.setText("Main Page  -> Input Product");
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		});
	}
	public void createOrder()
	{
		try {
			fis=new FileInputStream("img/menu/shopping-cart.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			setImgViewSize(imgView);
			lorder=new Label("Order",imgView);
			
			lorder.setCursor(Cursor.HAND);
			lorder.setPrefSize(195, 0);
		}catch (FileNotFoundException e) {
			lorder=new Label("Sell Product");
		}
		menuPane.add(lorder, 0, 4);
		GridPane.setMargin(lorder, new Insets(15,0,0,0));
		lorder.getStyleClass().add("menuMain");
		lorder.setOnMouseEntered(e->{
			lorder.setStyle(setColorMouseEntered);
		});
		lorder.setOnMouseExited(e->{
			if (lastClickedLabel != lorder) {
                lorder.setStyle(setColorMouseExited);
            }
		});
		lorder.setOnMouseClicked(e->{
			
			try {
				setClickedColor(lorder);
				lastClickedLabel=lorder;
				centerPane.setCenter(null);
				centerPane.setCenter(new OrderList().getMainPane());
				lwelcome.setText("Order");
				lpath.setText("Main Page  -> Order");
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		});
		
	}
	public void createTotalItem()
	{
		try {
			fis=new FileInputStream("img/menu/all.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			setImgViewSize(imgView);
			ltotalProduct=new Label("Total Product",imgView);
			ltotalProduct.setCursor(Cursor.HAND);
			ltotalProduct.setPrefSize(195, 0);
		}catch (FileNotFoundException e) {
			ltotalProduct=new Label("Total Product");
		}
		menuPane.add(ltotalProduct, 0, 6);
		GridPane.setMargin(ltotalProduct, new Insets(15,0,0,0));
		ltotalProduct.getStyleClass().add("menuMain");
		ltotalProduct.setOnMouseEntered(e->{
			ltotalProduct.setStyle(setColorMouseEntered);
		});
		ltotalProduct.setOnMouseExited(e->{
			if (lastClickedLabel != ltotalProduct) {
				ltotalProduct.setStyle(setColorMouseExited);
            }
		});
		ltotalProduct.setOnMouseClicked(e->{
			
			try {
				setClickedColor(ltotalProduct);
				lastClickedLabel=ltotalProduct;
				centerPane.setCenter(null);
				centerPane.setCenter(new TotalItem().getMainPane());
				lwelcome.setText("Total Product");
				lpath.setText("Main Page  -> Total Product");
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		});
	}
	public void createReport()
	{
		try {
			fis=new FileInputStream("img/menu/analysis.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			setImgViewSize(imgView);
			lreport=new Label("Report",imgView);
			lreport.setCursor(Cursor.HAND);
			lreport.setPrefSize(195, 0);
		}catch (FileNotFoundException e) {
			lreport=new Label("Report");
		}
		
		menuPane.add(lreport, 0, 7);
		GridPane.setMargin(lreport, new Insets(15,0,0,0));
		lreport.getStyleClass().add("menuMain");
		lreport.setOnMouseEntered(e->{
			lreport.setStyle(setColorMouseEntered);
		});
		lreport.setOnMouseExited(e->{
			if (lastClickedLabel != lreport) {
				lreport.setStyle(setColorMouseExited);
            }
		});
		lreport.setOnMouseClicked(e->{
			try {
				setClickedColor(lreport);
				lastClickedLabel=lreport;
				centerPane.setCenter(null);
				centerPane.setCenter(new Report().getMainPane());
				lwelcome.setText("Report");
				lpath.setText("Main Page  -> Report");
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		});
	}
	public void createRegister()
	{
		try {
			fis=new FileInputStream("img/menu/register1.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			setImgViewSize(imgView);
			lregister=new Label("Add Staff",imgView);
			lregister.setCursor(Cursor.HAND);
			lregister.setPrefSize(195, 0);
		}catch (FileNotFoundException e) {
			lregister=new Label("Register");
		}
		
		menuPane.add(lregister, 0, 9);
		GridPane.setMargin(lregister, new Insets(15,0,0,0));
		lregister.getStyleClass().add("menuMain");
		lregister.setOnMouseEntered(e->{
			lregister.setStyle(setColorMouseEntered);
		});
		lregister.setOnMouseExited(e->{
			if (lastClickedLabel != lregister) {
				lregister.setStyle(setColorMouseExited);
            }
		});
		lregister.setOnMouseClicked(e->{
			try {
				setClickedColor(lregister);
				lastClickedLabel=lregister;
				centerPane.setCenter(null);
				centerPane.setCenter(new Register().getMainPane());
				lwelcome.setText("Register");
				lpath.setText("Main Page  -> Register");
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		});
		
	}
	public void createLogout()
	{
		try {
			fis=new FileInputStream("img/menu/logout1.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			setImgViewSize(imgView);
			llogout=new Label("Log Out",imgView);
			llogout.setCursor(Cursor.HAND);
			llogout.setPrefSize(195, 0);
		}catch (FileNotFoundException e) {
			llogout=new Label("Log Out");
		}
		menuPane.add(llogout, 0, 10);
		GridPane.setMargin(llogout, new Insets(15,0,0,0));
		llogout.getStyleClass().add("menuMain");
		llogout.setOnMouseClicked(e->{
//			stage.hide();
			
			try {
				st.hide();
				
				LoginForm loginForm=new LoginForm();
				loginForm.start(new Stage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
	}
	
	public void setClickedColor(Label label)
	{
		 if (lastClickedLabel != null) 
		 {
	            lastClickedLabel.setStyle("-fx-background-color:rgb(244,244,244);-fx-text-fill:black;"); // Reset the color of the last clicked label
	     }
	     label.setStyle("-fx-background-color:rgb(100,151,177);-fx-text-fill:white;"); // Set the color of the current clicked label
	}
	
	public void createNode(Staff staff)
	{
		lwelcome=new Label();
		
		lwelcome.setAlignment(Pos.CENTER);
		lwelcome.setPrefWidth(195);
		
		lcompanyName=new Label();
		lcompanyName.setText("Badda Station");
		
		luserName=new Label(staff.getStaffName());
		
		try {
			fis=new FileInputStream("img/logo/human.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			luserLogo=new Label("",imgView);
			luserLogo.setCursor(Cursor.HAND);
			luserLogo.setPrefSize(195, 0);
		}catch (FileNotFoundException e) {
			luserLogo=new Label("User Logo");
		}
		
	}
	public static void setImgViewSize(ImageView imgView)
	{
		imgView.setFitWidth(25);
		imgView.setFitHeight(25);
	}
	public void defineLayout(Staff staff)
	{
	
		StackPane profilePane = new StackPane(luserLogo, luserName);
		StackPane.setAlignment(luserName, Pos.CENTER_LEFT); // Adjust the alignment as needed
		StackPane.setMargin(luserName, new Insets(0, 0, 0, 50)); // Adjust the margins as needed
		
		
		headerPane=new BorderPane();
		headerPane.setLeft(lwelcome);
		headerPane.setCenter(lcompanyName);
		headerPane.setRight(profilePane);
		
//		Insets topMargin = new Insets(15,0,0,0);
		
		
		
		centerPane=new BorderPane();
		
		
		
		lpath=new Label("Main Page  ->  Home");
		lwelcome.setText("DashBoard");
		
		pathPane=new GridPane();
		pathPane.add(lpath, 0, 0);
		centerPane.setTop(pathPane);
		
		
		menuPane=new GridPane();
		setColorMouseEntered="-fx-background-color:rgb(100,151,177);-fx-text-fill:white;";
		setColorMouseExited="-fx-background-color: rgb(244,244,244);-fx-text-fill:black;";
		checkRole(staff);
		
		
		
		
		mainPane=new BorderPane();

		
		mainPane.setTop(headerPane);
		mainPane.setLeft(menuPane);
		mainPane.setCenter(centerPane);
		
		
	}
	
	public void setStyle()
	{
		menuPane.getStyleClass().add("menupane");
		
		
		lwelcome.setId("welcomtext");
//		llogo.setId("logo");
		headerPane.setId("headerpane");
		lcompanyName.setId("companyname");
		lpath.setId("pathTextMain");
		luserName.setId("userNameMain");
		
		pathPane.setId("pathPaneMain");
		
	}
	@Override
	public void start(Stage st) throws Exception {
		
		
	}
	public static void main(String[] args) {
		
		Application.launch(args);
	}

}
