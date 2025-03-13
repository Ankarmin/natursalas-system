package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SedeInfoDTO;

public interface ISedeInfoDAO {
    SedeInfoDTO getSedeInfo(String idLocation);
}
