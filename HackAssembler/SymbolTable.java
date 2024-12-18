import java.util.Hashtable;

public class SymbolTable {

    private Hashtable<String, Integer> table;

    public SymbolTable() {
        table = new Hashtable<>();
    }

    public void addEntry(String symbol, int address) {
        this.table.put(symbol, Integer.valueOf(address));
    }

    public boolean contains(String symbol) {
		return this.table.containsKey(symbol);
	}

    public int getAddress(String symbol) {
        return this.table.get(symbol);
    }

}
