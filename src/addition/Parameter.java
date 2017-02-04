package addition;

public class Parameter {
	Object[] parObj;
	Class<?>[] partypes;
	
	public void setParObj(Object parObj, int i){
		this.parObj[i] = parObj;
	}
	
	public void setParTypes(Class<?> parTypes, int i){
		this.partypes[i] = parTypes;
	}
}
