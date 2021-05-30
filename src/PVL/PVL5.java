package PVL;

public interface PVL5 extends Iterable<Integer>{
    void insert(int element);

    void union(PVL5 sampleSet);

    void cut(PVL5 sampleSet);

    void relativeComplementWith(PVL5 sampleSet);

    boolean isSubsetOf(PVL5 sampleSet);
}
