package academits.demo.converter;

import java.util.List;

public interface Converter<S, D> {
    D convert(S sourse);

    List<D> convert(List<S> source);


}
