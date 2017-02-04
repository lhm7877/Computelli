grammar Add;

@header{
package addition;
import java.sql.*;
import addition.Computelli;
}

@members{
int x = 0;
int y = 0;
int z = 0;
}

eval returns [double value]
:
b=r {$value = $b.value;}
;

r returns [double value]
:
n1=atomExp {$value = $n1.value;}

(n3=Hangul n2=atomExp)* {
$value = Computelli.compute($n3.text,$n1.value,$n2.value);

System.out.print ($value+ " = "+ $n1.value +" "+$n3.text +" " + $n2.value );
/*   return (y + z);*/
}
;
/*
addExp returns [double value]
:
   '더하기' n1=atomExp
{
    $value = $n1.value;
    System.out.print ("+" + $n1.value );
    
}
;
*/
atomExp returns [double value]
:
n = NUM {$value=Double.parseDouble($n.text); }
|  '(' exp = r')'  {$value = $exp.value;} 
;

NUM 
: 
[0-9]+
;

Hangul
:
[ㄱ-ㅎㅏ-ㅣ가-힣]+
;
WS  : [ \t\r\n]+ -> skip;
