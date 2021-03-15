@Algorítmo para duplicar uma string em binário
fita 100010
init qEscreverB
accept qf

qEscreverB,*,qEscreverB,*,>
qEscreverB,_,qEscreverA,b,<

qEscreverA,*,qEscreverA,*,<
qEscreverA,_,qPegarValorA,a,>

qPegarValorA,0,qMudarA0,a,<
qPegarValorA,1,qMudarA1,a,<
qPegarValorA,b,qPegarValorB,*,>

qPegarValorB,1,qMudarB1,b,<
qPegarValorB,0,qMudarB0,b,<
qPegarValorB,b,qPegarValorB,*,>
qPegarValorB,_,qRemoverB,*,<

qRemoverB,b,qRemoverA,_,<

qRemoverA,0,qRemoverA,*,<
qRemoverA,1,qRemoverA,*,<
qRemoverA,a,qSubstitutoA,*,>

qTirarA,a,qIrAteInicio1,_,<

qIrAteInicio1,_,qIrAteInicio2,*,<
qIrAteInicio2,*,qIrAteInicio2,*,<
qIrAteInicio2,_,qf,*,<

qSubstitutoA,1,qSubstituirA1,a,<
qSubstitutoA,0,qSubstituirA0,a,<
qSubstitutoA,_,qSubstituirA_,a,<

qSubstituirA1,a,qRemoverA,1,>
qSubstituirA0,a,qRemoverA,0,>
qSubstituirA_,a,qTirarA,_,>

qMudarB1,b,qPegarValorB,1,>
qMudarB0,b,qPegarValorB,0,>

qMudarA0,*,qEscreverFinal0,0,>
qMudarA1,*,qEscreverFinal1,1,>

qEscreverFinal0,*,qEscreverFinal0,*,>
qEscreverFinal1,*,qEscreverFinal1,*,>

qEscreverFinal0,_,qIrAteA,0,<
qEscreverFinal1,_,qIrAteA,1,<

qIrAteA,0,qIrAteA,*,<
qIrAteA,1,qIrAteA,*,<
qIrAteA,b,qIrAteA,*,<
qIrAteA,a,qPegarValorA,*,>

@1, estado do cabeçote
@2, o que será lido
@3, pra qual estado o cabeçote irá
@4, o que será escrito
@5, próxima direção do cabeçote
