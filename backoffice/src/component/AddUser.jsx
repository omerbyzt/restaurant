import React, {Component} from 'react';
import axios from 'axios';
import Header from "./Header";

class AddUser extends Component {
    state = {
        //
        id: "",
        email: "",
        username: "",
        password: "",
        enabled: "Select Enable",
        roles: [],
        //
        roleEnabledTrue: "true",
        roleEnabledFalse: "false",
        dropDownEnabledName: "Select User Enabled",
        roleList: [],
        selectedRoleName: "Select Role"
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    componentDidMount() {
        axios.get('http://localhost:8080/role/list',
            {headers: {Authorization: sessionStorage.getItem('token')}})
            .then(res => {
                this.setState({roleList: res.data})
            });
    }

    addUser = () => {
        const {username, email, password, enabled, roles} = this.state;

        const newUser = {
            username: username,
            password: password,
            email: email,
            enabled: enabled,
            roles: roles
        }

        axios.post("http://localhost:8080/user/add", newUser,
            {headers: {Authorization: sessionStorage.getItem('token')}});

    }

    clickedAdmin = () => {
        this.setState({
            dropDownRoleName: this.state.roleAdmin
        })
    }

    clickedUser = () => {
        this.setState({
            dropDownRoleName: this.state.roleUser
        })
    }

    clickedEnabledTrue = () => {
        this.setState({
            enabled: this.state.roleEnabledTrue
        })
    }

    clickedEnabledFalse = () => {
        this.setState({
            enabled: this.state.roleEnabledFalse
        })
    }

    onClickItem = (e) => {
        this.setState({
            selectedRoleName: e.name
        })
        this.state.roles.push(e)
        console.log(this.state.roles)
    }

    render() {
        const {roleEnabledTrue, roleEnabledFalse, roleList, selectedRoleName} = this.state;
        const {username, email, password, enabled} = this.state;
        return (
            <div>
                <Header/>
                <div className="col-md-5 mr-auto mb-4 mt-4">

                    <div className="card">
                        <div className="card-header">
                            <h4>Add User Panel</h4>
                        </div>
                        <div className="card-body">
                            <form onSubmit={()=>this.addUser()}>
                                <div className="form-group">
                                    <label htmlFor="nameInput">User Name</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter User Name"
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
                                           placeholder="Enter User Password"
                                           name="password"
                                           id="passwordInput"
                                           value={password}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="emailInput">User Email</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter User Email"
                                           name="email"
                                           id="emailInput"
                                           value={email}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="dropdown">
                                    <label htmlFor="price">Product Category : </label>
                                    <button className="btn btn-info dropdown-toggle dropdownCss" type="button"
                                            id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
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

                                <div className="dropdown">
                                    <label htmlFor="enabledInput">User Enabled : </label>
                                    <button className="btn btn-info dropdown-toggle dropdownCss" type="button"
                                            id="enabledInput" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="true">
                                        {enabled}
                                    </button>
                                    <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">

                                        <a className="dropdown-item"
                                           onClick={this.clickedEnabledTrue}>{roleEnabledTrue}</a>
                                        <a className="dropdown-item"
                                           onClick={this.clickedEnabledFalse}>{roleEnabledFalse}</a>
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