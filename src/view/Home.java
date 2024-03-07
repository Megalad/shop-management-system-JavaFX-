package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Budget;
import model.Credit;
import model.DBHandler;
import model.DBHandler3;
import model.Item;

public class Home{
	
	private Label ldashBoard,lbestSellingItem,ldailyAmount,lhistory,lcreditSystem,lreport,lprofit,lnoOfItem,ltotalStaff,litemQuantityReport;
	private Label litem,lamount,lprofitAmount,ltotalItem,lnoOfStaff;
	private Label  litemLogo,lamountLogo,lprofitLogo,ltotalItemLogo,lstaffLogo;
	
	private TableView creditTv,reportTv;
	private TableView<Item> itemTv;
	private TableColumn tid,tname,tamount,tdate,tnote;
	private TableColumn trno,trdescription,trdate;
	
	private BorderPane mainPane;
	private GridPane amountPane,itemPane,profitPane,totalItemPane,staffPane,itemTvPane;
	private GridPane dashPane,creditPane,chartPane,reportPane,centerPane;
	private BarChart bc;
	private LineChart lc;
	private ArrayList<Credit> cal;
	private FileInputStream fis;
	
	private PieChart bestSellingItemPieChart;
	private BarChart<String, Number>  barChart;
	
	
	private ImageView imgView;
//	private GridPane amountPane,itemPane;
	
