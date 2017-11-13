
package quizkampen;

import java.io.Serializable;

public class SessionQ implements Serializable{
     //portnummer = 33333
   protected String username; 
   protected Question question; 
   protected String alternative; 
   protected String answer; 
  
  SessionQ () {
      
      
      
      
  }
   
   
    protected String getUsername(){
        return username;
    }
    
    protected Question getQuestion(){
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
    
    protected void setQuestion(Question q){
        question = q;
    }
    
    protected void setAlternative(String a){
        alternative = a;
    }
    
    protected void setAnswer(String s){
        answer = s;
    }
}
