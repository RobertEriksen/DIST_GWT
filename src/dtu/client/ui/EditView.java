package dtu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
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


public class EditView extends Composite {
	VerticalPanel editPanel;
	FlexTable t;

	// editing text boxes
	TextBox nameTxt;
	TextBox iniTxt;
	TextBox cprTxt;
	TextBox passTxt;
	TextBox activeTxt;
	TextBox levelTxt;

	// valid fields - initially a field is valid
	boolean nameValid = true;
	boolean iniValid = true;
	boolean cprValid = true;
	boolean passValid = true;
	boolean activeValid = true;
	boolean levelValid = true;

	int eventRowIndex;

	KartotekServiceClientImpl clientImpl;

	// operator list
	List<OperatoerDTO> operatoerer;

	// previous cancel anchor
	Anchor previousCancel = null;
	
	Button showInactiveOps;
	boolean showInactive = false;

	public EditView(KartotekServiceClientImpl clientImpl) {
		this.clientImpl = clientImpl;

		editPanel = new VerticalPanel();
		initWidget(this.editPanel);

		HorizontalPanel topPanel = new HorizontalPanel();
		showInactiveOps = new Button("Vis inaktive operatører");
		Label pageTitleLbl = new Label("Ret operatører");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.setWidth("450px");
		topPanel.add(pageTitleLbl);
		topPanel.add(showInactiveOps);
		editPanel.add(topPanel);
		
		t = new FlexTable();

		// adjust column widths
		t.getFlexCellFormatter().setWidth(0, 0, "40px");
		t.getFlexCellFormatter().setWidth(0, 1, "150px");
		t.getFlexCellFormatter().setWidth(0, 2, "50px");
		t.getFlexCellFormatter().setWidth(0, 3, "110px");
		t.getFlexCellFormatter().setWidth(0, 4, "80px");
		t.getFlexCellFormatter().setWidth(0, 5, "40px");
		t.getFlexCellFormatter().setWidth(0, 6, "30px");
		
		// style table
		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "ID");
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "Initialer");
		t.setText(0, 3, "CPR");
		t.setText(0, 4, "Password");
		t.setText(0, 5, "Aktiv");
		t.setText(0, 6, "Niveau");

		// V.2
		clientImpl.service.getOperators(new AsyncCallback<List<OperatoerDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<OperatoerDTO> result) {
				// populate table and add delete anchor to each row
				for (int rowIndex=0; rowIndex < result.size(); rowIndex++) {
					t.setText(rowIndex+1, 0, ""+result.get(rowIndex).getOprId());
					t.setText(rowIndex+1, 1, result.get(rowIndex).getOprNavn());
					t.setText(rowIndex+1, 2, result.get(rowIndex).getIni());
					t.setText(rowIndex+1, 3, result.get(rowIndex).getCpr());
					t.setText(rowIndex+1, 4, result.get(rowIndex).getPassword());
					t.setText(rowIndex+1, 5, result.get(rowIndex).getActive());
					t.setText(rowIndex+1, 6, result.get(rowIndex).getLevel());
					Anchor edit = new Anchor("edit");
					t.setWidget(rowIndex+1, 7, edit);
					edit.addClickHandler(new EditHandler());
				}
			}
		});


		editPanel.add(t);

		// text boxes
		nameTxt = new TextBox();
		nameTxt.setWidth("140px");
		iniTxt = new TextBox();
		iniTxt.setWidth("40px");
		cprTxt = new TextBox();
		cprTxt.setWidth("100px");
		passTxt = new TextBox();
		passTxt.setWidth("70px");
		activeTxt = new TextBox();
		activeTxt.setWidth("20px");
		levelTxt = new TextBox();
		levelTxt.setWidth("20px");
		
		
		showInactiveOps.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				for (int i = 1; i < t.getRowCount(); i++) t.removeRow(i); // clear FlexTable (except for first header row)
				if (!showInactive) {
					showInactiveOps.setText("Skjul inaktive operatører");
					showInactive = true;
					getOperators();
				}
				else {
					showInactiveOps.setText("Vis inaktive operatører");
					showInactive = false;
					getOperators();
				}
			}
		});
	}

	private class EditHandler implements ClickHandler {
		public void onClick(ClickEvent event) {

			// if previous edit open - force cancel operation�
			if (previousCancel != null)
				previousCancel.fireEvent(new ClickEvent(){});

			// get rowindex where event happened
			eventRowIndex = t.getCellForEvent(event).getRowIndex();

			// populate textboxes
			nameTxt.setText(t.getText(eventRowIndex, 1));
			iniTxt.setText(t.getText(eventRowIndex, 2));
			cprTxt.setText(t.getText(eventRowIndex, 3));
			passTxt.setText(t.getText(eventRowIndex, 4));
			activeTxt.setText(t.getText(eventRowIndex, 5));
			levelTxt.setText(t.getText(eventRowIndex, 6));

			// show text boxes for editing
			t.setWidget(eventRowIndex, 1, nameTxt);
			t.setWidget(eventRowIndex, 2, iniTxt);
			t.setWidget(eventRowIndex, 3, cprTxt);
			t.setWidget(eventRowIndex, 4, passTxt);
			t.setWidget(eventRowIndex, 5, activeTxt);
			t.setWidget(eventRowIndex, 6, levelTxt);

			// start editing here
			nameTxt.setFocus(true);

			// get edit anchor ref for cancel operation
			final Anchor edit =  (Anchor) event.getSource();

			// get textbox contents for cancel operation
			final String name = nameTxt.getText();
			final String ini = iniTxt.getText();
			final String cpr = cprTxt.getText();
			final String pass = passTxt.getText();
			final String active = activeTxt.getText();
			final String level = levelTxt.getText();


			final Anchor ok = new Anchor("ok");
			ok.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					// fill DTO with id and new values 
					OperatoerDTO operatoerDTO = new OperatoerDTO(Integer.parseInt(t.getText(eventRowIndex, 0)), nameTxt.getText(), iniTxt.getText(),
							cprTxt.getText(), passTxt.getText(), Integer.valueOf(activeTxt.getText()), Integer.valueOf(levelTxt.getText()));

					clientImpl.service.updateOperator(operatoerDTO, new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							// remove inputboxes
							t.setText(eventRowIndex, 1, nameTxt.getText());
							t.setText(eventRowIndex, 2, iniTxt.getText());
							t.setText(eventRowIndex, 3, cprTxt.getText());
							t.setText(eventRowIndex, 4, passTxt.getText());
							t.setText(eventRowIndex, 5, activeTxt.getText());
							t.setText(eventRowIndex, 6, levelTxt.getText());
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server fejl!" + caught.getMessage());
						}

					});
					// restore edit link
					t.setWidget(eventRowIndex, 7, edit);
					t.clearCell(eventRowIndex, 8);

					previousCancel = null;
				}
			});

			Anchor cancel = new Anchor("cancel");
			previousCancel = cancel;
			cancel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					// restore original content of textboxes and rerun input validation
					
					nameTxt.setText(name);
					nameTxt.fireEvent(new KeyUpEvent() {}); // validation
					
					iniTxt.setText(ini);
					iniTxt.fireEvent(new KeyUpEvent() {});  // validation
					
					cprTxt.setText(cpr);
					cprTxt.fireEvent(new KeyUpEvent() {}); // validation
					
					passTxt.setText(pass);
					passTxt.fireEvent(new KeyUpEvent() {}); // validation
					
					activeTxt.setText(pass);
					activeTxt.fireEvent(new KeyUpEvent() {}); // validation
					
					levelTxt.setText(pass);
					levelTxt.fireEvent(new KeyUpEvent() {}); // validation
					
					t.setText(eventRowIndex, 1, name);
					t.setText(eventRowIndex, 2, ini);
					t.setText(eventRowIndex, 3, cpr);
					t.setText(eventRowIndex, 4, pass);
					t.setText(eventRowIndex, 5, active);
					t.setText(eventRowIndex, 6, level);
					
					// restore edit link
					t.setWidget(eventRowIndex, 7, edit);
					t.clearCell(eventRowIndex, 8);
					
					previousCancel = null;
				}

			});


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

					// enable/disable ok depending on form status 
					if (nameValid&&iniValid&&cprValid&&passValid&&activeValid&&levelValid)
						t.setWidget(eventRowIndex, 7, ok);
					else
						t.setText(eventRowIndex, 7, "ok");				
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

					// enable/disable ok depending on form status 
					if (nameValid&&iniValid&&cprValid&&passValid&&activeValid&&levelValid)
						t.setWidget(eventRowIndex, 7, ok);
					else
						t.setText(eventRowIndex, 7, "ok");
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

					// enable/disable ok depending on form status 
					if (nameValid&&iniValid&&cprValid&&passValid&&activeValid&&levelValid)
						t.setWidget(eventRowIndex, 7, ok);
					else
						t.setText(eventRowIndex, 7, "ok");
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

					// enable/disable ok depending on form status 
					if (nameValid&&iniValid&&cprValid&&passValid&&activeValid&&levelValid)
						t.setWidget(eventRowIndex, 7, ok);
					else
						t.setText(eventRowIndex, 7, "ok");
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

					// enable/disable ok depending on form status 
					if (nameValid&&iniValid&&cprValid&&passValid&&activeValid&&levelValid)
						t.setWidget(eventRowIndex, 7, ok);
					else
						t.setText(eventRowIndex, 7, "ok");
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

					// enable/disable ok depending on form status 
					if (nameValid&&iniValid&&cprValid&&passValid&&activeValid&&levelValid)
						t.setWidget(eventRowIndex, 7, ok);
					else
						t.setText(eventRowIndex, 7, "ok");
				}

			});

			// showing ok and cancel widgets
			t.setWidget(eventRowIndex, 7, ok);
			t.setWidget(eventRowIndex, 8, cancel);		
		}
	}
}
