package liste.mape;

public class HashMap<TKey, TValue> {
    private Entry<TKey, TValue>[] entries;
    private int mapSize;

    public HashMap(){
        this.entries = new Entry[13];
    }

    public HashMap(int capacity){
        this.entries = new Entry[capacity];
    }

    public void put(TKey key, TValue value){
        int targetBucket = hash(key);

        if(this.entries[targetBucket] == null){
            Entry<TKey, TValue> entry = new Entry<>(key, value);
            this.entries[targetBucket] = entry;
            mapSize++;
        }
        else{
            Entry<TKey, TValue> currentEntry = this.entries[targetBucket];

            while (currentEntry.getNext() != null){
                if(currentEntry.getKey().hashCode() == key.hashCode() && currentEntry.getKey().equals(key)){
                    currentEntry.setValue(value);
                    return;
                }
                currentEntry = currentEntry.getNext();
            }

            if(currentEntry.getKey().hashCode() == key.hashCode() && currentEntry.getKey().equals(key)){
                currentEntry.setValue(value);
            }else{
                Entry<TKey, TValue> entry = new Entry<>(key, value);
                currentEntry.setNext(entry);
                mapSize++;
            }
        }
    }

    public TValue get(TKey key){
        return this.getOrDefault(key, null);
    }

    public TValue getOrDefault(TKey key, TValue defaultValue){
        int targetBucket = hash(key);

        if(this.entries[targetBucket] != null){
            Entry<TKey, TValue> currentEntry = this.entries[targetBucket];

            while (currentEntry != null){
                if (currentEntry.getKey().hashCode() == key.hashCode() && currentEntry.getKey().equals(key)){
                    return currentEntry.getValue();
                }

                currentEntry = currentEntry.getNext();
            }
        }

        return defaultValue;
    }

    public boolean containsKey(TKey key){
        return get(key) != null;
    }

    public boolean containsValue(TValue value){
        for (Entry<TKey, TValue> entry: this.entries) {
            if(entry != null){
                Entry<TKey, TValue> currentEntry = entry;

                while (currentEntry != null){
                    if (currentEntry.getValue().equals(value)){
                        return true;
                    }

                    currentEntry = currentEntry.getNext();
                }
            }
        }

        return false;
    }

    protected int hash(TKey key){
        int hashCode = key.hashCode();
        int targetBucket = Math.abs(hashCode % this.entries.length);
        return targetBucket;
    }

    public int size(){
        return this.mapSize;
    }

}