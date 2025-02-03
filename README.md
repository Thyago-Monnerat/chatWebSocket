# Chat com websocket
#### Projeto utilizando protocolo WebSocket e STOMP para troca das mensagens.
----
  
Utilizei Spring framework no backend para fazer um sistema de chat em tempo real.

As mensagens são enviadas para um controller e repassadas para /topics/chat, onde os clients se inscrevem para receber as novas mensagens.

Fiz um exemplo de Exception utilizando um ExceptionHandler simulando a tentativa de conexão com nome duplicado. O erro é enviado para /topics/errors.

---

### No mais, é isso ❤️

Tecnologias usadas:
~~~
    - Java 
    - Maven
    - Spring Boot
    - Spring Web
    - Spring WebSocket
    - STOMP
~~~