	public void createNodes()
	{	
		
		
		ldashBoard=new Label("Dash Board");
		lcreditSystem=new Label("Credit System");
		lhistory=new Label("History");
		lreport=new Label("Report");
		
		litemQuantityReport=new Label("Quantity Report");
		lprofit=new Label("Today Profit");
		try {
			fis=new FileInputStream("img/icon/payment.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			MainPage.setImgViewSize(imgView);
			lprofitLogo=new Label("",imgView);
			
		}catch (FileNotFoundException e) {
			lprofitLogo=new Label("Profit Logo");
		}
//		lprofitAmount=new  Label(Integer.toString(DBHandler3.getTodayProfit())+" Ks");
		lprofitAmount=new  Label(""+DBHandler3.getTodayProfit()+" Ks");
		lprofitAmount.setAlignment(Pos.CENTER);
		
		lnoOfItem=new Label("Total Item");
		try {
			fis=new FileInputStream("img/icon/product.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			MainPage.setImgViewSize(imgView);
			ltotalItemLogo=new Label("",imgView);
			
		}catch (FileNotFoundException e) {
			ltotalItemLogo=new Label("Total Item");
		}
		ltotalItem=new Label(DBHandler3.getTotalNumberOfItem());
		ltotalItem.setAlignment(Pos.CENTER);
		
		
		lbestSellingItem=new Label("Best Selling Item");
		
		try {
			fis=new FileInputStream("img/icon/winner.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			MainPage.setImgViewSize(imgView);
			litemLogo=new Label("",imgView);
			
		}catch (FileNotFoundException e) {
			litemLogo=new Label("Item Logo");
		}
		litem=new Label(DBHandler3.getBestSellingItem());
		litem.setAlignment(Pos.CENTER);
		
		
		ldailyAmount=new Label("Today Amount");
		try {
			fis=new FileInputStream("img/icon/money.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			MainPage.setImgViewSize(imgView);
			lamountLogo=new Label("",imgView);
			
		}catch (FileNotFoundException e) {
			litemLogo=new Label("Item Logo");
		}
		lamount=new Label(Integer.toString(DBHandler3.getTodayAmount())+ " Ks");
		lamount.setAlignment(Pos.CENTER);
		
		
		ltotalStaff=new Label("Total Staff");
		try {
			fis=new FileInputStream("img/icon/teamwork.png");
			Image img=new Image(fis);
			imgView=new ImageView(img);
			MainPage.setImgViewSize(imgView);
			lstaffLogo=new Label("",imgView);
			
		}catch (FileNotFoundException e) {
			lstaffLogo=new Label("Staff Logo");
		}
		lnoOfStaff=new Label(""+DBHandler3.getTotalNumberOfStaff());
		lnoOfStaff.setAlignment(Pos.CENTER);
		
		itemTv=new Report().getItemReportTable(800,400);
		
		
		
	}
	public void defineLayout()
	{
		amountPane=new GridPane();
		amountPane.add(lamount, 1, 0);
		amountPane.add(lamountLogo, 0, 1);
		amountPane.add(ldailyAmount, 1, 1);
		amountPane.setMargin(ldailyAmount, new Insets(10));
//		amountPane.setEffect(new BoxBlur(5, 5, 3));
		
		
		itemPane=new GridPane();
		itemPane.add(litem, 1, 0);
		itemPane.add(litemLogo, 0, 1);
		itemPane.add(lbestSellingItem, 1, 1);
		amountPane.setMargin(lbestSellingItem, new Insets(10));
		
		
		profitPane=new GridPane();
		profitPane.add(lprofitAmount, 1, 0);
		profitPane.add(lprofitLogo, 0, 1);
		profitPane.add(lprofit, 1, 1);
		amountPane.setMargin(lprofit, new Insets(10));
		
		totalItemPane=new GridPane();
		totalItemPane.add(ltotalItem, 1, 0);
		totalItemPane.add(lnoOfItem, 1, 1);
		totalItemPane.add(ltotalItemLogo, 0, 1);
		amountPane.setMargin( ltotalItemLogo,new Insets(10));
		
		
		
		staffPane=new GridPane();
		staffPane.add(lnoOfStaff, 1, 0);
		staffPane.add(lstaffLogo, 0, 1);
		staffPane.add(ltotalStaff, 1, 1);
		amountPane.setMargin(ltotalStaff, new Insets(10));
		
		
		
		dashPane=new GridPane();
		dashPane.add(ldashBoard, 0, 0);
		dashPane.add(amountPane, 0, 1);
		dashPane.add(itemPane, 1, 1);
		dashPane.add(profitPane, 2, 1);
		dashPane.add(totalItemPane, 3, 1);
		dashPane.add(staffPane, 4, 1);
		
		dashPane.setHgap(50);
		dashPane.setVgap(10);
		dashPane.setPadding(new Insets(20,10,20,10));
		
		showPieChart();
		
		itemTvPane=new GridPane();
		itemTvPane.add(litemQuantityReport, 0, 0);
		itemTvPane.add(bestSellingItemPieChart, 0, 1);
		itemTvPane.setVgap(20);
		itemTvPane.setPadding(new Insets(10));
		
		
		
		centerPane=new GridPane();
//		centerPane.add(itemTvPane, 0, 0);
		centerPane.add(bestSellingItemPieChart, 0, 0);
//		centerPane.add(creditPane, 1,0);
		centerPane.add(barChart, 1, 0);
		centerPane.setHgap(20);
		
		
		
		mainPane=new BorderPane();
		mainPane.setTop(dashPane);
		mainPane.setCenter(centerPane);
	}
	
	public void createLinePane()
	{
		CategoryAxis xa=new CategoryAxis();
		xa.setLabel("Month");
		
		NumberAxis ya=new NumberAxis();
		ya.setLabel("Income");
		
		lc=new LineChart<>(xa,ya);
		XYChart.Series<String,Integer> year=new Series<>();
		year.setName("2023");
		year.getData().add(new XYChart.Data("January",10000000));
		year.getData().add(new XYChart.Data("February",21054333));
		year.getData().add(new XYChart.Data("March",21300000));
		year.getData().add(new XYChart.Data("April",71000000));
		year.getData().add(new XYChart.Data("May",50000000));
		year.getData().add(new XYChart.Data("June",20000000));
		year.getData().add(new XYChart.Data("July",12000000));
		year.getData().add(new XYChart.Data("August",70000));
		year.getData().add(new XYChart.Data("September",30000009));
		year.getData().add(new XYChart.Data("October",3000000));
		year.getData().add(new XYChart.Data("November",60000000));
		year.getData().add(new XYChart.Data("Decembe",30000000));
		
		lc.getData().add(year);
		
		lc.setPrefWidth(600);
		lc.setPrefHeight(400);
		
		chartPane=new GridPane();
		chartPane.add(lhistory, 0, 0);
		chartPane.add(lc,0,1);
		chartPane.setHgap(90);
		chartPane.setVgap(0);
		
	}
	public void showPieChart()
	{
		try {
			bestSellingItemPieChart=new PieChart();
			ArrayList<Item> itemAl=DBHandler3.getBestSellingTop6ItemCurrentMonth();
			
			for (Item items : itemAl) {
				PieChart.Data slice = new PieChart.Data(items.getItemName(), items.getQuantity());
	            bestSellingItemPieChart.getData().add(slice);
	    
	        }
			
			 
			bestSellingItemPieChart.setPrefWidth(390);
			bestSellingItemPieChart.setPrefHeight(390);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}
	public void showBarChart()
	{
		 CategoryAxis xAxis = new CategoryAxis();
	     NumberAxis yAxis = new NumberAxis();

	        // Create the bar chart
	        barChart = new BarChart<>(xAxis, yAxis);
	        barChart.setTitle("Income vs. Expense");
	        xAxis.setLabel("Months");
	        yAxis.setLabel("Amount");

	        // Create the series for income and expense
	        XYChart.Series<String, Number> incomeSeries = new XYChart.Series<>();
	        incomeSeries.setName("Income");
	        

	        XYChart.Series<String, Number> expenseSeries = new XYChart.Series<>();
	        expenseSeries.setName("Expense");
	        
	        ArrayList<Budget> incomeData = DBHandler3.getCurrentMonthIncome();
	        ArrayList<Budget> expenseData = DBHandler3.getCurrentMonthExpense();

	        // Iterate through both lists simultaneously and add data to series
	        for (int i = 0; i < incomeData.size(); i++) {
	            Budget incomeBudget = incomeData.get(i);
	            Budget expenseBudget = expenseData.get(i);

	            // Get the month name
	            String monthName = Month.of(incomeBudget.getDmy()).toString();

	            // Add income data to series
	            incomeSeries.getData().add(new XYChart.Data<>(monthName, incomeBudget.getAmount()));

	            // Add expense data to series
	            expenseSeries.getData().add(new XYChart.Data<>(monthName, expenseBudget.getAmount()));
	        }

	        // Add the series to the bar chart
	        barChart.getData().addAll(incomeSeries, expenseSeries);
	        
	}
	
	public void createCreditSystem()
	{
		
		creditTv=new TableView<Credit>();
		
		TableColumn<Item,Integer> idCol=new TableColumn<Item,Integer>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("creditID")); 
		
		TableColumn<Item,String> customerNameCol=new TableColumn<Item,String>("Customer Name");
		customerNameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("customerName"));
		
		TableColumn<Item,String> phoneCol=new TableColumn<Item,String>("Phone");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Item,String>("phone")); 
		
		TableColumn<Item,Integer> amountCol=new TableColumn<Item,Integer>("Amount");
		amountCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("amount"));
		
		TableColumn<Item,String> noteCol=new TableColumn<Item,String>("Note");
		noteCol.setCellValueFactory(new PropertyValueFactory<Item,String>("note")); 
		
		TableColumn<Item,LocalDate> startDateCol=new TableColumn<Item,LocalDate>("Start Date");
		startDateCol.setCellValueFactory(new PropertyValueFactory<Item,LocalDate>("startDate"));
		
		TableColumn<Item,LocalDate> endDateCol=new TableColumn<Item,LocalDate>("End Date");
		endDateCol.setCellValueFactory(new PropertyValueFactory<Item,LocalDate>("endDate"));
		
		creditTv.getColumns().add(idCol);
		creditTv.getColumns().add(customerNameCol);
		creditTv.getColumns().add(phoneCol);
		creditTv.getColumns().add(amountCol);
		creditTv.getColumns().add(noteCol);
		creditTv.getColumns().add(startDateCol);
		creditTv.getColumns().add(endDateCol);
		
//		setTableData();
		
		
		creditPane=new GridPane();
		creditPane.add(lcreditSystem, 0, 0);
		creditPane.add(creditTv, 0, 1);
		creditPane.setAlignment(Pos.CENTER);
		creditTv.setPrefSize(800, 400);
		
	}
	public void createReport()
	{
		reportTv=new TableView();
		
		trno=new TableColumn("No");
		trdescription=new TableColumn("Description");
		trdate=new TableColumn("Date");
		
		reportTv.getColumns().add(trno);
		reportTv.getColumns().add(trdescription);
		reportTv.getColumns().add(trdate);
		reportTv.setPrefSize(600, 500);
		
		reportPane=new GridPane();
		reportPane.add(lreport, 0, 0);
		reportPane.add(reportTv, 0, 1);
		reportPane.setAlignment(Pos.CENTER);
		reportPane.setPrefSize(600, 500);
	
	}
	public void setTableData()
	{
		cal=new ArrayList<Credit>();
//		cal=DBHandler.getAllCredit();
		creditTv.getItems().addAll(cal);
	}
	public void setStyle()
	{
		ldashBoard.getStyleClass().add("titleHome");
		lcreditSystem.getStyleClass().add("titleHome");
		lhistory.getStyleClass().add("titleHome");
		lreport.getStyleClass().add("titleHome");
		
		
		amountPane.getStyleClass().add("dashchildHome");
		amountPane.getStyleClass().add("amountpaneHome");
		
		itemPane.getStyleClass().add("dashchildHome");
		itemPane.getStyleClass().add("itempaneHome");
		
		profitPane.getStyleClass().add("dashchildHome");
		profitPane.getStyleClass().add("profitpaneHome");
		totalItemPane.getStyleClass().add("dashchildHome");
		totalItemPane.getStyleClass().add("totalitempaneHome");
		staffPane.getStyleClass().add("dashchildHome");
		staffPane.getStyleClass().add("staffpaneHome");
		
		dashPane.setId("dashboardHome");
//		amountPane.setId("amountpane");
//		itemPane.setId("itempane");§
//		profitPane.setId("profitpane");
//		totalItemPane.setId("totalitempane");
		creditPane.setId("creditpaneHome");
		chartPane.setId("chartpaneHome");
		
		mainPane.setId("homepaneHome");
		centerPane.setId("centerpaneHome");
		
		lamount.setId("amountHome");
		ltotalItem.setId("totalitemHome");
		litem.setId("bestitemHome");
		lprofitAmount.setId("profitHome");
		lnoOfStaff.setId("noofstaffHome");
		itemTv.setId("itemTvHome");
		litemQuantityReport.setId("itemQuantityReportHome");
		
	}
	public BorderPane getMainPane()
	{
		createNodes();
		showBarChart();
		showPieChart();
		createLinePane();
		createCreditSystem();
		createReport();
		
		
		
		
		defineLayout();
		setStyle();
		
		return mainPane;
	}
	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

//	@Override
//	public void start(Stage st) throws Exception {
//		
//		
//		
//		Scene sc=new  Scene(mainPane,800,800);
//		URL url = this.getClass ().getResource ("mystylehome.css"); 
//		sc.getStylesheets().add(url.toExternalForm());
//	
//		
//		st.setScene(sc);
//		st.setTitle("Shop Management System");
//		st.show();
//		
//		
//	}
	public static void main(String[] args) {
		Application.launch(args);
	}

}
