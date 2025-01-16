package com.example.newpartssertvice.converter;

import com.example.newpartssertvice.entity.Part;
import org.json.JSONObject;

public interface PartConverter {
    Part convert(JSONObject jsonObject);
}
