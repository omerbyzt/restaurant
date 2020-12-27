import React, {Component} from 'react';
import Header from "../Header";
import CustomerService from "../../service/CustomerService";
import axios from "axios";

class UpdateCustomer extends Component {
    state = {
        id: this.props.id,
        name: this.props.name,
        surname: this.props.surname,
        phoneNumber: this.props.phoneNumber,
        address: this.props.address,
        token: "Basic YWRtaW46MTIz",
        selectedMediaID:"",
        selectedMediaName:"Select Media",
        selectedMediaURL:"",
        mediaList:[]
    }

    async componentDidMount() {
        await axios.get('http://localhost:8080/file/list',
            // {headers: {Authorization: sessionStorage.getItem('token')}})
            {headers: {Authorization: this.state.token}})
            .then(res => {
                this.setState({mediaList: res.data})
            });
    }

    updateCustomer = () => {
        const {id,name,surname,phoneNumber,address,token,selectedMediaName,selectedMediaID,selectedMediaURL} = this.state;

        const updateMedia = {
            id:selectedMediaID,
            name:selectedMediaName,
            fileContent:selectedMediaURL
        }

        const updateCustomer = {
            id:id,
            name:name,
            surname:surname,
            phoneNumber: phoneNumber,
            address: address,
            media:updateMedia
        }
        CustomerService.updateCustomer(updateCustomer,token)
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    onClickMediaItem = (e) => {
        console.log(e)
        this.setState({
            selectedMediaName: e.name,
            selectedMediaID: e.id,
            selectedMediaURL:e.fileContent
        })
    }

    render() {
        const {id, name, surname, phoneNumber, address,mediaList,selectedMediaName} = this.state;
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
                            <button className="btn btn-warning btn-block mt-3" onClick={() => this.updateCustomer()}>Update
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