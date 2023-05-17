package stc.trains.util;

import lombok.experimental.UtilityClass;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.NonNull;
import stc.trains.model.BaseEntity;

@UtilityClass
public class  ValidationUtil {

    //  Conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
    public static void assureIdConsistent(BaseEntity bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new DataIntegrityViolationException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static void checkModification(int count, int id) {
        if (count == 0) {
            throw new DataIntegrityViolationException("Entity with id=" + id + " not found");
        }
    }
}