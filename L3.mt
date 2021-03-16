@Algorítmo para verificar se um número binário é múltiplo de 5
fita 101
init q1
accept qf

q1,0,q1,0,>
q1,1,q2,1,>
q1,_,qf,_,>

q2,0,q3,0,>
q2,1,q5,1,>

q3,1,q1,1,>
q3,0,q4,0,>

q4,0,q5,0,>
q4,1,q4,1,>

q5,0,q2,0,>
q5,1,q3,1,>

@1, estado do cabeçote
@2, o que será lido
@3, pra qual estado o cabeçote irá
@4, o que será escrito
@5, próxima direção do cabeçote

end
