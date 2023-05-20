package backend.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public abstract class BaseRepository<ENTITY extends BaseEntity<ID>, ID> implements Repository<ENTITY, ID> {
    protected abstract List<ENTITY> getList();

    @Override
    public ENTITY save(ENTITY entity) throws IOException {
        getList().add(entity);
        String name = entity.getClass().getName();
        File file = new File(name + ".txt".toLowerCase());
        if (!file.exists()){
           file.createNewFile();
        }
        Path path = Path.of(file.getName());
        Files.writeString(path,entity.toString().concat("\n"), StandardOpenOption.APPEND);
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
