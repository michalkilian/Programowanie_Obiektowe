package DataFrame;

import java.util.ArrayList;

public class DataFrame implements Cloneable {

    ArrayList<Column> ColumnList = new ArrayList<Column>();


    public DataFrame(String[] columns, String[] types) {
        for (int i = 0; i < columns.length; i++) {
            ColumnList.add(new Column(columns[i], types[i]));
        }
    }

    public DataFrame(ArrayList<Column> do_kopii) {
        ColumnList = do_kopii;
    }

    public int size() {
        return ColumnList.get(0).getLista().size();
    }

    public Column get(String columnName) {
        Column szukana = null;
        for (int i = 0; i < ColumnList.size(); i++) {
            if (ColumnList.get(i).getName() == columnName) {
                szukana = ColumnList.get(i);
                break;
            }
        }
        return szukana;
    }


    public DataFrame get(String[] cols, boolean copy) {
        ArrayList<Column> newDataFrame = new ArrayList<Column>();
        if (copy) {
            for (int i = 0; i < cols.length; i++) {
                newDataFrame.add(get(cols[i]));
            }

        } else {
            String type;
            String name;
            for (int i = 0; i < cols.length; i++) {
                type = get(cols[i]).getType();
                name = get(cols[i]).getName();

                newDataFrame.add(new Column(name, type));
                for (int j = 0; j < size(); j++) {
                    newDataFrame.get(i).getLista().add(get(name).getLista().get(j));
                }

            }

        }
        return new DataFrame(newDataFrame);
    }

    public DataFrame iloc(int i) {


        DataFrame newDataFrame = makeBase();

        for (int j = 0; j < ColumnList.size(); j++) {
            Column kol = ColumnList.get(j);
            newDataFrame.ColumnList.get(j).getLista().add(kol.getLista().get(i));
        }


        return newDataFrame;
    }

    public DataFrame iloc(int from, int to) {
        DataFrame newDataFrame = makeBase();

        for (int i = 0; i < ColumnList.size(); i++) {
            Column kol = ColumnList.get(i);
            for (int j = from; j < to; j++) {
                newDataFrame.ColumnList.get(i).getLista().add(kol.getLista().get(j));
            }
        }


        return newDataFrame;
    }

    public DataFrame makeBase() {
        String[] columns = new String[ColumnList.size()];
        String[] types = new String[ColumnList.size()];
        for (int j = 0; j < ColumnList.size(); j++) {
            columns[j] = ColumnList.get(j).getName();
            types[j] = ColumnList.get(j).getType();
        }

        return new DataFrame(columns, types);
    }
}
