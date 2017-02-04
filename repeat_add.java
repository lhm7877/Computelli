 class add
{
public Integer add(Integer a, Integer b)
{
	Integer c = a + b;
	return c;
}
public Double add(Double a, Double b)
{
	Double c = a + b;
	return c;
}
}
public class repeat_add{ 
add a1 = new add();
	public repeat_add() {
} 

	public Double recur( Double n, Double k) {
	System.out.println("here is in the recur. n: " + n + ", k" + k);
	if (n == 0) {
	System.out.println("here is in the recur2. n: " + n + ", k" + k);
	  return 0.0;
	}
	    if (n > 0)
	    {
	System.out.println("here is in the recur3. n: " + n + ", k" + k);
	    return a1.add((Double)(recur(n-1, k)), k);
	    }
	    else
	    {
	System.out.println("here is in the recur3. n: " + n + ", k" + k);
	    return a1.add((Double)( recur( n+1, k)) ,- k);
	    }
 	}
 	public static void main(String[] args) {
	repeat_add aThisClass = new repeat_add();
     Double a = aThisClass.recur(10.0, 2.0);
System.out.println(""+a);}
}