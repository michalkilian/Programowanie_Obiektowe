package Workers;

public class Worker {
    private String pesel;
    private String imie;
    private String nazwisko;
    private double wynagrodzenie;

    public Worker(String pesel, String name, String surname, double wynagrodzenie){
        this.pesel = pesel;
        this.imie = name;
        this.nazwisko = surname;
        this.wynagrodzenie = wynagrodzenie;
    }

    public void print(){
        System.out.println("Pesel: " + pesel);
        System.out.println("Imie: " + imie);
        System.out.println("Nazwisko: " + nazwisko);
        System.out.println("Wynagrodzenie: " + wynagrodzenie);
        System.out.println();
    }

    public double getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}
