package org.ai.order.fetch.service.imp;

import org.ai.order.fetch.http.HttpUtils;
import org.ai.order.fetch.service.BlackEyeReader;
import org.ai.order.model.Order;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hua.ai on 2016/4/4.
 */
@Service
public class HttpBlackEyeReader implements BlackEyeReader {
    private final static Logger LOG = LoggerFactory.getLogger(HttpBlackEyeReader.class);

    @Value("${blackeye.homePage}")
    private String homePage;
    @Value("${blackeye.loginPage}")
    private String loginPage;
    @Value("${blackeye.orderPage}")
    private String orderPage;
    @Value("${blackeye.userName}")
    private String userName;
    @Value("${blackeye.password}")
    private String password;

    @Value("${blackeye.output}")
    private String output;

    public String read() throws IOException {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.login(loginPage, userName, password);
        int page = 1;
        // read page 1
        String result = httpUtils.get(MessageFormat.format(orderPage, page));
        writeToOutput(result, page);
        int totalPage = getTotalPage(result);
        if (totalPage <= 1) {
            LOG.info("No page found out.");
            return null;
        }
        for (; page <= totalPage; page++) {
            LOG.info("Processing page {}", page);
            String html = httpUtils.get(MessageFormat.format(orderPage, page));
            writeToOutput(html, page);
        }
        return null;
    }

    public String read(int page) throws IOException {
        HttpUtils httpUtils = new HttpUtils();

        httpUtils.login(loginPage, userName, password);

        // read page 1
        String result = httpUtils.get(MessageFormat.format(orderPage, page));

        if (LOG.isDebugEnabled()) {
            LOG.debug("Result -- > {}", result);
        }
        return result;
    }

    public int getTotalPage(String html) throws IOException {

        Document doc = Jsoup.parse(html);
        // pagination div
        Element paginationDiv = doc.select(".pagination").last();
        // page and total
        Elements numbers = paginationDiv.select(".number");

        Element orderSumElm = numbers.first();

        String orderSum = orderSumElm.html();


        if (StringUtils.isBlank(orderSum)) {
            return 0;
        }
        return Integer.parseInt(orderSum.split("/")[0]);

    }

    public int getTotalPage() throws IOException {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.login(loginPage, userName, password);
        // read page 1
        String result = httpUtils.get(MessageFormat.format(orderPage, 1));

        if (LOG.isDebugEnabled()) {
            LOG.debug("Result -- > {}", result);
        }

        Document doc = Jsoup.parse(result);

        // pagination div
        Element paginationDiv = doc.select(".pagination").last();

        // page and total
        String orderSum = paginationDiv.select(".number").first().html();

        if (StringUtils.isBlank(orderSum)) {
            return 0;
        }
        return Integer.parseInt(orderSum.split("/")[0]);

    }

    public void writeToOutput(String result, int page) throws IOException {
        File file = new File(output, "order_" + page + ".html");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        out.write(result.getBytes("gb2312"));
        out.flush();
        out.close();
    }

    public static void main(String args[]) throws IOException {
//        String html = HttpUtils.post();
        String html = "";

        List<Order> list = new ArrayList<Order>();
        Document doc = Jsoup.parse(new File("C:\\work\\xiayong\\result2.html"), "gb2312");
        Elements trs = doc.select(".color2 .color2");
        Iterator<Element> itr = trs.iterator();
        while (itr.hasNext()) {
            Order order = new Order();
            // tr
            Element e = itr.next();
            // get td
            Elements tds = e.select("td");
            Iterator<Element> tditr = tds.iterator();
            Element td = null;
            // checkbox
            td = tditr.next();
            // id
            td = tditr.next();
            order.setId(Integer.parseInt(getValue(td)));
            //dec
            td = tditr.next();
            order.setOrderName(getValue(td));
            //num/price
            td = tditr.next();
            String numAndPrice = getValue(td);
            order.setQuantity(Integer.parseInt(numAndPrice.split("/")[0]));
            order.setPrice(Double.parseDouble(numAndPrice.split("/")[1]));
            // receiver name
            td = tditr.next();
            order.setRecieverName(getValue(td));

            // receiver add
            td = tditr.next();
            order.setRecieverAddress(getValue(td));

            // receiver phone
            td = tditr.next();
            order.setRecieverPhone(getValue(td));

            // Pay Method
//            td = tditr.next();
//            order.setPaymethod(getValue(td));
//
//            // express status
//            td = tditr.next();
//            order.setExpressStatus(getValue(td));
//
//            // Order status
//            td = tditr.next();
//            order.setOrderStatus(getValue(td));
//
//            // Order IP
//            td = tditr.next();
//            order.setOrderIp(getValue(td));

            // Order Status
//            td = tditr.next();
//            order.setOrderStatus(getValue(td));
            list.add(order);
        }

        System.out.println("Total " + list.size() + " records.");
        for (Order order : list) {
            System.out.println(order);
        }
    }

    public static String getValue(Element td) {
        return td.html().trim().replaceAll("<br>", "");
    }

}
