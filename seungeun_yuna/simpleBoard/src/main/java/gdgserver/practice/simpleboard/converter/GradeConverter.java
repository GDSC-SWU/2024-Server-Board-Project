package gdgserver.practice.simpleboard.converter;

import gdgserver.practice.simpleboard.enums.GradeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GradeConverter implements AttributeConverter<GradeEnum, Long> {
    
    // db에 저장
    @Override
    public Long convertToDatabaseColumn(GradeEnum attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute.getGradeId();
    }
    // Entity로 반환
    @Override
    public GradeEnum convertToEntityAttribute(Long dbData) {
        if(dbData == null) {
            return null;
        }
        return GradeEnum.enumOf(dbData);
    }
}
