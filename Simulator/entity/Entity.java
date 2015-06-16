package entity;

public class Entity implements IEntity {
	private double brutto = 0.000;
	private double tara;
	private boolean RM20;
	private String mainDisplay = "";
	private String secDisplay = "";
	private String text1 = "";
	private String text2 = "";
	private String text3 = "";
	private String RM20Answer = "";
	private String storedSecDisplay = "";
	private String storedPrimDisplay = "";
		
	public Entity() {
		tara = 0.000;
		RM20 = false;
		
	}

	/* (non-Javadoc)
	 * @see entity.IEntity#getBrutto()
	 */
	@Override
	public double getBrutto() {
		return brutto;
	}


	/* (non-Javadoc)
	 * @see entity.IEntity#setBrutto(double)
	 */
	@Override
	public void setBrutto(double brutto) {
		this.brutto = brutto;
	}


	/* (non-Javadoc)
	 * @see entity.IEntity#getTara()
	 */
	@Override
	public double getTara() {
		return tara;
	}


	/* (non-Javadoc)
	 * @see entity.IEntity#setTara(double)
	 */
	@Override
	public void setTara(double tara) {
		this.tara = tara;
	}
	
	@Override
	public boolean getRM20() {
		return RM20;
	}

	@Override
	public void setRM20(boolean RM20) {
		this.RM20 = RM20;
	}

	@Override
	public void setText(String text) {
		mainDisplay = text;
		
	}

	@Override
	public String getText() {
		return mainDisplay;
	}

	public String getSecDisplay() {
		return secDisplay;
	}

	public void setSecDisplay(String secDisplay) {
		this.secDisplay = secDisplay;
	}

	@Override
	public String gettext1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String settext1(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gettext2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String settext2(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gettext3() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String settext3(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRM20Answer() {
		return RM20Answer;
	}

	@Override
	public void setRM20Answer(String text) {
		RM20Answer = text;
	}

	@Override
	public void storeDisplay() {
		// TODO Auto-generated method stub
		storedPrimDisplay = mainDisplay;
		storedSecDisplay = secDisplay;
	}

	@Override
	public void restoreDisplay() {
		mainDisplay = storedPrimDisplay;
		secDisplay = storedSecDisplay;
		// TODO Auto-generated method stub
	}

}
