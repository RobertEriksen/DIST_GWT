package dtu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.OperatoerDTO;

public class DeleteView extends Composite {
	VerticalPanel deletePanel;
	FlexTable t;

	// V.2
	KartotekServiceClientImpl clientImpl;

	// previous cancel anchor
	Anchor previousCancel = null;

	public DeleteView(KartotekServiceClientImpl clientImpl) {
		// v.2
		this.clientImpl = clientImpl;
		deletePanel = new VerticalPanel();
		initWidget(this.deletePanel);

		deletePanel.add(new Label("Slet operatør(er)"));
		
		t = new FlexTable();
		t.getFlexCellFormatter().setWidth(0, 0, "50px");
		t.getFlexCellFormatter().setWidth(0, 1, "170px");
		t.getFlexCellFormatter().setWidth(0, 2, "15px");
		t.getFlexCellFormatter().setWidth(0, 3, "100px");
		t.getFlexCellFormatter().setWidth(0, 4, "70px");

		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");


		// set headers in flextable
		t.setText(0, 0, "ID");
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "Initialer");
		t.setText(0, 3, "CPR");
		t.setText(0, 4, "Password");

		
		// V.2
		clientImpl.service.getOperators(new AsyncCallback<List<OperatoerDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!");
			}

			@Override
			public void onSuccess(List<OperatoerDTO> result) {
				// populate table and add delete anchor to each row
				for (int i=0; i < result.size(); i++) {
					t.setText(i+1, 0, ""+result.get(i).getOprId());
					t.setText(i+1, 1, result.get(i).getOprNavn());
					t.setText(i+1, 2, result.get(i).getIni());
					t.setText(i+1, 3, result.get(i).getCpr());
					t.setText(i+1, 4, result.get(i).getPassword());
					Anchor delete = new Anchor("delete");
					t.setWidget(i+1, 5, delete);
					delete.addClickHandler(new DeleteHandler());
				}

			}

		});

		deletePanel.add(t);
	}

	private class DeleteHandler implements ClickHandler {
		public void onClick(ClickEvent event) {

			// if previous cancel open - force cancel operation�
			if (previousCancel != null)
				previousCancel.fireEvent(new ClickEvent(){});


			// get rowindex where event happened
			final int eventRowIndex = t.getCellForEvent(event).getRowIndex();

			// get delete anchor ref for cancel operation
			final Anchor delete =  (Anchor) event.getSource();
//			delete.setStyleName("delete-red");

			Anchor ok = new Anchor("ok");
			ok.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					
					// V.2
					// delete object with id in back end					
					clientImpl.service.deleteOperator(Integer.parseInt(t.getText(eventRowIndex, 0)), new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							// remove row in flextable
							t.removeRow(eventRowIndex);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server fejl!" + caught.getMessage());
						}

					});
					
				}
			});

			Anchor cancel = new Anchor("cancel");
			previousCancel = cancel;
//			previousCancel.setStyleName("delete-red");
			cancel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					t.setWidget(eventRowIndex, 5, delete);
					t.clearCell(eventRowIndex, 6);
				}

			});

			// showing ok and cancel widgets
			t.setWidget(eventRowIndex, 5, ok);
			t.setWidget(eventRowIndex, 6, cancel);
		}
	}
}


