package dungeon.util.factory;

public abstract class Factory<F> {
	public abstract F get();

	public static <V> Factory<V> getClassFactory(final Class<V> targetClass) {
		return new Factory<V>() {
			@Override
			public V get() {
				try {
					return targetClass.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}
}