# Binairo-Java-


## Descrição do projeto: 

O projeto é formado por 2 pacotes: Front-end e Back-end. Além de utilizar uma engine chamada cardgamemaker-0.3 

### Front-end:

  #### -class Interface: onde esta toda interface visualizada pelo usuario.
        -> menu 
        -> visualização do tabuleiro
        -> Menu de pause
        -> entre outras interface...


### Back-End:

  #### -Class Card:
        -> class filha da "Carta" da engine cardgamemaker-0.3 
        
  #### -Class Board:
        -> class filha da "Tabuleiro" da engine cardgamemaker-0.3 

  #### -class Jogo: onde fica a logica do jogo.
        -> geração do tabuleiro
        -> execução dos movimentos
        -> Verificações de regras

  #### -Class Save: onde faz o salvamento do jogo.
        ->  salvar e carregar um jogo manualmente
        ->  salvar e carregar automaticamente 
      
  
  ## Regras do Jogo: 

Binairo é um quebra-cabeça lógico com regras simples e soluções desafiadoras.

#### Binairo é jogado em uma grade retangular sem tamanho padrão. Algumas células começam preenchidas com círculos Vermelhos ou Azuis. O resto das células estão vazias. O objetivo é colocar círculos em todas as células de forma que:
      1. Cada linha e cada coluna deve conter um número igual de círculos Vermelhos(1) e Azuis(0).
      2. Mais de dois círculos da mesma cor não podem ser adjacentes.
      3. Cada linha e coluna é única.
    Se mova pelo tabuleiro utilizando as setas, pressione ENTER/ESPAÇO para alternar entre as cartas azul(0), vermelha(1) e vazia.
