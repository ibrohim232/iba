package backend.common;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.List;

public abstract class BaseRepository<ENTITY extends BaseEntity<ID>, ID> implements Repository<ENTITY, ID> {
    protected abstract List<ENTITY> getList();

    protected abstract String getPath();

    @Override
    public ENTITY save(ENTITY entity) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(getPath()))) {
            objectOutputStream.writeObject(entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getList().add(entity);
        return entity;
    }

    @Override
    public ENTITY findById(ID id) {
        for (ENTITY entity : getList()) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }

        return null;
    }

    @Override
    public List<ENTITY> getAll() {

        return getList();
    }

    @Override
    public void deleteById(ID id) {
        for (ENTITY entity : getList()) {
            if (entity.getId().equals(id)) {
                getList().remove(entity);
            }
        }
    }


}
