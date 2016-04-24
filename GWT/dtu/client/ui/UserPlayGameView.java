package dtu.client.ui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.DatabaseServiceClientImpl;
//import dtu.shared.UserDTO;

public class UserPlayGameView extends Composite {
	DatabaseServiceClientImpl clientImpl;
	VerticalPanel browsePanel;
	int rowCount;
	final Image image = new Image();
	Label guessLbl = new Label("Gæt inden x sekunder: ");
	TextBox guessTxt = new TextBox();
	Button ok = new Button("ok");
	int untilLoss = 1;
	

	public UserPlayGameView(DatabaseServiceClientImpl clientImpl) {
		image.setUrl(GWT.getModuleBaseURL()+"Images/"+untilLoss+"_stage.png");
		this.clientImpl = clientImpl;
		browsePanel = new VerticalPanel();
		initWidget(this.browsePanel);
		HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.addStyleName("spacing-vertical");
		browsePanel.add(image);
		topPanel.add(guessLbl);
		topPanel.add(guessTxt);
		topPanel.add(ok);
		ok.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				untilLoss++;
				image.setUrl(GWT.getModuleBaseURL()+"Images/"+untilLoss+"_stage.png");
				if(untilLoss == 9){
					quickstart();
				}
			}
			});
		browsePanel.add(topPanel);
	}
	
	public void quickstart(){
		untilLoss = 1;
	}
	
}
