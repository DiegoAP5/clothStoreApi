package com.example.clothStore.services.interfaces;

import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.entities.User;
import lombok.Data;

public interface IRabbitService {

    void create(Data data);

    void update(Data data);
}
