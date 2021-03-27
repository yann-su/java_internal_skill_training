package communication.trans;

import java.util.ArrayList;
import java.util.List;

public class MyList {

    volatile private List<String> list = new ArrayList();

    public void  add(){
        list.add("sujy");
    }

    public int size(){
        return list.size();
    }


}
