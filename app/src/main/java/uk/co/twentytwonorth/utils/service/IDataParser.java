package uk.co.twentytwonorth.utils.service;


import com.android.volley.NetworkResponse;

import java.io.IOException;

/**
 * Created by colinlight on 30/11/15.
 */
public interface IDataParser {
    public void parseData( NetworkResponse response )  throws Exception, IOException;
}
