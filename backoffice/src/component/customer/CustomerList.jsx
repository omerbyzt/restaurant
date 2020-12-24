import React, {Component} from 'react';
import Header from "../Header";
import {Link} from "react-router-dom";
import Table from "react-bootstrap/Table";
import CustomerService from "../../service/CustomerService";
import Loading from "../Loading";
import UpdateCustomer from "./UpdateCustomer";

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

    async componentDidMount() {
        this.setState({loadingIsVisible: true});
        const token = "Basic YWRtaW46MTIz";
        await CustomerService.getPageCustomer(token, this.state.selectedPage).then((res) => {
            this.setState({
                totalPage: res.data.totalPages,
                customerList: res.data.content
            })
        })
        this.setState({loadingIsVisible: false});
    }

    async loadPage(e) {
        this.setState({loadingIsVisible: true});
        this.setState({selectedPage: e + 1})

        await CustomerService.getPageCustomer(this.state.token, e).then((res) => {
            this.setState({
                totalPage: res.data.totalPages,
                customerList: res.data.content
            })
        })
        this.setState({loadingIsVisible: false});
    }

    deleteCustomer = (e) => {
        CustomerService.deleteCustomer(e.id, this.state.token).then(res => {
            this.setState({customerList: this.state.customerList.filter(table => table.id !== e.id)})
        })
    }

    updateButton = (e) => {
        this.setState({
            selectedCustomer: e,
            isUpdateCustomer: !this.state.isUpdateCustomer
        })
    }

    render() {
        const {customerList} = this.state;

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

        return (
            <div>
                <div>
                    <Header/>

                    <Link to="/addcustomer">
                        <button className="btn btn-success productListAddProduct">+ Add Customer</button>
                    </Link>

                    {
                        this.state.isUpdateCustomer ?
                            <UpdateCustomer
                                id={this.state.selectedCustomer.id}
                                name={this.state.selectedCustomer.name}
                                surname={this.state.selectedCustomer.surname}
                                phoneNumber={this.state.selectedCustomer.phoneNumber}
                                address={this.state.selectedCustomer.address}
                            /> : null
                    }

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
                                            <button className="btn btn-danger mr-2"
                                                    onClick={() => this.deleteCustomer(v)}>Delete
                                            </button>
                                            <button className="btn btn-warning mr-2"
                                                    onClick={() => this.updateButton(v)}>Update
                                            </button>
                                            <button className="btn btn-info">View</button>
                                        </td>
                                    </tr>
                                )
                            }
                            </tbody>
                        </Table>
                        <div align="center">
                            {buttonArray}
                        </div>
                    </div>
                </div>
                {
                    this.state.loadingIsVisible ?
                        <Loading/> : null
                }
            </div>
        );
    }
}

export default CustomerList;