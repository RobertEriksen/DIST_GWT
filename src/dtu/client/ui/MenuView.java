package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.controller.MainView;

public class MenuView extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	// receive reference to MainView for call back
	public MenuView(final MainView main) {
		initWidget(this.hPanel);
		
		
		Anchor showOperators = new Anchor("Vis operatører");
		hPanel.add(showOperators);
		// call back the controller
		showOperators.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showOperators();
			}
		});
	
		// use unicode escape sequence \u00F8 for '�'
		Anchor add = new Anchor("Tilf\u00F8j operatør");
		hPanel.add(add);
		add.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addOperator();
			}
		});
		
		Anchor edit = new Anchor("Ret operatør");
		hPanel.add(edit);
		edit.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.editOperators();
			}
		});
		
		Anchor delete = new Anchor("Slet operatør");
		delete.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.deleteOperators();
			}
		});
		hPanel.add(delete);
	}
}
