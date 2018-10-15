package DataFrame;

import java.util.ArrayList;

public class Column implements Cloneable {
    private String name;
    private String type;
    private ArrayList<Object> list;

    public Column(String name_, String type_) {
        name = name_;
        type = type_;
        list = new ArrayList<Object>();
    }

    public ArrayList getLista() {
        return list;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
