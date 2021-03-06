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
import dtu.client.service.DatabaseServiceClientImpl;


public class WelcomeView extends Composite {
	DatabaseServiceClientImpl clientImpl;
	TextBox userIdTxt;
	TextBox passwordTxt;
	
	public WelcomeView(final DatabaseServiceClientImpl clientImpl, final MainView mainView) {
		this.clientImpl = clientImpl;
		final VerticalPanel w = new VerticalPanel();
		initWidget(w);
		Label welcomeLbl = new Label("Velkommen til Galgeleg som et distribueret system, udviklet af gruppe 3.");
		welcomeLbl.addStyleName("spacing-top");
		w.add(welcomeLbl);
		
		Label welcomeLbl2 = new Label("Du skal logge ind forneden for at komme videre faggot.");
		welcomeLbl2.addStyleName("spacing-bottom");
		w.add(welcomeLbl2);
		
		FlexTable loginTable = new FlexTable();

		Label userIdLbl = new Label("ID:");
		userIdTxt = new TextBox();
		userIdTxt.setWidth("3em");
		loginTable.setWidget(0, 0, userIdLbl);
		loginTable.setWidget(0, 1, userIdTxt);
		
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
//				clientImpl.service.login(Integer.valueOf(userIdTxt.getText()), passwordTxt.getText(), new AsyncCallback<Integer>() {
//
//					@Override
//					public void onSuccess(Integer result) {
//						if (result==1) {
//							
//							// clear view and show new label
//							Window.alert("Du er operatør og har ikke adgang til programmet");
//							
//						}
//						else if(result==2) {
//							mainView.showForemanMenu(result);
//							
//							// clear view and show new label
//							w.clear();
//							Label loginLbl = new Label("Du er nu logget ind og kan bruge navigationsmenuen foroven: " + result);
//							loginLbl.addStyleName("spacing-vertical");
//							w.add(loginLbl);
//							
//						}
//						
//						else if(result==4) {
//							mainView.showAdministratorMenu(result);
//							
//							// clear view and show new label
//							w.clear();
//							Label loginLbl = new Label("Du er nu logget ind og kan bruge navigationsmenuen foroven: " + result);
//							loginLbl.addStyleName("spacing-vertical");
//							w.add(loginLbl);
//							
//						}
//						else  Window.alert("Ugyldigt login, prøv igen!");
//					}
//
//					@Override
//					public void onFailure(Throwable caught) {
//						Window.alert("Server feeeeeeeeeejl! " + caught.getMessage());
//					}
//
//				});
			}
		});
		
		Label demoLabel = new Label("----- Demo knapper til login forneden -----");
		demoLabel.addStyleName("redText");
		
		Button SuperLogin = new Button("Log ind som superbruger");
		SuperLogin.addStyleName("spacing-vertical");
		SuperLogin.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainView.showAdministratorMenu();
				
				// clear view and show new label
				w.clear();
				Label loginLbl = new Label("Du er nu logget ind som superbruger og kan bruge navigationsmenuen foroven.");
				loginLbl.addStyleName("spacing-vertical");
				w.add(loginLbl);
			}
			});
		
		
		
		loginTable.setWidget(3, 1, demoLabel);
		
		loginTable.setWidget(4, 1, SuperLogin);
		
		w.add(loginTable);
	}

}
