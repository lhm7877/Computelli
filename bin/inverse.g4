grammar Inverse;

@header{
package addition;
import java.sql.*;
import addition.Computelli;
}

@members{
int x = 0;
int y = 0;
int z = 0;
int n2=0;
int n1=0;
String n3="0.0";
}

/*


*/



eval returns [double value]
:
(b=equation1 { $value=$b.value;} | b2=equation2 { $value = $b2.value;}) 
;

equation2 returns [double value]
:
unon=UNKNOWN (n3=Hangul n2=atomExp '는')* n4=atomExp {$value=Computelli.inverse($unon.text,$n3.text,$n2.value,$n4.value);
} ;

equation1 returns [double value]
:
n1=atomExp {$value=$n1.value;} (n3=Hangul unon=UNKNOWN '는' )* n4=atomExp{$value=Computelli.inverse($unon.text, $n3.text,$n1.value,$n4.value);
} ;

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
r returns [double value]
:
n1=atomExp {$value=$n1.value;}

(n3=Hangul n2=atomExp)* {

};

atomExp returns [double value]
:
n=NUM {$value=Double.parseDouble($n.text); }
/*n = NUM {$value=$n.text; } */
|  '(' exp = r')'  {$value = $exp.value;} 
;

NUM 
: 
[0-9]+ 
;

UNKNOWN
:'X';

Equal
:'는';

Hangul
:
[ㄱ-ㅎㅏ-ㅣ가-힣]+
;

WS  : [ \t\r\n]+ -> skip;
