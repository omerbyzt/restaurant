import React, {Component} from 'react';
import Table from "react-bootstrap/Table";
import axios from "axios";
import Header from "./Header";
import {Link} from "react-router-dom";
import DropdownButton from "react-bootstrap/DropdownButton";
import Dropdown from "react-bootstrap/Dropdown";
import UserContext from "../Context";
import Loading from "./Loading";

class UserList extends Component {
    static contextType = UserContext;
    state = {
        userList: [],
        userUpdate: false,
        username: "",
        password: "",
        enabled: "",
        email: "",
        roles: [],
        id: "",
        roleList: [],
        selectedRoleName: "Select Role",
        loadingIsVisible: false
    }

    async componentDidMount() {
        this.setState({loadingIsVisible: true});
        const {token} = this.context

        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            let uri = "http://localhost:8080/user/list";
            await fetch(uri, {
                method: 'get',
                headers: new Headers({
                    // 'Authorization': sessionStorage.getItem('token'),
                    'Authorization': token,
                }),
            })
                .then(response => response.json())
                .then(data => {
                    this.setState({
                        userList: data
                    })
                })

            await axios.get('http://localhost:8080/role/list',
                // {headers: {Authorization: sessionStorage.getItem('token')}})
                {headers: {Authorization: token}})
                .then(res => {
                    this.setState({roleList: res.data})
                });
        } else {
            this.props.history.push('/');
        }
        this.setState({loadingIsVisible: false});
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
            enabled: e.enabled,
            id: e.id,
            email: e.email,
        })
    }

    onClickDeleteBtn = async (e) => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        await axios.delete('http://localhost:8080/user/delete/' + e.id,
            // {headers:{Authorization: sessionStorage.getItem('token')}})
            {headers: {Authorization: token}})
            .then(res => {
                this.setState({userList: this.state.userList.filter(table => table.id !== e.id)})
            });
        this.setState({loadingIsVisible: false});
    }

    updateUser = async (e) => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        const {username, password, enabled, id, email, roles} = this.state;

        const putUser = {
            id: id,
            email: email,
            username: username,
            password: password,
            enabled: enabled,
            roles: roles,
        }

        await axios.put('http://localhost:8080/user/update', putUser,
            // {headers: {Authorization: sessionStorage.getItem('token')}});
            {headers: {Authorization: token}});
        this.setState({loadingIsVisible: false});
        e.preventDefault();
    }

    onClickItem = (e) => {
        this.setState({
            selectedRoleName: e.name
        })
        this.state.roles.push(e)
        console.log(this.state.roles)
    }

    render() {
        const {userList, userUpdate, username, password, enabled, id, email, roleList, selectedRoleName} = this.state;
        return (
            <div>
                <Header></Header>
                <Link to="/adduser">
                    <button className="btn btn-success productListAddProduct">+ Add User</button>
                </Link>
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
                                                <label htmlFor="idInput">User ID</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={id}
                                                       name="id"
                                                       id="idInput"
                                                       value={id}
                                                       disabled={id}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="nameInput">User Name</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={username}
                                                       name="username"
                                                       id="nameInput"
                                                       value={username}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="passwordInput">User Password</label>
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
                                                <label htmlFor="emailInput">User EMail</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={email}
                                                       name="email"
                                                       id="emailInput"
                                                       value={email}
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

                                            <div className="dropdown">
                                                <label htmlFor="price">Product Category : </label>
                                                <button className="btn btn-info dropdown-toggle dropdownCss"
                                                        type="button"
                                                        id="dropdownMenuButton" data-toggle="dropdown"
                                                        aria-haspopup="true"
                                                        aria-expanded="true">
                                                    {selectedRoleName}
                                                </button>
                                                <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                    {
                                                        roleList.map(v => {
                                                            return (

                                                                <div className="row col-md -12">
                                                                    <label>
                                                                        <input type="checkbox" value=""
                                                                               onClick={this.onClickItem.bind(this, v)}
                                                                        />{v.name}
                                                                    </label>
                                                                </div>

                                                            )
                                                        })
                                                    }
                                                </div>
                                            </div>

                                            <button className="btn btn-warning btn-block mt-3"
                                                    onClick={() => this.updateUser()}>Update
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
                        <th>User Email</th>
                        <th>User Enabled</th>
                        <th>User Roles</th>
                        <th>Buttons</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        userList.map(v => {
                            return (
                                <tr>
                                    <td>{v.username}</td>
                                    <td className="hidePassword">{v.password}</td>
                                    <td>{v.email}</td>
                                    <td>{v.enabled.toString()}</td>
                                    <td>
                                        <ul>
                                            {
                                                v.roles.map(value => {
                                                    return (
                                                        <li>{value.name}</li>
                                                    )
                                                })
                                            }
                                        </ul>
                                    </td>

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
                {
                    this.state.loadingIsVisible ?
                        <Loading/> : null
                }
            </div>
        );
    }
}

export default UserList;