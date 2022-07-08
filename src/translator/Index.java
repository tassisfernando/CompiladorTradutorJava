package translator;

public class Index implements Comparable<Index> {

    private int index;
    private int usage = 0;

    public Index(int index, int usage) {
        this.index = index;
        this.usage = usage;
    }

    @Override
    public int compareTo(Index i) {
        if(this.index > i.index)
            return -1;
        else if(this.index < i.index)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Index: %s Usages: %s", index, usage);
    }

    public int getIndex() {
        return index;
    }

    public int getUsage() {
        return usage;
    }

    public void incrementUsage() {
        this.usage++;
    }
}
