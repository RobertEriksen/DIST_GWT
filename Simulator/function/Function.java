package function;

import boundary.Menu;
import entity.IEntity;

public class Function implements IFunction {
	Menu menu;
	IEntity data;

	public Function(IEntity data) {
		this.data = data;
	}

	public void setBoundary(Menu menu) {
		this.menu = menu;
	}

	@Override
	public String interpret(String input, boolean extCmd) {
		if (input.equals("T")){ // Tar�r v�gt
			tareWeight();
			return "T S     " + String.format("%.3f",getTara()) + " kg";
		}
		else if (input.startsWith("B ")) {
			try {
				if(!input.contains(".")) {
					input += ".";
				}
				input += "0000";
				changeWeight(Double.parseDouble(input.substring(2,7)));
				return "DB";
			} catch(NumberFormatException e) {
				return "Input fejl, prøv igen.\nIndtast kommando:";
			}
		}
		if (extCmd) { // Kommandoer kan kun bruges af en ekstern klient
			if(getRM20()) {
				return "RM20 I";
			}
			else if (input.equals("S")) {
				if(getWeight() < 0) {
					return "S S     " + String.format("%.3f",getWeight()) + " kg";
				}
				return "S S      " + String.format("%.3f",getWeight()) + " kg";

			}
			else if (input.equals("T")){ // Tar�r v�gt
				tareWeight();
				return "T S";
			} else if (input.startsWith("D ")) {
				try{
					if (input.split(" ")[1].split("\"")[1].length() <= 7) {
						displayMsg(input.substring(3, input.lastIndexOf("\"")));
						return "D A";
					}
				}catch (IndexOutOfBoundsException e){
					return	"ES";
				}
				return "ES - Message too long (max. 7 chars)";

			} else if (input.equals("DW")){
				displayMsg(" ");
				return "DW A";

			} 

			else if(input.startsWith("P111 ")){
				try {
					if (input.split(" \"")[1].length() < 30) {
						displaySecMsg(input.substring(6, input.lastIndexOf("\"")));
						return "P111 A";
					}
				} catch (IndexOutOfBoundsException e) {
					return "ES";
				}
				return "ES - Message too long (max. 30 chars)";
			}
			else if (input.startsWith("RM20 ")){
				engageRM20(true);
				try {
					storeDisplay();
					String split[] = input.split(" ");
					if(split[1].equals("4") || split[1].equals("8")) {
						split = input.split("\"");
						if (split[1].length() < 24)
							data.setSecDisplay(split[1]);
						else {
							restoreDisplay();
							engageRM20(false);
							return "ES - Message too long (max. 24 chars)";
						}
					}
					else {
						restoreDisplay();
						engageRM20(false);
						data.setSecDisplay("");
						return "RM20 L";
					}
					
				} catch(IndexOutOfBoundsException e) {
					restoreDisplay();
					engageRM20(false);
					data.setSecDisplay("");
					return "RM20 L";
				}
				return "RM20 B";
				
			}
		}
		else { // Kun tilgængelig via vægtens lokale konsol
			if (input.equals("Q")){
				System.out.println("Systemet lukker ned.");
				System.exit(1);
			} 
		}
		if (extCmd)
			return "ES";
		else
			return "ES\nIndtast kommando:";
	}

	@Override
	public String getWeightString() {
		return Double.toString(data.getBrutto()-data.getTara());
	}

	@Override
	public void tareWeight() {
		data.setTara(data.getBrutto());
	}

	@Override
	public void displayMsg(String msg) {
		data.setText(msg);
	}

	@Override
	public void displayWeight() {
	}

	@Override
	public void displaySecMsg(String msg) {
		data.setSecDisplay(msg);
	}

	@Override
	public void changeWeight(double weight) {
		if(weight > 6) {
			System.out.println("Brutto må ikke overstige 6 kg");
		} else {
			data.setBrutto(weight);			
		}
	}


	@Override
	public void quit() {
		System.out.println("Systemet lukker");
		System.exit(1);
		//luk alle connections
	}

	@Override
	public void zeroWeight() {
		data.setBrutto(0.0);
		data.setTara(0.0);
	}

	@Override
	public Double getWeight() {
		return data.getBrutto()-data.getTara();
	}

	@Override
	public boolean getRM20() {
		return data.getRM20();
	}

	@Override
	public String getText() {
		return data.getText();
	}

	@Override
	public String getSecText() {
		// TODO Auto-generated method stub
		return data.getSecDisplay();
	}

	@Override
	public double getBrutto(){
		return data.getBrutto();
	}

	@Override
	public double convert(String input){
		return Double.parseDouble(input) + 1;
	}

	@Override
	public double getTara() {
		return data.getTara();
	}

	@Override
	public void engageRM20(boolean state) {
		data.setRM20(state);
	}

	@Override
	public String getRM20Answer() {
		return data.getRM20Answer();
	}

	@Override
	public void setRM20Answer(String text) {
		data.setRM20Answer(text);
	}

	@Override
	public void storeDisplay() {
		data.storeDisplay();
		// TODO Auto-generated method stub
	}

	@Override
	public void restoreDisplay() {
		data.restoreDisplay();
	}
}
