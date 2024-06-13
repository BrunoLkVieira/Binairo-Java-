/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frontend;
import Backend.Board;
import Backend.Jogo;
import Backend.Card;
import Backend.Save;
import cores.StringColorida;

/**
 *
 * @author BrunoLK
 */
public class Interface {
    
    
    
 //================metodo para mostrar o MENU, utilizando teclas do teclado para selecionar a opção desejada pelo Usuario================
    public static void menu(){
        
        //definindo variaveis:
        console.Tecla tec;
        int escolha =1;
        int opcao = 1; 
        
        //print inicial do menu:
        console.Console.limpaTela();
        console.Console.print("============M E N U===========\n");
        console.Console.print("_______________________________\n");
        console.Console.print("|                             |\n");
        console.Console.print("|   (1 - Iniciar Novo Jogo)   |\n");
        console.Console.print("|    2 - Continuar Jogo       |\n");
        console.Console.print("|    3 - Carregar Jogo        |\n");
        console.Console.print("|    4 - Regras de jogo       |\n");
        console.Console.print("|    5 - Sair                 |\n");
        console.Console.print("_______________________________\n\n");
        
       
        tec = console.Console.getTecla();
       
          
        //loop para selecionar a opção desejada:
        while(tec != console.Tecla.ENTER){
            
            //movimento de acordo com a tecla pressionada:
            if(tec == console.Tecla.UP || tec == console.Tecla.W ){
                if (opcao !=1){
                    opcao-=1;
                }
            
            }
            if(tec == console.Tecla.DOWN || tec == console.Tecla.S ){
                if (opcao !=5){
                    opcao+=1;
                }
            }
            
            //mundança de estado do menu, printando o menu e a opção selecionada:
            switch(opcao){
                case 1:
                    console.Console.limpaTela();
                    
                    console.Console.print("============M E N U===========\n");
                    console.Console.print("_______________________________\n");
                    console.Console.print("|                             |\n");
                    console.Console.print("|   (1 - Iniciar Novo Jogo)   |\n");
                    console.Console.print("|    2 - Continuar Jogo       |\n");
                    console.Console.print("|    3 - Carregar Jogo        |\n");
                    console.Console.print("|    4 - Regras de jogo       |\n");
                    console.Console.print("|    5 - Sair                 |\n");
                    console.Console.print("_______________________________\n\n");
                    
                    break;
                case 2:
                    
                    console.Console.limpaTela();
                    
                    console.Console.print("============M E N U===========\n");
                    console.Console.print("_______________________________\n");
                    console.Console.print("|                             |\n");
                    console.Console.print("|    1 - Iniciar Novo Jogo    |\n");
                    console.Console.print("|   (2 - Continuar Jogo)      |\n");
                    console.Console.print("|    3 - Carregar Jogo        |\n");
                    console.Console.print("|    4 - Regras de jogo       |\n");
                    console.Console.print("|    5 - Sair                 |\n");
                    console.Console.print("_______________________________\n\n");    
                    
                    break;
                    
                    
                    
                case 3:
                    
                    console.Console.limpaTela();
                    
                    console.Console.print("============M E N U===========\n");
                    console.Console.print("_______________________________\n");
                    console.Console.print("|                             |\n");
                    console.Console.print("|    1 - Iniciar Novo Jogo    |\n");
                    console.Console.print("|    2 - Continuar Jogo       |\n");
                    console.Console.print("|   (3 - Carregar Jogo)       |\n");
                    console.Console.print("|    4 - Regras de jogo       |\n");
                    console.Console.print("|    5 - Sair                 |\n");
                    console.Console.print("_______________________________\n\n");    
                    
                    break;    
                case 4:
                    
                    console.Console.limpaTela();
                  
                    console.Console.print("============M E N U===========\n");
                    console.Console.print("_______________________________\n");
                    console.Console.print("|                             |\n");
                    console.Console.print("|    1 - Iniciar Novo Jogo    |\n");
                    console.Console.print("|    2 - Continuar Jogo       |\n");
                    console.Console.print("|    3 - Carregar Jogo        |\n");
                    console.Console.print("|   (4 - Regras de jogo)      |\n");
                    console.Console.print("|    5 - Sair                 |\n");
                    console.Console.print("_______________________________\n\n");
                    
                    break;
                case 5:
                    console.Console.limpaTela();
                  
                    console.Console.print("============M E N U===========\n");
                    console.Console.print("_______________________________\n");
                    console.Console.print("|                             |\n");
                    console.Console.print("|    1 - Iniciar Novo Jogo    |\n");
                    console.Console.print("|    2 - Continuar Jogo       |\n");
                    console.Console.print("|    3 - Carregar Jogo        |\n");
                    console.Console.print("|    4 - Regras de jogo       |\n");
                    console.Console.print("|   (5 - Sair)                |\n");
                    console.Console.print("_______________________________\n\n");
                    
                    break;
            }
          
           tec = console.Console.getTecla();       
          
           //se o ENTER for pressionado, a opção escolhida é definida e o loop se encerra
           if (tec == console.Tecla.ENTER){
              escolha = opcao;
              opcao =4;
              break;
           }
        }
        
        
        
        // opção : 1- Iniciar Novo Jogo 
        if(escolha==1){novojogo();}
        
        // opção : 2- Continuar Jogo
        if(escolha==2){Save.continuarJogo();}
       
        // opção : 3- Carregar Jogo
        if(escolha==3){
            console.Console.limpaTela();
            console.Console.print("Digite o caminho do Arquivo salvo: ");
            String caminho = console.Console.input();
            console.Console.limpaTela();
            try{ 
                Save.carregarJogo(caminho);  
            }catch(RuntimeException e){
              console.Console.limpaTela();
              console.Console.println("Ocorreu um erro ao ler o arquivo! O arquivo não existe ou esta com informações incorretas!");
              console.Console.input();
              console.Console.saiDoPrograma();
            }
          
        }
       
        // opção : 4- Regras
        if(escolha==4){regras();}
       
        // opção : 5- Sair
        if(escolha==5){console.Console.saiDoPrograma();}
       
    }
    
    
    
