package Workers;

import java.util.Comparator;
import java.util.LinkedList;

public class SortWorkers implements Comparator<Pracownik> {
    public LinkedList<Pracownik> sort(LinkedList<Pracownik> listaPracownikow) {

        int n = listaPracownikow.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (this.compare(listaPracownikow.get(i), listaPracownikow.get(i + 1)) > 0) {
                    Pracownik temp = listaPracownikow.get(i + i);
                    listaPracownikow.set(i + 1, listaPracownikow.get(i));
                    listaPracownikow.set(i, temp);
                }

        return listaPracownikow;
    }

    public int compare(Pracownik prac1, Pracownik prac2) {
        return Double.compare(prac1.wynagrodzenieBrutto, prac2.wynagrodzenieBrutto);
    }

}
