package com.ba.service;

import com.ba.dto.CustomerDTO;
import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerSoapService {

    public boolean deleteCustomer(Long id){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/xml");
        RequestBody body = RequestBody.create(mediaType, "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:ser=\"http://service.ba.com\">\r\n   <soapenv:Header/>\r\n   <soapenv:Body>\r\n      <ser:deleteCustomer>\r\n         " +
                "<ser:id>" +
                id +
                "</ser:id>\r\n      " +
                "</ser:deleteCustomer>\r\n   " +
                "</soapenv:Body>\r\n</soapenv:Envelope>");
        Request request = new Request.Builder()
                .url("http://localhost:8081/Customerv2/services/CustomerServiceImpl.CustomerServiceImplHttpEndpoint/deleteCustomer")
                .method("POST", body)
                .addHeader("Content-Type", "text/xml")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addCustomer(CustomerDTO customerDTO){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/xml");
        RequestBody body = RequestBody.create(mediaType, "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\" xmlns:xsd=\"http://dto.ba.com/xsd\">\r\n   <soapenv:Header/>\r\n   <soapenv:Body>\r\n      <ser:addCustomer>\r\n         <ser:customerDTO>\r\n            " +
                "<xsd:address>" +
                customerDTO.getAddress() +
                "</xsd:address>\r\n            " +
                "<xsd:name>" +
                customerDTO.getName() +
                "</xsd:name>\r\n            " +
                "<xsd:phoneNumber>" +
                customerDTO.getPhoneNumber() +
                "</xsd:phoneNumber>\r\n            " +
                "<xsd:surname>" +
                customerDTO.getSurname() +
                "</xsd:surname>\r\n         " +
                "</ser:customerDTO>\r\n      </ser:addCustomer>\r\n   </soapenv:Body>\r\n</soapenv:Envelope>");
        Request request = new Request.Builder()
                .url("http://localhost:8081/Customerv2/services/CustomerServiceImpl.CustomerServiceImplHttpEndpoint/addCustomer")
                .method("POST", body)
                .addHeader("Content-Type", "text/xml")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(CustomerDTO customerDTO){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/xml");
        RequestBody body = RequestBody.create(mediaType, "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:ser=\"http://service.ba.com\" xmlns:xsd=\"http://dto.ba.com/xsd\">\r\n   <soapenv:Header/>\r\n   <soapenv:Body>\r\n      " +
                "<ser:updateCustomer>\r\n         <ser:customerDTO>\r\n            " +
                "<xsd:address>" +
                customerDTO.getAddress() +
                "</xsd:address>\r\n            " +
                "<xsd:id>" +
                customerDTO.getId() +
                "</xsd:id>\r\n            " +
                "<xsd:name>" +
                customerDTO.getName() +
                "</xsd:name>\r\n            " +
                "<xsd:phoneNumber>" +
                customerDTO.getPhoneNumber() +
                "</xsd:phoneNumber>\r\n            " +
                "<xsd:surname>" +
                customerDTO.getSurname() +
                "</xsd:surname>\r\n         </ser:customerDTO>\r\n      </ser:updateCustomer>\r\n   </soapenv:Body>\r\n</soapenv:Envelope>");
        Request request = new Request.Builder()
                .url("http://localhost:8081/Customerv2/services/CustomerServiceImpl.CustomerServiceImplHttpEndpoint/updateCustomer")
                .method("POST", body)
                .addHeader("Content-Type", "text/xml")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CustomerDTO> getAllCustomer(){
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\">\r\n   <soapenv:Header/>\r\n   <soapenv:Body>\r\n      <ser:getAllCustomer/>\r\n   </soapenv:Body>\r\n</soapenv:Envelope>");
        Request request = new Request.Builder()
                .url("http://localhost:8081/Customerv2/services/CustomerServiceImpl.CustomerServiceImplHttpEndpoint/getAllCustomer")
                .method("POST", body)
                .addHeader("Content-Type", "text/plain")
                .build();
        try {
            Response response = client.newCall(request).execute();
            customerXmlParse(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerDTOList;
    }

    public static List<CustomerDTO> customerXmlParse(String xml) {

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        String firstKey = "<ns:getAllCustomerResponse xmlns:ns=\"http://service.ba.com\" xmlns:ax21=\"http://model.ba.com/xsd\" xmlns:ax25=\"http://dto.ba.com/xsd\">";
        String endKey = "</ns:getAllCustomerResponse>";
        int firstIndex = xml.indexOf(firstKey) + firstKey.length();
        int endIndex = xml.indexOf(endKey, firstIndex);
        xml = xml.substring(firstIndex, endIndex);
        firstKey = "<ns:return xsi:type=\"ax21:Customer\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
        firstIndex = xml.indexOf(firstKey) + firstKey.length() + 1;

        endIndex=0;

        while (firstIndex > -1) {
            CustomerDTO customerDTO = new CustomerDTO();
            firstKey="<ax21:address>";
            endKey="</ax21:address>";
            firstIndex=xml.indexOf(firstKey,endIndex)+firstKey.length();
            endIndex=xml.indexOf(endKey,firstIndex);
            customerDTO.setAddress(xml.substring(firstIndex,endIndex));

            firstKey="<ax21:id>";
            endKey="</ax21:id>";
            firstIndex=xml.indexOf(firstKey,endIndex)+firstKey.length();
            endIndex=xml.indexOf(endKey,firstIndex);
            customerDTO.setId(Integer.parseInt(xml.substring(firstIndex,endIndex)));

            firstKey="<ax21:name>";
            endKey="</ax21:name>";
            firstIndex=xml.indexOf(firstKey,endIndex)+firstKey.length();
            endIndex=xml.indexOf(endKey,firstIndex);
            customerDTO.setName(xml.substring(firstIndex,endIndex));

            firstKey="<ax21:phoneNumber>";
            endKey="</ax21:phoneNumber>";
            firstIndex=xml.indexOf(firstKey,endIndex)+firstKey.length();
            endIndex=xml.indexOf(endKey,firstIndex);
            customerDTO.setPhoneNumber(xml.substring(firstIndex,endIndex));

            firstKey="<ax21:surname>";
            endKey="</ax21:surname>";
            firstIndex=xml.indexOf(firstKey,endIndex)+firstKey.length();
            endIndex=xml.indexOf(endKey,firstIndex);
            customerDTO.setSurname(xml.substring(firstIndex,endIndex));
            customerDTOList.add(customerDTO);
            firstKey = "<ns:return xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ax21:CustomerDTO\">";
            firstIndex = xml.indexOf(firstKey,endIndex);
            if(firstIndex>-1){
                firstIndex+=firstKey.length();
            }

        }
        return customerDTOList;
    }
}
