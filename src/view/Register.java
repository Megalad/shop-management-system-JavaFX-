package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DBHandler3;
import model.Staff;

public class Register extends Application{
	private Label lname,lphone,lgender,ldob,lpassword,lrepassword,lrole,lheader,lshow,lhide,lforgotPassword;
	private Label lnameError,lphoneError,ldobError,lpasswordError,lrepasswordError,lroleError;
	private TextField tname,tphone,tpassword,trepassword;
	private RadioButton rmale,rfemale;
	private PasswordField ppassword,prepassword;
	private ComboBox<Staff> crole;
	private DatePicker ddob;
	private Button btnRegister,btnClear;
	
	private GridPane registerPane,headerPane,gp1,gp2;
	private GridPane namePane,phonePane,dobPane,rolePane,passwordPane,repasswordPane,forgotPasswordPane,genPane;
	private TilePane genderPane;
	private BorderPane mainPane,mainPaneAdd;
	private Stage addStage;
	
	private GridPane buttonPane,centerPane;
	private ToggleGroup tgGender;
	
	
	public void createNodes()
	{
		FileInputStream fis;
		
		lnameError=new Label();
		lphoneError=new Label();
		ldobError=new Label();
		lpasswordError=new Label();
		lrepasswordError=new Label();
		lroleError=new Label();
		
		lheader=new Label("Register Form");
		lheader.setAlignment(Pos.CENTER);
		
		lname=new Label("Name:");
		lphone=new Label("Phone:");
		ldob=new Label("Date Of Birth:");
		lgender=new Label("Gender:");
		lpassword=new Label("Password:");
		lrepassword=new Label("Re-Password:");
		lrole=new Label("Role:");
		
		lforgotPassword=new Label("Forgot Password?");
		lforgotPassword.setOnMouseClicked(e->{
			try {
				newStage(new ForgotPassword().getMainPage());
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
		});
		lforgotPassword.setCursor(Cursor.HAND);
//		lforgotPassword.setPadding(new Insets(0,0,50,0));
//		lforgotPassword.setAlignment(Pos.CENTER);
		try {
			fis=new FileInputStream("img/icon/show.png");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			imgView.setFitWidth(30);
			imgView.setFitHeight(30);
			lshow=new Label("",imgView);
			lshow.setPadding(new Insets(10));
			
		} catch (FileNotFoundException e1) {
			lshow=new Label("Show");
			
		}
		
		try {
			fis=new FileInputStream("img/icon/hide.png");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			imgView.setFitWidth(30);
			imgView.setFitHeight(30);
			lhide=new Label("",imgView);
			lhide.setPadding(new Insets(10));
			
		} catch (FileNotFoundException e1) {
			lhide=new Label("Hide");
			
		}
		
		lshow.setCursor(Cursor.HAND);
		lhide.setCursor(Cursor.HAND);		
		lhide.setVisible(false);
		lshow.setPadding(new Insets(0,0,0,-10));
		lhide.setPadding(new Insets(0,0,0,-10));
		
		lshow.setOnMouseClicked(e->{
			lshow.setVisible(false);
			lhide.setVisible(true);
			tpassword.setText(ppassword.getText());
			trepassword.setText(prepassword.getText());
			
			tpassword.setVisible(true);
			trepassword.setVisible(true);
			
			ppassword.setVisible(false);
			prepassword.setVisible(false);
			
		});
		lhide.setOnMouseClicked(e->{
//			changeVisibilityPassword();
			lshow.setVisible(true);
			lhide.setVisible(false);
			ppassword.setText(tpassword.getText());
			prepassword.setText(trepassword.getText());
			
			ppassword.setVisible(true);
			prepassword.setVisible(true);
			
			trepassword.setVisible(false);
			tpassword.setVisible(false);
		});
		
		ddob=new DatePicker();
	
		tname=new TextField();
		tphone=new TextField();
		
		tpassword=new TextField();
		tpassword.setVisible(false);
		
		trepassword=new TextField();
		trepassword.setVisible(false);
		
		tgGender=new ToggleGroup();
		rmale=new RadioButton("Male");
		rmale.setToggleGroup(tgGender);
		
		rmale.setSelected(true);
		
		rfemale=new RadioButton("Female");
		rfemale.setToggleGroup(tgGender);
		
		ppassword=new PasswordField();
		prepassword=new PasswordField();
		
		
		ArrayList<model.Staff> roleNameAl=DBHandler3.getRoleName();
		
		
		
		
		crole=new ComboBox<model.Staff>(FXCollections.observableArrayList(roleNameAl));
		crole.setItems(FXCollections.observableArrayList(roleNameAl));
		crole.setPromptText("-select-");
		crole.setOnAction(e->{
			System.out.print(crole.getValue());
		});
		
		
		btnRegister=new Button("Register");
		btnRegister.setOnAction(e->{
			try {
				if(checkData())
				{
					String sgender;
					
					if(rmale.isSelected())
						  sgender="male";
					  else
						  sgender="female";
					
					DBHandler3.addStaff(tname.getText(),tphone.getText(),Date.valueOf(ddob.getValue()),ppassword.getText(),sgender,crole.getValue().toString());
					setNull();
				}				
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
		});
		btnClear=new Button("Clear");
			
			
		
			
	} 
	public void defineLayout()
	{
	    namePane=new GridPane();
	    namePane.add(tname, 0, 0);
	    namePane.add(lnameError, 0, 1);

	    phonePane=new GridPane();
	    phonePane.add(tphone, 0, 0);
	    phonePane.add(lphoneError, 0, 1);

	    dobPane=new GridPane();
	    dobPane.add(ddob, 0, 0);
	    dobPane.add(ldobError, 0, 1);

	    passwordPane=new GridPane();
	    passwordPane.add(ppassword, 0, 0);
	    passwordPane.add(tpassword, 0, 0);
	    passwordPane.add(lshow, 1, 0);
	    passwordPane.add(lhide, 1, 0);
	    passwordPane.add(lpasswordError, 0, 1);
	    
	    forgotPasswordPane=new GridPane();
	    forgotPasswordPane.add(lforgotPassword, 0, 0);
	    forgotPasswordPane.setAlignment(Pos.CENTER);
	    forgotPasswordPane.setPadding(new Insets(0,0,50,0));

	    repasswordPane=new GridPane();
	    repasswordPane.add(prepassword, 0, 0);
	    repasswordPane.add(trepassword, 0, 0);
	    repasswordPane.add(lrepasswordError, 0, 1);

	    headerPane=new GridPane();
	    headerPane.add(lheader, 0, 0);
	    headerPane.setAlignment(Pos.BOTTOM_CENTER);

	    genderPane=new TilePane(20,20,rmale,rfemale);
	    genderPane.setPadding(new Insets(0,0,30,0));
	    
	    genPane=new GridPane();
	    genPane.add(rmale, 0, 0);
	    genPane.add(rfemale, 1, 0);
	    genPane.setPadding(new Insets(20,0,30,0));
	    genPane.setHgap(20);
	    
	    rolePane=new GridPane();
	    rolePane.add(crole, 0, 0);
	    rolePane.add(lroleError, 0, 1);

	    buttonPane=new GridPane();
	    buttonPane.add(btnClear, 0, 0);
	    buttonPane.add(btnRegister, 1, 0);
	    buttonPane.setHgap(20);
	    buttonPane.setPadding(new Insets(30,0,0,0));

	    gp1=new GridPane();
	    gp1.add(lname, 0, 0);
	    gp1.add(lphone, 0, 1);
	    gp1.add(ldob, 0, 2);
	    gp1.add(lgender, 0, 3);
	    gp1.add(lpassword, 0, 4);
	    gp1.add(lrepassword, 0, 5);
	    gp1.add(lrole, 0, 6);
	    gp1.setVgap(42);
	    gp1.setPadding(new Insets(0,0,90,0));
	    gp1.setAlignment(Pos.CENTER);

	    gp2=new GridPane();
	    gp2.add(namePane, 0, 0);
	    gp2.add(phonePane, 0, 1);
	    gp2.add(dobPane, 0, 2);
	    gp2.add(genPane, 0, 3);
	    gp2.add(passwordPane, 0, 4);
	    gp2.add(repasswordPane, 0,5);
	    gp2.add(rolePane, 0, 6);
	    gp2.add(buttonPane, 0, 7);
	    gp2.setAlignment(Pos.CENTER);
	    gp2.setVgap(10);

	    registerPane=new GridPane();
	    registerPane.add(gp1,0,0);
	    registerPane.add(gp2,1,0);
	    registerPane.setPadding(new Insets(20));
	    registerPane.setHgap(30);
	    registerPane.setAlignment(Pos.CENTER);

	    mainPane=new BorderPane();
	    mainPane.setCenter(registerPane);
	    mainPane.setBottom(forgotPasswordPane);
	}
	public void setNull()
	{
		tname.setText(null);
		tphone.setText(null);
		ddob.setValue(null);
		ppassword.setText(null);
		prepassword.setText(null);
		
	}
	public void setStyle()
	{
		lheader.setId("headerRegister");
		mainPane.setId("mainpaneRegister");
		buttonPane.setId("buttonpaneRegister");
		btnRegister.setId("btnregisterRegister");
//		btnClear.setId("btnclearRegister");
		registerPane.setId("registerpaneRegister");
		
		lnameError.getStyleClass().add("errorRegister");
		lphoneError.getStyleClass().add("errorRegister");
		lpasswordError.getStyleClass().add("errorRegister");
		lrepasswordError.getStyleClass().add("errorRegister");
		lroleError.getStyleClass().add("errorRegister");
		ldobError.getStyleClass().add("errorRegister");
		
		lforgotPassword.setId("forgotPassword");

		tname.getStyleClass().add("textFieldRegiter");
		tphone.getStyleClass().add("textFieldRegiter");
		ppassword.getStyleClass().add("textFieldRegiter");
		prepassword.getStyleClass().add("textFieldRegiter");
		tpassword.getStyleClass().add("textFieldRegiter");
		trepassword.getStyleClass().add("textFieldRegiter");
		
		
		
		
	}
	public BorderPane getMainPane()
	{
		createNodes();
		defineLayout();
		setStyle();
		
		return mainPane;
	}
	public boolean checkData() {
	    boolean isValid = true;

	    if (tname.getText().isEmpty()) {
	        lnameError.setText("Fill Name!");
	        isValid = false;
	    } 
	    else if(!Checker.isValidName(tname.getText()))
	    {
	    	lnameError.setText("Wrong Format!(eg.Min Aung Hlaing)");
	    	isValid=false;
	    }
	    else {
	        lnameError.setText(null);
	    }

	    if (tphone.getText().isEmpty()) {
	        lphoneError.setText("Fill Phone Number!");
	        isValid = false;
	    } else if (!Checker.isValidPh(tphone.getText())) {
	        lphoneError.setText("Phone number must be numbers!(eg.09444200661)");
	        isValid = false;
	    } else {
	        lphoneError.setText(null);
	    }

	    if (ppassword.getText().isEmpty()) {
	        lpasswordError.setText("Fill Password!");
	        isValid = false;
	    } else if (!Checker.isValidPw(ppassword.getText())) {
	        lpasswordError.setText("Wrong Format!");
	        isValid = false;
	    } else {
	        lpasswordError.setText(null);
	    }

	    if (prepassword.getText().isEmpty()) {
	        lrepasswordError.setText("Fill Repassword!");
	        isValid = false;
	    } else if (!Checker.isValidPw(prepassword.getText())) {
	        lrepasswordError.setText("Wrong Format!");
	        isValid = false;
	    }
	    else if(!prepassword.getText().equals(ppassword.getText()))
	    {
	    	lrepasswordError.setText("Repassword does not match!");
	    	isValid=false;
	    }
	    else {
	        lrepasswordError.setText(null);
	    }
	    
	    
	    if(ddob.getValue()==null)
	    {
	    	ldobError.setText("Fill Date Of Birth!");
	    	isValid=false;
	    }
	    else if(!Checker.isValidDOB(ddob.getValue()))
	    {
	    	ldobError.setText("Wrong Data!Date of birth must be over 16");
	    	isValid=false;
	    }
	    else {
	    	ldobError.setText(null);
	    }
	    
	    
	    if(crole.getValue()==null)
	    {
	    	lroleError.setText("Select Role!");
	    	isValid=false;
	    }
	    else {
	    	lroleError.setText(null);
	    }

	    // Additional validation logic for password match

	    return isValid;
	}
	public void newStage(BorderPane bp) throws Exception {
		

		mainPaneAdd=new BorderPane();
		mainPaneAdd.setCenter(bp);
		
		Scene sc=new Scene(mainPaneAdd);
		
		
		addStage=new Stage();
		
		addStage.setWidth(500);
		addStage.setHeight(300);
		addStage.setResizable(false);
		addStage.setScene(sc);
		addStage.initModality(Modality.APPLICATION_MODAL);
//		addStage.initStyle(StageStyle.UNDECORATED);
		addStage.showAndWait();
		 

	}
	public void start(Stage st) throws Exception {
		
		createNodes();
		defineLayout();
		setStyle();
		
		Scene sc=new  Scene(mainPane,700,600);
		URL url = this.getClass ().getResource ("mystyleregister.css"); 
		sc.getStylesheets().add(url.toExternalForm());
		
		st.setScene(sc);
		st.setTitle("Shop Management System");
		st.show();
		
		
	}
	public static void main(String[] args) {
		Application.launch(args);

	}

}
