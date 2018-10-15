import java.util.LinkedList;
import java.util.Scanner;

public class Test{


    public static void main(String [] args){
        LinkedList<Punkt3D> punkty = new LinkedList<>();
        WyswietlMenu(punkty);
    }

    public static void WyswietlMenu(LinkedList<Punkt3D> punkty){
        System.out.println("1. Wczytaj punkt 3D");
        System.out.println("2. Wyświetl wszystkie punkty");
        System.out.println("3. Oblicz odległość");
        System.out.println("4. Zakończ");
        WczytajLiczbe(punkty);

    }

    public static void WczytajLiczbe(LinkedList<Punkt3D> punkty){
        System.out.println("?");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int n = reader.nextInt();
        switch (n){
            case 1:
                WczytajPunkt(punkty);
                break;
            case 2:
                WyswietlPunkty(punkty);
                break;
            case 3:
                ObliczOdleglosc(punkty);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                WczytajLiczbe(punkty);
        }


    }

    public static void WczytajPunkt(LinkedList<Punkt3D> punkty){
        System.out.println("Podaj wspolrzedne punktu i jego nazwę");
        Scanner reader = new Scanner(System.in);
        System.out.println("x: ");
        double x = reader.nextDouble();
        System.out.println("y: ");
        double y = reader.nextDouble();
        System.out.println("z: ");
        double z = reader.nextDouble();
        System.out.println("Nazwa: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        Punkt3D point = new Punkt3D(x, y, z, name);
        punkty.add(point);
        WyswietlMenu(punkty);
    }

    public static void WyswietlPunkty(LinkedList<Punkt3D> punkty){
        for (int i = 0; i < punkty.size(); ++i){
            System.out.println("Punkt " + punkty.get(i).GetName() + " x: " + punkty.get(i).GetX() + " y: " + punkty.get(i).GetY() + " z: " + punkty.get(i).GetZ());
        }
        WyswietlMenu(punkty);
    }

    public static void ObliczOdleglosc(LinkedList<Punkt3D> punkty){
        System.out.println("Podaj nazwę pierwszego punktu ");
        Scanner reader = new Scanner(System.in);
        String nazwaPierwszego = reader.nextLine();
        System.out.println("Podaj nazwę drugiego punktu ");
        reader = new Scanner(System.in);
        String nazwaDrugiego = reader.nextLine();
        int a = -1;
        int b = -1;
        for(int i=0; i < punkty.size(); i++){
            if(punkty.get(i).GetName().equals(nazwaPierwszego)) {
                a = i;
            }
            if(punkty.get(i).GetName().equals(nazwaDrugiego))
                b = i;
        }
        if(a == -1 || b == -1){
            System.out.println("Nie odnaleziono jednego z punktow.");
            WyswietlMenu(punkty);
        }
        else{
            System.out.println("Odleglosc miedzy punktami "+punkty.get(a).GetName() + " i "+ punkty.get(b).GetName() + " = " + punkty.get(a).distance(punkty.get(b)));
        }
        WyswietlMenu(punkty);
    }


}