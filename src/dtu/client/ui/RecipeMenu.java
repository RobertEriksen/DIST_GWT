package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class RecipeMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();


	public RecipeMenu(final MainView main, final int level) {
		initWidget(this.hPanel);

		Anchor tilfoej_recept = new Anchor("Tilføj Recept");
		hPanel.add(tilfoej_recept);
		tilfoej_recept.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addreceptOperator();
			}
		});

		Anchor ret_raavare = new Anchor("Vis Recept");
		hPanel.add(ret_raavare);
		ret_raavare.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showRecepts();
			}
		});

		Anchor back = new Anchor("Tilbage");
		hPanel.add(back);
		back.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){	
				main.clearContentView();
				if(level==4){
					main.showAdministratorMenu(level);
				}
				else if(level == 3){
					main.showpharmacistMenu(level);
				}

				else if(level == 2){
					main.showForemanMenu(level);
				}
			}
		});
	}
}

