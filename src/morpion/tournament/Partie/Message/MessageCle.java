/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Partie.Message;

/**
 *
 * @author deniaul
 */
public class MessageCle extends Message{
      private String clé;
      
      public MessageCle(Action action,String clé){
        super(action);
        setClé(clé);
      

    }
       
      
      
    /**
     * @return the clé
     */
    public String getClé() {
        return clé;
    }

    /**
     * @param clé the clé to set
     */
    public void setClé(String clé) {
        this.clé = clé;
    }
      
}
