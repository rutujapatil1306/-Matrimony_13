package com.spring.jwt.FamilyBackground;


import com.spring.jwt.entity.FamilyBackground;
import org.springframework.stereotype.Component;

@Component
public class FamilyBackgroundMapper {

    public FamilyBackgroundDTO toDTO(FamilyBackground bg) {
        if (bg == null) {
            return null;
        }
        FamilyBackgroundDTO dto = new FamilyBackgroundDTO();

        dto.setFathersName(bg.getFathersName());
        dto.setFatherOccupation(bg.getFatherOccupation());
        dto.setMothersName(bg.getMothersName());
        dto.setMotherOccupation(bg.getMotherOccupation());
        dto.setBrothers(bg.getBrother());
        dto.setMarriedBrothers(bg.getMarriedBrothers());
        dto.setSisters(bg.getSisters());
        dto.setMarriedSisters(bg.getMarriedSisters());
        dto.setInterCasteInFamily(bg.getInterCasteInFamily());
        dto.setParentResiding(bg.getParentResiding());
        dto.setMamaSurname(bg.getMamaSurname());
        dto.setMamaPlace(bg.getMamaPlace());
        dto.setRelativeSurnames(bg.getRelativeSurnames());
        dto.setFamilyBackgroundCol(bg.getFamilyBackgroundCol());

        return dto;
    }

    public FamilyBackground toEntity(FamilyBackgroundDTO dto) {
        if (dto == null){
            return null;
        }

        FamilyBackground bg = new FamilyBackground();

        bg.setFathersName(dto.getFathersName());
        bg.setFatherOccupation(dto.getFatherOccupation());
        bg.setMothersName(dto.getMothersName());
        bg.setMotherOccupation(dto.getMotherOccupation());
        bg.setBrother(dto.getBrothers());
        bg.setMarriedBrothers(dto.getMarriedBrothers());
        bg.setSisters(dto.getSisters());
        bg.setMarriedSisters(dto.getMarriedSisters());
        bg.setInterCasteInFamily(dto.getInterCasteInFamily());
        bg.setParentResiding(dto.getParentResiding());
        bg.setMamaSurname(dto.getMamaSurname());
        bg.setMamaPlace(dto.getMamaPlace());
        bg.setRelativeSurnames(dto.getRelativeSurnames());
        bg.setFamilyBackgroundCol(dto.getFamilyBackgroundCol());

        return bg;
    }
}

