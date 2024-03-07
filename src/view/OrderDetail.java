package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

public class OrderDetail extends Application{
	
	private Label lorderNumber,lorderNo,ldate,lquantity,lproductName,lvoucher,ldiscountAmount,ltotalAmount,lpaymentStatus,ltotalProduct,lsearch,lcustomerName,lphone,lplus;
	private TableView productTv,voucherTv;
	private TableColumn pidCol,pnameCol,ppriceCol,pquantityCol;
	
	
	private Label ltrash,ladd;
	private DatePicker ddate;
	private ComboBox<String> cstatus;
	private ComboBox<Color> ccolor;
	private ComboBox<Size> csize;
	private Button btnAdd,btnSave,btnPrint,btnCancel;
	private Spinner<Integer> squantity;
	private MenuItem editMI,deleteMI;
	
	private ContextMenu rightClick;
	
	private ArrayList<Size> sizes;
	
	private TextField tname,tquantity,ttotal,tdiscountAmount,ttotalAmount,ttotalProduct,tsearch;
	private static TextField tproductName;
	private TextField tcustomerName;
	private TextField tphone;
	private HBox discountHBox,statusHBox,totalProductHBox,totalAmountHBox,savePrintHBox,productNameHBox,searchHBox;
	private VBox vvoucher,vcenterRight;
	
	private GridPane centerPane,topPane,centerRight,buttonPane,gp1,gp2,gp3,gp4,gp5,gp6,gp7,gp8;
	
	private BorderPane mainPane,mainPaneItemList;
	private Stage itemListStage;
	
	int currentOrderNumber;
	
