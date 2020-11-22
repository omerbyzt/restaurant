import React, {Component} from 'react';
import axios from 'axios';
import Header from "./Header";

class AddUser extends Component {
    state = {
        userName: "",
        userPassword: "",
        userRole: ""
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addUser = (e) => {
        const {userName, userPassword, userRole} = this.state;

        const newUser = {
            userName: userName,
            password: userPassword,
            role: userRole
        }

        axios.post("http://localhost:8080/user/add", newUser,
            {headers:{Authorization:'Basic '+btoa('admin:pass3')}});
    }

    render() {
        const {userName, userPassword, userRole} = this.state;
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