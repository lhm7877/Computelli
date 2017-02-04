public class 곱하기{ 

//add a1 = new add(); 

public 곱하기() { }  

public Integer add(Integer a, Integer b) 

{ Integer c = a + b; return c; } 

public double add(double a, double b)

{ double c = a + b; return c; } 

public double 곱하기( double n, double k) 

{ Double interval = (double)(0.01); 

System.out.println("where is in the recur. n: " + n + ", k:" + k); 

if ((n >= 0.0)&&(n<0.0001)){ 

System.out.println("here is in the recur2. n: " + n + ", k:" + k); 

return 0.0; }    

if (n >= 0.0001)    

{ 

System.out.println("phere is in the recur3. n: " + n + ", k:" + k);    

return add(곱하기((double)(n-interval), (double)(k)), (double)(k*interval));   

}    else    { System.out.println("here is in the recur3. n: " + n + ", k:" + k);    

return add(곱하기((double)(n+interval), (double)(k)) ,(double)(-k*interval));   

}  

}  

public static void main(String[] args)

{ 

곱하기 aThisClass = new 곱하기();    

Double a = aThisClass.곱하기(10.0, 2.0); 

System.out.println(""+a);      

}  

}
