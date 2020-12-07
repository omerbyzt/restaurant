import React, {Component} from 'react';
import Header from "./Header";
import Table from "react-bootstrap/Table";
import {Link} from "react-router-dom";
import axios from "axios";

class WaiterList extends Component {
    state = {
        waiterList:[],
        isVisible:false,
        id:"",
        name:"",
        phoneNumber:"",
        mail:"",
        address:"",
        urlToImage:"",
        salary:""
    }

    componentDidMount() {
        const {waiterList} = this.state

        let uri = "http://localhost:8080/waiter/list-waiters";
        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    waiterList: data
                })
            })
    }

    updateWaiterOpenForm = (e) => {
        this.setState({
            isVisible:!this.state.isVisible,
            id:e.id,
            name:e.name,
            phoneNumber:e.phoneNumber,
            mail:e.mail,
            address:e.address,
            urlToImage:e.urlToImage,
            salary:e.salary
        })
        console.log(this.state.name)
    }

    deleteWaiter = (e) => {
        axios.delete('http://localhost:8080/waiter/delete-waiter/' + e.id,
            {headers:{Authorization: sessionStorage.getItem('token')}})
            .then(res => {this.setState({waiterList:this.state.waiterList.filter(table => table.id!==e.id)})});
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    updateWaiter = () => {
        const{id,name,phoneNumber,mail,address,urlToImage,salary,waiterList}=this.state;

        const putWaiter = {
            id:id,
            name:name,
            phoneNumber:phoneNumber,
            mail:mail,
            address:address,
            urlToImage: urlToImage,
            salary: salary
        }

        axios.put('http://localhost:8080/waiter/update-waiter',putWaiter,
            {headers:{Authorization: sessionStorage.getItem('token')}})
            .then(res => {this.setState({waiterList: this.state.waiterList})});
    }

    closeUpdate = () => {
        this.setState({
            isVisible:!this.state.isVisible
        })
    }

    render() {
        const{waiterList,isVisible,id,name,phoneNumber,mail,address,urlToImage,salary}=this.state;
        return (
            <div>
                <Header></Header>
                <Link to = "/addwaiter"><button className="btn btn-success productListAddProduct">+ Add Waiter</button></Link>
                {
                    isVisible ?
                        <div className="col-md-6 mr-auto mb-4 mt-2">
                            <div className="card">
                                <div className="card-header" >
                                    <h4>Update Waiter</h4>
                                    {/*<span className="cardHeader">Update Waiter</span>*/}
                                    {/*<button className="btn btn-danger closeButton" onClick={this.closeUpdate}>Close</button>*/}
                                </div>
                                <div className="card-body">
                                    <form onSubmit={this.updateWaiter}>
                                        <div className="form-group">
                                            <label htmlFor="input-waiter-id">Waiter ID</label>
                                            <input type="number"
                                                   className="form-control"
                                                   name="name"
                                                   id="input-waiter-id"
                                                   value ={id}
                                                   disabled={id}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

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
                                        <button className="btn btn-warning submitButton" type="submit">Update Waiter</button>
                                        <button className="btn btn-danger bottomCloseButton" onClick={this.closeUpdate}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>:null
                }
                <Table striped bordered hover className="usersTable">
                    <thead>
                    <tr>
                        <th>#Waiter ID</th>
                        <th>Waiter Name</th>
                        <th>Waiter Phone Number</th>
                        <th>Waiter Mail</th>
                        <th>Waiter Address</th>
                        <th>Waiter Image Url</th>
                        <th>Waiter Salary</th>
                        <th>Buttons</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        waiterList.map(v=>{
                            return(
                                <tr>
                                    <td>{v.id}</td>
                                    <td>{v.name}</td>
                                    <td>{v.phoneNumber}</td>
                                    <td>{v.mail}</td>
                                    <td>{v.address}</td>
                                    <td>{v.urlToImage}</td>
                                    <td>{v.salary}</td>
                                    <td align="center">
                                        <button className="btn btn-warning mr-2" onClick={this.updateWaiterOpenForm.bind(this,v)}>Update</button>
                                        <button className="btn btn-danger mr-2" onClick={this.deleteWaiter.bind(this,v)}>Delete</button>
                                    </td>
                                </tr>
                            )
                        })
                    }
                    </tbody>
                </Table>

            </div>
        );
    }
}

export default WaiterList;