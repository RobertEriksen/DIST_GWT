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

	KartotekServiceClientImpl clientImpl;

	// previous cancel anchor
	Anchor previousCancel = null;

	int eventRowIndex;

	public DeleteView(KartotekServiceClientImpl clientImpl) {
		this.clientImpl = clientImpl;
		deletePanel = new VerticalPanel();
		initWidget(this.deletePanel);

		Label pageTitleLbl = new Label("Slet operatør(er)");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.addStyleName("spacing-vertical");
		deletePanel.add(pageTitleLbl);

		t = new FlexTable();
		t.getFlexCellFormatter().setWidth(0, 0, "40px");
		t.getFlexCellFormatter().setWidth(0, 1, "150px");
		t.getFlexCellFormatter().setWidth(0, 2, "50px");
		t.getFlexCellFormatter().setWidth(0, 3, "110px");
		t.getFlexCellFormatter().setWidth(0, 4, "80px");
		t.getFlexCellFormatter().setWidth(0, 5, "40px");
		t.getFlexCellFormatter().setWidth(0, 6, "30px");

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


		clientImpl.service.getOperators(new AsyncCallback<List<OperatoerDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!");
			}

			@Override
			public void onSuccess(List<OperatoerDTO> result) {
				// populate table and add delete anchor to each row
				for (int i=0; i < result.size(); i++) {
					if (Integer.valueOf(result.get(i).getActive()) == 1) {
						t.setText(i+1, 0, ""+result.get(i).getOprId());
						t.setText(i+1, 1, result.get(i).getOprNavn());
						t.setText(i+1, 2, result.get(i).getIni());
						t.setText(i+1, 3, result.get(i).getCpr());
						t.setText(i+1, 4, result.get(i).getPassword());
						t.setText(i+1, 5, result.get(i).getActive());
						t.setText(i+1, 6, result.get(i).getLevel());
						Anchor delete = new Anchor("delete");
						t.setWidget(i+1, 7, delete);
						delete.addClickHandler(new DeleteHandler());
					}
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
			eventRowIndex = t.getCellForEvent(event).getRowIndex();

			// get delete anchor ref for cancel operation
			final Anchor delete =  (Anchor) event.getSource();
			//			delete.setStyleName("delete-red");

			Anchor ok = new Anchor("ok");
			ok.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					// delete object with id in back end					
					clientImpl.service.deleteOperator(Integer.parseInt(t.getText(eventRowIndex, 0)), new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							t.removeRow(eventRowIndex);
							//							eventRowIndex-=1;
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server fejl!" + caught.getMessage());
						}
					});
					previousCancel = null;
				}
			});

			Anchor cancel = new Anchor("cancel");
			previousCancel = cancel;
			//			previousCancel.setStyleName("delete-red");
			cancel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					t.setWidget(eventRowIndex, 7, delete);
					t.clearCell(eventRowIndex, 8);
				}

			});

			// showing ok and cancel widgets
			t.setWidget(eventRowIndex, 7, ok);
			t.setWidget(eventRowIndex, 8, cancel);
		}
	}
}


