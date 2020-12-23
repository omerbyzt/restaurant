import React, {useState, useEffect} from "react";
import {Link, useHistory} from 'react-router-dom';
import CustomerService from "../../service/CustomerService";
import Header from "../Header";
import Table from 'react-bootstrap/Table';
import {render} from "@testing-library/react";

const CustomerListComponent = () => {
    const [customerList, setCustomerList] = useState([]);
    const [selectedPage, setSelectedPage] = useState(0);
    const [totalPage, setTotalPage] = useState(5);
    const [buttonArray, setButtonArray] = useState([]);
    const history = useHistory();

    async function getCustomers() {
        const token = "Basic YWRtaW46MTIz";
        console.log("selectedPage : " + selectedPage);
        await CustomerService.getPageCustomer(token, selectedPage).then((res) => {
            setTotalPage(res.data.totalPages);
            setCustomerList(res.data.content);
        })

        console.log(customerList)
    }

    useEffect(() => {
        getCustomers();
    }, [])

    const pageButtons = () => {

        for (let i = 0; i < 5; i++) {
            if(selectedPage === i){
                buttonArray.push(
                    <button className="btn btn-info ml-1" onClick={() => clickPageNumber(i+1)} id={i}>
                        <h4>{i+1}</h4>
                    </button>
                )
            }
            else{
                buttonArray.push(
                    <button className="btn btn-outline-info ml-1" onClick={() => clickPageNumber(i+1)} id={i}>
                        <h4>{i+1}</h4>
                    </button>
                )
            }

        }
        return buttonArray;
    }

    const clickPageNumber = async (e) => {
        await setSelectedPage(e)
        console.log("selected page : " + e)
        getCustomers();
        // const btn = document.getElementById(e-1)
        // btn.className ="btn btn-info ml-1";
    }

    return (
        <div>
            <Header/>

            <Link to="/addcustomer">
                <button className="btn btn-success productListAddProduct">+ Add User</button>
            </Link>

            <div align="center">
                <Table striped bordered hover className="usersTable">
                    <thead>
                    <tr>
                        <th>CustomerID</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th>Buttons</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        customerList.map((v) =>
                            <tr key={v.id}>
                                <td>{v.id}</td>
                                <td>{v.name}</td>
                                <td>{v.surname}</td>
                                <td>{v.phoneNumber}</td>
                                <td>{v.address}</td>
                                <td align="center">
                                    <button className="btn btn-danger mr-2">Delete</button>
                                    <button className="btn btn-warning mr-2">Update</button>
                                    <button className="btn btn-info">View</button>
                                </td>
                            </tr>
                        )
                    }
                    </tbody>
                </Table>
                    {pageButtons()}
            </div>

        </div>
    )
}
export default CustomerListComponent;