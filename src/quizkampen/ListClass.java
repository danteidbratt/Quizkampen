package quizkampen;

import java.util.ArrayList;
import java.util.List;


public class ListClass<Question> extends ArrayList<Question>{
    protected String name;
//    public List<Question> list;
    
    
    public String getName(){
        return this.name;
    }
//    public List getList(){
//        return this.list;
//    }
    public void setName(String name){
        this.name = name;
    }
//    public void setList(List list){
//        this.list = list;
//    }
}
