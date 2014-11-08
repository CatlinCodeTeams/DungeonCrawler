package dungeon.util.defualtMap;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class DefaultMap<K, V> extends HashMap<K, V>{

	public final Factory<V> getInstance;

	public DefaultMap(Factory<V> factory){
		getInstance=factory;
	}
	public DefaultMap(final Class<V> defualtClass){
		this(Factory.getClassFactory(defualtClass));
	}
    @SuppressWarnings("unchecked")
	@Override
    public V get(Object key) {
        V returnValue = super.get(key);
        if (returnValue == null) {
            returnValue = getInstance.get();
            this.put((K) key, returnValue);
        }
        return returnValue;
    }
    public static <K1,K2,V> Map<K1,Map<K2,V>> nestedDefualtMap(final Factory<V> valueFactory){
    	return new DefaultMap<>(new Factory<Map<K2,V>>(){
    				@Override
    				public DefaultMap<K2, V> get() {
    					return new DefaultMap<K2, V>(valueFactory);
    				};
    			});
    }
}