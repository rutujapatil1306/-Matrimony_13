package com.spring.jwt.HoroscopeDetails;


import com.spring.jwt.entity.HoroscopeDetails;
import com.spring.jwt.entity.Status;
import org.springframework.stereotype.Component;

@Component
public class HoroscopeMapper {

    public HoroscopeDTO toDTO(HoroscopeDetails h) {
        if (h == null){
            return null;
        }
        HoroscopeDTO dto = new HoroscopeDTO();

        dto.setDob(h.getDob());
        dto.setTime(h.getTime());
        dto.setBirthPlace(h.getBirthPlace());
        dto.setRashi(h.getRashi());
        dto.setNakshatra(h.getNakshatra());
        dto.setCharan(h.getCharan());
        dto.setNadi(h.getNadi());
        dto.setGan(h.getGan());
        dto.setGotra(h.getGotra());
        dto.setMangal(h.getMangal());
        dto.setDevak(h.getDevak());


        return dto;
    }

    public HoroscopeDetails toEntity(HoroscopeDTO dto) {
        if (dto == null){
            return null;
        }

        HoroscopeDetails h = new HoroscopeDetails();

        h.setDob(dto.getDob());
        h.setTime(dto.getTime());
        h.setBirthPlace(dto.getBirthPlace());
        h.setRashi(dto.getRashi());
        h.setNakshatra(dto.getNakshatra());
        h.setCharan(dto.getCharan());
        h.setNadi(dto.getNadi());
        h.setGan(dto.getGan());
        h.setGotra(dto.getGotra());
        h.setMangal(dto.getMangal());
        h.setDevak(dto.getDevak());

        return h;
    }
}
