package mycompany.myproducts.websecuritytestbed.model;

public class GenericInput {
	
	private String input;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	
	@Override
	public String toString(){
		return this.input;
	}
	

}
