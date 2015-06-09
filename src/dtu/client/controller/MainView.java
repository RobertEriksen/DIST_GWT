package dtu.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.client.ui.AdministratorMenu;
import dtu.client.ui.ContentView;
import dtu.client.ui.RaavareBatchMenu;
import dtu.client.ui.ReceptMenu;
import dtu.client.ui.UserAdminMenu;
import dtu.client.ui.raavareMenu;


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

	public void showMenuView() {
		// wrap menuView
		RootPanel.get("nav").clear();
		UserAdminMenu m = new UserAdminMenu(this);
		RootPanel.get("nav").add(m);
	}
	
	public void showAdministratorMenu() {
		// wrap menuView
		RootPanel.get("nav").clear();
		AdministratorMenu m = new AdministratorMenu(this);
		RootPanel.get("nav").add(m);
	}
	
	public void showRaavareMenu(){
		RootPanel.get("nav").clear();
		raavareMenu m = new raavareMenu(this);
		RootPanel.get("nav").add(m);
	}

	public void showReceptMenu(){
		RootPanel.get("nav").clear();
		ReceptMenu m = new ReceptMenu(this);
		RootPanel.get("nav").add(m);
	}
	
	public void showRaavareBatchMenu(){
		RootPanel.get("nav").clear();
		RaavareBatchMenu m = new RaavareBatchMenu(this);
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
	public void addreceptOperator() {
		contentView.openAddReceptView();
	}
	
	public void addRaavareBatchOperator() {
		contentView.openAddReceptView();
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
	
	
	
	
	
	

}
