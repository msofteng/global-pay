# global-pay

![capa](https://global-pay.onrender.com/assets/img/static/capa.png)

**Site Oficial:** https://global-pay.onrender.com/

**Documentação:** https://global-pay.onrender.com/swagger-ui.html

**Instruções:** [AvaliaçãoPraticaJava 1 1.pdf](https://drive.google.com/file/d/1QDeZzTx4MXaFd461IAawLpmYuXimv-u3/view?usp=sharing)

### Funcionalidades

- **Transferências TED** onde você pode enviar dinheiro entre contas GlobalPay

- **Extrato 100% digital** para visualizar suas movimentações financeiras sem sair de casa. Tudo no controle na palma da sua mão

- **Confira nossas tarifas**
  - **2,5% + R$ 3,00** para TED entre contas GlobalPay (no mesmo dia)
  - **R$ 12,00** para TED entre contas GlobalPay (agendamento em até 10 dias)
  - **8,2%** para TED entre contas GlobalPay (agendamento em até 20 dias)
  - **6,9%** para TED entre contas GlobalPay (agendamento em até 30 dias)
  - **4,7%** para TED entre contas GlobalPay (agendamento em até 40 dias)
  - **1,7%** para TED entre contas GlobalPay (agendamento em até 50 dias)

### Tecnologias

- **Front-end**
  - [JavaScript](https://developer.mozilla.org/pt-BR/docs/Web/JavaScript)
  - [Vue.js](https://vuejs.org/)
  - [Tailwind CSS](https://tailwindcss.com/)
- **Back-end**
  - [Java 11](https://www.java.com/pt-BR/)
  - [Spring Boot](https://spring.io/projects/spring-boot)  
- **Banco de dados**
  - [H2](https://www.h2database.com/html/main.html)

### Instalação

1. Clonar esse repositório

    ```bash
    git clone https://github.com/msofteng/global-pay.git
    ```

2. Acessar o projeto

    ```bash
    cd global-pay
    ```

3. Executá-lo em seu computador ([_com JDK 11 instalado_](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html))

    ```bash
    ./mvnw spring-boot:run
    ```

4. Empacotar a aplicação e executar pelo JDK

    - Construir a aplicação (build)

      ```bash
      ./mvnw clean package
      ```

    - Executar o `.jar` gerado no build dentro do JDK

      ```bash
      java -jar target/global-pay-1.0.0.jar
      ```
    
    - Executar no [Docker](https://www.docker.com/) (opcional)

      ```bash
      # build da imagem docker
      docker build -t globalpay:1.0.0 .

      # executa o container em segundo plano
      docker run -d -p 8080:8080 --name globalpay globalpay:1.0.0      
      ```