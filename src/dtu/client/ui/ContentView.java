package dtu.client.ui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.controller.MainView;
import dtu.client.service.DatabaseServiceClientImpl;



public class ContentView extends Composite {
	
	// reference to remote data layer
	private DatabaseServiceClientImpl clientImpl;

	VerticalPanel contentPanel;

	public ContentView() {
	}

	public ContentView(DatabaseServiceClientImpl clientImpl) {
		this.clientImpl = clientImpl;
		contentPanel = new VerticalPanel();
		initWidget(this.contentPanel);
	}	

	// Sub views
	public void openWelcomeView(MainView mainView) {
		contentPanel.clear();
		WelcomeView welcomeView = new WelcomeView(clientImpl, mainView);
		contentPanel.add(welcomeView);
	}

	public void openUserAddView() {
		contentPanel.clear();
		UserAddView userAddView = new UserAddView(clientImpl);
		contentPanel.add(userAddView);
	}
	
	public void openAddCommoditiesView() {
		contentPanel.clear();
		CommoditiesAddView addView = new CommoditiesAddView(clientImpl);
		contentPanel.add(addView);
	}
	
	
	public void openAddRecipeComponentsView() {
		contentPanel.clear();
		RecipeComponentAddView addView = new RecipeComponentAddView(clientImpl);
		contentPanel.add(addView);
	}
	
	public void openAddRecipeView() {
		contentPanel.clear();
		RecipeAddView addView = new RecipeAddView(clientImpl);
		contentPanel.add(addView);
	}
	
	public void openAddCommoditiesBatchView() {
		contentPanel.clear();
		CommoditiesBatchAddView addView = new CommoditiesBatchAddView(clientImpl);
		contentPanel.add(addView);
	}
	
	public void openAddProductBatchView() {
		contentPanel.clear();
		ProductBatchAddView addView = new ProductBatchAddView(clientImpl);
		contentPanel.add(addView);
	}
	
	public void clearView() {
		contentPanel.clear();
	}

	public void openBrowseUsersView() {
		contentPanel.clear();
		UserBrowseView userBrowseView = new UserBrowseView(clientImpl);
		contentPanel.add(userBrowseView);
	}
	
	public void openRecipeBrowseView() {
		contentPanel.clear();
		RecipeBrowseView browseView = new RecipeBrowseView(clientImpl);
		contentPanel.add(browseView);
	}
	
//	public void openRaavareBatchView() {
//		contentPanel.clear();
//		RaavareBatch_View browseView = new RaavareBatch_View(clientImpl);
//		contentPanel.add(browseView);
//	}
	
	public void openRecipeComponentsBrowseView() {
		contentPanel.clear();
		RecipeComponentBrowseView browseView = new RecipeComponentBrowseView(clientImpl);
		contentPanel.add(browseView);
	}

	public void openDeleteUsersView() {
		contentPanel.clear();
		UserDeleteView userDeleteView = new UserDeleteView(clientImpl);
		contentPanel.add(userDeleteView);
	}

	public void openEditUsersView() {
		contentPanel.clear();
		UserEditView userEditView = new UserEditView(clientImpl);
		contentPanel.add(userEditView);
	}

	public void openEditCommoditiesView() {
		contentPanel.clear();
		CommodityEditView editView = new CommodityEditView(clientImpl);
		contentPanel.add(editView);
	}

	public void openCommoditiesBatchBrowseView() {
		contentPanel.clear();
		CommodityBatchBrowseView browseView = new CommodityBatchBrowseView(clientImpl);
		contentPanel.add(browseView);
	}
	
	public void openProductBatchBrowseView() {
		contentPanel.clear();
		ProductBatchBrowseView browseView = new ProductBatchBrowseView(clientImpl);
		contentPanel.add(browseView);
	}
}
