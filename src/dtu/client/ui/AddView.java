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

public class AddView extends Composite {
	VerticalPanel addPanel;

	// controls
	Label nameLbl;
	Label iniLbl;
	Label cprLbl;
	Label passLbl;
	Label activeLbl;
	Label levelLbl;
	
	TextBox nameTxt;
	TextBox iniTxt;
	TextBox cprTxt;
	TextBox passTxt;
	TextBox activeTxt;
	TextBox levelTxt;
	
	Button save = new Button("Tilf\u00F8j");

	// valid fields
	boolean nameValid = false;
	boolean iniValid = false;
	boolean cprValid = false;
	boolean passValid = false;
	boolean activeValid = false;
	boolean levelValid = false;

	public AddView(final KartotekServiceClientImpl clientImpl) {

		addPanel = new VerticalPanel();
		initWidget(this.addPanel);
		
		FlexTable addTable = new FlexTable();
		
		Label pageTitleLbl = new Label("Tilføj operatør");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.addStyleName("spacing-vertical");
		addPanel.add(pageTitleLbl);

		nameLbl = new Label("Navn:");
		nameTxt = new TextBox();
		Label nameRulesLbl = new Label("(min. 2 og max. 20 karakterer)");
		addTable.setWidget(0, 0, nameLbl);
		addTable.setWidget(0, 1, nameTxt);
		addTable.setWidget(0, 2, nameRulesLbl);

		iniLbl = new Label("Initialer:");
		iniTxt = new TextBox();
		Label iniRulesLbl = new Label("(min. 2 og max. 3 karakterer)");
		addTable.setWidget(1, 0, iniLbl);
		addTable.setWidget(1, 1, iniTxt);
		addTable.setWidget(1, 2, iniRulesLbl);

		cprLbl = new Label("CPR:");
		cprTxt = new TextBox();
		Label cprRulesLbl = new Label("(10 karakterer)");
		addTable.setWidget(2, 0, cprLbl);
		addTable.setWidget(2, 1, cprTxt);
		addTable.setWidget(2, 2, cprRulesLbl);

		passLbl = new Label("Password:");
		passTxt = new TextBox();
		Label passRulesLbl = new Label("(7-8 karakterer og skal overholde DTU's regler for password.");
		addTable.setWidget(3, 0, passLbl);
		addTable.setWidget(3, 1, passTxt);
		addTable.setWidget(3, 2, passRulesLbl);
		
		activeLbl = new Label("Aktiv:");
		activeTxt = new TextBox();
		Label activeRulesLbl = new Label("(1 = aktiv, 0 = inaktiv)");
		addTable.setWidget(4, 0, activeLbl);
		addTable.setWidget(4, 1, activeTxt);
		addTable.setWidget(4, 2, activeRulesLbl);
		
		levelLbl = new Label("Niveau:");
		levelTxt = new TextBox();
		Label levelRulesLbl = new Label("(1 = operatør, 2 = superbruger)");
		addTable.setWidget(5, 0, levelLbl);
		addTable.setWidget(5, 1, levelTxt);
		addTable.setWidget(5, 2, levelRulesLbl);
		
		nameTxt.setStyleName("gwt-TextBox-invalidEntry");
		iniTxt.setStyleName("gwt-TextBox-invalidEntry");
		cprTxt.setStyleName("gwt-TextBox-invalidEntry");
		passTxt.setStyleName("gwt-TextBox-invalidEntry");
		activeTxt.setStyleName("gwt-TextBox-invalidEntry");
		levelTxt.setStyleName("gwt-TextBox-invalidEntry");

		// use unicode escape sequence \u00F8 for '�'
		save = new Button("Tilf\u00F8j");
		save.setEnabled(false);
		addTable.setWidget(6, 1, save);

		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// create new OperatoerDTO
				OperatoerDTO newOperatoer = new OperatoerDTO(nameTxt.getText(), iniTxt.getText(), cprTxt.getText(), passTxt.getText(), Integer.valueOf(activeTxt.getText()), Integer.valueOf(levelTxt.getText()));

				// save on server
				clientImpl.service.createOperator(newOperatoer, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("Operatoer gemt i kartotek.");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl!" + caught.getMessage());
					}

				});
			}
		});


		// register event handlers

		nameTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidName(nameTxt.getText())) {
					nameTxt.setStyleName("gwt-TextBox-invalidEntry");
					nameValid = false;
				}
				else {
					nameTxt.removeStyleName("gwt-TextBox-invalidEntry");
					nameValid = true;
				}
				checkFormValid();
			}

		});

		iniTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidInitials(iniTxt.getText())) {
					iniTxt.setStyleName("gwt-TextBox-invalidEntry");
					iniValid = false;
				}
				else {
					iniTxt.removeStyleName("gwt-TextBox-invalidEntry");
					iniValid = true;
				}
				checkFormValid();
			}

		});

		cprTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidCpr(cprTxt.getText())) {
					cprTxt.setStyleName("gwt-TextBox-invalidEntry");
					cprValid = false;
				}
				else {
					cprTxt.removeStyleName("gwt-TextBox-invalidEntry");
					cprValid = true;
				}
				checkFormValid();
			}

		});
		
		passTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidPass(passTxt.getText())) {
					passTxt.setStyleName("gwt-TextBox-invalidEntry");
					passValid = false;
				}
				else {
					passTxt.removeStyleName("gwt-TextBox-invalidEntry");
					passValid = true;
				}
				checkFormValid();
			}

		});

		activeTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidActive(activeTxt.getText())) {
					activeTxt.setStyleName("gwt-TextBox-invalidEntry");
					activeValid = false;
				}
				else {
					activeTxt.removeStyleName("gwt-TextBox-invalidEntry");
					activeValid = true;
				}
				checkFormValid();
			}

		});
		
		levelTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidLevel(levelTxt.getText())) {
					levelTxt.setStyleName("gwt-TextBox-invalidEntry");
					levelValid = false;
				}
				else {
					levelTxt.removeStyleName("gwt-TextBox-invalidEntry");
					levelValid = true;
				}
				checkFormValid();
			}

		});
		
		addPanel.add(addTable);
	}

	private void checkFormValid() {
		if (nameValid&&iniValid&&cprValid&&passValid&&activeValid&&levelValid)
			save.setEnabled(true);
		else
			save.setEnabled(false);
	}

}
