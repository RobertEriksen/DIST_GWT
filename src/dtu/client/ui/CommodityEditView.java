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

import dtu.client.service.DatabaseServiceClientImpl;
import dtu.shared.FieldVerifier;
import dtu.shared.UserDTO;
import dtu.shared.CommoditiesDTO;


public class CommodityEditView extends Composite {
	VerticalPanel editPanel;
	FlexTable t;

	// editing text boxes
	TextBox nameTxt;
	TextBox lvrTxt;

	boolean nameValid = true;
	boolean lvrValid = true;

	int eventRowIndex;

	DatabaseServiceClientImpl clientImpl;

	// operator list
	List<CommoditiesDTO> raavarer;

	// previous cancel anchor
	Anchor previousCancel = null;


	public CommodityEditView(DatabaseServiceClientImpl clientImpl) {
		this.clientImpl = clientImpl;
		editPanel = new VerticalPanel();
		initWidget(this.editPanel);
		HorizontalPanel topPanel = new HorizontalPanel();
		Label pageTitleLbl = new Label("Ret råvarer");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.setWidth("450px");
		topPanel.add(pageTitleLbl);
		topPanel.addStyleName("spacing-vertical");
		editPanel.add(topPanel);

		t = new FlexTable();

		// adjust column widths
		t.getFlexCellFormatter().setWidth(0, 0, "140px");
		t.getFlexCellFormatter().setWidth(0, 1, "150px");
		t.getFlexCellFormatter().setWidth(0, 2, "150px");

		// style table
		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "ID");
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "Leverandør");

		getRaavarer();

		editPanel.add(t);

		// text boxes
		nameTxt = new TextBox();
		nameTxt.setWidth("140px");
		lvrTxt = new TextBox();
		lvrTxt.setWidth("140px");
	}

	private void getRaavarer() {
		clientImpl.service.getCommodity(new AsyncCallback<List<CommoditiesDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl! " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<CommoditiesDTO> result) {
				// populate table and add delete anchor to each row
				for (int rowIndex=0; rowIndex < result.size(); rowIndex++) {
					t.setText(rowIndex+1, 0, ""+result.get(rowIndex).getRvrId());
					t.setText(rowIndex+1, 1, result.get(rowIndex).getRvrNavn());
					t.setText(rowIndex+1, 2, result.get(rowIndex).getlvr());
					Anchor edit = new Anchor("edit");
					t.setWidget(rowIndex+1, 3, edit);
					edit.addClickHandler(new EditHandler());
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
			lvrTxt.setText(t.getText(eventRowIndex, 2));

			// show text boxes for editing
			t.setWidget(eventRowIndex, 1, nameTxt);
			t.setWidget(eventRowIndex, 2, lvrTxt);

			// start editing here
			nameTxt.setFocus(true);

			// get edit anchor ref for cancel operation
			final Anchor edit =  (Anchor) event.getSource();

			// get textbox contents for cancel operation
			final String name = nameTxt.getText();
			final String ini = lvrTxt.getText();


			final Anchor ok = new Anchor("ok");
			ok.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					// fill DTO with id and new values 
					CommoditiesDTO commoditiesDTO = new CommoditiesDTO(Integer.parseInt(t.getText(eventRowIndex, 0)), nameTxt.getText(), lvrTxt.getText());


					clientImpl.service.updateCommodity(commoditiesDTO, new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							// remove inputboxes
							t.setText(eventRowIndex, 1, nameTxt.getText());
							t.setText(eventRowIndex, 2, lvrTxt.getText());
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server fejl!" + caught.getMessage());
						}

					});
					// restore edit link
					t.setWidget(eventRowIndex, 3, edit);
					t.clearCell(eventRowIndex, 4);
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

					lvrTxt.setText(ini);
					lvrTxt.fireEvent(new KeyUpEvent() {});  // validation
					
					t.setText(eventRowIndex, 1, name);
					t.setText(eventRowIndex, 2, ini);
					// restore edit link
					t.setWidget(eventRowIndex, 3, edit);
					t.clearCell(eventRowIndex, 4);

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

					if (nameValid&&lvrValid)
						t.setWidget(eventRowIndex, 3, ok);
					else
						t.setText(eventRowIndex, 3, "ok");				
				}

			});

			lvrTxt.addKeyUpHandler(new KeyUpHandler(){

				@Override
				public void onKeyUp(KeyUpEvent event) {
					if (!FieldVerifier.isValidName(lvrTxt.getText())) {
						lvrTxt.setStyleName("gwt-TextBox-invalidEntry");
						lvrValid = false;
					}
					else {
						lvrTxt.removeStyleName("gwt-TextBox-invalidEntry");
						lvrValid = true;
					}

					if (nameValid&&lvrValid)
						t.setWidget(eventRowIndex, 3, ok);
					else
						t.setText(eventRowIndex, 3, "ok");
				}

			});

			// showing ok and cancel widgets
			t.setWidget(eventRowIndex, 3, ok);
			t.setWidget(eventRowIndex, 4, cancel);		
		}
	}
}
