package ddd;

public interface Repository<TId, TEntity> {
    TEntity get(TId id);

    boolean exists(TId id);

    void add(TEntity entity);

    void delete(TEntity entity);
}
