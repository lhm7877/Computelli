package addition;
public class Ref{
                String text;
	String type;

	public String getText() {
		return text;
	}

	public void setText(String text) {
                                System.out.println("setText함수실행");
		this.text = text;
                                System.out.println(this.text);
	}
}