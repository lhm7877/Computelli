package addition;
public class Ref{
                String text;
	String type;
public Ref Ref(Ref ref){ //생성자든 함수든 둘다 같은 알고리즘으로 실행시키기 위해 함수로 통일
	this.text = ref.text;
	this.type = ref.type;
                return ref;
}

	public String getText() {
		return text;
	}

	public void setText(String text) {
                                System.out.println("setText함수실행");
		this.text = text;
                                System.out.println(this.text);
	}
}