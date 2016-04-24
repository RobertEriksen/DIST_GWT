package dtu.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import dtu.client.service.DatabaseServiceClientImpl;
import dtu.client.ui.AdministratorMenu;
import dtu.client.ui.ContentView;
import dtu.client.ui.UserAdminMenu;
import dtu.client.ui.UserLobbyMenu;



public class MainView  {

	// reference to ContentView
	private ContentView contentView;

	// V.2
	// reference to remote data layer
	private DatabaseServiceClientImpl clientImpl;


	public MainView() {

		// V.2
		// add server side implementation of data layer
		clientImpl = new DatabaseServiceClientImpl(GWT.getModuleBaseURL() + "kartotekservice");

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

	public void showUserMenu() {
		// wrap menuView
		RootPanel.get("nav").clear();
		UserAdminMenu m = new UserAdminMenu(this);
		RootPanel.get("nav").add(m);
	}
	
	public void showLobbyMenu() {
		RootPanel.get("nav").clear();
		UserLobbyMenu m = new UserLobbyMenu(this);
		RootPanel.get("nav").add(m);
		
	}
	
	public void showAdministratorMenu() {
		// wrap menuView
		RootPanel.get("nav").clear();
		AdministratorMenu m = new AdministratorMenu(this);
		RootPanel.get("nav").add(m);
	}

	// Call back handlers
//	public void addUser() {
//		contentView.openUserAddView();
//	}
//	
//	public void addCommodities() {
//		contentView.openAddCommoditiesView();
//	}
//	
//// Recept
//	public void addRecipeComponent() {
//		contentView.openAddRecipeComponentsView();
//	}
//	
//	public void addRecipe() {
//		contentView.openAddRecipeView();
//	}
//	
//	public void addCommoditiesBatch() {
//		contentView.openAddCommoditiesBatchView();
//	}
//	
//	public void addProductBatch() {
//		contentView.openAddProductBatchView();
//	}
	
	public void showUsers() {
		contentView.openBrowseUsersView();
	}

	public void playGame() {
		contentView.openPlayGameView();
	}

//	public void editUsers() {
//		contentView.openEditUsersView();
//	}
//	
//	public void editCommodities() {
//		contentView.openEditCommoditiesView();
//	}
//
//	public void deleteUsers() {
//		contentView.openDeleteUsersView();
//	}
//	
//	public void showRecipes() {
//		contentView.openRecipeBrowseView();
//	}
//	
//	public void showCommoditiesBatch() {
//		contentView.openCommoditiesBatchBrowseView();
//	}
//	
//	public void showProductBatch() {
//		contentView.openProductBatchBrowseView();
//	}
//	
//	public void showRecipeComponents() {
//		contentView.openRecipeComponentsBrowseView();
//	}

//	public void showRecipeMenu(int level) {
//		RootPanel.get("nav").clear();
//		RecipeMenu m = new RecipeMenu(this, level);
//		RootPanel.get("nav").add(m);		
//	}

}
