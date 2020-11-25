import React, {Component} from 'react';
import Table from "react-bootstrap/Table";
import axios from "axios";
import Header from "./Header";
import {Link} from "react-router-dom";

class UserList extends Component {
    state = {
        userList: [],
        userUpdate: false,
        username: "",
        password: "",
        enabled: "",
    }

    componentDidMount() {

        const {userList} = this.state

        let uri = "http://localhost:8080/users/listall";

        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    userList: data
                })
            })
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    onClickUpdateBtn = (e) => {
        this.setState({
            userUpdate: !this.state.userUpdate,
            username: e.username,
            password: e.password,
            enabled: e.enabled
        })
    }

    onClickDeleteBtn = (e) => {
        window.location.reload();
        axios.delete('http://localhost:8080/users/delete/' + e.username,
            {headers:{Authorization: sessionStorage.getItem('token')}});

        axios.delete('http://localhost:8080/auth/delete/' + e.username,
            {headers:{Authorization: sessionStorage.getItem('token')}});

    }

    updateUser = (e) => {

        const {userList, userUpdate, username, password,enabled} = this.state;

        const putUser = {
            username: username,
            password: password,
            enabled: enabled
        }

        axios.put('http://localhost:8080/users/update/',putUser,
            {headers:{Authorization: sessionStorage.getItem('token')}});
    }

    render() {
        const {userList, userUpdate, username, password, enabled} = this.state;
        return (
            <div>
                <Header></Header>
                <Link to = "/adduser"><button className="btn btn-success productListAddProduct">+ Add User</button></Link>
                {
                    userUpdate ?
                        <div>
                            <div className="col-md-12 mb-4 mt-2">
                                <div className="card" align="left">
                                    <div className="card-header" align="center">
                                        <h4>Update User</h4>
                                    </div>
                                    <div className="card-body">
                                        <form>
                                            <div className="form-group">
                                                <label htmlFor="username">User Name</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={username}
                                                       name="username"
                                                       id="nameInput"
                                                       value={username}
                                                       disabled = {username}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="password">User Password</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={password}
                                                       name="password"
                                                       id="passwordInput"
                                                       value={password}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="enabled">User Enabled</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={enabled}
                                                       name="enabled"
                                                       id="nameInput"
                                                       value={enabled}
                                                       onChange={this.changeInput}
                                                />
                                            </div>
                                            <button className="btn btn-warning btn-block"
                                                    onClick={this.updateUser}>Update
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div> : null
                }
                <Table striped bordered hover className="usersTable">
                    <thead>
                    <tr>
                        <th>User Name</th>
                        <th>User Password</th>
                        <th>User Enabled</th>
                        <th>Buttons</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        userList.map(v => {
                            return (
                                <tr>
                                    <td>{v.username}</td>
                                    <td>{v.password}</td>
                                    <td>{v.enabled.toString()}</td>
                                    p<td align="center">
                                        <button className="btn btn-warning mr-2"
                                                onClick={this.onClickUpdateBtn.bind(this, v)}>Update
                                        </button>
                                        <button className="btn btn-danger"
                                                onClick={this.onClickDeleteBtn.bind(this, v)}>Delete
                                        </button>
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

export default UserList;