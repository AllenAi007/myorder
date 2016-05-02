package org.ai.order.fetch.http;

import org.ai.order.model.Order;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hua.ai on 2016/4/23.
 */
public final class HtmlUtils {

    static String charset = "gb2312";

    public static List<Order> unmarshall(String html) throws IOException {
        Document doc = Jsoup.parse(html, charset);
        return unmarshall(doc);
    }

    public static List<Order> unmarshall(File htmlFile) throws IOException {
        Document doc = Jsoup.parse(htmlFile, charset);
        return unmarshall(doc);
    }

    public static List<Order> unmarshall(Document document) throws IOException {
        List<Order> list = new ArrayList<Order>();
        Elements trs = document.select(".color2");
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
            order.setId(Integer.parseInt(getTheValue(td)));
            //dec
            td = tditr.next();
            order.setOrderName(getTheValue(td));
            //num/price
            td = tditr.next();
            String numAndPrice = getTheValue(td);
            order.setQuantity(Integer.parseInt(numAndPrice.split("/")[0]));
            order.setPrice(Double.parseDouble(numAndPrice.split("/")[1]));
            // receiver name
            td = tditr.next();
            order.setRecieverName(getTheValue(td));

            // receiver add
            td = tditr.next();
            order.setRecieverAddress(getTheValue(td));

            // receiver phone
            td = tditr.next();
            order.setRecieverPhone(getValue(td));

            // Pay Method
            td = tditr.next();
            order.setPayMethod(getTheValue(td));
//
//            // express status
            td = tditr.next();
            order.setExpressSatus(getTheValue(td));
//
//            // Order status
            td = tditr.next();
            order.setOrderStatus(getTheValue(td));
//
//            // Order IP
            td = tditr.next();
            order.setOrderIp(getValue(td));

            // order date
            td = tditr.next();
            order.setOrderDate(getTheValue(td));

            // order other
            td = tditr.next();
            order.setOthers(getValue(td));

            // order comments
            td = tditr.next();
            order.setComments(getTheValue(td));


            list.add(order);
        }
        return list;
    }

    private static Document getDocument(String html) {
        return Jsoup.parse(html, charset);
    }

    private static Document getDocment(File htmlFile) throws IOException {
        return Jsoup.parse(htmlFile, charset);
    }

    public static void main(String args[]) throws IOException {
        String file = "C:\\blackeye\\order\\order_2.html";
        List<Order> list = unmarshall(new File(file));
        if (list == null || list.isEmpty()) {
            System.out.print("Not able to find the result");
        }
        System.out.println("Total order " + list.size());
        for (Order order : list) {
            System.out.println(order);
        }
    }

    /**
     *
     * @param td
     * @return
     */
    public static String getValue(Element td) {
        return td.html().trim().replaceAll("<br>", "").replaceAll("<br/>", "");
    }

    /**
     *
     * @param element
     * @return
     */
    public static String getTheValue(Element element) {
        if (element.children().isEmpty()) {
            return getValue(element);
        }
        return getTheValue(element.child(0));
    }
}
