public class 반복{ 
add a1 = new add();
	public 반복() {
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
	반복 aThisClass = new 반복();
     Double a = aThisClass.recur(10.0, 2.0);
System.out.println(""+a);}}
