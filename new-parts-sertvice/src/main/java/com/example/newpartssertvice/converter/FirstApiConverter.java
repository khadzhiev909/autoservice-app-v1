package com.example.newpartssertvice.converter;


import com.example.newpartssertvice.entity.Condition;
import com.example.newpartssertvice.entity.Part;
import org.json.JSONObject;

import java.time.LocalDate;

public class FirstApiConverter implements PartConverter {

    @Override
    public Part convert(JSONObject jsonObject) {
        Part part = new Part();
        part.setName(jsonObject.getString("itemName"));
        part.setCategory(jsonObject.getString("itemCategory"));
        part.setBrand(jsonObject.getString("itemBrand"));
        part.setPrice(jsonObject.getBigDecimal("itemPrice"));
        part.setCondition(Condition.valueOf(jsonObject.getString("itemCondition")));
        part.setQuantity(jsonObject.getInt("itemQuantity"));
        part.setDescription(jsonObject.optString("itemDescription")); // Используем optString для возможности отсутствия поля
        part.setPartNumber(jsonObject.getString("itemPartNumber"));
        part.setAddedDate(LocalDate.parse(jsonObject.getString("itemAddedDate")));
        return part;
    }
}
