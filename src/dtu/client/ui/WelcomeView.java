package dtu.client.ui;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;


public class WelcomeView extends Composite {
	KartotekServiceClientImpl clientImpl;
	TextBox operatoerIdTxt;
	TextBox passwordTxt;
	
	public WelcomeView(final KartotekServiceClientImpl clientImpl) {
		this.clientImpl = clientImpl;
		final VerticalPanel w = new VerticalPanel();
		initWidget(w);
		Label welcomeLbl = new Label("Velkommen til operatør kartotek af CDIO gruppe 16!");
		welcomeLbl.addStyleName("spacing-bottom");
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
		
		HorizontalPanel oprIdPanel = new HorizontalPanel();
		Label operatoerIdLbl = new Label("Operatør id:");
		operatoerIdTxt = new TextBox();
		oprIdPanel.add(operatoerIdLbl);
		oprIdPanel.add(operatoerIdTxt);
		oprIdPanel.addStyleName("spacing-bottom");
		
		HorizontalPanel passwordPanel = new HorizontalPanel();
		Label passwordLbl = new Label("Password:");
		passwordTxt = new TextBox();
		passwordPanel.add(passwordLbl);
		passwordPanel.add(passwordTxt);
		passwordPanel.addStyleName("spacing-bottom");
		
		Button loginButton = new Button("Log ind");
		
		loginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clientImpl.service.login(Integer.valueOf(operatoerIdTxt.getText()), passwordTxt.getText(), new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						if (result) Window.alert("Du er nu logget ind!");
						else  Window.alert("Ugyldigt login, prøv igen!");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl! " + caught.getMessage());
					}

				});
			}
		});
		
		w.add(oprIdPanel);
		w.add(passwordPanel);
		w.add(loginButton);
	}

}
