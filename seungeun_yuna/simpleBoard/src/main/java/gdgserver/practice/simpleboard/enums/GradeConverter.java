package gdgserver.practice.simpleboard.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GradeConverter implements AttributeConverter<Grade, Long> {
    
    // db에 저장
    @Override
    public Long convertToDatabaseColumn(Grade attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute.getGradeId();
    }
    // Entity로 반환
    @Override
    public Grade convertToEntityAttribute(Long dbData) {
        if(dbData == null) {
            return null;
        }
        return Grade.enumOf(dbData);
    }
}
