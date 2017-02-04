class plus
{
	public Double plus(Double a, Double b)
	{
		return a + b;
	}
	public Integer plus(Integer a, Integer b)
	{
		return a + b;
	}

}
public class repeat_plus{ 
plus a1 = new plus();
	public repeat_plus() {
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
	    return a1.plus((Double)(recur(n-1, k)), k);
	    }
	    else
	    {
	System.out.println("here is in the recur3. n: " + n + ", k" + k);
	    return a1.plus((Double)( recur( n+1, k)) ,- k);
	    }
 	}
 	public static void main(String[] args) {
	repeat_plus aThisClass = new repeat_plus();
     Double a = aThisClass.recur(10.0, 2.0);
System.out.println(""+a);}
}