    //================metodo para mostrar as Regras de jogo================
    private static void regras(){
        
        console.Tecla tec;
       
        
        do{
            
        console.Console.limpaTela();
        console.Console.print("_________________________________________R E G R A S_________________________________________\n");
        console.Console.print("|                                                                                           |\n");
        console.Console.print("|   1. Cada linha e cada coluna deve conter um número igual de círculos brancos e pretos.   |\n");
        console.Console.print("|   2. Mais de dois círculos da mesma cor não podem ser adjacentes.                         |\n");
        console.Console.print("|   3. Cada linha e coluna é única                                                          |\n");
        console.Console.print("_____________________________________________________________________________________________\n");
        console.Console.print("__________________________________CLIQUE ENTER PARA VOLTAR____________________________________\n");
        tec = console.Console.getTecla();
        
        }while(tec!= console.Tecla.ENTER);
        menu();
    }
  
    
    
  //================metodo que mostra as possiveis dificuldades do Jogo================   
    private static void novojogo(){
        
        //definindo variaveis:
        int opcao = 1;
        int tam_tabuleiro = 0;
        console.Tecla tec;
        
        //loop para selecionar a opção desejada:
        do{
            
            //mundança de estado do menu, printando o menu e a opção selecionada:
            switch(opcao){
                case 1:
                    console.Console.limpaTela();
                    console.Console.print("======Selecione o Tamanho======\n");
                    console.Console.print("_______________________________\n");
                    console.Console.print("|                             |\n");
                    console.Console.print("|     (  1 -     4x4    )     |\n");
                    console.Console.print("|        2 -     6x6          |\n");
                    console.Console.print("|        3 -     8x8          |\n");
                    console.Console.print("|        4 -    10x10         |\n");
                    console.Console.print("|        5 -    12x12         |\n");
                    console.Console.print("_______________________________\n\n");
                    tam_tabuleiro = 4;
                    break;
                case 2:
                    console.Console.limpaTela();
                    console.Console.print("======Selecione o Tamanho======\n");
                    console.Console.print("_______________________________\n");
                    console.Console.print("|                             |\n");
                    console.Console.print("|        1 -     4x4          |\n");
                    console.Console.print("|     (  2 -     6x6    )     |\n");
                    console.Console.print("|        3 -     8x8          |\n");
                    console.Console.print("|        4 -    10x10         |\n");
                    console.Console.print("|        5 -    12x12         |\n");
                    console.Console.print("_______________________________\n\n"); 
                    tam_tabuleiro = 6;
                    break;
                case 3:
                    console.Console.limpaTela();
                    console.Console.print("======Selecione o Tamanho======\n");
                    console.Console.print("_______________________________\n");
                    console.Console.print("|                             |\n");
                    console.Console.print("|        1 -     4x4          |\n");
                    console.Console.print("|        2 -     6x6          |\n");
                    console.Console.print("|     (  3 -     8x8    )     |\n");
                    console.Console.print("|        4 -    10x10         |\n");
                    console.Console.print("|        5 -    12x12         |\n");
                    console.Console.print("_______________________________\n\n");
                    tam_tabuleiro = 8;
                    break;
                
                case 4:
                    console.Console.limpaTela();
                    console.Console.print("======Selecione o Tamanho======\n");
                    console.Console.print("_______________________________\n");
                    console.Console.print("|                             |\n");
                    console.Console.print("|        1 -     4x4          |\n");
                    console.Console.print("|        2 -     6x6          |\n");
                    console.Console.print("|        3 -     8x8          |\n");
                    console.Console.print("|     (  4 -    10x10   )     |\n");
                    console.Console.print("|        5 -    12x12         |\n");
                    console.Console.print("_______________________________\n\n");
                    tam_tabuleiro = 10;
                    break;
                
                case 5:
                    console.Console.limpaTela();
                    console.Console.print("======Selecione o Tamanho======\n");
                    console.Console.print("_______________________________\n");
                    console.Console.print("|                             |\n");
                    console.Console.print("|        1 -     4x4          |\n");
                    console.Console.print("|        2 -     6x6          |\n");
                    console.Console.print("|        3 -     8x8          |\n");
                    console.Console.print("|        4 -    10x10         |\n");
                    console.Console.print("|     (  5 -    12x12   )     |\n");
                    console.Console.print("_______________________________\n\n");
                    tam_tabuleiro = 12;
                    break;
            }
            
            tec = console.Console.getTecla();

            //movimento de acordo com a tecla pressionada:
            if(tec == console.Tecla.UP || tec == console.Tecla.W ){
                if (opcao <=1){
                    opcao = 5;
                }else{opcao-=1;}
            
            }
            
            if(tec == console.Tecla.DOWN || tec == console.Tecla.S ){
                if (opcao ==5){
                    opcao = 1;
                }else{opcao+=1;}
            }

        }while(tec != console.Tecla.ENTER);
        
        console.Console.limpaTela(); 
        
        //criando o tabuleiro com o tamanho selecionado
        Jogo.inicializaJogo(tam_tabuleiro);          
    }

    
    
//================metodo para mostrar o tabuleiro e os movimentos feitos pelo usuario================
    public static void mostrarTabuleiro(Board tabuleiro) throws IllegalArgumentException{
         if (tabuleiro == null) {
            throw new IllegalArgumentException("O parâmetro 'tabuleiro' não pode ser nulo.");
        } 
         
        //definindo variaveis: 
        int L=0;
        int C = 0;
        int espacoClick=0;
        
        StringColorida stringFundo = new StringColorida("[ - ]", cores.Cor.BEGE);
        StringColorida stringFundoSelect = new StringColorida("[ - ]", cores.Cor.BEGE, cores.Cor.FUNDO_BRANCO);
        Card fundoTab = new Card(stringFundo);
        Card fundoSelect = new Card(stringFundoSelect);
        
        StringColorida stringCarta0 = new StringColorida("[ 0 ]", cores.Cor.CIANO_CLARO);
        StringColorida stringCarta0Select = new StringColorida("[ 0 ]", cores.Cor.CIANO_CLARO, cores.Cor.FUNDO_BRANCO);
        StringColorida stringCarta0Fixa = new StringColorida("[ 0 ]", cores.Cor.AZUL);
        StringColorida stringCarta0FixaSelect = new StringColorida("[ 0 ]", cores.Cor.AZUL, cores.Cor.VERMELHO);
        StringColorida stringCarta0Errado = new StringColorida("[ 0 ]", cores.Cor.CIANO_CLARO, cores.Cor.FUNDO_VERMELHO);
        Card carta0 = new Card(stringCarta0);
        Card carta0Select = new Card(stringCarta0Select);
        Card carta0Fixa = new Card(stringCarta0Fixa);
        Card carta0FixaSelect = new Card(stringCarta0FixaSelect);
        Card carta0Errado = new Card(stringCarta0Errado);
        
        StringColorida stringCarta1 = new StringColorida("[ 1 ]", cores.Cor.ROSA);        
        StringColorida stringCarta1Select = new StringColorida("[ 1 ]", cores.Cor.ROSA, cores.Cor.FUNDO_BRANCO);        
        StringColorida stringCarta1Fixa = new StringColorida("[ 1 ]", cores.Cor.ROSA_CHOQUE);
        StringColorida stringCarta1FixaSelect = new StringColorida("[ 1 ]", cores.Cor.ROSA_CHOQUE, cores.Cor.VERMELHO);
        StringColorida stringCarta1Errado = new StringColorida("[ 1 ]", cores.Cor.ROSA, cores.Cor.FUNDO_VERMELHO); 
        Card carta1Errado = new Card(stringCarta1Errado);
        Card carta1 = new Card(stringCarta1);
        Card carta1Select = new Card(stringCarta1Select);
        Card carta1Fixa = new Card(stringCarta1Fixa);
        Card carta1FixaSelect = new Card(stringCarta1FixaSelect);        
               
        console.Tecla tec;
        
        
        console.Console.limpaTela();
          
        //mostrando o tabuleiro com a primeira carta selecionada:
        if(tabuleiro.getPilha(L, C).verTopo().equals(fundoTab)){  
            tabuleiro.colocaCarta(L, C, fundoSelect);
        }
           
        if(tabuleiro.getPilha(L, C).verTopo().equals(carta0)){
           tabuleiro.colocaCarta(L, C, carta0Select);
        }
        
        if(tabuleiro.getPilha(L, C).verTopo().equals(carta1)){
            tabuleiro.colocaCarta(L, C, carta1Select);
        }
       
        if(tabuleiro.getPilha(L, C).verTopo().equals(carta0Fixa)){
            tabuleiro.colocaCarta(L, C,carta0FixaSelect);
        }
       
        if(tabuleiro.getPilha(L, C).verTopo().equals(carta1Fixa)){
            tabuleiro.colocaCarta(L, C, carta1FixaSelect);
        }
        console.Console.print(tabuleiro);
        //--------------------------------------------------------
        
        //mudando o estado do tabuleiro de acordo com a tecla pressionada:
        boolean terminal=false;
        do{
            
            int C_anterior=C;
            int L_anterior=L;
            tec = console.Console.getTecla();
            tabuleiro.setFundo(L, C, fundoTab );
            
            
            if(tec == console.Tecla.RIGHT || tec == console.Tecla.D ){
                
                if (C < tabuleiro.getTotalColunas()-1){
                    tabuleiro.pegaCarta(L, C);
                    C+=1;
                    
                    espacoClick =0;
                }
            }
            
            if(tec == console.Tecla.LEFT || tec == console.Tecla.A ){
                if (C > 0){
                   tabuleiro.pegaCarta(L, C);
                    C-=1;
                    
                    espacoClick =0;
                }
            }
            
            if(tec == console.Tecla.UP || tec == console.Tecla.W ){
                if (L > 0 ){
                    tabuleiro.pegaCarta(L, C);
                    L-=1;
                    
                    espacoClick =0;
                }
            }
            
            if(tec == console.Tecla.DOWN || tec == console.Tecla.S ){
                if (L < tabuleiro.getTotalLinhas()-1){
                    tabuleiro.pegaCarta(L, C);
                    L+=1;
                   
                    espacoClick =0;
                }
            }
            
            if(tabuleiro.getPilha(L, C).verTopo()!= carta1Errado && tabuleiro.getPilha(L, C).verTopo()!= carta0Errado ){
                if(Jogo.isTabuleiroCompleto(tabuleiro)){

                    if(tabuleiro.getPilha(L_anterior, C_anterior).verTopo()!= carta0Errado){
                       tabuleiro.colocaCarta(L_anterior, C_anterior, carta1);
                    }
                    if(Jogo.isLinhasColunasUnicas(tabuleiro)==true){
                       terminal=true;
                    }
                }
                
                
                
            } 
            
            
            if (terminal==false){
            
           
                if(tabuleiro.getPilha(L, C).verTopo().equals(fundoTab)){
                    
                    tabuleiro.colocaCarta(L, C, fundoSelect);
                }
              
                if(tabuleiro.getPilha(L, C).verTopo().equals(carta0)){

                    tabuleiro.colocaCarta(L, C, carta0Select);
                }
                
                if(tabuleiro.getPilha(L, C).verTopo().equals(carta1)){

                    tabuleiro.colocaCarta(L, C, carta1Select);
                }
                
                if(tabuleiro.getPilha(L, C).verTopo().equals(carta0Fixa)){

                    tabuleiro.colocaCarta(L, C,carta0FixaSelect);
                }
                
                if(tabuleiro.getPilha(L, C).verTopo().equals(carta1Fixa)){

                    tabuleiro.colocaCarta(L, C, carta1FixaSelect);
                }
                
                if(tabuleiro.getPilha(L, C).verTopo().equals(carta0Errado)){
                    tabuleiro.colocaCarta(L, C, fundoTab);
                    tabuleiro.colocaCarta(L, C, carta0Errado);
                }else{
                    if(tabuleiro.getPilha(L, C).verTopo().equals(carta1Errado)){
                        tabuleiro.colocaCarta(L , C, fundoTab);
                        tabuleiro.colocaCarta(L, C, carta1Errado);
                    }    
                }
            }
                 
                    
                    
            
            
            //se a tecla pressionada for ESPAÇO, ele coloca a carta no local escolhido pelo usuario:
            if(tec == console.Tecla.ESPACO || tec == console.Tecla.ENTER ){
                if(tabuleiro.getPilha(L, C).verTopo() != carta0FixaSelect&& tabuleiro.getPilha(L, C).verTopo()!= carta1FixaSelect){ 
                    
                   try{ 
                     Jogo.movimento(tabuleiro, L, C);      
                   }catch(RuntimeException e){
                       console.Console.limpaTela();
                       console.Console.println("Erro ao tentar salvar o movimento, é necessario acesso de administrador");
                       console.Console.input();
                       console.Console.saiDoPrograma();
                       
                   }
                
                
                }
                
            }
            //--------------------------------------------------------------------------------------------------------------------------
            
            // se a tecla pressionada for ESC, ele abre o menu de pause:
            if(tec == console.Tecla.ESC  ){
                tabuleiro.pegaCarta(L, C);
                Pause(tabuleiro);
            }
            //----------------------------------------------------------
            
            console.Console.limpaTela();
            console.Console.print(tabuleiro);
            

        }while(terminal==false);
        fim(tabuleiro);
    }
        
    
    
