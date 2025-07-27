package it.emanuelebondattidev.WebfluxLearning.utils.functional;

@FunctionalInterface
public interface UnsafeSupplier<T> {
    T get() throws Exception;
}