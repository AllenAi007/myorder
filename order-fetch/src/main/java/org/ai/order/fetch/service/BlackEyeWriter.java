package org.ai.order.fetch.service;


import java.io.IOException;


/**
 * Created by hua.ai on 2016/4/4.
 */
public interface BlackEyeWriter {

    public void write() throws IOException;

    public void write(int from, int to) throws IOException;

}
