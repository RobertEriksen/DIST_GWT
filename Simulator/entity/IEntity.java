package entity;

public interface IEntity {

	public abstract double getBrutto();

	public abstract void setBrutto(double brutto);

	public abstract double getTara();

	public abstract void setTara(double tara);
	
	public abstract void setRM20(boolean RM20);
	
	public abstract boolean getRM20();
	
	public abstract String getRM20Answer();
	public abstract void setRM20Answer(String text);
	
	public abstract void setText(String text);
	
	public abstract String getText();
	
	public abstract String getSecDisplay();
	
	public abstract void setSecDisplay(String secDisplay);
	
	public abstract String gettext1();
	public abstract String settext1(String text);
	public abstract String gettext2();
	public abstract String settext2(String text);
	public abstract String gettext3();
	public abstract String settext3(String text);
	
	public abstract void storeDisplay();
	public abstract void restoreDisplay();
}