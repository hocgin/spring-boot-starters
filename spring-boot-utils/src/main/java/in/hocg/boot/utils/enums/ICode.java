package in.hocg.boot.utils.enums;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by hocgin on 2020/10/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface ICode {

    Serializable getCode();

    default String getCodeStr() {
        return String.valueOf(getCode());
    }

    default Integer getCodeInt() {
        return Integer.valueOf(getCodeStr());
    }

    default boolean eq(Serializable val) {
        final Serializable code = this.getCode();
        if (code instanceof String) {
            return StrUtil.equals((String) code, (String) val);
        } else if (code instanceof Integer) {
            return ObjectUtil.equal(code, val);
        }
        return ObjectUtil.equal(code, val);
    }

    static <T extends ICode> T ofThrow(Serializable code, Class<T> enumClass) {
        final Optional<T> enumOpl = of(code, enumClass);
        if (enumOpl.isPresent()) {
            return enumOpl.get();
        } else {
            throw new IllegalArgumentException("未找到匹配的类型");
        }
    }

    static <T extends ICode> Optional<T> of(Serializable code, Class<T> enumClass) {
        final T[] constants = enumClass.getEnumConstants();
        for (T value : constants) {
            if (value.eq(code)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

    static <T extends Enum<T>> Optional<T> ofName(String name, Class<T> enumClass) {
        return Optional.of(Enum.valueOf(enumClass, name));
    }
}
