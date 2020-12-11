import React, {Component} from 'react';
import Header from "./Header";
import axios from "axios";
import DropdownButton from "react-bootstrap/DropdownButton";
import Dropdown from "react-bootstrap/Dropdown";

class AddWaiter extends Component {

    state = {
        name:"",
        phoneNumber:"",
        mail:"",
        address:"",
        urlToImage:"",
        salary:"",
        mediaList:[],
        selectedMediaName: "Chose Media",
        selectedMediaUrl: "",
        selectedMediaID:"",
    }

    componentDidMount() {
        let uri = "http://localhost:8080/file/list";
        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    mediaList: data
                })
            })
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addWaiter = () => {
        const{name,phoneNumber,mail,address,urlToImage,salary,selectedMediaName,selectedMediaUrl,selectedMediaID} = this.state


        const newMedia = {
            id:selectedMediaID,
            name:selectedMediaName,
            fileContent:selectedMediaUrl,
        }

        const newWaiter = {
            name:name,
            phoneNumber: phoneNumber,
            mail:mail,
            address: address,
            urlToImage: urlToImage,
            salary: salary,
            mediaDTO:newMedia
        }

        axios.post("http://localhost:8080/waiter/add-waiter", newWaiter,
            {headers:{Authorization:sessionStorage.getItem('token')}});
    }

    onClickItem = (e) => {
        this.setState({
            selectedMediaName: e.name,
            selectedMediaUrl:e.fileContent,
            selectedMediaID:e.id
        })
    }

    render() {
        const{name,phoneNumber,mail,address,urlToImage,salary,mediaList,selectedMediaID,selectedMediaName,selectedMediaUrl}=this.state
        return (
            <div>
                <Header/>

                <div className="container-fluid">
                    <div className="row">
                        <div className="col-md-5 mr-auto mb-4 mt-4">
                            <div className="card">
                                <div className="card-header">
                                    <h4>Add Waiter</h4>
                                </div>
                                <div className="card-body">
                                    <form onSubmit={this.addWaiter}>
                                        <div className="form-group">
                                            <label htmlFor="input-waiter-name">Waiter Name</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Name"
                                                   name="name"
                                                   id="input-waiter-name"
                                                   value ={name}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-phoneNumber">Waiter Phone Number</label>
                                            <input type="number"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Phone Number"
                                                   name="phoneNumber"
                                                   id="input-waiter-phoneNumber"
                                                   value = {phoneNumber}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-mail">Waiter Mail</label>
                                            <input type="mail"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Mail"
                                                   name="mail"
                                                   id="input-waiter-mail"
                                                   value ={mail}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-address">Waiter Address</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Address"
                                                   name="address"
                                                   id="input-waiter-address"
                                                   value ={address}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-urlToImage">Waiter Image URL</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Image URL"
                                                   name="urlToImage"
                                                   id="input-waiter-urlToImage"
                                                   value ={urlToImage}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-salary">Waiter Salary</label>
                                            <input type="number"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Salary"
                                                   name="salary"
                                                   id="input-waiter-salary"
                                                   value ={salary}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div>
                                            <DropdownButton id="dropdown-basic-button" title={selectedMediaName}>
                                                {
                                                    mediaList.map(v => {
                                                        return (
                                                            <Dropdown.Item
                                                                onClick={this.onClickItem.bind(this, v)}
                                                            >{v.name}</Dropdown.Item>
                                                        )
                                                    })
                                                }
                                            </DropdownButton>
                                        </div>


                                        <button className="btn btn-warning btn-block mt-3" type="submit">Add Waiter</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddWaiter;