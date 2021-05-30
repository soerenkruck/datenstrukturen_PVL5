package Loesung_G33;

public class Test {

    // Diese Klasse ist nur zum Testen. Kann ignoriert werden.

    public static void main(String[] args) {
        Loesung_Beispiel_Name l = new Loesung_Beispiel_Name();
        l.insert(1);
        l.insert(4);
        l.insert(2);
        l.insert(5);
        l.insert(51);

        Loesung_Beispiel_Name k = new Loesung_Beispiel_Name();
        k.insert(4);
        k.insert(51);

        l.cut(k);

        for (Integer i: l) {
            System.out.println(i);
        }

    }
}
