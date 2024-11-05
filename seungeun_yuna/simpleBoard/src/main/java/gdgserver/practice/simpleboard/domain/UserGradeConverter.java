package gdgserver.practice.simpleboard.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserGradeConverter implements AttributeConverter<UserGrade, String> {
    
    // db에 저장
    @Override
    public String convertToDatabaseColumn(UserGrade attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute.getGradeCode();
    }
    // Entity로 반환
    @Override
    public UserGrade convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        return UserGrade.enumOf(dbData);
    }
}