	public void createNodes()
	{
		FileInputStream fis;
		
		lorderNumber=new Label("Order Number : ");
		//get order last number
		currentOrderNumber = DBHandler3.getOrderNumber();
		System.out.print(currentOrderNumber);
		String nextOrderNumber = Integer.toString(currentOrderNumber);
	
		lorderNo = new Label(nextOrderNumber);
		
		ldate=new Label("Date : ");
		lquantity=new Label("Quantity : ");
		
		lproductName=new Label("Product Name : ");
		
		lsearch=new Label("Search : ");
		
		lplus=new Label();
		try {
			fis=new FileInputStream("img/icon/setting.png");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			lplus=new Label("",imgView);
			lplus.setPrefSize(195,0);
			
			lplus.setCursor(Cursor.HAND);
		}catch (FileNotFoundException e) {
			lplus=new Label("Shwe Nan Htet");
		}
		
		
		ldiscountAmount=new Label("Discount Amount : ");
		ltotalProduct=new Label("Total Product : ");
		ltotalAmount=new Label("Total Amount : ");
		lpaymentStatus=new Label("Payment Status : ");
		
		lvoucher=new Label("Voucher Table");
	
		lcustomerName=new Label("Customer Name : ");
		lphone=new Label("Phone : ");
		
		tname=new TextField();
		
	
		tquantity=new TextField();
		ttotal=new TextField();
		ttotal.setDisable(true);
		
		
		tdiscountAmount=new TextField();
		tdiscountAmount.setOnKeyPressed(e->{
			if(tdiscountAmount.getText()!=null && e.getCode()==KeyCode.ENTER)
			{
				if(tdiscountAmount.getText()!=null)
				{
					try {
						int discountAmount=Integer.parseInt(tdiscountAmount.getText());
						DBHandler3.insertOrderDetailDiscount(squantity.getValue(),discountAmount,currentOrderNumber,tproductName.getText());
						refresh();
						setNull();
						
						 Alert alert = new Alert(AlertType.INFORMATION);
				         alert.setTitle("Confirm");
				         alert.setHeaderText("Item has been added successfully!");
				         alert.showAndWait();
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
					
				}
			}
		});
//		ArrayList<Item> al=DBHandler3.getItemList(currentOrderNumber);
		ttotalAmount=new TextField();
		ttotalAmount.setEditable(false);
		ttotalAmount.setText(Integer.toString(DBHandler3.getTotalAmountOrderDetail(currentOrderNumber)));
		
		
		ttotalProduct=new TextField();
		ttotalProduct.setEditable(false);
		ttotalProduct.setText(Integer.toString(DBHandler3.getTotalProduct(currentOrderNumber)));
		
		tsearch=new  TextField();

		
		tsearch.setPrefWidth(100);
		tsearch.setPrefHeight(10);
		
		tcustomerName=new TextField(DBHandler3.getCustomerName(currentOrderNumber));
		tcustomerName.setEditable(false);
//		tcustomerName.setFocused(false);
		
		tphone=new TextField(DBHandler3.getCustomerPhone(currentOrderNumber));
		tphone.setEditable(false);
		
		squantity=new Spinner(1,1000,1);
		squantity.getValueFactory ().wrapAroundProperty().set(true);
		squantity.setEditable(true);
	    	
		tproductName=new TextField();
		tproductName.setOnKeyPressed(e->{
			
			if(e.getCode()==KeyCode.ENTER)
			{
				
				try {
					itemListStage(new ItemList().getMainPane(tproductName.getText()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
//		autoCompleteSearch(tproductName);
		
		ddate=new DatePicker();
		
		ArrayList<String> al=new ArrayList<String>();
		al.add("--select--");
		al.add("Paid");
		al.add("Not Paid");
		
		cstatus=new ComboBox<String>(FXCollections.observableArrayList(al));
		cstatus.getSelectionModel().select(0);
		
		editMI=new MenuItem("Edit");
		editMI.setOnAction(e->{
			
		});
		
		deleteMI=new MenuItem("Delete");
		deleteMI.setOnAction(e->{
			try {
				
				Item selectedItem=(Item) voucherTv.getSelectionModel().getSelectedItem();
				DBHandler3.deleteItemFromOrderdetail(currentOrderNumber,selectedItem.getItemName());
				

				
				
				refresh();
				
				Alert alert = new Alert(AlertType.INFORMATION);
		         alert.setTitle("Confirm");
		         alert.setHeaderText("deleted successfully!");
		         alert.showAndWait();
				
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			
		});
		
		rightClick=new ContextMenu();
		rightClick.getItems().addAll(editMI,deleteMI);
		
		
		btnSave=new Button("Save");
		btnSave.setCursor(Cursor.HAND);
		btnSave.setOnAction(e->{
			
			try {
				mainPane.setCenter(new OrderList().getMainPane());
				mainPane.setTop(null);
				
				MainPage.lpath.setText("Main Page  -> Order ");
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
			
			
			
		});
		
		btnPrint=new Button("Print");
		btnPrint.setCursor(Cursor.HAND);
		
		
		btnCancel=new Button("Cancel");
		btnCancel.setCursor(Cursor.HAND);
		btnCancel.setOnAction(e->{
			try {
				DBHandler3.deleteOrdersFromOrderdetail(currentOrderNumber);
				mainPane.setCenter(new OrderList().getMainPane());
				mainPane.setTop(null);
				
				MainPage.lpath.setText("Main Page  -> Order ");
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
			
		});
		
		btnAdd=new Button("Add to Voucher");
		btnAdd.setCursor(Cursor.HAND);
		
		btnAdd.setOnAction(e->{
			if(tdiscountAmount.getText()!=null)
			{
				try {
					int discountAmount=Integer.parseInt(tdiscountAmount.getText());
					DBHandler3.insertOrderDetailDiscount(squantity.getValue(),discountAmount,currentOrderNumber,tproductName.getText());
					refresh();
					setNull();
					
					 Alert alert = new Alert(AlertType.INFORMATION);
			         alert.setTitle("Confirm");
			         alert.setHeaderText("Item has been added successfully!");
			         alert.showAndWait();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				
			}
			
		});
		
		
	}
	public static void setProductName(String itemName)
	{
		try {
			tproductName.setText(itemName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setColorComboBox(String productName)
	{
		
		
		ArrayList<Color> colors;
		colors=DBHandler3.getAvailableColor(productName);
		
		ccolor.getItems().clear();
		
		ccolor.getItems().addAll(colors);
	
		ccolor.getSelectionModel().select(0);
		Color selectedCategory = (Color) ccolor.getValue();
        String colorName = selectedCategory.getColorName();
		
		setSizeComboBox(productName,colorName);
	}
	public void setSizeComboBox(String productName,String colorName)
	{
		
		
		ArrayList<Size> sizes;
		
		sizes=DBHandler3.getAvailableSize(productName,colorName);
		
		csize.getItems().clear();
		
		csize.getItems().addAll(sizes);
	
		csize.getSelectionModel().select(0);
	}
	public static void autoCompleteSearch(TextField tf)
	{
		ArrayList<String> suggestions = new ArrayList<String>();
	    suggestions = DBHandler3.getMatchingItemNames();
	    TextFields.bindAutoCompletion(tf, suggestions);
	}
	public void defineLayout()
	{
		
		
		
		discountHBox=new HBox(ldiscountAmount,tdiscountAmount);
		statusHBox=new HBox(lpaymentStatus,cstatus);
		totalProductHBox=new HBox(ltotalProduct,ttotalProduct);
		totalAmountHBox=new HBox(ltotalAmount,ttotalAmount);
		productNameHBox=new HBox(lproductName,tproductName);
		searchHBox=new HBox(lsearch,tsearch);
		searchHBox.setAlignment(Pos.TOP_LEFT);
		
//		vproduct=new VBox(searchHBox,productTv);
		vvoucher=new VBox(lvoucher,voucherTv);
		vvoucher.setAlignment(Pos.TOP_LEFT);
	
		savePrintHBox=new HBox(btnSave,btnPrint);
		savePrintHBox.setMargin(btnSave, new Insets(10));
		savePrintHBox.setMargin(btnPrint, new Insets(10));
		
		buttonPane=new GridPane();
		buttonPane.add(savePrintHBox, 0, 0);
		buttonPane.add(btnCancel, 1, 0);
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setHgap(10);
		
		vcenterRight=new VBox(discountHBox,totalProductHBox,statusHBox,totalAmountHBox,buttonPane);
		vcenterRight.setMargin(discountHBox, new Insets(10));
		vcenterRight.setMargin(totalProductHBox, new Insets(10));
		vcenterRight.setMargin(statusHBox, new Insets(10));
		vcenterRight.setMargin(totalAmountHBox, new Insets(10));
		vcenterRight.setMargin(buttonPane, new Insets(10));
		vcenterRight.setPadding(new Insets(50,0,0,0));
		vcenterRight.setAlignment(Pos.CENTER);
		
		
		centerRight=new GridPane();
		centerRight.add(ltotalProduct, 0, 0);
		centerRight.add(ttotalProduct, 0, 1);
		centerRight.add(ltotalAmount, 0, 2);
		centerRight.add(ttotalAmount, 0, 3);
		centerRight.add(buttonPane, 0, 4);
		
		centerRight.setVgap(10);
		centerRight.setPadding(new Insets(40,0,0,0));
		centerRight.setVgap(10);
//		centerRight.setAlignment(Pos.CENTER);

		gp3=new GridPane();
		gp3.add(lorderNumber, 0, 0);
		gp3.add(lproductName, 0, 1);
		gp3.setVgap(30);
		
		gp2=new GridPane();
		gp2.add(tproductName, 0, 0);
//		gp2.add(lplus, 1, 0);
		
		gp4=new GridPane();
		gp4.add(lorderNo, 0, 0);
		gp4.add(gp2, 0, 1);
		gp4.setVgap(30);
		
		
		gp5=new GridPane();
		gp5.add(lcustomerName, 0, 0);
		gp5.add(lquantity, 0, 1);
		gp5.setVgap(30);
		
		
		gp6=new GridPane();
		gp6.add(tcustomerName, 0, 0);
		gp6.add(squantity, 0, 1);
		gp6.setVgap(20);
		
		gp7=new GridPane();
		gp7.add(lphone, 0, 0);
		gp7.add(ldiscountAmount, 0, 1);
		gp7.setVgap(30);
		
		
		gp8=new GridPane();
		gp8.add(tphone, 0, 0);
		gp8.add(tdiscountAmount, 0, 1);
		gp8.add(btnAdd, 0, 2);
		gp8.setVgap(20);
		
		
		topPane=new GridPane();
		topPane.add(gp3, 0, 0);
		topPane.add(gp4, 1, 0);
		topPane.add(gp5, 2, 0);
		topPane.add(gp6, 3, 0);
		topPane.add(gp7, 4, 0);
		topPane.add(gp8, 5, 0);
		topPane.setAlignment(Pos.CENTER);
		topPane.setHgap(20);
		
		
		centerPane=new GridPane();
		centerPane.add(vvoucher, 0, 0);
		centerPane.add(centerRight, 1, 0);
		centerPane.setHgap(30);
		centerPane.setAlignment(Pos.CENTER);
		
		mainPane=new BorderPane();
		mainPane.setTop(topPane);
//		mainPane.setCenter(new OrderList().getMainPane());
		mainPane.setCenter(centerPane);
	
		
	}
	public void setData()
	{
		int orderNumber = DBHandler.getOrderNumber();
	    lorderNo.setText(Integer.toString(orderNumber));
	}
	public void createProductTable()
	{
		productTv=new TableView();
		
		pidCol=new TableColumn("Id");
		pnameCol=new TableColumn("Name");
		ppriceCol=new TableColumn("Price");
		pquantityCol=new TableColumn("Quantity");
		
		productTv.getColumns().add(pidCol);
		productTv.getColumns().add(pnameCol);
		productTv.getColumns().add(ppriceCol);
		productTv.getColumns().add(pquantityCol);
		productTv.setPrefWidth(500);
		productTv.setPrefHeight(200);
			
		
	}
	public void createVoucherTable()
	{
		voucherTv=new TableView();
		voucherTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
//		vnoCol=new TableColumn("No");
		
		TableColumn<Item,String> vnameCol=new TableColumn<Item,String>("Name");
		vnameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("itemName"));
		
		
		TableColumn<Item,Integer> vquantityCol=new TableColumn<Item,Integer>("Quantity");
		vquantityCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("quantity"));
		
		TableColumn<Item,Integer> vdiscountCol=new TableColumn<Item,Integer>("Discount");
		vdiscountCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("discountAmount"));
		
		TableColumn<Item,Integer> vsellPriceCol=new TableColumn<Item,Integer>("Sell Price");
		vsellPriceCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("sellPrice"));
		
		TableColumn<Item,Integer> vtotalPriceCol=new TableColumn<Item,Integer>("Total");
		vtotalPriceCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("totalPrice"));
		
		voucherTv.getColumns().add(vnameCol);
		voucherTv.getColumns().add(vquantityCol);
		voucherTv.getColumns().add(vdiscountCol);
		voucherTv.getColumns().add(vsellPriceCol);
		voucherTv.getColumns().add(vtotalPriceCol);
		
		voucherTv.setPrefWidth(800);
		voucherTv.setPrefHeight(530);
		
		voucherTv.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.SECONDARY)
			{
				rightClick.show(voucherTv, e.getScreenX(), e.getScreenY());
			}
			
		});
		
		
		
		
	}
	public void setDataForVoucherTable()
	{
		ArrayList<Item> al=DBHandler3.getItemList(currentOrderNumber);
		voucherTv.getItems().addAll(al);
		ttotalAmount.setText(STYLESHEET_CASPIAN);
	}
	public void refresh()
	{
		voucherTv.getItems().clear();
		setDataForVoucherTable();
		
		ttotalAmount.setText(Integer.toString(DBHandler3.getTotalAmountOrderDetail(currentOrderNumber)));
		ttotalProduct.setText(Integer.toString(DBHandler3.getTotalProduct(currentOrderNumber)));
	}
	public void setNull()
	{
		tproductName.setText(null);
		squantity.getValueFactory().setValue(0);
		tdiscountAmount.setText(null);
	}
	public void itemListStage(BorderPane bp) throws Exception{
		
		mainPaneItemList=new BorderPane();
		mainPaneItemList.setCenter(bp);
		
		Scene sc=new Scene(mainPaneItemList);
		
		itemListStage=new Stage();
		itemListStage.setWidth(800);
		itemListStage.setHeight(500);
		itemListStage.setScene(sc);
		itemListStage.initModality(Modality.APPLICATION_MODAL);
		itemListStage.showAndWait();
		
	}
	public void setStyle()
	{
		
		topPane.setId("toppaneOrder");
		centerPane.setId("centerpaneOrder");
		btnAdd.setId("btnaddvouncherOrder");
		btnSave.setId("btnsaveOrder");
		btnPrint.setId("btnprintOrder");
		btnCancel.setId("btncancelOrder");
		
		lorderNo.setId("ordernumberOrder");
		
		lvoucher.getStyleClass().add("titleOrder");
		
		btnSave.getStyleClass().add("buttonOrder");
		btnPrint.getStyleClass().add("buttonOrder");
		btnCancel.getStyleClass().add("buttonOrder");
		
		
	}
	public BorderPane getMainPane()
	{
		createNodes();
		createProductTable();
		createVoucherTable();
		
		defineLayout();
		setDataForVoucherTable();
		
		setStyle();
		
		return mainPane;
	}
	public void start(Stage st) throws Exception {
		
		createNodes();
		createProductTable();
		createVoucherTable();
		
		defineLayout();
		setDataForVoucherTable();
		
		setStyle();
		
		Scene sc=new Scene(mainPane,1200,1000);
		URL url = this.getClass ().getResource ("mystyle.css"); 
		sc.getStylesheets().add(url.toExternalForm());
		
		st.setScene(sc);
		st.setTitle("Order");
		st.show();
		
	}
	public static void main(String[] args) {
		Application.launch(args);

	}

}
