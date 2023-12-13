Feature: Operações CRUD para entregadores

  Scenario Outline: Criação de entregadores com sucesso
    Given o endpoint <endpoint> do tipo <tipo>
    When eu enviar uma requisição para salvar um novo entregador com o documento <documento> e a validade da CNH <validadeCNH>
    Then eu recebo uma resposta com código <codigo>
    And conteudo igual documento <documento> e a validade da CNH <validadeCNH>

  Examples:
  |       endpoint             |  tipo  | documento      | validadeCNH   | codigo | nome  |
  | "/api/v1/entregadores"     | "POST" | "12345678910"  | "2023-12-31"  |  201   | "Washington"|
  | "/api/v1/entregadores"     | "POST" | "98765432100"  | "2024-12-31"  |  201   | "Maria"|

  Scenario Outline: Criação de entregadores com falhas
    Given o endpoint <endpoint> do tipo <tipo>
    When eu enviar uma requisição para salvar um novo entregador com o documento <documento> e a validade da CNH <validadeCNH>
    Then eu recebo uma resposta com código <codigo>
    And conteudo com a mensagem <mensagem>

  Examples:
  |       endpoint             |  tipo  | documento      | validadeCNH   | codigo | mensagem                          |
  | "/api/v1/entregadores"     | "POST" | "12345678910"  | "2023-12-31"  |  400   | "O documento informado já existe"|
  | "/api/v1/entregadores"     | "POST" | "98765432100"  | "2024-12-31"  |  400   | "O documento informado não é válido"|

  Scenario Outline: Remoção de entregadores
    Given o endpoint <endpoint> do tipo <tipo>
    When eu enviar uma requisição para remover o entregador com ID <id>
    Then eu recebo uma resposta com código <codigo>

  Examples:
  |       endpoint                |  tipo  | id  | codigo |
  | "/api/v1/entregadores/remover"| "DELETE"| 1   |   200  |
  | "/api/v1/entregadores/remover"| "DELETE"| 2   |   404  |