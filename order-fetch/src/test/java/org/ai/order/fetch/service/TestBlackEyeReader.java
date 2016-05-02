package org.ai.order.fetch.service;

import org.ai.order.fetch.service.imp.HttpBlackEyeReader;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by hua.ai on 2016/4/9.
 */

public class TestBlackEyeReader {

    BlackEyeReader blackEyeReader;
    int page = 569;

    @Before
    public void init() {
        blackEyeReader = new HttpBlackEyeReader();
    }

    @Test
    public void getTotalPage() throws IOException {
        int page = blackEyeReader.getTotalPage();
        assertTrue(page == this.page);
    }


}
