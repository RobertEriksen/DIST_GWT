package function;

import boundary.Menu;

public interface IFunction {
	void setBoundary(Menu menu);
	String interpret(String input, boolean b);
	String getWeightString();
	Double getWeight();
	boolean getRM20();
	void engageRM20(boolean state);
	void tareWeight();
	void displayMsg(String msg);
	void displayWeight();
	void displaySecMsg(String msg);
	void changeWeight(double weight);
	void zeroWeight();
	void quit();
	String getText();
	String getSecText();
	double getBrutto();
	double convert(String input);
	public double getTara();
	public String getRM20Answer();
	public void setRM20Answer(String text);
	public void storeDisplay();
	public void restoreDisplay();
}
