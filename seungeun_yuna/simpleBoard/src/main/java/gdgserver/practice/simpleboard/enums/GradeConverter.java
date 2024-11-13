package gdgserver.practice.simpleboard.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GradeConverter implements AttributeConverter<Grade, String> {
    
    // db에 저장
    @Override
    public String convertToDatabaseColumn(Grade attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute.getGradeCode();
    }
    // Entity로 반환
    @Override
    public Grade convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        return Grade.enumOf(dbData);
    }
}
