package gdgserver.practice.simpleboard.converter;

public interface Converter<Response, Entity>{
    Response toDto(Entity entity);
}
