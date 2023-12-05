package com.akinnova.Ecoms.dto.requestDto;

import com.akinnova.Ecoms.entity.AddressEntity;
import com.akinnova.Ecoms.entity.CardEntity;
import com.akinnova.Ecoms.entity.UserEntity;
import lombok.Data;

import java.io.Serializable;
@Data
public class NewOrder implements Serializable {
    AddressEntity address;
    CardEntity card;
    UserEntity user;

}
