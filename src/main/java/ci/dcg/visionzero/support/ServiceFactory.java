package ci.dcg.visionzero.support;

import java.util.List;

public interface ServiceFactory<T,V> {
    //Optional<T> findById(V v);
    T getOne(V v);
    List<T> findAll();
    int count();
    T save(T t);
    void update(T t);
    void delete(V v);
    boolean isExiste(V v);
    V retourneId();
}
