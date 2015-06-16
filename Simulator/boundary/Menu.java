package boundary;

import function.IFunction;

public class Menu extends Thread  {

	String mainDisplay = " ";
	String secDisplay = "";
	IFunction func;
	Double brutto = 0.000;
	Double tara = 0.000;
	Double displayed = 0.000;
	Boolean displayrm20 = false;

	public Menu(IFunction func) {
		this.func = func;
	}

	@Override
	public void run() {
		displayrm20 = false;
		InputThread input = new InputThread(func);
		try {
			input.start();
			
			System.out.println("===========================");
			System.out.println("   Mettler Vægt Simulator  ");
			System.out.println("===========================");
			System.out.printf("Primær display:     %.3f kg \n", displayed);
			System.out.println("Sekundær display: "+ secDisplay);
			System.out.print("Indtast kommando: ");
			
			while (true) {
				
				if(!func.getRM20()) {
					if(!func.getText().equals("") && !func.getText().equals(mainDisplay) || (!func.getSecText().equals("") && !func.getSecText().equals(secDisplay))) {
						displayrm20 = false;

						System.out.println("\n\n\n");
						System.out.println("===========================");
						System.out.println("   Mettler Vægt Simulator     ");
						System.out.println("===========================");
						
						if(!func.getText().equals(" ") && (!func.getText().equals("") && !func.getText().equals(mainDisplay))) {
							mainDisplay = func.getText();
							System.out.println("Primær display: " + mainDisplay);
						} else if (func.getText().equals(" ") && !mainDisplay.equals(" ")) {
							mainDisplay = func.getText();
							displayed = func.getBrutto()-func.getTara();
							System.out.printf("Primær display:     %.3f kg \n", displayed);
						} else if(func.getText().equals(" ")) {
							mainDisplay = "";
							func.displayMsg("");
							displayed = func.getBrutto()-func.getTara();
							System.out.printf("Primær display:     %.3f kg \n", displayed);
							
						} else if((!func.getSecText().equals("") && !func.getSecText().equals(secDisplay)) && !mainDisplay.equals("") && mainDisplay.equals(" ")) {
							mainDisplay = func.getText();
							System.out.println("Primær display: " + mainDisplay);
						} else {
							System.out.println("Primær display: " + mainDisplay);
						}
						
						secDisplay = func.getSecText();
						//Else brutto
						mainDisplay = func.getText();
						System.out.println("Sekundær display: " + secDisplay);
						System.out.print("Indtast kommando: ");

					} else if((func.getBrutto()-func.getTara() != displayed && func.getText().equals("")) || !func.getText().equals(" ") && func.getText().equals("") && (func.getBrutto() != brutto || !func.getText().equals("") || func.getTara() != tara || (!func.getSecText().equals("") && !func.getSecText().equals(secDisplay)))) {
						//Dette vises ved ændring i brutto
						mainDisplay = func.getText();
						secDisplay = func.getSecText();
						
						brutto = func.getBrutto();
						tara = func.getTara();
						displayrm20 = false;
						System.out.println("\n\n\n");
						System.out.println("===========================");
						System.out.println("   Mettler Vægt Simulator  ");
						System.out.println("===========================");
						displayed = func.getBrutto()-func.getTara();
						System.out.printf("Primær display:     %.3f kg \n", displayed);
						System.out.println("Sekundær display: "+ secDisplay);
						System.out.print("Indtast kommando: ");
					} 
				} else {
					if(displayrm20 == false) {
						mainDisplay = func.getText();
						secDisplay = func.getSecText();
						System.out.println("\n\n\n");
						System.out.println("===========================");
						System.out.println("   Mettler Vægt Simulator  ");
						System.out.println("===========================");
						System.out.printf("Primær display:   \n");
						System.out.printf("Sekundær display: %s\n", func.getSecText());
						System.out.print("Indtast kommando: ");
						displayrm20 = true;
					}
				}
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("God historie 1");
		}
	}
}