 //================metodo para mostrar o menu de pause================    
     private static void Pause(Board tabuleiro)throws IllegalArgumentException{
        if (tabuleiro == null) {
            throw new IllegalArgumentException("O parâmetro 'tabuleiro' não pode ser nulo.");
        } 
        
        //definição de variaveis:
        int opcao = 1;
        console.Tecla tec;
        
        
        do{
            //mundança de estado do menu de pause, printando o menu e a opção selecionada:
            switch(opcao){
                case 1:
                    console.Console.limpaTela();
                    console.Console.print("==================P A U S E=================\n");
                    console.Console.print("____________________________________________\n");
                    console.Console.print("|    (  1 - Iniciar Novo Jogo    )          |\n");
                    console.Console.print("|       2 - Carregar Jogo                   |\n");
                    console.Console.print("|       3 - Salvar Jogo                     |\n");
                    console.Console.print("|       4 - Voltar para o Jogo              |\n");
                    console.Console.print("|       5 - Voltar ao Menu Principal        |\n");
                    console.Console.print("|       6 - Sair                            |\n");
                    console.Console.print("____________________________________________\n\n");
                    break;
                    
                case 2:
                    console.Console.limpaTela();
                    console.Console.print("==================P A U S E=================\n");
                    console.Console.print("____________________________________________\n");
                    console.Console.print("|       1 - Iniciar Novo Jogo               |\n");
                    console.Console.print("|   (   2 - Carregar Jogo     )             |\n");
                    console.Console.print("|       3 - Salvar Jogo                     |\n");
                    console.Console.print("|       4 - Voltar para o Jogo              |\n");
                    console.Console.print("|       5 - Voltar ao Menu Principal        |\n");
                    console.Console.print("|       6 - Sair                            |\n");
                    console.Console.print("____________________________________________\n\n");
                    break;
                    
                case 3:
                    console.Console.limpaTela();
                    console.Console.print("==================P A U S E=================\n");
                    console.Console.print("____________________________________________\n");
                    console.Console.print("|       1 - Iniciar Novo Jogo               |\n");
                    console.Console.print("|       2 - Carregar Jogo                   |\n");
                    console.Console.print("|   (   3 - Salvar Jogo     )               |\n");
                    console.Console.print("|       4 - Voltar para o Jogo              |\n");
                    console.Console.print("|       5 - Voltar ao Menu Principal        |\n");
                    console.Console.print("|       6 - Sair                            |\n");
                    console.Console.print("____________________________________________\n\n");
                    break;
                
                case 4:
                    console.Console.limpaTela();
                    console.Console.print("==================P A U S E=================\n");
                    console.Console.print("____________________________________________\n");
                    console.Console.print("|       1 - Iniciar Novo Jogo               |\n");
                    console.Console.print("|       2 - Carregar Jogo                   |\n");
                    console.Console.print("|       3 - Salvar Jogo                     |\n");
                    console.Console.print("|   (   4 - Voltar para o Jogo     )        |\n");
                    console.Console.print("|       5 - Voltar ao Menu Principal        |\n");
                    console.Console.print("|       6 - Sair                            |\n");
                    console.Console.print("____________________________________________\n\n");
                    break;
                
                case 5:
                    console.Console.limpaTela();
                    console.Console.print("==================P A U S E=================\n");
                    console.Console.print("____________________________________________\n");
                    console.Console.print("|       1 - Iniciar Novo Jogo               |\n");
                    console.Console.print("|       2 - Carregar Jogo                   |\n");
                    console.Console.print("|       3 - Salvar Jogo                     |\n");
                    console.Console.print("|       4 - Voltar para o Jogo              |\n");
                    console.Console.print("|   (   5 - Voltar ao Menu Principal     )  |\n");
                    console.Console.print("|       6 - Sair                            |\n");
                    console.Console.print("____________________________________________\n\n");
                    break;
                    
                case 6:
                    console.Console.limpaTela();
                    console.Console.print("==================P A U S E=================\n");
                    console.Console.print("____________________________________________\n");
                    console.Console.print("|       1 - Iniciar Novo Jogo               |\n");
                    console.Console.print("|       2 - Carregar Jogo                   |\n");
                    console.Console.print("|       3 - Salvar Jogo                     |\n");
                    console.Console.print("|       4 - Voltar para o Jogo              |\n");
                    console.Console.print("|       5 - Voltar ao Menu Principal        |\n");
                    console.Console.print("|   (   6 - Sair     )                      |\n");
                    console.Console.print("____________________________________________\n\n");
                    break;
            }
            
            tec = console.Console.getTecla();

            //movimento de acordo com a tecla pressionada:
            if(tec == console.Tecla.UP || tec == console.Tecla.W ){
                if (opcao <=1){
                    opcao = 6;
                }else{opcao-=1;}
            
            }
            if(tec == console.Tecla.DOWN || tec == console.Tecla.S ){
                if (opcao ==6){
                    opcao = 1;
                }else{opcao+=1;}
            }
            
            
            
            if(tec ==console.Tecla.ESC){
                opcao=4;
                tec = console.Tecla.ENTER;
            }
            
       }while(tec != console.Tecla.ENTER);
        
        
        // opção: 1-Iniciar Novo Jogo
        if(opcao==1){novojogo();}
        
        // opção: 2- Carregar Jogo
        if(opcao==2){
            console.Console.limpaTela();
            String caminho = console.Console.input("Digite o caminho do Arquivo salvo: ");
            console.Console.limpaTela();
            try{ 
                Save.carregarJogo(caminho);  
            }catch(RuntimeException e){
                console.Console.limpaTela();
                console.Console.println("Ocorreu um erro ao ler o arquivo! O arquivo não existe ou esta com informações incorretas!");
                console.Console.input();
                mostrarTabuleiro(tabuleiro);
            }
        }
        
        // opção: 3-Salvar Jogo
        if(opcao==3){
            console.Console.limpaTela();
            String caminho = console.Console.input("Digite o Caminho do Diretório + nome do aquivo onde deseja salvar o Jogo: ");
            
            try{ 
                Save.salvarJogo(tabuleiro,caminho); 
            }catch(RuntimeException e){
                console.Console.limpaTela();
                console.Console.println("Ocorreu um erro ao escrever no arquivo!");
                console.Console.input();
                mostrarTabuleiro(tabuleiro);
            }
        }
        
        // opção: 4-Voltar para o Jogo
        if(opcao==4 || tec ==console.Tecla.ESC ){mostrarTabuleiro(tabuleiro);}
           
        // opção: 5-Voltar ao menu principal
        if(opcao==5){menu();}
       
        // opção: 6-Sair
        if(opcao==6){console.Console.saiDoPrograma();}
     
     }
     
     
     
