import React, {Component} from 'react';
import Header from "../Header";
import {Link} from "react-router-dom";
import Table from "react-bootstrap/Table";
import CustomerService from "../../service/CustomerService";
import Loading from "../Loading";
import UpdateCustomer from "./UpdateCustomer";
import ReactToExcel from "react-html-table-to-excel";

class CustomerList extends Component {
    state = {
        customerList: [],
        selectedPage: 1,
        totalPage: 0,
        loadingIsVisible: false,
        token: "Basic YWRtaW46MTIz",
        isUpdateCustomer: false,
        selectedCustomer: ""
    }

    componentDidMount() {
        this.getCustomerPageFromService(CustomerService.token, this.state.selectedPage - 1);
    }

    loadPage(e) {
        this.setState({loadingIsVisible: true, selectedPage: e + 1});
        this.getCustomerPageFromService(e);
    }

    getCustomerPageFromService(e) {
        this.setState({loadingIsVisible: true});
        CustomerService.getPageCustomer(this.state.token, this.state.selectedPage - 1).then((res) => {
            this.setState({
                totalPage: res.data.totalPages,
                customerList: res.data.content,
                loadingIsVisible: false
            })
        }).catch(
            this.setState({loadingIsVisible: false, customerList: null})
        )
    }

    deleteCustomer = (e) => {
        CustomerService.deleteCustomer(e.id, this.state.token).then(res => {
            if (res.status == 200) {
                this.setState({customerList: this.state.customerList.filter(table => table.id !== e.id)})
            }
        })
    }

    updateButton = (e) => {
        this.setState({
            selectedCustomer: e,
            isUpdateCustomer: !this.state.isUpdateCustomer
        })
    }

    pageButtons() {
        let buttonArray = []

        for (let i = 0; i < this.state.totalPage; i++) {
            if (this.state.selectedPage == i + 1) {
                buttonArray.push(
                    <button className="btn btn-primary ml-1" onClick={() => this.loadPage(i)}>
                        <h4>{i + 1}</h4>
                    </button>)
            } else {
                buttonArray.push(
                    <button className="btn btn-outline-primary ml-1" onClick={() => this.loadPage(i)}>
                        <h4>{i + 1}</h4>
                    </button>
                )
            }
        }
        return buttonArray;
    }

    updateCustomerForm() {
        return this.state.isUpdateCustomer ?
            <UpdateCustomer
                id={this.state.selectedCustomer.id}
                name={this.state.selectedCustomer.name}
                surname={this.state.selectedCustomer.surname}
                phoneNumber={this.state.selectedCustomer.phoneNumber}
                address={this.state.selectedCustomer.address}
            /> : null;
    }

    mapCustomerList(customerList) {
        return customerList.map((v) =>
            <tr key={v.id}>
                <td>{v.id}</td>
                <td>{v.name}</td>
                <td>{v.surname}</td>
                <td>{v.phoneNumber}</td>
                <td>{v.address}</td>
                <td align="center">
                    <img src={'data:image/png;base64,' + v.media.fileContent} width="100"
                         style={{margin: 10}}/>
                </td>
                <td align="center">
                    <button className="btn btn-danger mr-2"
                            onClick={() => this.deleteCustomer(v)}>Delete
                    </button>
                    <button className="btn btn-warning mr-2"
                            onClick={() => this.updateButton(v)}>Update
                    </button>
                    <button className="btn btn-info">View</button>
                </td>
            </tr>
        );
    }

    customerTableList(customerList) {
        if (customerList == null) {
            return (
                <h3 align="center">List cannot view...!</h3>
            )
        }

        return <div align="center">
            <Table striped bordered hover className="usersTable" id="customer-table">
                <thead>
                <tr>
                    <th>CustomerID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                    <th>Customer Madia</th>
                    <th>Buttons</th>
                </tr>
                </thead>
                <tbody>
                {
                    this.mapCustomerList(customerList)
                }
                </tbody>
            </Table>

            <ReactToExcel
                className="btn btn-success"
                table="customer-table"
                filename="customer-table"
                sheet="sheet 1"
                buttonText="Export"
            />

            <div align="center">
                {this.pageButtons()}
            </div>
        </div>;
    }

    loadingVisible() {
        return(
            this.state.loadingIsVisible ?
                <Loading/> : null
        )
    }

    render() {
        const {customerList} = this.state;
        return (
            <div>
                <div>
                    <Header/>
                    <Link to="/addcustomer">
                        <button className="btn btn-success productListAddProduct">+ Add Customer</button>
                    </Link>
                    {
                        this.updateCustomerForm()
                    }
                    {this.customerTableList(customerList)}
                </div>
                    {this.loadingVisible()}
            </div>
        );
    }
}

export default CustomerList;