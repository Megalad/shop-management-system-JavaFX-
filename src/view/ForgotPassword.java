package view;

import java.net.URL;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.DBHandler3;

public class ForgotPassword extends Application{

	private Label lname,lphone,lnewPassword,lnameError,lphoneError,lpasswordError,lerror;
	private Button btnChange,btnCancel;
	private TextField tname,tphone,tnewPassword;
	private PasswordField pnewPassword;
	
	private GridPane namePane,phonePane,passwordPane,centerPane,buttonPane,labelPane,textFieldPane,topPane;
	private BorderPane mainPane;
	
	public void createNode()
	{
		lname=new Label("Name : ");
		lphone=new Label("Phone : ");
		lnewPassword=new Label("New-Password : ");
		
		lnameError=new Label();
		lphoneError=new Label();
		lpasswordError=new Label();
		
		tname=new TextField();
		tphone=new TextField();
		pnewPassword=new PasswordField();
		
		lerror=new Label();
		
		
		
		
		
		
		btnChange=new Button("Change");
		btnChange.setOnAction(e->{
			try {
				if(checkData())
				{
					int staffID=DBHandler3.getStaffID(tname.getText(), tphone.getText());
					
					if(staffID==-1)
					{
						lerror.setText("Check Name And Password Again!");
					}
					else {
						lerror.setText(null);
						DBHandler3.updatePassword(staffID,pnewPassword.getText());
						Checker.alertMessage("Password Update", "Password updated successfully!");
					}
				}
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
		});
		btnCancel=new Button("Cancel");
		btnCancel.setOnMouseClicked(e->{
			 Stage stage = (Stage) btnCancel.getScene().getWindow();
			 stage.close();
		});
		
	}
	public void defineLayout()
	{
		
		namePane=new GridPane();
		namePane.add(tname, 0, 0);
		namePane.add(lnameError, 0, 1);
		
		phonePane=new GridPane();
		phonePane.add(tphone, 0, 0);
		phonePane.add(lphoneError, 0, 1);
		
		passwordPane=new GridPane();
		passwordPane.add(pnewPassword, 0, 0);
		passwordPane.add(lpasswordError, 0, 1);
		
		buttonPane=new GridPane();
		buttonPane.add(btnCancel, 0, 0);
		buttonPane.add(btnChange, 1, 0);
		buttonPane.setHgap(20);
		
		labelPane=new GridPane();
		labelPane.add(lname, 0, 0);
		labelPane.add(lphone,0, 1);
		labelPane.add(lnewPassword, 0, 2);
		labelPane.setVgap(40);
		
		textFieldPane=new GridPane();
		textFieldPane.add(namePane, 0, 0);
		textFieldPane.add(phonePane, 0, 1);
		textFieldPane.add(passwordPane, 0, 2);
		textFieldPane.add(buttonPane, 0, 3);
		textFieldPane.setVgap(10);
		
		topPane=new GridPane();
		topPane.add(lerror, 0, 0);
		topPane.setPadding(new Insets(20,0,0,0));
		topPane.setAlignment(Pos.CENTER);
		
		
		centerPane=new GridPane();
		centerPane.add(labelPane, 0, 0);
		centerPane.add(textFieldPane, 1, 0);
		centerPane.setHgap(20);
		centerPane.setAlignment(Pos.CENTER);
		
		mainPane=new BorderPane();
		mainPane.setTop(topPane);
		mainPane.setCenter(centerPane);
		
	}
	public boolean checkData()
	{
		boolean isValid=true;
		
		if(tname.getText().isEmpty())
		{
			lnameError.setText("Fill Staff Name!");
			isValid=false;
		}
		else {
			lnameError.setText(null);
		}
		
		
		if(tphone.getText().isEmpty())
		{
			lphoneError.setText("Fill Phone Number!");
			isValid=false;
		}
		else {
			lphoneError.setText(null);
		}
		
//		if(DBHandler3.getStaffID(tname.getText(), tphone.getText())==-1)
//		{
//			lerror.setText("Check name and phone number again!");
//			isValid=false;
//		}
		if(pnewPassword.getText().isEmpty())
		{
			lpasswordError.setText("Fill New Password!");
			isValid=false;
		}
		else if(!Checker.isValidPw(pnewPassword.getText()))
		{
			lpasswordError.setText("Wrong Format!");
			isValid=false;
		}
		else {
			lpasswordError.setText(null);
		}
		
		
		
		return isValid;
		
	}
	
	public void setStyle()
	{
		lnameError.getStyleClass().add("errorForgot");
		lphoneError.getStyleClass().add("errorForgot");
		lpasswordError.getStyleClass().add("errorForgot");
		lerror.getStyleClass().add("errorForgot");
	}

	public BorderPane getMainPage()
	{
		createNode();
		defineLayout();
		setStyle();
		
		return mainPane;
	}
	
	@Override
	public void start(Stage st) throws Exception {
		createNode();
		defineLayout();
		setStyle();
		
		Scene sc=new  Scene(mainPane,350,350);
		URL url = this.getClass ().getResource ("mystyle.css"); 
		sc.getStylesheets().add(url.toExternalForm());
		
		st.setScene(sc);
		st.setTitle("Register");
		st.show();
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}
