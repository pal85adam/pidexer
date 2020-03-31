package pidexer;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.productListPrinter();
    }

    private void productListPrinter(){

        try {
            File file = new File("userdata.xml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            System.out.println("Root element: " + document.getDocumentElement().getNodeName());
            if (document.hasChildNodes()) {
                HashMap<String ,Product> productsList = productsList(document.getElementsByTagName("products")
                        .item(0)
                        .getChildNodes());
                String toPrint = productsList.entrySet()
                        .stream()
                        .map(entry -> entry.getValue().toString())
                        .reduce("",(result,entry)-> result + "\n" + entry);
                System.out.println(toPrint);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   private HashMap<String,Product> productsList(NodeList nodeList) {
        HashMap<String,Product> products = new HashMap<>();
        for (int count = 0; count < nodeList.getLength(); count++) {

            Node elemNode = nodeList.item(count);

            if (elemNode.getNodeType() == Node.ELEMENT_NODE) {
                String name = elemNode.getNodeName();
                String key ="";
                if (elemNode.hasAttributes()) {
                    key = elemNode.getAttributes().item(0).getNodeValue();
                }

                products.put(key,productObjectCreator(elemNode.getChildNodes(),key,name));

            }
        }
        return products;
    }

    public Product productObjectCreator(NodeList productInfo, String key, String name){
        Product product = new Product();
        for (int count = 0; count < productInfo.getLength(); count++) {

            Node elemNode = productInfo.item(count);

            if (elemNode.getNodeType() == Node.ELEMENT_NODE) {
                product.SN = key;
                product.name = name;

                if(elemNode.getNodeName().equals("fett")){
                    product.fett = Double.parseDouble(elemNode.getTextContent());
                }
                if(elemNode.getNodeName().equals("energy")){
                    product.energy = Double.parseDouble(elemNode.getTextContent());
                }
                if(elemNode.getNodeName().equals("kolhydrat")){
                    product.kolhydrat = Double.parseDouble(elemNode.getTextContent());
                }
                if(elemNode.getNodeName().equals("protein")){
                    product.protein = Double.parseDouble(elemNode.getTextContent());
                }
                if(elemNode.getNodeName().equals("fiber")){
                    product.fiber = Double.parseDouble(elemNode.getTextContent());
                }
                if(elemNode.getNodeName().equals("review")){
                    product.reviewers = reviewersList(elemNode.getChildNodes());
                }
            }
        }
        //System.out.println(product.toString());
        return product;
    }

    private HashMap<String,Reviewer> reviewersList(NodeList nodeList){
        HashMap<String,Reviewer> reviewers = new HashMap<>();
        for (int count = 0; count < nodeList.getLength(); count++) {

            Node elemNode = nodeList.item(count);

            if (elemNode.getNodeType() == Node.ELEMENT_NODE) {
                String key ="";
                if (elemNode.hasAttributes()) {
                    key = elemNode.getAttributes().item(0).getNodeValue();
                }

                reviewers.put(key,reviewerObjectCreator(elemNode.getChildNodes(),key));

            }
        }
        //System.out.println(reviewers.entrySet().stream().map(entry -> entry.getValue().toString()));

        return reviewers;
    }


    public Reviewer reviewerObjectCreator(NodeList reviewerInfo, String key){
        Reviewer reviewer = new Reviewer();
        for (int count = 0; count < reviewerInfo.getLength(); count++) {

            Node elemNode = reviewerInfo.item(count);

            if (elemNode.getNodeType() == Node.ELEMENT_NODE) {
                reviewer.personID = key;

                if(elemNode.getNodeName().equals("date")){
                    try {
                        reviewer.date = new SimpleDateFormat("yyyy-MM-dd").parse(elemNode.getTextContent());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if(elemNode.getNodeName().equals("score")){
                    reviewer.score = Integer.parseInt(elemNode.getTextContent());
                }
            }
        }
        //System.out.println(reviewer);
        return reviewer;
    }

}