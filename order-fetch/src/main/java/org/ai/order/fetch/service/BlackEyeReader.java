package org.ai.order.fetch.service;

import java.io.IOException;

/**
 * Created by hua.ai on 2016/4/4.
 */
public interface BlackEyeReader {

    public String read() throws IOException;

    public String read(int page) throws IOException;

    public int getTotalPage() throws IOException;

}
