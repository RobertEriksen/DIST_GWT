package dtu.client.ui;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.controller.MainView;
import dtu.client.service.KartotekServiceClientImpl;


public class WelcomeView extends Composite {
	KartotekServiceClientImpl clientImpl;
	TextBox operatoerIdTxt;
	TextBox passwordTxt;
	
	public WelcomeView(final KartotekServiceClientImpl clientImpl, final MainView mainView) {
		this.clientImpl = clientImpl;
		final VerticalPanel w = new VerticalPanel();
		initWidget(w);
		Label welcomeLbl = new Label("Velkommen til operatør kartotek af CDIO gruppe 16. Du skal logge ind for at komme videre!");
		welcomeLbl.addStyleName("spacing-vertical");
		w.add(welcomeLbl);
		
//		clientImpl.service.getSize(new AsyncCallback<Integer>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Server fejl!" + caught.getMessage());
//			}
//
//			@Override
//			public void onSuccess(Integer result) {
//				w.add(new Label("Antal operatører i kartoteket: " + result));
//				
//			}
//		});
		
		FlexTable loginTable = new FlexTable();

		Label operatoerIdLbl = new Label("Operatør id:");
		operatoerIdTxt = new TextBox();
		operatoerIdTxt.setWidth("3em");
		loginTable.setWidget(0, 0, operatoerIdLbl);
		loginTable.setWidget(0, 1, operatoerIdTxt);
		
		Label passwordLbl = new Label("Password:");
		passwordTxt = new PasswordTextBox();
		passwordTxt.setWidth("8em");
		loginTable.setWidget(1, 0, passwordLbl);
		loginTable.setWidget(1, 1, passwordTxt);
		
		Button loginButton = new Button("Log ind");
		loginTable.setWidget(2, 1, loginButton);
		
		loginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clientImpl.service.login(Integer.valueOf(operatoerIdTxt.getText()), passwordTxt.getText(), new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							mainView.showMenuView();
							
							// clear view and show new label
							w.clear();
							Label loginLbl = new Label("Du er nu logget ind og kan bruge navigationsmenuen foroven.");
							loginLbl.addStyleName("spacing-vertical");
							w.add(loginLbl);
							
						}
						else  Window.alert("Ugyldigt login, prøv igen!");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl! " + caught.getMessage());
					}

				});
			}
		});
		
		w.add(loginTable);
	}

}
