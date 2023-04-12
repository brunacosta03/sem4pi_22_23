package domain.dto;

import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GeneralDTO implements Map<String, Object> {

    private String type;
    private Map<String, Object> map = new ConcurrentHashMap<>();


    public GeneralDTO(final String type) {
        Preconditions.ensure(!StringPredicates.isNullOrWhiteSpace(type));
        this.type = type;
    }

    public List<String> fields() {
        return List.copyOf(map.keySet());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        if(key != null && value != null)
            return map.put(key, value);

        return null;
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return map.entrySet();
    }
}
