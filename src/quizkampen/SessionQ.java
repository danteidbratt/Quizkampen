
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
   
   
    public String getUsername(){
        return username;
    }
    
    public Question getQuestion(){
        return question;
    }
    public String getAlternative(){
        return alternative;  
    }
    
    public String getAnswer() {
        return answer; 
    }
    
    public void setUsername(String u){
        username = u;
    }
    
    public void setQuestion(Question q){
        question = q;
    }
    
    public void setAlternative(String a){
        alternative = a;
    }
    
    public void setAnswer(String s){
        answer = s;
    }
}
