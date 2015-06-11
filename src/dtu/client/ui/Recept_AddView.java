package dtu.client.ui;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.FieldVerifier;
import dtu.shared.OperatoerDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptKomponentDTO;

public class Recept_AddView extends Composite {
	VerticalPanel addPanel;

	// controls
	Label receptIdLbl;	
	Label receptnameLbl;
	
	TextBox receptIdTxt;
	TextBox receptnameTxt;
	
	Button save = new Button("Tilf\u00F8j");

	// valid fields
	boolean receptValid = false;
	boolean nameValid = false;
	
	public Recept_AddView(final KartotekServiceClientImpl clientImpl) {

		addPanel = new VerticalPanel();
		initWidget(this.addPanel);
		
		FlexTable addTable = new FlexTable();
		
		Label pageTitleLbl = new Label("tilfoej Recept");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.addStyleName("spacing-vertical");
		addPanel.add(pageTitleLbl);

		receptIdLbl = new Label("Recept ID:");
		receptIdTxt = new TextBox();
		Label nameRulesLbl = new Label("(Heltal kun)");
		addTable.setWidget(0, 0, receptIdLbl);
		addTable.setWidget(0, 1, receptIdTxt);
		addTable.setWidget(0, 2, nameRulesLbl);
		
		receptnameLbl = new Label("Recept Navn:");
		receptnameTxt = new TextBox();
		Label receptnameRulesLbl = new Label("(Mellem 2 og 20 karaktere)");
		addTable.setWidget(1, 0, receptnameLbl);
		addTable.setWidget(1, 1, receptnameTxt);
		addTable.setWidget(1, 2, receptnameRulesLbl);
		
		receptIdTxt.setStyleName("gwt-TextBox-invalidEntry");
		receptnameTxt.setStyleName("gwt-TextBox-invalidEntry");

		// use unicode escape sequence \u00F8 for '�'
		save.setEnabled(false);
		addTable.setWidget(5, 1, save);

		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// create new OperatoerDTO
				
				ReceptDTO newRecept = new ReceptDTO(Integer.valueOf(receptIdTxt.getText()), receptnameTxt.getText());
				
				// save on server
				clientImpl.service.createRecept(newRecept, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("ReceptKomponent gemt i kartotek.");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl!" + caught.getMessage());
					}

				});
			}
		});


		// register event handlers

		receptIdTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidID(receptIdTxt.getText())) {
					receptIdTxt.setStyleName("gwt-TextBox-invalidEntry");
					receptValid = false;
				}
				else {
					receptIdTxt.removeStyleName("gwt-TextBox-invalidEntry");
					receptValid = true;
				}
				checkFormValid();
			}

		});

		receptnameTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidName(receptnameTxt.getText())) {
					receptnameTxt.setStyleName("gwt-TextBox-invalidEntry");
					nameValid = false;
				}
				else {
					receptnameTxt.removeStyleName("gwt-TextBox-invalidEntry");
					nameValid = true;
				}
				checkFormValid();
			}

		});
		addPanel.add(addTable);
	}
		
	private void checkFormValid() {
		if (receptValid&&nameValid)
			save.setEnabled(true);
		else
			save.setEnabled(false);
	}
	

}
