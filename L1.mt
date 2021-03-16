@Algorítmo para verificar se número binário termina em '01'
fita 1011010
init q0
accept qf

q0,1,q0,*,>
q0,0,q1,*,>

q1,0,q1,*,>
q1,1,q2,*,>

q2,0,q1,*,>
q2,1,q0,*,>
q2,_,qf,*,>

@1, estado do cabeçote
@2, o que será lido
@3, pra qual estado o cabeçote irá
@4, o que será escrito
@5, próxima direção do cabeçote

end