 //================metodo para mostrar infração de regra: Linhas iguais================     
    public static void linhasIguais(){

        console.Tecla tec;
        do{
        console.Console.limpaTela();
        console.Console.print("_________________________________________R E G R A S_________________________________________\n");
        console.Console.print("|                                                                                           |\n");
        console.Console.print("|                                ERRO:     TEM LINHAS IGUAIS.                               |\n");
        console.Console.print("|                                                                                           |\n");
        console.Console.print("_____________________________________________________________________________________________\n");
        console.Console.print("__________________________________CLIQUE ENTER PARA VOLTAR____________________________________\n");
        tec = console.Console.getTecla();
        }while(tec!= console.Tecla.ENTER);
        
    } 

    
    
 //================metodo para mostrar infração de regra: Colunas iguais================    
     public static void colunasIguais(){
         
         console.Tecla tec;
        do{
        console.Console.limpaTela();
        console.Console.print("_________________________________________R E G R A S_________________________________________\n");
        console.Console.print("|                                                                                           |\n");
        console.Console.print("|                                ERRO:     TEM COLUNAS IGUAIS.                              |\n");
        console.Console.print("|                                                                                           |\n");
        console.Console.print("_____________________________________________________________________________________________\n");
        console.Console.print("__________________________________CLIQUE ENTER PARA VOLTAR____________________________________\n");
        tec = console.Console.getTecla();
        }while(tec!= console.Tecla.ENTER);
        
     }
     

//================metodo para mostrar o fim do jogo================       
    private static void fim(Board tabuleiro)throws IllegalArgumentException{
        if (tabuleiro == null) {
            throw new IllegalArgumentException("O parâmetro 'tabuleiro' não pode ser nulo.");
        } 
        console.Console.limpaTela();
        console.Console.println(tabuleiro);
        console.Console.print("___________________________________________________________________________________________\n");
        console.Console.print("|                                                                                           |\n");
        console.Console.print("|                    PARABENS!!! O tabuleiro foi completado corretamente                    |\n");
        console.Console.print("|                                                                                           |\n");
        console.Console.print("_____________________________________________________________________________________________\n");
        console.Console.getTecla();
        console.Console.saiDoPrograma();
    }    
}
