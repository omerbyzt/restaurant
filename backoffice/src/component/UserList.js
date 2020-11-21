import React, {Component} from 'react';
import Table from "react-bootstrap/Table";
import axios from "axios";

class UserList extends Component {
    state = {
        userList: [],
        userUpdate: false,
        userID:"",
        userName: "",
        password: "",
        role: ""
    }

    componentDidMount() {

        const {userList} = this.state

        let uri = "http://localhost:8080/user/listall";

        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Basic ' + btoa('admin:pass3'),
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
            userID: e.userID,
            userName: e.userName,
            password: e.password,
            role: e.role
        })
    }

    onClickDeleteBtn = (e) => {
/*
        return fetch('http://localhost:8080/user/delete/' + e.userID, {
            method: 'DELETE',
        }).then(response => response.json())
*/
        console.log('http://localhost:8080/user/delete/', e.userID);
/*
        axios.delete('http://localhost:8080/user/delete/', e.userID, {
            headers: {
                Authorization: 'Basic ' + btoa('admin:pass3') //the token is a variable which holds the token
            }
        })
*/
    }

    updateUser = (e) => {

        const {userList, userUpdate, userID, userName, password, role} = this.state;

        const putUser = {
            userID: userID,
            userName: userName,
            password: password,
            role: role
        }
        axios.put('http://localhost:8080/user/update/', putUser);

    }

    render() {
        const {userList, userUpdate, userID, userName, password, role} = this.state;
        return (
            <div>
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
                                                <label htmlFor="id">User ID</label>
                                                <input type="number"
                                                       className="form-control"
                                                       placeholder={userID}
                                                       name="userID"
                                                       id="idInput"
                                                       value={userID}
                                                       disabled={userID}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="id">User Name</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={userName}
                                                       name="userName"
                                                       id="nameInput"
                                                       value={userName}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="id">User Password</label>
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
                                                <label htmlFor="id">User Role</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={role}
                                                       name="role"
                                                       id="roleInput"
                                                       value={role}
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
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>#UserID</th>
                        <th>User Name</th>
                        <th>User Password</th>
                        <th>User Role</th>
                        <th>Buttons</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        userList.map(v => {
                            return (
                                <tr>
                                    <td>{v.userID}</td>
                                    <td>{v.userName}</td>
                                    <td>{v.password}</td>
                                    <td>{v.role}</td>
                                    <td align="center">
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