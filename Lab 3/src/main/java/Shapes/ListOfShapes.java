package Shapes;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ListOfShapes {
    private LinkedList<Shape> listOfShapes = new LinkedList<Shape>();

    public void addShape(Shape shape) {
        listOfShapes.add(shape);
    }

    public void getList() {
        for (int i = 0; i < listOfShapes.size(); ++i) {
            System.out.println("index: " + i + " nazwa: " + listOfShapes.get(i).name);
        }
    }

    public void interactWithList() {
        Scanner scanner = new Scanner(System.in);
        int a, b;
        String name;
        while (true) {
            System.out.println("Wpisz 1 aby dodać nową figurę 2 aby wyświetlić istniejącą lub 3 by wyjść");
            int n = scanner.nextInt();
            switch (n) {
                case 1:
                    System.out.println("Podaj typ figury");
                    System.out.print("1 - prostokąt \n2 - kwadrat \n3 - trójkąt \n4 - koło \n");
                    int k = scanner.nextInt();
                    switch (k) {
                        case 1:
                            System.out.println("Podaj pierwszy bok: ");
                            a = scanner.nextInt();
                            System.out.println("Podaj drugi bok: ");
                            b = scanner.nextInt();
                            System.out.println("Podaj nazwę: ");
                            scanner = new Scanner(System.in);
                            name = scanner.nextLine();
                            this.addShape(new Rectangle(a, b, name));
                            break;
                        case 2:
                            System.out.println("Podaj bok: ");
                            a = scanner.nextInt();
                            System.out.println("Podaj nazwę: ");
                            scanner = new Scanner(System.in);
                            name = scanner.nextLine();
                            this.addShape(new Square(a, name));

                            break;
                        case 3:
                            System.out.println("Podaj bok: ");
                            a = scanner.nextInt();
                            System.out.println("Podaj nazwę: ");
                            scanner = new Scanner(System.in);
                            name = scanner.nextLine();
                            this.addShape(new Triangle(a, name));

                            break;
                        case 4:
                            System.out.println("Podaj promień: ");
                            a = scanner.nextInt();
                            System.out.println("Podaj nazwę: ");
                            scanner = new Scanner(System.in);
                            name = scanner.nextLine();
                            this.addShape(new Circle(a, name));
                            break;
                        default:
                            break;

                    }
                    break;
                case 2:
                    System.out.println("Dostępne figury: ");
                    this.getList();
                    System.out.println("Podaj indeks pierwszej figury do narysowania: ");
                    int m = scanner.nextInt();
                    int i = m;
                    while (i < listOfShapes.size() && i >= 0) {
                        System.out.println("index: " + i + " nazwa: " + listOfShapes.get(i).name);
                        listOfShapes.get(i).draw();
                        System.out.println("Wpisz '1' aby wyświetlić następną figurę '2' aby wyświetlić poprzednią '3' aby podać indeks figury do wyświetlenia lub '4' aby wyjść");
                        int c = scanner.nextInt();
                        switch (c) {
                            case 1:
                                i++;
                                break;
                            case 2:
                                i--;
                                break;
                            case 3:
                                i = scanner.nextInt();
                                break;
                            case 4:
                                i = listOfShapes.size();
                                break;
                            default:
                                return;

                        }
                    }
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }
}
