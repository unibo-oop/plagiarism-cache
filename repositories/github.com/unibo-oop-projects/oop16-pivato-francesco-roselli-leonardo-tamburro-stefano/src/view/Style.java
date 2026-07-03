package view;

public enum Style {
	
	BASIC ("/view/resources/application.css");
	
	private String url;
	
	private Style(final String url) {
		this.url = url;
	}
	
	public String url(){
		return this.url;
	}

}
