@Algorítmo para aumentar em 1 o valor binário
fita 01
init qLer
accept qf

qLer,*,qLer,*,>
qLer,_,qEscrever,*,<

qEscrever,0,qf,1,<
qEscrever,1,qEscrever,0,<
qEscrever,_,qf,1,<

@1, estado do cabeçote
@2, o que será lido
@3, pra qual estado o cabeçote irá
@4, o que será escrito
@5, próxima direção do cabeçote
@101 -> 110
@01 -> 10
@11101 -> 11110
@11111 -> 100000
@10110101 -> 10110110