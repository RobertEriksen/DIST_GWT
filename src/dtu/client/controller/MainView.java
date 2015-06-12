package dtu.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.client.ui.AdministratorMenu;
import dtu.client.ui.ContentView;
import dtu.client.ui.ForemanMenu;
import dtu.client.ui.PharmacistMenu;
import dtu.client.ui.ProductBatchMenu;
import dtu.client.ui.CommoditiesBatchMenu;
import dtu.client.ui.RecipeComponentMenu;
import dtu.client.ui.RecipeMenu;
import dtu.client.ui.UserAdminMenu;
import dtu.client.ui.CommoditiesMenu;


public class MainView  {

	// reference to ContentView
	private ContentView contentView;

	// V.2
	// reference to remote data layer
	private KartotekServiceClientImpl clientImpl;


	public MainView() {

		// V.2
		// add server side implementation of data layer
		clientImpl = new KartotekServiceClientImpl(GWT.getModuleBaseURL() + "kartotekservice");

		// wrap contentView
		contentView = new ContentView(clientImpl);
		RootPanel.get("section").add(contentView);	
	}

	public void run() {
		// show welcome panel
		contentView.openWelcomeView(this);		
	}
	
	public void clearContentView() {
		contentView.clearView();
	}

	public void showUserMenu(int level) {
		// wrap menuView
		RootPanel.get("nav").clear();
		UserAdminMenu m = new UserAdminMenu(this, level);
		RootPanel.get("nav").add(m);
	}
	
	public void showForemanMenu(int level) {
		// wrap menuView
		RootPanel.get("nav").clear();
		ForemanMenu m = new ForemanMenu(this, level);
		RootPanel.get("nav").add(m);
	}
	
	public void showpharmacistMenu(int level) {
		// wrap menuView
		RootPanel.get("nav").clear();
		PharmacistMenu m = new PharmacistMenu(this, level);
		RootPanel.get("nav").add(m);
	}
	
	public void showAdministratorMenu(int level) {
		// wrap menuView
		RootPanel.get("nav").clear();
		AdministratorMenu m = new AdministratorMenu(this, level);
		RootPanel.get("nav").add(m);
	}
	
	public void showRaavareMenu(int level){
		RootPanel.get("nav").clear();
		CommoditiesMenu m = new CommoditiesMenu(this, level);
		RootPanel.get("nav").add(m);
	}

	public void showReceptKomponentMenu(int level){
		RootPanel.get("nav").clear();
		RecipeComponentMenu m = new RecipeComponentMenu(this, level);
		RootPanel.get("nav").add(m);
	}
	
	public void showRaavareBatchMenu(int level){
		RootPanel.get("nav").clear();
		CommoditiesBatchMenu m = new CommoditiesBatchMenu(this, level);
		RootPanel.get("nav").add(m);
	}
	
	public void showProduktBatchMenu(int level){
		RootPanel.get("nav").clear();
		ProductBatchMenu m = new ProductBatchMenu(this, level);
		RootPanel.get("nav").add(m);
	}
	
	// Call back handlers
	public void addOperator() {
		contentView.openAddView();
	}
	
	public void addraavareOperator() {
		contentView.openAddRaavareView();
	}
	
// Recept
	public void addreceptKomponentOperator() {
		contentView.openAddReceptKomponentView();
	}
	
	public void addreceptOperator() {
		contentView.openAddReceptView();
	}
	
	public void addRaavareBatchOperator() {
		contentView.openAddRaavareBatchView();
	}
	
	public void addProduktBatchOperator() {
		contentView.openAddProduktBatchView();
	}
	
	public void showOperators() {
		contentView.openBrowseView();
	}

	public void editOperators() {
		contentView.openEditView();
	}
	
	public void editRaavare() {
		contentView.openEditRaavareView();
	}

	public void deleteOperators() {
		contentView.openDeleteView();
	}
	
	public void showRecepts() {
		contentView.openReceptBrowseView();
	}
	
	public void showRaavareBatch() {
		contentView.openRaavareBatchBrowseView();
	}
	
	public void showProduktBatch() {
		contentView.openProduktBatchBrowseView();
	}
	
	public void showReceptKomponents() {
		contentView.openReceptKomponentBrowseView();
	}

	public void showReceptMenu(int level) {
		RootPanel.get("nav").clear();
		RecipeMenu m = new RecipeMenu(this, level);
		RootPanel.get("nav").add(m);		
	}
	
	
	
	
	
	

}
