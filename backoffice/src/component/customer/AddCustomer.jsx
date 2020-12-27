import React, {Component} from 'react';
import Header from "../Header";
import CustomerService from "../../service/CustomerService";
import axios from "axios";

class AddCustomer extends Component {
    state = {
        name: "",
        surname: "",
        phoneNumber: "",
        address: "",
        selectedMediaName: "Select Media",
        selectedMediaID: "",
        selectedMediaURL: "",
        token: "Basic YWRtaW46MTIz",
        mediaList: []
    }

    async componentDidMount() {
        await axios.get('http://localhost:8080/file/list',
            // {headers: {Authorization: sessionStorage.getItem('token')}})
            {headers: {Authorization: this.state.token}})
            .then(res => {
                this.setState({mediaList: res.data})
            });
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addCustomer = async () => {
        const {name, surname, phoneNumber, address, selectedMediaName, selectedMediaID, selectedMediaURL} = this.state;
        const token = "Basic YWRtaW46MTIz";

        const newMedia = {
            id: selectedMediaID,
            name: selectedMediaName,
            fileContent: selectedMediaURL
        }

        const newCustomer = {
            name: name,
            surname: surname,
            phoneNumber: phoneNumber,
            address: address,
            media: newMedia
        }

        CustomerService.addCustomer(token, newCustomer)
        this.props.history.push("/customerlist")
    }

    onClickMediaItem = (e) => {
        console.log(e)
        this.setState({
            selectedMediaName: e.name,
            selectedMediaID: e.id,
            selectedMediaURL: e.fileContent
        })
    }

    render() {
        const {name, surname, phoneNumber, address, selectedMediaName, mediaList} = this.state;
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

                                <div className="dropdown d-inline">
                                    <label htmlFor="price">Table Category Media : </label>
                                    <button className="btn btn-info dropdown-toggle dropdownCss" type="button"
                                            id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="true">
                                        {selectedMediaName}
                                    </button>
                                    <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        {
                                            mediaList.map(v => {
                                                return (
                                                    <div className="row col-md -12">
                                                        <a className="dropdown-item"
                                                           onClick={this.onClickMediaItem.bind(this, v)}>
                                                            {v.name}
                                                        </a>
                                                    </div>
                                                )
                                            })
                                        }
                                    </div>
                                </div>
                            </form>

                            <button className="btn btn-warning btn-block mt-3" onClick={() => this.addCustomer()}>Add
                                Customer
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddCustomer;