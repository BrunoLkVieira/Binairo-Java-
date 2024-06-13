/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Frontend.Interface;
import cores.StringColorida;

import static java.nio.charset.StandardCharsets.UTF_16;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.File;
/**
 *
 * @author BrunoLK
 */



public class Save {
    
    
    
//================metodo que carrega um jogo salvo, utilizando um arquivo================     
    //este metodo foi feito utilizando a ajuda do chat GPT:
     public static void carregarJogo(String caminho){
         
         
         
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
        Board tabuleiro;
        int L = 0;
        int C = 0;
         
         
          List<String> linhas = new ArrayList<>();
          int tamanho=0;
         //tenta ler o aruivo:
        try {
            FileReader arquivoReader = new FileReader(caminho);
           
             BufferedReader bufferedReader = new BufferedReader(arquivoReader);
             String linha;
              while ((linha = bufferedReader.readLine()) != null) {
                linhas.add(linha);
                
            }

           
        } catch (IOException e) {
            
             throw new RuntimeException("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }
        int cont=0;
        boolean token=false;
         for (String linha : linhas) {
             
            cont++;
            //verifica o token do arquivo de save:
            if(cont==1){
                if(linha.equals("1010101")){
                    continue;
                }else{
                    throw new RuntimeException("arquivo com informações erradas: Token" );
                    
                }
                
            }
            //verifica se o tamanho indicado no arquivo esta correto:
            if(cont==2){
                
                try{
                    tamanho = Integer.parseInt(linha);
                   
                    if(tamanho!=4 && tamanho!=6 && tamanho!=8 && tamanho!=10 && tamanho!=12){
                       
                       
                        throw new RuntimeException("arquivo com informações erradas: Tamanho" );
                    }
                    
                    
                }
                catch (NumberFormatException ex){
                   throw new RuntimeException("arquivo com informações erradas: " + ex.getMessage());
                   
                }
            }
            
            
         }
         
         tabuleiro = new Board(tamanho,tamanho, fundoTab);
         
         //lê cada linha e transforma em uma carta adicionada no tabuleiro
         cont=0;
        for (String linha : linhas) { 
            cont++;
            if(cont>2){
                if(cont-3 <tamanho){
                     C= cont-3;
                }
                if(C==tamanho){
                    C=0;
                    L++;
                }
                if(C<tamanho ){
                    C++;
                }
                
                
                
                 
                
                if(linha.equals("0")){
                    tabuleiro.colocaCarta(L, C-1, carta0);
                }
                if(linha.equals("00")){
                    tabuleiro.colocaCarta(L, C-1, carta0Fixa);
                }
                if(linha.equals("1")){
                     tabuleiro.colocaCarta(L, C-1, carta1);
                }
                if(linha.equals("11")){
                     tabuleiro.colocaCarta(L, C-1, carta1Fixa);
                     
                }
                if(linha.equals("2")){
                     tabuleiro.colocaCarta(L, C-1, fundoTab);
                    
                }
                
               
               
               
               
               if((C-1)==tamanho-1 && L == tamanho-1){
                  Interface.mostrarTabuleiro(tabuleiro);
                }
            
            
            
            
            
         }
        
        
    }
        
     }
     
     
     
//================metodo que salva um jogo em um arquivo================         
     //este metodo foi feito utilizando a ajuda do chat GPT:
     public static void salvarJogo(Board tabuleiro, String caminho){
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
        
        
        
        
        int tamanho = tabuleiro.getTotalColunas();
        List<String> linhas = new ArrayList<>();
        
        //adiciona um token padrao dos saves desse jogo
        linhas.add("1010101");
        //adiciona o tamanho do tabuleiro
        linhas.add(""+tamanho);
        
        //adiciona cada area do tabuleiro
        for (int i = 0; i < tamanho; i++){

            for (int j = 0; j < tamanho; j++){
                if(tabuleiro.getPilha(i, j).verTopo().equals(carta0)){linhas.add("0");}
                if(tabuleiro.getPilha(i, j).verTopo().equals(carta0Fixa)){linhas.add("00");}
                if(tabuleiro.getPilha(i, j).verTopo().equals(carta1)){linhas.add("1");}
                if(tabuleiro.getPilha(i, j).verTopo().equals(carta1Fixa)){linhas.add("11");}
                if(tabuleiro.getPilha(i, j).verTopo().equals(fundoTab)){linhas.add("2");}
            }
            
         }
        
        
        
        
       
        try {
            FileWriter arquivoWriter = new FileWriter(caminho);
            BufferedWriter bufferedWriter = new BufferedWriter(arquivoWriter);

        for (String linha : linhas) {
            bufferedWriter.write(linha);
            bufferedWriter.newLine(); 
        }

        bufferedWriter.close();
           
        } catch (IOException e) {
            throw new RuntimeException("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
            
        }
    }
       
     
     
//================metodo que salva um jogo em um arquivo padrao================         
     
     public static void salvarAutomatico(Board tabuleiro){
             salvarJogo(tabuleiro, "ContinuarJogo_SaveAuto.txt");
     }      
     
     
     
//================metodo para abrir o ultimo jogo do usuario================         
         
      public static void continuarJogo(){
          
        String caminhoArquivo = "ContinuarJogo_SaveAuto.txt";

       
        File arquivo = new File(caminhoArquivo);

        // Verificar se o arquivo existe
        if (arquivo.exists()) {
            carregarJogo("ContinuarJogo_SaveAuto.txt");
        } else {
            Interface.menu();
        }
            
         
      }
     
}