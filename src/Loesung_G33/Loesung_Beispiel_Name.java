package Loesung_G33;

import PVL.PVL5;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Loesung_Beispiel_Name implements PVL5 {

    private T elemente;

    @Override
    public void insert(int element) {

        if (elemente == null) {
            elemente = new T(element);
        } else {
            elemente.addNextVal(element);
        }

    }

    @Override
    public void union(PVL5 sampleSet) {

        if (this != sampleSet) {
            for (Integer i : sampleSet) {
                elemente.addNextVal(i);
            }
        } else {
            System.err.println("Menge darf nicht mit gleicher Menge vereint werden.");
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void cut(PVL5 sampleSet) {

        if (this == sampleSet) {
            elemente = null;
        } else {

            /* Hier wird jeder Wert des sampleSet einzeln aus den
            Elementen enfernt.
             */
            for (Integer i: sampleSet) {
                /* Die Elemente (menge) werden von ein anderen Menge ├╝berschrieben,
                welche nicht den zu entfernenden Wert beinhaltet.
                 */
                elemente = elemente.eraseValue(i);
            }
        }

    }

    @Override
    public void relativeComplementWith(PVL5 sampleSet) {

    }

    @Override
    public boolean isSubsetOf(PVL5 sampleSet) {
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {

        return new Iterator<Integer>() {

            int i = 0;

            @Override
            public boolean hasNext() {
                return (elemente.get(i) != null);
            }

            @Override
            public Integer next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                else
                    return elemente.getVal(i++);
            }

        }; // TODO
    }


}

// Klasse, um eine Menge zu speichern.
class T {

    /*
     * Funktionsweise:
     *
     * Jeder Wert hat einen nachfolge Wert, sodass beliebig viele Werte
     * hinzugef├╝gt werden k├Ânnen.
     */

    // Index eines Werts im Speicher
    private int index = 0;

    // Wert
    private int val;

    // N├Ąchster Wert.
    private T nextVal;

    // Aufruf beim ersten initieren der Elemente
    public T(int val) {
        this.val = val;
    }

    // Wird aufgerufen, wenn ein neuer Wert hinzugef├╝gt wird, welcher nicht der root Wert ist.
    public T(int val, int index) {
        this.val = val;
        this.index = index;
    }

    /**
     * F├╝gt der Menge einen Wert hinzu.
     * @param val Der Hinzuzuf├╝gende Wert
     */
    public void addNextVal(int val) {
        if (nextVal == null) this.nextVal = new T(val, this.index + 1);
        else this.nextVal.addNextVal(val);

    }

    /**
     * Gibt einen Wert eines bestimmten Elementes zur├╝ck.
     * @param index bestimmt das Element
     * @return Der Wert des Elementes 'index'
     */
    public int getVal(int index) {
        if (this.index == index) return val;
        else return nextVal.getVal(index);
    }

    /**
     * Gibt einen ein bestimmtes Element zur├╝ck.
     * @param index bestimmt das Element
     * @return Das Element
     */
    public T get(int index) {
        if (this.index == index) {
            return this;
        } else {
            if (this.nextVal != null)
                return this.nextVal.get(index);
            else
                return null;
        }
    }

    /**
     * Entfernt einen Wert aus der Menge.
     * @param value Der zu entfernende Wert
     */
    public T eraseValue(int value) {
        
        T t = null;
        
        /* In dieser Funktion wird einfach eine neue Reihe an Elementene
            angelegt es werden alle Werte der Menge hinzugef├╝gt, au├čer der
            Wert, der entfernt werden soll.

            ├ťbrig bleibt eine Menge ohne den Wert, welche dann einfach der
            bisherigen Menge gleichgezetzt wird.
         */
        

        for (int i = 0; get(i) != null; i++) {
            
            if (i == 0) {
                if (get(i).val != value) {
                    t = new T(get(i).val);
                } else {
                    t = new T(get(++i).val);
                }
            } else {
                if (get(i).val != value) {
                    t.addNextVal(get(i).val);
                }
            }
        }

        return t;

    }

}
