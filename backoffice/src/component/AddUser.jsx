import React, {Component} from 'react';
import axios from 'axios';
import Header from "./Header";

class AddUser extends Component {
    state = {
        userName: "",
        userPassword: "",
        userRole: "",
        userEnabled:""
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addUser = (e) => {
        const {userName, userPassword, userRole,userEnabled} = this.state;

        const newUsers = {
            username : userName,
            password : "{noop}"+userPassword,
            enabled : userEnabled
        }

        axios.post("http://localhost:8080/users/add", newUsers,
            {headers:{Authorization:sessionStorage.getItem('token')}});

        const newAuth = {
            username : userName,
            authority : userRole
        }

        axios.post("http://localhost:8080/auth/add", newAuth,
            {headers:{Authorization:sessionStorage.getItem('token')}});

    }

    render() {
        const {userName, userPassword, userRole,userEnabled} = this.state;
        return (
            <div>
                <Header/>
                <div className="col-md-12 mb-4 mt2">

                    <div className="card">
                        <div className="card-header">
                            <h4>Add User Panel</h4>
                        </div>
                        <div className="card-body">
                            <form onSubmit={this.addUser}>
                                <div className="form-group">
                                    <label htmlFor="name">User Name</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter User Name"
                                           name="userName"
                                           id="nameInput"
                                           value={userName}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="password">User Password</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter User Password"
                                           name="userPassword"
                                           id="passwordInput"
                                           value={userPassword}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="role">User Role</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter User Role"
                                           name="userRole"
                                           id="roleInput"
                                           value={userRole}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="enabled">User Enabled</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter User Enabled"
                                           name="userEnabled"
                                           id="enabledInput"
                                           value={userEnabled}
                                           onChange={this.changeInput}
                                    />
                                </div>
                                <button className="btn btn-warning btn-block" type="submit">Add User</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddUser;