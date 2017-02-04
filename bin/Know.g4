grammar Know;

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

(n3=Hangul n2=atomExp '는' n4=atomExp) {
Computelli.learn($n3.text,$n1.value,$n2.value,$n4.value);

System.out.print ($n4.value+ " = "+ $n1.value+" " +$n3.text +" " + $n2.value );
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
n = number {$value=Double.parseDouble($n.text); }
|  '(' exp = r')'  {$value = $exp.value;} 
;
number
 :    unary_operator? unsigned_number
 ;
 
unary_operator
 :    '+'
 |    '-'
 ;
 
unsigned_number
 :    UNSIGNED_INT
 |    UNSIGNED_FLOAT
 ;
 
UNSIGNED_INT : ('0' | '1'..'9' '0'..'9'*);
 
UNSIGNED_FLOAT
 :   ('0'..'9')+ '.' ('0'..'9')* Exponent?
 |   '.' ('0'..'9')+ Exponent?
 |   ('0'..'9')+ Exponent
 ;
 
fragment
Exponent : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;
/*
NUM 
: 
[-|0-9]+
;
*/
Hangul
:
[ㄱ-ㅎㅏ-ㅣ가-힣]+
;
WS  : [ \t\r\n]+ -> skip;
