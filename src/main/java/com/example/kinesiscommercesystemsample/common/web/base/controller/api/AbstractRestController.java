package com.example.kinesiscommercesystemsample.common.web.base.controller.api;

import com.example.kinesiscommercesystemsample.common.web.base.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
@Slf4j
public class AbstractRestController extends BaseController {
}
