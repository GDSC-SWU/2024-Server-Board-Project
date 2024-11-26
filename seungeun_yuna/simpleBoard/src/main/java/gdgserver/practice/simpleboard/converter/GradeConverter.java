package gdgserver.practice.simpleboard.converter;

import gdgserver.practice.simpleboard.enums.GradeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GradeConverter implements AttributeConverter<GradeEnum, String> {
    
    // db에 저장
    @Override
    public String convertToDatabaseColumn(GradeEnum _attribute) {
        if(_attribute == null) {
            return null;
        }
        return _attribute.getGradeName();
    }
    // Entity로 반환
    @Override
    public GradeEnum convertToEntityAttribute(String _dbData) {
        if(_dbData == null) {
            return null;
        }
        return GradeEnum.enumOf(_dbData);
    }
}
