/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizkampen;

/**
 *
 * @author claudiastenberg
 */
public class SessionQ {
     //portnummer = 33333
   protected String username; 
   protected String question; 
   protected String alternative; 
   protected String answer; 
  
  
    protected String getUsername(){
        return username;
    }
    
    protected String getQuestion(){
        return question;
    }
    protected String getAlternative(){
        return alternative;  
    }
    
    protected String getAnswer() {
        return answer; 
    }
    
    protected void setUsername(String u){
        username = u;
    }
    
    protected void setQuestion(String q){
        question = q;
    }
    
    protected void setAlternative(String a){
        alternative = a;
    }
    
    protected void setAnswer(String s){
        answer = s;
    }
}
