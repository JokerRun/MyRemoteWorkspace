package com.lr.mule.lesson03.transformer;


import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public class CSVToListOfMapsTransformer extends AbstractTransformer {

    @SuppressWarnings({ "resource", "rawtypes" })
	@Override
    protected Object doTransform(final Object src, final String enc) throws TransformerException {

        List<Map> products = new ArrayList<Map>();
        //此处引入CSVReader主要是用来自动执行split操作而已，可以根据需要选用。。。
        CSVReader reader = new CSVReader(new StringReader((String) src));

        String[] nextLine;
        try {
            while ((nextLine = reader.readNext()) != null) {
                Map<String,String> product = new HashMap<String,String>();
                product.put("name",nextLine[0]);
                product.put("acv",nextLine[1]);
                product.put("cost",nextLine[2]);
                product.put("description",nextLine[3]);
                products.add(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return products;
    }
}
