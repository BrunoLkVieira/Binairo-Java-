/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;
import cores.StringColorida;
import Frontend.Interface;
import java.util.Random;
import java.util.*;

/**
 *
 * @author BrunoLK
 */
public class Jogo {
    
    
    
//================metodo que cria o tabuleiro e chama os metodos para gerar um jogo e mostrar o tabuleiro================ 
    public static void inicializaJogo(int tam) throws IllegalArgumentException{
        if ((tam %2) !=0 || tam <4 ) {
            throw new IllegalArgumentException("O parâmetro 'tamanho' não pode ser menor que 4 e impar.");
        }   
        
        //criação do tabuleiro utilizando o tamanho escolhido 
        StringColorida stringFundo = new StringColorida("[---]", cores.Cor.BEGE);
        Card fundoTab = new Card(stringFundo);
        Board tabuleiro = new Board(tam, tam, fundoTab);
        //---------------------------------------------------
        
        //chama o metodo para gerar as cartas fixas:
        gerarTabuleiro(tabuleiro);     
        
        //chama o metodo para mostrar o tabuleiro gerado:
        Interface.mostrarTabuleiro(tabuleiro);
    }
    
    
   
//================metodo que gera as cartas fixas no tabuleiro================ 
    private static void gerarTabuleiro(Board tabuleiro) throws IllegalArgumentException{
        if (tabuleiro == null) {
            throw new IllegalArgumentException("O parâmetro 'tabuleiro' não pode ser nulo.");
        }   
        
        
        //Definição de variaveis:
        Random random = new Random();
        int tamanho = tabuleiro.getTotalColunas();
        int[][]tabuleiro_temp = new int[tamanho][tamanho];
        int quantidadeFixas_linhas= 0;
        boolean geracao = false; 
        
        
        
        //loop para gerar um tabuleiro varias vezes ate ser um tabuleiro valido:
        while(geracao==false){
            
            
            //de acordo com o tamanho, tem uma quantidade diferentes de cartas fixas
            if(tamanho==4){quantidadeFixas_linhas=2;}
            if(tamanho==6 ||tamanho==8 ||tamanho==10 || tamanho==12){quantidadeFixas_linhas=3;}
            
            
            
            //defini um tabuleiro(matriz) temporario vazio
            for (int i = 0; i < tamanho; i++){

                for (int j = 0; j < tamanho; j++){
                    tabuleiro_temp[i][j]=2;
                }    
            }       
            
            //coloca 0s e 1s aleatoriamente no tabuleiro, verificando se é um movimento possivel ou nao
            for (int i = 0; i < tamanho; i++){               
                for (int j = 0; j < quantidadeFixas_linhas; j++){
                    int r = random.nextInt(tamanho);  
                    int randomNumber = random.nextInt(2);

                    if (verificaMovimento(tabuleiro_temp,i,r,randomNumber)==true){
                        tabuleiro_temp[i][r]=randomNumber;
                    } 
                }    
            }  
            
            
            // verifica onde esta vazio, testando se é possivel colocar 0 ou 1. Caso nao seja possivel colocar nenhum dos dois, ele guarda um erro 
            int contError=0;
            for (int i = 0; i < tamanho; i++){
                for (int j = 0; j < tamanho; j++){
                    
                    if(tabuleiro_temp[i][j]==2){
                        if (verificaMovimento(tabuleiro_temp,i,j,0)==false && verificaMovimento(tabuleiro_temp,i,j,1)==false  ){
                            contError+=1;
                        }else{
                            
                        }
                    } 
                }
            }
            
            //se ainda nao tiver erro, ele faz outro teste
            if(contError==0){
                
                //definindo tabuleiro teste igual ao tabuleiro temp
                int[][]tabuleiro_teste = new int[tamanho][tamanho];
                for (int i = 0; i < tamanho; i++){
                    for (int j = 0; j < tamanho; j++){
                        if (tabuleiro_temp[i][j]==0){tabuleiro_teste[i][j]=0;}
                        if (tabuleiro_temp[i][j]==1){tabuleiro_teste[i][j]=1;}
                        if (tabuleiro_temp[i][j]==2){tabuleiro_teste[i][j]=2;}
                    }
                }
                
                
                
                //loop que coloca 1 ou 0 nos locais onde só é possivel um ou outro, faz esse loop 2 vezes.  Caso nao seja possivel colocar nenhum dos dois, ele guarda um erro
                 for (int k = 0; k < 2; k++){
                    for (int i = 0; i < tamanho; i++){
                       for (int j = 0; j < tamanho; j++){
                            if(tabuleiro_teste[i][j]==2){
                                if (verificaMovimento(tabuleiro_teste,i,j,0)== true && verificaMovimento(tabuleiro_teste,i,j,1)== false){
                                    tabuleiro_teste[i][j]= 0;
                                }
                                
                                if(verificaMovimento(tabuleiro_teste,i,j,0)== false && verificaMovimento(tabuleiro_teste,i,j,1)== true){
                                    tabuleiro_teste[i][j]= 1;
                                }
                                
                                if(verificaMovimento(tabuleiro_teste,i,j,0)== false && verificaMovimento(tabuleiro_teste,i,j,1)== false){
                                    contError++;
                                }
                            }    
                        }
                    }
                }
            }
            
            
            //se não tiver nenhum erro, o loop principal se encerra.
            if(contError == 0){ 
                geracao=true;
            }else{
                geracao = false;
            }
        }
        
        
        // Definido as cartas 
        StringColorida stringVazia = new StringColorida("[ - ]", cores.Cor.BEGE);
        StringColorida stringCarta0 = new StringColorida("[ 0 ]", cores.Cor.AZUL);
        StringColorida stringCarta1 = new StringColorida("[ 1 ]", cores.Cor.ROSA_CHOQUE);
        Card carta0Fixa = new Card(stringCarta0);
        Card carta1Fixa = new Card(stringCarta1);
        Card cartaVazia = new Card(stringVazia);
        
        //transformando o tabuleiro temporario no tabuleiro principal de cartas
        for (int i = 0; i < tamanho; i++){
           
            for (int j = 0; j < tamanho; j++){
                if( tabuleiro_temp[i][j]== 0){
                    tabuleiro.colocaCarta(i, j, carta0Fixa);
                }else{
                
                    if( tabuleiro_temp[i][j]== 1){
                        tabuleiro.colocaCarta(i, j, carta1Fixa);
                    }else{
                        tabuleiro.colocaCarta(i, j, cartaVazia);
                    }
                }
            }
        }
        
        console.Console.print(tabuleiro);
    }
    
    
    
//================metodo para verificar cada movimento feito, seguindo as regras do jogo================     
    private static boolean verificaMovimento(int[][]tabuleiro, int linha, int coluna, int valor)throws IllegalArgumentException   {
        
        if (tabuleiro == null  ) {
            throw new IllegalArgumentException("O parâmetro 'tabuleiro' não pode ser nulo.");
        }
         if (linha >= tabuleiro.length || linha < 0||coluna >= tabuleiro.length || coluna < 0) {
            throw new IllegalArgumentException("Os parâmetro 'Linha' e 'coluna' devem ser entre 0 e (tamanho do tabuleiro)-1 .");
        }  
         
        
        
        
        int verificacao = 0;
        int numeros0=0;
        int numeros1=0;
         int quantidadeMaxima= tabuleiro.length/2;
        
         
         //--------verifica se o movimento adiciona uma sequencia com mais de dois valores iguais na linha------------
         
        if(linha>1){
            
            if(tabuleiro[linha-1][coluna]!= 2 && tabuleiro[linha-2][coluna]!= 2 && tabuleiro[linha-1][coluna] == tabuleiro[linha-2][coluna] && tabuleiro[linha-1][coluna]==valor){
                verificacao = 1;
            
            }    
        } 
        
        if(linha<tabuleiro.length-2){
            if(tabuleiro[linha+1][coluna]!= 2 && tabuleiro[linha+2][coluna]!= 2 && tabuleiro[linha+1][coluna] == tabuleiro[linha+2][coluna] && tabuleiro[linha+1][coluna]==valor){
                verificacao = 1;
            
            }  
        }
        if(linha>0 && linha<tabuleiro.length-1){
            if(tabuleiro[linha+1][coluna]==valor && valor== tabuleiro[linha-1][coluna]){
                verificacao = 1;
            }
        }
           
        
        //--------verifica se o movimento adiciona uma sequencia com mais de dois valores iguais na coluna------------
        
        if(coluna>1){
            
            if(tabuleiro[linha][coluna-1]!= 2 && tabuleiro[linha][coluna-2]!= 2 && tabuleiro[linha][coluna-1] == tabuleiro[linha][coluna-2] && tabuleiro[linha][coluna-1]==valor){
                verificacao = 1;
            } 
        }
            
        if(coluna<tabuleiro.length-2){
            if(tabuleiro[linha][coluna+1]!= 2 && tabuleiro[linha][coluna+2]!= 2 && tabuleiro[linha][coluna+1] == tabuleiro[linha][coluna+2] && tabuleiro[linha][coluna+1]==valor){
                verificacao = 1;
            }    
        }
        
        if(coluna>0 && coluna<tabuleiro.length-1){
            if(tabuleiro[linha][coluna+1]==valor && valor== tabuleiro[linha][coluna-1]){
                verificacao = 1;
            }
        }
            
        
        
        
        //------------verifica se tem mais 0s e 1s do que o permitido em uma mesma linha------------
        
         for (int i = 0; i < tabuleiro.length; i++){
             if (tabuleiro[linha][i]==0){numeros0 +=1;}
             if (tabuleiro[linha][i]==1){numeros1 +=1;}
             
         }
         if( (valor==0 && numeros0 ==quantidadeMaxima) || (valor == 1 && numeros1==quantidadeMaxima) ){
             verificacao = 1;
         }
         
          //------------verifica se tem mais 0s e 1s do que o permitido em uma mesma coluna------------
        numeros0=0;
        numeros1=0;
        for (int i = 0; i < tabuleiro.length; i++){
             if (tabuleiro[i][coluna]==0){numeros0 +=1;}
             if (tabuleiro[i][coluna]==1){numeros1 +=1;}
             
         }
         if( (valor==0 && numeros0 ==quantidadeMaxima) || (valor == 1 && numeros1==quantidadeMaxima) ){
             verificacao = 1;
         }
        
        
        
        
        if(verificacao == 1){
            return false;
        }else{
            return true;
        }
    }
        
   
    
//================metodo para executar um movimento, para cada movimento é chamado o metodo verificaMovimento()================     
    public static void movimento(Board tabuleiro,int linha, int coluna)throws IllegalArgumentException{
        
        if (tabuleiro == null) {
            throw new IllegalArgumentException("O parâmetro 'tabuleiro' não pode ser nulo.");
        }   
        
        if (linha >= tabuleiro.getTotalColunas() || linha < 0||coluna >= tabuleiro.getTotalColunas() || coluna < 0) {
            throw new IllegalArgumentException("Os parâmetro 'Linha' e 'coluna' devem ser entre 0 e (tamanho do tabuleiro)-1 .");
        } 
         
        
        
        //Definindo as cartas:
        StringColorida stringFundo = new StringColorida("[ - ]", cores.Cor.BEGE);
        StringColorida stringFundoSelect = new StringColorida("[ - ]", cores.Cor.BEGE, cores.Cor.FUNDO_BRANCO);
        Card fundoTab = new Card(stringFundo);
        Card fundoTabSelect = new Card(stringFundoSelect);
                
        StringColorida stringCarta0 = new StringColorida("[ 0 ]", cores.Cor.CIANO_CLARO);
        StringColorida stringCarta0Select = new StringColorida("[ 0 ]", cores.Cor.CIANO_CLARO, cores.Cor.FUNDO_BRANCO);
        StringColorida stringCarta0Errado = new StringColorida("[ 0 ]", cores.Cor.CIANO_CLARO, cores.Cor.FUNDO_VERMELHO);
        StringColorida stringCarta0Fixa = new StringColorida("[ 0 ]", cores.Cor.AZUL);
        StringColorida stringCarta0FixaSelect = new StringColorida("[ 0 ]", cores.Cor.AZUL, cores.Cor.VERMELHO);
        Card carta0 = new Card(stringCarta0);
        Card carta0Select = new Card(stringCarta0Select);
        Card carta0Fixa = new Card(stringCarta0Fixa);
        Card carta0Errado = new Card(stringCarta0Errado);
        Card carta0FixaSelect = new Card(stringCarta0FixaSelect);
        
        StringColorida stringCarta1 = new StringColorida("[ 1 ]", cores.Cor.ROSA);        
        StringColorida stringCarta1Select = new StringColorida("[ 1 ]", cores.Cor.ROSA, cores.Cor.FUNDO_BRANCO);       
        StringColorida stringCarta1Errado = new StringColorida("[ 1 ]", cores.Cor.ROSA, cores.Cor.FUNDO_VERMELHO);  
        StringColorida stringCarta1Fixa = new StringColorida("[ 1 ]", cores.Cor.ROSA_CHOQUE);
        StringColorida stringCarta1FixaSelect = new StringColorida("[ 1 ]", cores.Cor.ROSA_CHOQUE, cores.Cor.VERMELHO);
        Card carta1 = new Card(stringCarta1);
        Card carta1Select = new Card(stringCarta1Select);
        Card carta1Fixa = new Card(stringCarta1Fixa);
        Card carta1FixaSelect = new Card(stringCarta1FixaSelect);        
        Card carta1Errado = new Card(stringCarta1Errado);
         
        
        //Definindo as variaveis:
        int tamanho=tabuleiro.getTotalLinhas();
        int[][]tabuleiro_temp = new int[tamanho][tamanho];
        int valorCelula=2;
        int valor=2;
         
        
        
        
        //retira as cartas de seleção:
        if(tabuleiro.getPilha(linha, coluna).verTopo().equals(carta1Select) || tabuleiro.getPilha(linha, coluna).verTopo().equals(carta1Errado)){ 
            tabuleiro.pegaCarta(linha, coluna);
            tabuleiro.pegaCarta(linha, coluna);
            tabuleiro.colocaCarta(linha, coluna, fundoTab );
            Save.salvarAutomatico(tabuleiro);
            tabuleiro.colocaCarta(linha, coluna, fundoTabSelect);
        }else{
        
            if(tabuleiro.getPilha(linha, coluna).verTopo().equals(carta0Select) || tabuleiro.getPilha(linha, coluna).verTopo().equals(carta0Errado)){
                valor =1;
            }else{
                if(tabuleiro.getPilha(linha, coluna).verTopo().equals(fundoTabSelect) ){ 
                    valor=0;
                }
            }
         
           //transforma o tabuleiro em uma matriz para passar no processo do metodo que verifica cada movimento 
            for (int i = 0; i < tamanho; i++){

               for (int j = 0; j < tamanho; j++){

                   if(tabuleiro.getPilha(i, j).verTopo().equals(fundoTab)||tabuleiro.getPilha(i, j).verTopo().equals(fundoTabSelect)){
                       valorCelula=2;
                       
                   }else{
                       if(tabuleiro.getPilha(i, j).verTopo().equals(carta0) || tabuleiro.getPilha(i, j).verTopo().equals(carta0Fixa)|| tabuleiro.getPilha(i, j).verTopo().equals(carta0Select)||tabuleiro.getPilha(i, j).verTopo().equals(carta0Errado)){
                           valorCelula=0;
                       }else{
                           if(tabuleiro.getPilha(i, j).verTopo().equals(carta1) || tabuleiro.getPilha(i, j).verTopo().equals(carta1Fixa)|| tabuleiro.getPilha(i, j).verTopo().equals(carta1Select)||tabuleiro.getPilha(i, j).verTopo().equals(carta1Errado)){
                               valorCelula=1;
                           }
                       }
                   }
                   tabuleiro_temp[i][j] = valorCelula;
               }
           } 
            
            
            //verifica se o movimento é possivel ou não: se for possivel, coloca a carta, se não, coloca a  carta de erro.
            if(verificaMovimento(tabuleiro_temp, linha,coluna,valor)){
               if(tabuleiro.getPilha(linha, coluna).verTopo().equals(carta0Select) || tabuleiro.getPilha(linha, coluna).verTopo().equals(carta0Errado)){ 
                   tabuleiro.pegaCarta(linha, coluna);
                   tabuleiro.pegaCarta(linha, coluna);
                   tabuleiro.colocaCarta(linha, coluna, carta1);
                    Save.salvarAutomatico(tabuleiro);
                   tabuleiro.colocaCarta(linha, coluna, carta1Select);
               }else{
                    if(tabuleiro.getPilha(linha, coluna).verTopo().equals(fundoTabSelect) ){ 
                        tabuleiro.pegaCarta(linha, coluna);
                        tabuleiro.pegaCarta(linha, coluna);
                        tabuleiro.colocaCarta(linha, coluna, carta0);
                        Save.salvarAutomatico(tabuleiro);
                        tabuleiro.colocaCarta(linha, coluna, carta0Select);
                    }
               }
               

           }else{
               if(tabuleiro.getPilha(linha, coluna).verTopo().equals(carta0Select)){ 
                   tabuleiro.pegaCarta(linha, coluna);
                   tabuleiro.pegaCarta(linha, coluna);
                   tabuleiro.colocaCarta(linha, coluna, fundoTab);
                   
                   tabuleiro.colocaCarta(linha, coluna, carta1Errado); 
               }else{

                       if(tabuleiro.getPilha(linha, coluna).verTopo().equals(fundoTabSelect)){ 
                           tabuleiro.colocaCarta(linha, coluna, fundoTab);
                           tabuleiro.colocaCarta(linha, coluna, carta0Errado);
                       }
               }
            }
        }
    }
     
    
    
//================metodo que verifica se o tabuleiro esta completo ou não================     
    public static boolean isTabuleiroCompleto(Board tabuleiro)throws IllegalArgumentException{
       
        if (tabuleiro == null) {
            throw new IllegalArgumentException("O parâmetro 'tabuleiro' não pode ser nulo.");
        }   
        
        //Definição das Cartas 
        StringColorida stringFundo = new StringColorida("[ - ]", cores.Cor.BEGE);
        StringColorida stringFundoSelect = new StringColorida("[ - ]", cores.Cor.BEGE, cores.Cor.FUNDO_BRANCO);
        Card fundoTab = new Card(stringFundo);
        Card fundoSelect = new Card(stringFundoSelect);
        Card fundoTabSelect = new Card(stringFundoSelect);
        
        
        StringColorida stringCarta0 = new StringColorida("[ 0 ]", cores.Cor.CIANO_CLARO);
        StringColorida stringCarta0Select = new StringColorida("[ 0 ]", cores.Cor.CIANO_CLARO, cores.Cor.FUNDO_BRANCO);
        StringColorida stringCarta0Errado = new StringColorida("[ 0 ]", cores.Cor.CIANO_CLARO, cores.Cor.FUNDO_VERMELHO);
        StringColorida stringCarta0Fixa = new StringColorida("[ 0 ]", cores.Cor.AZUL);
        StringColorida stringCarta0FixaSelect = new StringColorida("[ 0 ]", cores.Cor.AZUL, cores.Cor.VERMELHO);
        Card carta0 = new Card(stringCarta0);
        Card carta0Select = new Card(stringCarta0Select);
        Card carta0Fixa = new Card(stringCarta0Fixa);
        Card carta0Errado = new Card(stringCarta0Errado);
        Card carta0FixaSelect = new Card(stringCarta0FixaSelect);
        
        
        StringColorida stringCarta1 = new StringColorida("[ 1 ]", cores.Cor.ROSA);        
        StringColorida stringCarta1Select = new StringColorida("[ 1 ]", cores.Cor.ROSA, cores.Cor.FUNDO_BRANCO);       
        StringColorida stringCarta1Errado = new StringColorida("[ 1 ]", cores.Cor.ROSA, cores.Cor.FUNDO_VERMELHO);  
        StringColorida stringCarta1Fixa = new StringColorida("[ 1 ]", cores.Cor.ROSA_CHOQUE);
        StringColorida stringCarta1FixaSelect = new StringColorida("[ 1 ]", cores.Cor.ROSA_CHOQUE, cores.Cor.VERMELHO);
        Card carta1 = new Card(stringCarta1);
        Card carta1Select = new Card(stringCarta1Select);
        Card carta1Fixa = new Card(stringCarta1Fixa);
        Card carta1Errado = new Card(stringCarta1Errado);
         
        
        //Definição de variaveis
         int tamanho=tabuleiro.getTotalLinhas();
         int[][]tabuleiro_temp = new int[tamanho][tamanho];
         int valorCelula=2;
         int error = 0;
         
         
         //definição do tabuleiro(matriz) temporario vazio
         for (int i = 0; i < tamanho; i++){

            for (int j = 0; j < tamanho; j++){
                tabuleiro_temp[i][j] = 2;
            }
         }
         
         
         
         int vazia=0;
         
         
          //verifica se todos os espaços do tabuleiro estão preenchidos com carta
         
        for (int i = 0; i < tamanho; i++){

            for (int j = 0; j < tamanho; j++){


                if(tabuleiro.getPilha(i, j).verTopo().equals(fundoTab)||tabuleiro.getPilha(i, j).verTopo().equals(fundoTabSelect)){
                    return false;
                    
                }else{

                    if(tabuleiro.getPilha(i, j).verTopo().equals(carta0) || tabuleiro.getPilha(i, j).verTopo().equals(carta0Fixa)|| tabuleiro.getPilha(i, j).verTopo().equals(carta0Select)|| tabuleiro.getPilha(i, j).verTopo().equals(carta0Errado)){
                        valorCelula=0;
                        if(verificaMovimento(tabuleiro_temp,i,j,valorCelula)==false){
                            error++;
                            vazia+=1;
                            
                            
                        }
                        
                    }else{
                        if(tabuleiro.getPilha(i, j).verTopo().equals(carta1) || tabuleiro.getPilha(i, j).verTopo().equals(carta1Fixa)|| tabuleiro.getPilha(i, j).verTopo().equals(carta1Select)|| tabuleiro.getPilha(i, j).verTopo().equals(carta1Errado)){
                            valorCelula=1;
                            if(verificaMovimento(tabuleiro_temp,i,j,valorCelula)==false){
                                error++;
                                vazia+=1;
                                
                            }
                        }
                    }
                    
                }
              
                
                
                

            }    
        }
        //----------------------------------------------------------------------------
        
        
        
        // se o tabuleiro estiver completo retorna true, se nao, false
        if(vazia==0 ){
            return true;
            
        }else{
            return false;
        }
 
    }   
     
    
     
//================metodo que verifica se as colunas e linhas são unicas quando o tabuleiro esta completo================ 
    public static boolean isLinhasColunasUnicas(Board tabuleiro)throws IllegalArgumentException{
        if (tabuleiro == null) {
            throw new IllegalArgumentException("O parâmetro 'tabuleiro' não pode ser nulo.");
        } 
        
        //Definição de cartas
        StringColorida stringFundo = new StringColorida("[ - ]", cores.Cor.BEGE);
        StringColorida stringFundoSelect = new StringColorida("[ - ]", cores.Cor.BEGE, cores.Cor.FUNDO_BRANCO);
        Card fundoTab = new Card(stringFundo);
        
        StringColorida stringCarta0 = new StringColorida("[ 0 ]", cores.Cor.CIANO_CLARO);
        StringColorida stringCarta0Select = new StringColorida("[ 0 ]", cores.Cor.CIANO_CLARO, cores.Cor.FUNDO_BRANCO);
        StringColorida stringCarta0Errado = new StringColorida("[ 0 ]", cores.Cor.CIANO_CLARO, cores.Cor.FUNDO_VERMELHO);
        StringColorida stringCarta0Fixa = new StringColorida("[ 0 ]", cores.Cor.AZUL);
        StringColorida stringCarta0FixaSelect = new StringColorida("[ 0 ]", cores.Cor.AZUL, cores.Cor.VERMELHO);
        Card carta0 = new Card(stringCarta0);
        Card carta0Select = new Card(stringCarta0Select);
        Card carta0Fixa = new Card(stringCarta0Fixa);
        Card carta0Errado = new Card(stringCarta0Errado);
        Card carta0FixaSelect = new Card(stringCarta0FixaSelect);
        
        
        StringColorida stringCarta1 = new StringColorida("[ 1 ]", cores.Cor.ROSA);        
        StringColorida stringCarta1Select = new StringColorida("[ 1 ]", cores.Cor.ROSA, cores.Cor.FUNDO_BRANCO);       
        StringColorida stringCarta1Errado = new StringColorida("[ 1 ]", cores.Cor.ROSA, cores.Cor.FUNDO_VERMELHO);  
        StringColorida stringCarta1Fixa = new StringColorida("[ 1 ]", cores.Cor.ROSA_CHOQUE);
        StringColorida stringCarta1FixaSelect = new StringColorida("[ 1 ]", cores.Cor.ROSA_CHOQUE, cores.Cor.VERMELHO);
        Card carta1 = new Card(stringCarta1);
        Card carta1Select = new Card(stringCarta1Select);
        Card carta1Fixa = new Card(stringCarta1Fixa);
        Card carta1FixaSelect = new Card(stringCarta1FixaSelect);        
        Card carta1Errado = new Card(stringCarta1Errado);
        Card fundoTabSelect = new Card(stringFundoSelect);
       
        
        //Definição de variaveis
        int tamanho = tabuleiro.getTotalColunas();
        int cont=0;
        
        
        
        //verifica se as linhas são unicas:
        for (int i = 0; i < tamanho-1; i++){
            cont=0;
            for (int k = i+1; k < tamanho; k++){
                for (int j = 0; j < tamanho; j++){



                    if((tabuleiro.getPilha(i, j).verTopo()!= fundoTab && tabuleiro.getPilha(i, j).verTopo()!= fundoTabSelect && tabuleiro.getPilha(k, j).verTopo()!= carta1Errado && tabuleiro.getPilha(i, j).verTopo()!= carta0Errado)&&(tabuleiro.getPilha(i, j).verTopo()!= fundoTab && tabuleiro.getPilha(k, j).verTopo()!= fundoTabSelect && tabuleiro.getPilha(k, j).verTopo()!= carta1Errado && tabuleiro.getPilha(k, j).verTopo()!= carta0Errado)){

                        if((tabuleiro.getPilha(i, j).verTopo().equals(tabuleiro.getPilha(k, j).verTopo())) ||
                           (tabuleiro.getPilha(i, j).verTopo().equals(carta1Fixa) && tabuleiro.getPilha(k, j).verTopo().equals(carta1)) ||  (tabuleiro.getPilha(i, j).verTopo().equals(carta1) && tabuleiro.getPilha(k, j).verTopo().equals(carta1Fixa))||
                           (tabuleiro.getPilha(i, j).verTopo().equals(carta0) && tabuleiro.getPilha(k, j).verTopo().equals(carta0Fixa)) ||  (tabuleiro.getPilha(i, j).verTopo().equals(carta0Fixa) && tabuleiro.getPilha(k, j).verTopo().equals(carta0))  ) {
                            cont=cont+1;


                        }else{
                           cont=0;
                        }


                    }
                    if((tabuleiro.getPilha(i, j).verTopo()== fundoTab && tabuleiro.getPilha(i, j).verTopo()== fundoTabSelect)&&(tabuleiro.getPilha(k, j).verTopo()== fundoTab && tabuleiro.getPilha(k, j).verTopo()== fundoTabSelect)){
                        cont=0;
                    }
                    if(cont==tamanho){
                                Interface.linhasIguais();
                                if(tabuleiro.getPilha(i, j).verTopo()!= carta0Fixa && tabuleiro.getPilha(i, j).verTopo()!= carta1Fixa){
                                    tabuleiro.colocaCarta(i, j, fundoTab);
                                }
                                if(tabuleiro.getPilha(k, j).verTopo()!= carta0Fixa && tabuleiro.getPilha(k, j).verTopo()!= carta1Fixa){
                                    tabuleiro.colocaCarta(k, j, fundoTab);
                                }
                                return false;
                            }



                }
            cont=0;    

            }    


               
        }
        
        
         //verifica se as colunas são unicas:
        for (int i = 0; i < tamanho-1; i++){
            cont=0;
                for (int k = i+1; k < tamanho; k++){
                    for (int j = 0; j < tamanho; j++){



                        if((tabuleiro.getPilha(j, i).verTopo()!= fundoTab && tabuleiro.getPilha(j, i).verTopo()!= fundoTabSelect)&&(tabuleiro.getPilha(j, k).verTopo()!= fundoTab && tabuleiro.getPilha(j, k).verTopo()!= fundoTabSelect && tabuleiro.getPilha(k, j).verTopo()!= carta1Errado && tabuleiro.getPilha(k, j).verTopo()!= carta0Errado)){
                            if(tabuleiro.getPilha(j, i).verTopo().equals(tabuleiro.getPilha(j, k).verTopo())||
                              (tabuleiro.getPilha(j, i).verTopo().equals(carta1Fixa) && tabuleiro.getPilha(j, k).verTopo().equals(carta1)) ||  (tabuleiro.getPilha(j, i).verTopo().equals(carta1) && tabuleiro.getPilha(j, k).verTopo().equals(carta1Fixa))||
                              (tabuleiro.getPilha(j, i).verTopo().equals(carta0) && tabuleiro.getPilha(j, k).verTopo().equals(carta0Fixa)) ||  (tabuleiro.getPilha(j, i).verTopo().equals(carta0Fixa) && tabuleiro.getPilha(j, k).verTopo().equals(carta0))  ) {
                                cont=cont+1;
                               
                                 
                            }else{
                               cont=0;
                            }
                            
                            
                        }
                        
                        if((tabuleiro.getPilha(j, i).verTopo()== fundoTab && tabuleiro.getPilha(j, i).verTopo()== fundoTabSelect)&&(tabuleiro.getPilha(j, k).verTopo()== fundoTab && tabuleiro.getPilha(j, k).verTopo()== fundoTabSelect)){
                            cont=0;
                        }
                        
                        if(cont==tamanho){
                                    
                                    Interface.colunasIguais();
                                    if(tabuleiro.getPilha(j, i).verTopo()!= carta0Fixa && tabuleiro.getPilha(j, i).verTopo()!= carta1Fixa){
                                        tabuleiro.colocaCarta(j, i, fundoTab);
                                    }
                                    if(tabuleiro.getPilha(j, k).verTopo()!= carta0Fixa && tabuleiro.getPilha(j, k).verTopo()!= carta1Fixa){
                                        tabuleiro.colocaCarta(j, k, fundoTab);
                                    }
                                    return false;
                        }
                        
                    }
                    cont=0;

                }    


                
        }
          
          
        return true;
    }
    
   

         
    
}
