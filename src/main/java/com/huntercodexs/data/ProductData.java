package com.huntercodexs.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductData {
    private Integer id;
    private String name;
    private String description;
    private Integer price;

    public final static String HEADER_CSV = "ID;NAME;DESCRIPTION;PRICE\n";

    public String[] toStringArray() {
        return new String[]{String.valueOf(id), name, description, String.valueOf(price)};
    }

    public static String stringCsvLineFromArray(String[] array) {
        return String.join(";", array).concat("\n");
    }
}
