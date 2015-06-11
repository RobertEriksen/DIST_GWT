package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class PharmacistMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();

	// receive reference to MainView for call back
	public PharmacistMenu(final MainView main, final int level) {
		initWidget(this.hPanel);

		//		Anchor Bruger_Administration = new Anchor("Bruger Administration");
		//		hPanel.add(Bruger_Administration);
		//		Bruger_Administration.addClickHandler(new ClickHandler(){
		//			public void onClick(ClickEvent event){	
		//				main.showMenuView();
		//			}
		//		});


		Anchor Raavare_administration = new Anchor("Raavare administration");
		hPanel.add(Raavare_administration);
		Raavare_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showRaavareMenu(level);
			}
		});


		Anchor Recept_administration = new Anchor("Recept administration");
		hPanel.add(Recept_administration);
		Recept_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showReceptMenu(level);
			}
		});

		Anchor ReceptKomponent_administration = new Anchor("Receptkomponent administration");
		hPanel.add(ReceptKomponent_administration);
		ReceptKomponent_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showReceptKomponentMenu(level);
			}
		});

		Anchor Raavarebatch_administration = new Anchor("Raavarebatch administration");
		hPanel.add(Raavarebatch_administration);
		Raavarebatch_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showRaavareBatchMenu(level);
			}
		});

		Anchor Produktbatch_administration = new Anchor("Produktbatch administration");
		hPanel.add(Produktbatch_administration);
		Produktbatch_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showProduktBatchMenu(level);
			}
		});

//		Anchor back = new Anchor("Tilbage");
//		hPanel.add(back);
//		back.addClickHandler(new ClickHandler(){
//			public void onClick(ClickEvent event){	
//				main.clearContentView();
//				if(level==4){
//					main.showAdministratorMenu(level);
//				}
//				else if(level == 3){
//					main.showpharmacistMenu(level);
//				}
//
//				else if(level == 2){
//					main.showForemanMenu(level);
//				}
//			}
//		});
	}
}
