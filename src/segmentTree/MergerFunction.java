package segmentTree;


@FunctionalInterface
public interface MergerFunction<E> {
    E merge(E a, E b);
}
