package dtu.client.ui;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
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
		// total height of widget. Components are distributed evenly
		addPanel.setHeight("300px");	
		initWidget(this.addPanel);
		
		Label pageTitleLbl = new Label("Tilføj operatør");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.addStyleName("spacing-vertical");
		addPanel.add(pageTitleLbl);

		HorizontalPanel namePanel = new HorizontalPanel();
		HorizontalPanel iniPanel = new HorizontalPanel();
		HorizontalPanel cprPanel = new HorizontalPanel();
		HorizontalPanel passPanel = new HorizontalPanel();
		HorizontalPanel activePanel = new HorizontalPanel();
		HorizontalPanel levelPanel = new HorizontalPanel();

		nameLbl = new Label("Navn:");
		nameLbl.setWidth("7em");
		nameTxt = new TextBox();
		nameTxt.setHeight("1em");
		Label nameRulesLbl = new Label("(min. 2 og max. 20 karakterer)");
		nameRulesLbl.addStyleName("rulesLabel");
		namePanel.add(nameLbl);
		namePanel.add(nameTxt);
		namePanel.add(nameRulesLbl);

		iniLbl = new Label("Initialer:");
		iniLbl.setWidth("7em");
		iniTxt = new TextBox();
		iniTxt.setHeight("1em");
		Label iniRulesLbl = new Label("(min. 2 og max. 3 karakterer)");
		iniRulesLbl.addStyleName("rulesLabel");
		iniPanel.add(iniLbl);
		iniPanel.add(iniTxt);
		iniPanel.add(iniRulesLbl);

		cprLbl = new Label("CPR:");
		cprLbl.setWidth("7em");
		cprTxt = new TextBox();
		cprTxt.setHeight("1em");
		Label cprRulesLbl = new Label("(10 karakterer)");
		cprRulesLbl.addStyleName("rulesLabel");
		cprPanel.add(cprLbl);
		cprPanel.add(cprTxt);
		cprPanel.add(cprRulesLbl);

		passLbl = new Label("Password:");
		passLbl.setWidth("7em");
		passTxt = new TextBox();
		passTxt.setHeight("1em");
		Label passRulesLbl = new Label("(min. 7 og max. 8 karakterer)");
		passRulesLbl.addStyleName("rulesLabel");
		passPanel.add(passLbl);
		passPanel.add(passTxt);
		passPanel.add(passRulesLbl);
		
		activeLbl = new Label("Aktiv:");
		activeLbl.setWidth("7em");
		activeTxt = new TextBox();
		activeTxt.setHeight("1em");
		Label activeRulesLbl = new Label("(1 = aktiv, 0 = inaktiv)");
		activeRulesLbl.addStyleName("rulesLabel");
		activePanel.add(activeLbl);
		activePanel.add(activeTxt);
		activePanel.add(activeRulesLbl);
		
		levelLbl = new Label("Navn:");
		levelLbl.setWidth("7em");
		levelTxt = new TextBox();
		levelTxt.setHeight("1em");
		Label levelRulesLbl = new Label("(1 = operatør, 2 = superbruger)");
		levelRulesLbl.addStyleName("rulesLabel");
		levelPanel.add(levelLbl);
		levelPanel.add(levelTxt);
		levelPanel.add(levelRulesLbl);
		
		nameTxt.setStyleName("gwt-TextBox-invalidEntry");
		iniTxt.setStyleName("gwt-TextBox-invalidEntry");
		cprTxt.setStyleName("gwt-TextBox-invalidEntry");
		passTxt.setStyleName("gwt-TextBox-invalidEntry");
		activeTxt.setStyleName("gwt-TextBox-invalidEntry");
		levelTxt.setStyleName("gwt-TextBox-invalidEntry");

		// use unicode escape sequence \u00F8 for '�'
		save = new Button("Tilf\u00F8j");
		save.setEnabled(false);

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
		
		addPanel.add(namePanel);
		addPanel.add(iniPanel);
		addPanel.add(cprPanel);
		addPanel.add(passPanel);
		addPanel.add(activePanel);
		addPanel.add(levelPanel);
		addPanel.add(save);
	}

	private void checkFormValid() {
		if (nameValid&&iniValid&&cprValid&&passValid&&activeValid&&levelValid)
			save.setEnabled(true);
		else
			save.setEnabled(false);
	}

}
