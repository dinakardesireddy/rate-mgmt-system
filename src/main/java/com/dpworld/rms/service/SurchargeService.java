package com.dpworld.rms.service;

import java.util.Map;

public interface SurchargeService {
    Map<String,String> getSurcharge();
    Map<String,String> defaultSurcharge();
}
