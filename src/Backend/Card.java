/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;
import cores.StringColorida;
import mecanicas.Carta;

/**
 *
 * @author BrunoLK
 */
public class Card extends Carta{
     public Card(StringColorida frente, StringColorida verso, boolean viradaParaCima) {
        super(frente, verso, viradaParaCima);
    }
    public Card(StringColorida frente) {
        super(frente);
    }
    
   
    
    
    
}
