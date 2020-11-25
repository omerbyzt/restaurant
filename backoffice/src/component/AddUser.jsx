import React, {Component} from 'react';
import axios from 'axios';
import Header from "./Header";

class AddUser extends Component {
    state = {
        userName: "",
        userPassword: "",
        userRole: "",
        userEnabled:"",
        roleAdmin:"ROLE_ADMIN",
        roleUser:"ROLE_USER",
        roleEnabledTrue:"true",
        roleEnabledFalse:"false",
        dropDownRoleName:"Select User Role",
        dropDownEnabledName:"Select User Enabled"

    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addUser = (e) => {
        const {userName, userPassword, userRole,userEnabled,dropDownRoleName,dropDownEnabledName} = this.state;

        const newUsers = {
            username : userName,
            password : "{noop}"+userPassword,
            enabled : dropDownEnabledName
        }

        axios.post("http://localhost:8080/users/add", newUsers,
            {headers:{Authorization:sessionStorage.getItem('token')}});

        const newAuth = {
            username : userName,
            authority : dropDownRoleName
        }

        axios.post("http://localhost:8080/auth/add", newAuth,
            {headers:{Authorization:sessionStorage.getItem('token')}});

    }

    clickedAdmin = () => {
        this.setState({
            dropDownRoleName:this.state.roleAdmin
        })
    }

    clickedUser = () => {
        this.setState({
            dropDownRoleName:this.state.roleUser
        })
    }

    clickedEnabledTrue = () => {
        this.setState({
            dropDownEnabledName:this.state.roleEnabledTrue
        })
    }

    clickedEnabledFalse = () => {
        this.setState({
            dropDownEnabledName:this.state.roleEnabledFalse
        })
    }

    render() {
        const {userName, userPassword, userRole,userEnabled,roleAdmin,roleUser,dropDownRoleName,dropDownEnabledName,roleEnabledTrue,roleEnabledFalse} = this.state;
        return (
            <div>
                <Header/>
                <div className="col-md-12 mb-4 mt-4">

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

                                <div className="dropdown">
                                    <label htmlFor="price">User Role : </label>
                                    <button className="btn btn-info dropdown-toggle dropdownCss2" type="button"
                                            id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="true">
                                        {dropDownRoleName}
                                    </button>
                                    <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">

                                            <a className="dropdown-item" onClick={this.clickedUser} >{roleUser}</a>
                                            <a className="dropdown-item" onClick={this.clickedAdmin}>{roleAdmin}</a>
                                    </div>
                                </div>

                                <div className="dropdown">
                                    <label htmlFor="price">User Enabled : </label>
                                    <button className="btn btn-info dropdown-toggle dropdownCss" type="button"
                                            id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="true">
                                        {dropDownEnabledName}
                                    </button>
                                    <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">

                                        <a className="dropdown-item" onClick={this.clickedEnabledTrue} >{roleEnabledTrue}</a>
                                        <a className="dropdown-item" onClick={this.clickedEnabledFalse}>{roleEnabledFalse}</a>
                                    </div>
                                </div>
                                <button className="btn btn-warning btn-block addUserCss" type="submit">Add User</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddUser;