import React, {Component} from 'react';
import Header from "./Header";
import Table from "react-bootstrap/Table";
import axios from "axios";
import {Link} from "react-router-dom";

class AuthList extends Component {
    state = {
        authUpdate : false,
        authList:[],
        name:"",
        authority:"",
        id:""
    }
    componentDidMount() {
        let uri = "http://localhost:8080/role/list";
        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    authList: data
                })
            })
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    onClickedUpdateAuthBtn = (e) => {
        this.setState({
            authUpdate: !this.state.authUpdate,
            name: e.name,
            id:e.id
        })
    }

    updateAuth = () => {
        const {name,id} = this.state;

        const putAuth = {
            id:id,
            name : name
        }

        axios.put('http://localhost:8080/role/update/',putAuth,
            {headers:{Authorization: sessionStorage.getItem('token')}});
    }

    deleteRole = (e) => {
        axios.delete('http://localhost:8080/role/delete/'+e.id,
            {headers:{Authorization: sessionStorage.getItem('token')}})
            .then(res => {this.setState({authList:this.state.authList.filter(table => table.id!==e.id)})});
    }

    render() {
        const{authUpdate,authList,name,id} = this.state;
        return (
            <div>
                <Header/>
                <Link to = "/addrole"><button className="btn btn-success productListAddProduct">+ Add Role</button></Link>
                {
                    authUpdate ?
                        <div>
                            <div className="col-md-12 mb-4 mt-2">
                                <div className="card" align="left">
                                    <div className="card-header" align="center">
                                        <h4>Update Auth</h4>
                                    </div>
                                    <div className="card-body">
                                        <form>

                                            <div className="form-group">
                                                <label htmlFor="idInput">Role ID</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={id}
                                                       name="id"
                                                       id="idInput"
                                                       value={id}
                                                       disabled = {id}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="nameInput">Role Name</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={name}
                                                       name="name"
                                                       id="nameInput"
                                                       value={name}
                                                       onChange={this.changeInput}
                                                />
                                            </div>



                                            <button className="btn btn-warning btn-block"
                                                    onClick={this.updateAuth}>Update
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>:null
                }
                <Table striped bordered hover className="usersTable">
                    <thead>
                        <tr>
                            <th>#ID</th>
                            <th>Name</th>
                            <th>Buttons</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        authList.map(v => {
                            return(
                                <tr>
                                    <td>{v.id}</td>
                                    <td>{v.name}</td>
                                    <td align="center">
                                        <button className="btn btn-warning mr-2" onClick={this.onClickedUpdateAuthBtn.bind(this,v)}>Update</button>
                                        <button className="btn btn-danger mr-2" onClick={this.deleteRole.bind(this,v)}>Delete</button>
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

export default AuthList;