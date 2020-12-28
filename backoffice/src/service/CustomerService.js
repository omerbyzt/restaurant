import axios from 'axios';

const Customer_Api_Base_URL = "http://localhost:8080/customer";
const Customer_Api_Page_URL = "http://localhost:8080/customer/page?page=";
const Customer_Api_Slice_URL = "http://localhost:8080/customer/slice";

class CustomerService {
    static token = "Basic YWRtaW46MTIz";//context veya localden alınacak

    getCustomer(token) {
        return axios.get(Customer_Api_Base_URL, {
            headers: {
                Authorization: token
            }
        })
    }

    addCustomer(token,customer) {
        console.log(customer)
        return axios.post(Customer_Api_Base_URL, customer, {
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            }
        })
    }

    updateCustomer(customer, token) {
        return axios.put(Customer_Api_Base_URL, customer, {
            headers: {
                Authorization: token
            }
        })
    }

    deleteCustomer(id,token){
        return axios.delete(Customer_Api_Base_URL+'/'+id,{
            headers: {
                Authorization: token
            }
        })
    }

    getCustomerById(id,token){
        return axios.get(Customer_Api_Base_URL+'/'+id,{
            headers: {
                Authorization: token
            }
        })
    }

    getPageCustomer(token,page){
        return axios.get(Customer_Api_Page_URL+page,{
            headers: {
                Authorization: token
            }
        })
    }

}

export default new CustomerService();

