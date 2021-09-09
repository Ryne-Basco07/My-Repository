package model;

public class KeyValues implements Comparable<KeyValues> {
    private String key;
    private String value;

    public KeyValues(String key, String value){
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(KeyValues keyValues){
        return this.value.compareTo(keyValues.value);
    }

}
