package gdgserver.practice.simpleboard.converter;

public interface Converter<DTO, Entity>{
    DTO toDto(Entity entity);
}
