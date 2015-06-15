package dtu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.DatabaseServiceClientImpl;
import dtu.shared.UserDTO;

public class UserBrowseView extends Composite {
	DatabaseServiceClientImpl clientImpl;
	VerticalPanel browsePanel;
	static FlexTable t;
	Button showInactiveOps;
	boolean showInactive = false;
	int rowCount;

	public UserBrowseView(DatabaseServiceClientImpl clientImpl) {
		this.clientImpl = clientImpl;
		browsePanel = new VerticalPanel();
		initWidget(this.browsePanel);

		HorizontalPanel topPanel = new HorizontalPanel();
		showInactiveOps = new Button("Vis inaktive brugere");
		Label pageTitleLbl = new Label("Vis brugere");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.setWidth("450px");
		topPanel.add(pageTitleLbl);
		topPanel.add(showInactiveOps);
		topPanel.addStyleName("spacing-vertical");
		browsePanel.add(topPanel);

		t = new FlexTable();
		
		t.getFlexCellFormatter().setWidth(0, 0, "4em");
		t.getFlexCellFormatter().setWidth(0, 1, "20em");
		t.getFlexCellFormatter().setWidth(0, 2, "3em");
		t.getFlexCellFormatter().setWidth(0, 3, "10em");
		t.getFlexCellFormatter().setWidth(0, 4, "8em");
		t.getFlexCellFormatter().setWidth(0, 5, "3em");
		t.getFlexCellFormatter().setWidth(0, 6, "3em");
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
		
		browsePanel.add(t);

		getUsers();
		showInactiveOps.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				rowCount = t.getRowCount();
				for (int i = rowCount-1; i > 0; i--) t.removeRow(i); // clear FlexTable (except for first header row)
				if (!showInactive) {
					showInactiveOps.setText("Skjul inaktive operatører");
					showInactive = true;
					getUsers();
				}
				else {
					showInactiveOps.setText("Vis inaktive operatører");
					showInactive = false;
					getUsers();
				}
			}
		});
	}
	
	private void getUsers() {
		clientImpl.service.getUser(new AsyncCallback<List<UserDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<UserDTO> result) {
				int j = 1;
				for (int i=0; i < result.size(); i++) {
					if (!showInactive) {
						if (Integer.valueOf(result.get(i).getActive()) == 1) {
							t.setText(j, 0, ""+result.get(i).getOprId());
							t.setText(j, 1, result.get(i).getOprNavn());
							t.setText(j, 2, result.get(i).getIni());
							t.setText(j, 3, result.get(i).getCpr());
							t.setText(j, 4, result.get(i).getPassword());
							t.setText(j, 5, result.get(i).getActive());
							t.setText(j, 6, result.get(i).getLevel());
							j++;
						}
					} 
					else {
						t.setText(j, 0, ""+result.get(i).getOprId());
						t.setText(j, 1, result.get(i).getOprNavn());
						t.setText(j, 2, result.get(i).getIni());
						t.setText(j, 3, result.get(i).getCpr());
						t.setText(j, 4, result.get(i).getPassword());
						t.setText(j, 5, result.get(i).getActive());
						t.setText(j, 6, result.get(i).getLevel());
						j++;
					}
				}
			}
		});
	}
}
