import React, {Component} from 'react';
import Header from "../Header";
import CustomerService from "../../service/CustomerService";

class AddCustomer extends Component {
    state = {
        name: "",
        surname: "",
        phoneNumber: "",
        address: "",
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addCustomer = async () => {
        const {name, surname, phoneNumber, address} = this.state;
        const token = "Basic YWRtaW46MTIz";
        const newCustomer = {
            name: name,
            surname: surname,
            phoneNumber: phoneNumber,
            address: address
        }

        CustomerService.addCustomer(token,newCustomer)
        this.props.history.push("/customerlist")
    }

    render() {
        const {name, surname, phoneNumber, address} = this.state;
        return (
            <div>
                <Header/>
                <div className="col-md-6 mr-auto mb-4 mt-4">
                    <div className="card">
                        <div className="card-header">
                            <h4>Add Customer</h4>
                        </div>
                        <div className="card-body">
                            <form>
                                <div className="form-group">
                                    <label htmlFor="custName">Customer Name</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Customer Name"
                                           name="name"
                                           id="custName"
                                           value={name}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="custSurname">Customer Surname</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Customer Surname"
                                           name="surname"
                                           id="custSurname"
                                           value={surname}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="custPhoneNumber">Customer Phone Number</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Customer Phone Number"
                                           name="phoneNumber"
                                           id="custPhoneNumber"
                                           value={phoneNumber}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="custAddress">Customer Address</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Customer Surname"
                                           name="address"
                                           id="custAddress"
                                           value={address}
                                           onChange={this.changeInput}
                                    />
                                </div>
                            </form>
                            <button className="btn btn-warning btn-block" onClick={()=> this.addCustomer()}>Add Customer
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddCustomer;