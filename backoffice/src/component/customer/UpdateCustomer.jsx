import React, {Component} from 'react';
import Header from "../Header";
import CustomerService from "../../service/CustomerService";

class UpdateCustomer extends Component {
    state = {
        id: this.props.id,
        name: this.props.name,
        surname: this.props.surname,
        phoneNumber: this.props.phoneNumber,
        address: this.props.address,
    }

    updateCustomer = () => {
        const {id,name,surname,phoneNumber,address,token} = this.state;

        const updateCustomer = {
            id:id,
            name:name,
            surname:surname,
            phoneNumber: phoneNumber,
            address: address,
            token: "Basic YWRtaW46MTIz"
        }

        CustomerService.updateCustomer(updateCustomer,token)
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    render() {
        const {id, name, surname, phoneNumber, address} = this.state;
        return (
            <div>
                <div className="col-md-6 mr-auto mb-4 mt-4">
                    <div className="card">
                        <div className="card-header">
                            <h4>Update Customer</h4>
                        </div>
                        <div className="card-body">
                            <form>
                                <div className="form-group">
                                    <label htmlFor="custId">Customer Id</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Customer ID"
                                           name="id"
                                           id="custId"
                                           value={id}
                                           disabled={id}
                                           onChange={this.changeInput}
                                    />
                                </div>

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
                            <button className="btn btn-warning btn-block" onClick={() => this.updateCustomer()}>Update
                                Customer
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default UpdateCustomer;