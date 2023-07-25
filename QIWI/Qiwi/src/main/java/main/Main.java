package main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*StringBuilder input = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        input.append(sc.nextLine());*/

        String query = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=02/03/2002";
        //String query = input.toString();

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(query).openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(350);
            connection.setReadTimeout(350);

            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));





                /*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(String.valueOf(new InputStreamReader(connection.getInputStream())));*/

                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                InputSource is = new InputSource(new StringReader(sb.toString()));
                Document document = builder.parse(is);

                Element currencysElement = (Element) document.getElementsByTagName("ValCurs").item(0);
                String name = currencysElement.getAttribute("name");

                NodeList currencyList = document.getElementsByTagName("Valute");

                List<Currency> currencyListOut = new ArrayList<>();

                for (int i = 0; i < currencyList.getLength(); i++) {
                    if (currencyList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element currencyElement = (Element) currencyList.item(i);

                        Currency currency = new Currency();
                        currency.setFirstName(name);
                        currency.setID((currencyElement.getAttribute("ID")));

                        NodeList childNodes = currencyElement.getChildNodes();

                        for (int j = 0; j < childNodes.getLength(); j++) {
                            if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                                Element childElement = (Element) childNodes.item(j);

                                switch (childElement.getNodeName()) {
                                    case "CharCode": {
                                        currency.setCharCode(childElement.getTextContent());

                                    }
                                    break;

                                    case "Value": {
                                        currency.setValue(Double.valueOf(childElement.getTextContent()));

                                    }
                                    break;
                                }

                            }
                        }

                        currencyListOut.add(currency);

                    }
                }

                currencyListOut.forEach(System.out::println);

            } else {
                System.out.println("fail" + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }


        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}