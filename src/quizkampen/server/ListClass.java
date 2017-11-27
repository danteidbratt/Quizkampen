package quizkampen.server;

import java.util.ArrayList;
import java.util.List;


public class ListClass<Question> extends ArrayList<Question>{
    
    protected String name;
  
    public String getName(){
        return this.name;
    }
    public void setName(String name){
          this.name = name;
    }
}