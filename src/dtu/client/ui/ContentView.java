package dtu.client.ui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.controller.MainView;
import dtu.client.service.KartotekServiceClientImpl;



public class ContentView extends Composite {
	
	// reference to remote data layer
	private KartotekServiceClientImpl clientImpl;

	VerticalPanel contentPanel;

	public ContentView() {
	}

	public ContentView(KartotekServiceClientImpl clientImpl) {
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

	public void openAddView() {
		contentPanel.clear();
		AddView addView = new AddView(clientImpl);
		contentPanel.add(addView);
	}
	
	public void openAddRaavareView() {
		contentPanel.clear();
		raavare_AddView addView = new raavare_AddView(clientImpl);
		contentPanel.add(addView);
	}
	
	
	public void openAddReceptKomponentView() {
		contentPanel.clear();
		receptKomponent_AddView addView = new receptKomponent_AddView(clientImpl);
		contentPanel.add(addView);
	}
	
	public void openAddReceptView() {
		contentPanel.clear();
		Recept_AddView addView = new Recept_AddView(clientImpl);
		contentPanel.add(addView);
	}
	
	public void openAddRaavareBatchView() {
		contentPanel.clear();
		RaavareBatch_AddView addView = new RaavareBatch_AddView(clientImpl);
		contentPanel.add(addView);
	}
	
	public void openAddProduktBatchView() {
		contentPanel.clear();
		ProduktBatch_AddView addView = new ProduktBatch_AddView(clientImpl);
		contentPanel.add(addView);
	}
	
	public void clearView() {
		contentPanel.clear();
	}

	public void openBrowseView() {
		contentPanel.clear();
		BrowseView browseView = new BrowseView(clientImpl);
		contentPanel.add(browseView);
	}
	
	public void openReceptBrowseView() {
		contentPanel.clear();
		Recept_BrowseView browseView = new Recept_BrowseView(clientImpl);
		contentPanel.add(browseView);
	}
	
//	public void openRaavareBatchView() {
//		contentPanel.clear();
//		RaavareBatch_View browseView = new RaavareBatch_View(clientImpl);
//		contentPanel.add(browseView);
//	}
	
	public void openReceptKomponentBrowseView() {
		contentPanel.clear();
		ReceptKomponent_BrowseView browseView = new ReceptKomponent_BrowseView(clientImpl);
		contentPanel.add(browseView);
	}

	public void openDeleteView() {
		contentPanel.clear();
		DeleteView deleteView = new DeleteView(clientImpl);
		contentPanel.add(deleteView);
	}

	public void openEditView() {
		contentPanel.clear();
		EditView editView = new EditView(clientImpl);
		contentPanel.add(editView);
	}

	public void openEditRaavareView() {
		contentPanel.clear();
		RaavareEditView editView = new RaavareEditView(clientImpl);
		contentPanel.add(editView);
	}

	public void openRaavareBatchBrowseView() {
		contentPanel.clear();
		RaavareBatch_BrowseView browseView = new RaavareBatch_BrowseView(clientImpl);
		contentPanel.add(browseView);
	}
	
	public void openProduktBatchBrowseView() {
		contentPanel.clear();
		ProduktBatch_BrowseView browseView = new ProduktBatch_BrowseView(clientImpl);
		contentPanel.add(browseView);
	}
}
