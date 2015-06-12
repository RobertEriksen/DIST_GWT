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
		Label welcomeLbl = new Label("Velkommen til kartoteket af CDIO gruppe 16");
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

		Label operatoerIdLbl = new Label("ID:");
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
				clientImpl.service.login(Integer.valueOf(operatoerIdTxt.getText()), passwordTxt.getText(), new AsyncCallback<Integer>() {

					@Override
					public void onSuccess(Integer result) {
						if (result==1) {
							
							// clear view and show new label
							Window.alert("Du er operatør og har ikke adgang til programmet");
							
						}
						else if(result==2) {
							mainView.showForemanMenu(result);
							
							// clear view and show new label
							w.clear();
							Label loginLbl = new Label("Du er nu logget ind og kan bruge navigationsmenuen foroven: " + result);
							loginLbl.addStyleName("spacing-vertical");
							w.add(loginLbl);
							
						}
						else if(result==3) {
							mainView.showpharmacistMenu(result);
							
							// clear view and show new label
							w.clear();
							Label loginLbl = new Label("Du er nu logget ind og kan bruge navigationsmenuen foroven: " + result);
							loginLbl.addStyleName("spacing-vertical");
							w.add(loginLbl);
							
						}
						else if(result==4) {
							mainView.showAdministratorMenu(result);
							
							// clear view and show new label
							w.clear();
							Label loginLbl = new Label("Du er nu logget ind og kan bruge navigationsmenuen foroven: " + result);
							loginLbl.addStyleName("spacing-vertical");
							w.add(loginLbl);
							
						}
						else  Window.alert("Ugyldigt login, prøv igen!");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server feeeeeeeeeejl! " + caught.getMessage());
					}

				});
			}
		});
		
		Label Superbruger = new Label("Superbruger");
		Button SuperLogin = new Button("Log ind som superbruger");
		SuperLogin.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainView.showAdministratorMenu(4);
				
				// clear view and show new label
				w.clear();
				Label loginLbl = new Label("Du er nu logget ind og kan bruge navigationsmenuen foroven: " + 4);
				loginLbl.addStyleName("spacing-vertical");
				w.add(loginLbl);
			}
			});
	
		
		Label Pharmaceut = new Label("Pharmaceut");
		Button Pharmaceutlogin = new Button("Log ind som Pharmaceut");
		Pharmaceutlogin.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainView.showpharmacistMenu(3);
				
				// clear view and show new label
				w.clear();
				Label loginLbl = new Label("Du er nu logget ind og kan bruge navigationsmenuen foroven: " + 3);
				loginLbl.addStyleName("spacing-vertical");
				w.add(loginLbl);
			}
			});
		
		Label Værkføre = new Label("Værkføre");
		Button Værkførelogin = new Button("Log ind som værkføre");
		Værkførelogin.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainView.showForemanMenu(2);
				
				// clear view and show new label
				w.clear();
				Label loginLbl = new Label("Du er nu logget ind og kan bruge navigationsmenuen foroven: " + 2);
				loginLbl.addStyleName("spacing-vertical");
				w.add(loginLbl);
			}
			});
		
		Label Operatør = new Label("Operatør login");
		Button Operatørlogin = new Button("Log ind som Operatør");
		Operatørlogin.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Du er operatør og har ikke adgang til programmet");
			}
			});
		
		loginTable.setWidget(3, 0, Superbruger);
		loginTable.setWidget(3, 1, SuperLogin);
		
		
		loginTable.setWidget(4, 0, Pharmaceut);
		loginTable.setWidget(4, 1, Pharmaceutlogin);
		
		loginTable.setWidget(5, 0, Værkføre);
		loginTable.setWidget(5, 1, Værkførelogin);
		
		loginTable.setWidget(6, 0, Operatør);
		loginTable.setWidget(6, 1, Operatørlogin);
		
		w.add(loginTable);
	}

}
